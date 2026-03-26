# Chirp Backend

## 📌 About the project
This is the backend for the **Chirp** app — a real-time chat platform. It is built with Spring Boot using a multi-module Gradle architecture, providing services for user management, messaging, and notifications.

## ✨ Features
🔹 User registration, authentication, and profile management

🔹 Real-time chat via WebSocket

🔹 Group and direct messaging

🔹 Push notifications via Firebase Cloud Messaging (FCM)

🔹 Email notifications (account verification, password reset)

🔹 JWT-based authentication and authorization

🔹 Rate limiting with Redis

🔹 Message queue integration with RabbitMQ

🔹 Profile picture upload via Supabase Storage

## 🚀 Techs
- **Spring Boot 4**
- **Kotlin**
- **Gradle** (multi-module build)
- **PostgreSQL**
- **Redis**
- **RabbitMQ** (CloudAMQP)
- **Firebase Admin SDK** (Push Notifications)
- **Supabase** (Storage)
- **Thymeleaf** (Email templates)
- **Mailgun / Yopmail** (Email delivery)
- **JWT** (jjwt)
- **WebSocket**
- **OkHttp**

## 🏗 Project Modules
| Module         | Description                                      |
|----------------|--------------------------------------------------|
| `app`          | Application entry point and global configuration |
| `user`         | User registration, authentication, and profiles  |
| `chat`         | Chat rooms, messages, and WebSocket handling     |
| `notification` | Email and push notification services             |
| `common`       | Shared utilities, events, and infrastructure     |

## 🛠 Installation and Configuration

### Prerequisites
- JDK 21+
- PostgreSQL running instance
- Redis running instance
- RabbitMQ (or CloudAMQP account)
- Supabase project (for storage)
- Firebase project (for push notifications)

### Running the project

Clone this repository:
```bash
git clone https://github.com/cainan/chirp-backend.git
cd chirp-backend
```

Build and run the application:
```bash
./gradlew :app:bootJar
```

> Make sure to configure your environment variables or `application-dev.yml` / `application-prod.yml` with the correct credentials before running.

---

Questions or suggestions? Feel free to open an issue or contribute with pull requests! 😊
