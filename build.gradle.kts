import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        "classpath"("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
    }
}

application {
    mainClass.set("MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("dev.inmo:tgbotapi:5.0.1")
}

tasks.test {
    useJUnitPlatform()
}

val mainClassName = "MainKt"
tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    manifest {
        attributes(mapOf("Main-Class" to mainClassName))
    }

    from(configurations.compileClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}