// Entry point of the project configuration.
// This file defines settings for the entire Gradle build, such as repository locations, included builds, and subprojects.

pluginManagement {
    // Define repositories where Gradle should look for plugins.
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }

    // Custom Maven repository with authentication credentials.
//    repositories.maven("https://my.location/repo") {
//        credentials.username = "user"
//        credentials.password = "<PASSWORD>"
//    }

    // Include another Gradle build that defines custom plugins for this project.
    includeBuild("gradle/plugins")
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

@Suppress("UnstableApiUsage") // Suppress warnings for using experimental APIs in Gradle
dependencyResolutionManagement {
    // Define repositories for resolving dependencies.
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()

        includeBuild("gradle/platform")
    }

    // Uncomment and configure if using a private repository for project dependencies.
//    repositories.maven("https://my.location/repo") {
//        credentials.username = "user"
//        credentials.password = "<PASSWORD>"
//    }

    // Include another Gradle build to resolve dependencies from its output.
    // Useful for modularized or multi-repo projects.
//    includeBuild("../my-other-project")
}

// Set the root project name. This is the base name of the build and is used in the generated output.
rootProject.name = "gradle-basics"

// Include subprojects/modules into the Gradle build. These are treated as part of the same build.
include("app")
include("business-logic")
include("data-model")
