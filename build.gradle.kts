plugins {
	kotlin("jvm") version "1.9.25" apply false
	kotlin("plugin.spring") version "1.9.25" apply false
	kotlin("plugin.jpa") version "1.9.25" apply false
	id("org.springframework.boot") version "3.5.0" apply false
	id("io.spring.dependency-management") version "1.1.7" apply false
}

allprojects {
	group = "com.school"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
		maven("https://packages.confluent.io/maven/")
	}
}

subprojects {
	plugins.withType<JavaPlugin> {
		extensions.configure<JavaPluginExtension> {
			toolchain {
				languageVersion.set(JavaLanguageVersion.of(21))
			}
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	repositories {
		mavenCentral()
	}
}
