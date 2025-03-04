import org.birthdayreminder.Versions

plugins {
    id("java")
}

group = "org.birthdayreminder"
version = "2.0.1"

dependencies {
    compileOnly("org.projectlombok:lombok:${Versions.Libs.PROJECT_LOMBOK}")

    annotationProcessor("org.projectlombok:lombok:${Versions.Libs.PROJECT_LOMBOK}")

    testImplementation(platform("org.junit:junit-bom:${Versions.Bom.JUNIT}"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml
    implementation("org.apache.poi:poi-ooxml:${Versions.Libs.APACHE_POI}")


    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations
    implementation("com.fasterxml.jackson.core:jackson-annotations:${Versions.Libs.JACKSON}")

}

tasks.test {
    useJUnitPlatform()
}