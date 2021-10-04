pluginManagement {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("com.android")) {
                useModule("com.android.tools.build:gradle:7.0.2")
            }
            if (requested.id.id.startsWith("org.jetbrains.kotlin")) {
                useVersion("1.5.21")
            }
            if (requested.id.id.startsWith("org.jetbrains.dokka")) {
                useModule("dokka-android-gradle-plugin:0.9.17")
            }
            if (requested.id.id.startsWith("com.google.dagger")) {
                useModule("hilt-android-gradle-plugin:2.38.1")
            }
        }
    }
}

rootProject.name = "kts-android-09-2021"
include(":app")
