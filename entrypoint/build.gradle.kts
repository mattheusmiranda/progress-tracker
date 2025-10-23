plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("kapt")
    id("com.github.davidmc24.gradle.plugin.avro") version "1.5.0"
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://packages.confluent.io/maven/")
    }
}

dependencies {
    implementation(project(":core"))

    implementation("org.springframework.boot:spring-boot-starter-web:3.5.0")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.5.0")
    implementation("org.springframework.kafka:spring-kafka:3.1.2")

    implementation("io.confluent:kafka-avro-serializer:7.6.1")
    implementation("org.apache.avro:avro:1.11.1")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.1")
    implementation("org.springframework.boot:spring-boot-starter-logging")

    implementation("org.mapstruct:mapstruct:1.6.0")
    kapt("org.mapstruct:mapstruct-processor:1.6.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.5.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.mockito:mockito-core:5.12.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.3.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

sourceSets {
    main {
        java {
            srcDirs("build/generated-main-avro-java/main")
        }
    }
}

avro {
    setCreateSetters(true)
    setFieldVisibility("PRIVATE")
}
