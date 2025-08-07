plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))
    implementation(project(":infrastructure"))
    implementation(project(":entrypoint"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.flywaydb:flyway-core:10.13.0")
    implementation("org.flywaydb:flyway-mysql:10.13.0")
    implementation("org.springframework.boot:spring-boot-starter-logging")
    implementation("org.slf4j:slf4j-api")
    runtimeOnly("com.mysql:mysql-connector-j")
    implementation("io.micrometer:micrometer-tracing-bridge-brave")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
