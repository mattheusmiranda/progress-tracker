pluginManagement {
    repositories {
        gradlePluginPortal() // necessário para plugins do tipo com.github.*
        mavenCentral()
        maven { url = uri("https://packages.confluent.io/maven/") }
    }
}

// O nome do projeto e a inclusão dos módulos vêm em seguida.
rootProject.name = "progress-tracker"
include("core", "infrastructure", "bootstrap", "entrypoint")
