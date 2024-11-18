plugins {
    kotlin("jvm") version "1.9.25"
    id("java-library")
    `maven-publish`
}

group = "com.techullurgy.whatsappclone"
version = "0.0.1"

repositories {
    mavenCentral()
}

publishing {
    repositories {
        maven {
            name = "GithubPackages"
            url = uri("https://maven.pkg.github.com/Mikkareem/whatsapp-clone-microservices")
            credentials {
                username = System.getenv("GITHUB_PACKAGE_USER")
                password = System.getenv("GITHUB_PACKAGE_CREDENTIALS")
            }
        }
    }
    publications {
        register<MavenPublication>("default") {
            groupId = "com.techullurgy.whatsappclone"
            artifactId = "shared"
            version = "0.0.1"
            from(components["kotlin"])
        }
    }
}