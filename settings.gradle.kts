rootProject.name = "birthday-reminder-app"
include("domain")
include("telegram-client")
include("app")

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}
include("telegram-bot-client")
include("android-client")
