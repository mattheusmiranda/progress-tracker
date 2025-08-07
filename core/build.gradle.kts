plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")

    testImplementation(kotlin("test"))
}
