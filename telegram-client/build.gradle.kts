import org.birthdayreminder.Versions

plugins {
    id("java")
}

group = "org.birthdayreminder"
version = "2.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":domain"))
    compileOnly("org.projectlombok:lombok:${Versions.Libs.PROJECT_LOMBOK}")

    testImplementation("org.mock-server:mockserver-junit-jupiter-no-dependencies:${Versions.Bom.MOCKSERVER}")

//    testImplementation("org.mock-server:mockserver-junit-jupiter-no-dependencies:5.15.0")
    testImplementation(platform("org.junit:junit-bom:${Versions.Bom.JUNIT}"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("com.fasterxml.jackson.core:jackson-databind:${Versions.Libs.JACKSON}")
//    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.3")

    implementation("com.fasterxml.jackson.core:jackson-annotations:${Versions.Libs.JACKSON}")
//    implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.3")


    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.6.1")
}

tasks {
    test {
        useJUnitPlatform()
    }
}
