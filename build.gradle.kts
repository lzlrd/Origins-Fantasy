plugins {
    id("java")
}

group = "com.starshootercity.originsfantasy"
version = "1.1.14"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://api.modrinth.com/maven") {
        content {
            includeGroup("maven.modrinth")
        }
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.3.build.248-stable")
    compileOnly("maven.modrinth:origins-reborn:2.10.11")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}

tasks.jar {
    // The same sources always give the same jar, byte for byte.
    isPreserveFileTimestamps = false
    isReproducibleFileOrder = true
}
