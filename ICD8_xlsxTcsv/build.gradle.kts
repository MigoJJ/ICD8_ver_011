// File: build.gradle.kts

plugins {
    // core Gradle plugins
    java
    application

    // shadow-jar for fat JARs
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.migojj.icd8"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Excel reading/writing
    implementation("org.apache.poi:poi-ooxml:5.2.3")
    // Commons-IO for UnsynchronizedByteArrayOutputStream
    implementation("commons-io:commons-io:2.11.0")
    // MySQL connector
    implementation("mysql:mysql-connector-java:8.0.31")
    // Logging API + Logback impl
    implementation("org.slf4j:slf4j-api:2.0.0")
    implementation("org.apache.logging.log4j:log4j-core:2.19.0")
    runtimeOnly("ch.qos.logback:logback-classic:1.4.5")
    // Testing
    testImplementation("junit:junit:4.13.2")
}

application {
    // your main class
    mainClass.set("com.migojj.icd8.MainApplication")
}

// disable plain JAR; only build the shadow (fat) JAR
tasks.named<Jar>("jar") {
    enabled = false
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveBaseName.set("icd8-converter")
    archiveVersion.set(version.toString())
    archiveClassifier.set("")
}
