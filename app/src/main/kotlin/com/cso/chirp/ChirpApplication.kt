package com.cso.chirp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChirpApplication

fun main(args: Array<String>) {
    runApplication<ChirpApplication>(*args)
}

//@Component
//class Demo (
//    private val userRepository: UserRepository
//) {
//
//    @PostConstruct
//    fun initial() {
//        userRepository.save(
//            UserEntity(
//                email = "email@email.com",
//                username = "First Test",
//                hashedPassword = "12345"
//            )
//        )
//    }
//}