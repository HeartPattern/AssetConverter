plugins {
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.serialization") version "1.3.72"
    application
}

group = "io.heartpattern"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.heartpattern.io/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx","kotlinx-serialization-runtime","0.20.0")
    implementation("io.heartpattern","MCVersions","1.0.0-SNAPSHOT")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

application{
    mainClassName = "io.heartpattern.assetconverter.AppKt"
}