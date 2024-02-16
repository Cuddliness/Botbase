plugins {
    id("java")
    id("org.springframework.boot") version "2.5.4"
    id("io.freefair.lombok") version "8.4"
}

group = "care.cuddliness.stacy"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("net.dv8tion:JDA:5.0.0-beta.20")
    implementation("org.springframework.boot:spring-boot-starter:3.2.0")
    implementation("org.springframework.boot:spring-boot-configuration-processor:3.2.0")
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.0")
    implementation("org.hibernate:hibernate-core:6.4.0.Final")
    implementation("com.mysql:mysql-connector-j:8.2.0")
    implementation("com.vdurmont:emoji-java:5.1.1")

}

tasks.test {
    useJUnitPlatform()
}