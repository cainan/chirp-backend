plugins {
    id("java-library")
    id("chirp.kotlin-common")
}

group = "com.cso"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}