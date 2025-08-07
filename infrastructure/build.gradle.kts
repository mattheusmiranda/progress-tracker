// infrastructure/build.gradle.kts
plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("kapt")
    // ID do plugin do Avro
    id("com.github.davidmc24.gradle.plugin.avro") version "1.5.0"
}

// Repositórios necessários para os plugins e suas dependências
repositories {
    mavenCentral()
    maven {
        url = uri("https://packages.confluent.io/maven/")
    }
}

dependencies {
    // Dependência do módulo core
    implementation(project(":core"))

    // Dependências do Spring Boot e outras tecnologias
    implementation("org.springframework.boot:spring-boot-starter-web:3.5.0")
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.5.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.5.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.1")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")
    implementation("org.slf4j:slf4j-api")

    // Dependências do Kafka e Avro
    implementation("org.springframework.kafka:spring-kafka:3.1.2")
    implementation("org.apache.avro:avro:1.11.1")
    implementation("io.confluent:kafka-avro-serializer:7.5.1")

    // Dependências de teste
    testImplementation("org.mockito:mockito-core:5.12.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.3.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.2")
}

// Configuração do plugin Avro
avro {
    fieldVisibility.set("PRIVATE")
    stringType.set("string")
}

sourceSets {
    main {
        java {
            srcDir("build/generated-src/avro/main")
        }
    }
}
