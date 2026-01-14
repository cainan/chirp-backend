package com.cso.chirp.service.auth

import com.cso.chirp.domain.exception.InvalidTokenException
import com.cso.chirp.domain.exception.UserNotFoundException
import com.cso.chirp.domain.model.EmailVerificationToken
import com.cso.chirp.infra.database.entities.EmailVerificationTokenEntity
import com.cso.chirp.infra.database.mappers.toEmailVerificationToken
import com.cso.chirp.infra.database.repositories.EmailVerificationTokenRepository
import com.cso.chirp.infra.database.repositories.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.time.temporal.ChronoUnit

@Service
class EmailVerificationService(
    private val emailVerificationTokenRepository: EmailVerificationTokenRepository,
    private val userRepository: UserRepository,
    @param:Value("\${chirp.email.verification.expiry-hours}") private val expiryHours: Long
) {

    @Transactional
    fun createVerificationToken(email: String): EmailVerificationToken {
        val userEntity = userRepository.findByEmail(email)
            ?: throw UserNotFoundException()

        val existingTokens = emailVerificationTokenRepository.findByUserAndUsedAtIsNull(userEntity)

        val now = Instant.now()
        val usedTokens = existingTokens.map {
            it.apply { this.usedAt = now }
        }

        emailVerificationTokenRepository.saveAll(usedTokens)

        val token = EmailVerificationTokenEntity(
            expiresAt = now.plus(expiryHours, ChronoUnit.HOURS),
            user = userEntity
        )

        return emailVerificationTokenRepository.save(token).toEmailVerificationToken()
    }

    @Transactional
    fun verifyEmail(token: String) {
        val verificationTokenEntity = emailVerificationTokenRepository.findByToken(token)
            ?: throw InvalidTokenException(
                "Email verification token is invalid."
            )

        if (verificationTokenEntity.isUsed) {
            throw InvalidTokenException("Email verification token is already used.")
        }

        if (verificationTokenEntity.isExpired) {
            throw InvalidTokenException("Email verification token has already expired.")
        }

        emailVerificationTokenRepository.save(
            verificationTokenEntity.apply { this.usedAt = Instant.now() }
        )

        userRepository.save(
            verificationTokenEntity.user.apply { this.hasVerifiedEmail = true }
        )
    }

    @Scheduled(cron = "0 0 3 * * *")
    fun cleanupExpiredTokens() {
        emailVerificationTokenRepository.deleteByExpiresAtLessThan(
            now = Instant.now()
        )
    }
}