group = "com.jamiussiam"
version = "0.0.1-SNAPSHOT"

plugins {
    `java-library`
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.bouncycastle:bcprov-jdk15on:1.65")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}

tasks {
    test {
        useJUnitPlatform()
    }
}