plugins {
	java
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("kapt") version "2.4.10" // важно для генерации
}

group = "io.hexlet"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	runtimeOnly("com.h2database:h2")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	compileOnly("org.projectlombok:lombok:1.18.34")
	annotationProcessor("org.projectlombok:lombok:1.18.34")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("net.datafaker:datafaker:2.7.0")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.mapstruct:mapstruct:1.6.3")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
	testAnnotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
