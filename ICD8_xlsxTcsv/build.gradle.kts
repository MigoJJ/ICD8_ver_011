plugins {
    id 'java'
    id 'application'
    id 'com.github.johnrengelman.shadow' version '8.1.1'
}

group = 'com.migojj.icd8'
version = '1.0.0'

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
    implementation 'org.apache.poi:poi-ooxml:5.2.3'
    // Commons-IO for UnsynchronizedByteArrayOutputStream, etc.
    implementation 'commons-io:commons-io:2.11.0'
    // MySQL JDBC driver
    implementation 'mysql:mysql-connector-java:8.0.31'
    // Logging
    implementation 'org.slf4j:slf4j-api:2.0.0'
    runtimeOnly   'ch.qos.logback:logback-classic:1.4.5'
    // Testing
    testImplementation 'junit:junit:4.13.2'
}

application {
    mainClass.set('com.migojj.icd8.MainApplication')
}

// Disable the plain JAR task so only the Shadow (fat) JAR is built
tasks.named('jar') {
    enabled = false
}

// Produce a single executable JAR containing all deps
tasks.named('shadowJar') {
    archiveBaseName.set('icd8-converter')
    archiveVersion.set(version)
    archiveClassifier.set('')
}
