plugins {
    java
    application
}

group = "com.migojj.icd8"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("mysql:mysql-connector-java:8.0.31")
    implementation("org.apache.poi:poi-ooxml:5.2.3")
    implementation("org.slf4j:slf4j-api:2.0.0")
    runtimeOnly("ch.qos.logback:logback-classic:1.4.5")
    testImplementation("junit:junit:4.13.2")
}

application {
    mainClass.set("com.migojj.icd8.MainApplication")
}
