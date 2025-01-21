plugins {
    id("my-java-library")
}

// Implementation scope is also a configuration included in both the runtimeClasspath and the compileClasspath
dependencies {
    implementation(platform("com.example:platform"))
    testImplementation(platform("com.example:platform"))

    implementation(project(":data-model"))
    implementation("org.apache.commons:commons-lang3")
    implementation("org.slf4j:slf4j-api")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

//    integrationTestImplementation("org.junit.jupiter:junit-jupiter-api")
//    integrationTestRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

//    implementation(libs.commons.lang)
//    implementation(libs.slf4j.api)

    // compileOnly("group:name") <-- only at compile time. Usually there are very little practical examples where you
    // really need this. There are certain edge cases, like when environments already provide something in a kind of
    // container.

    // runtimeOnly("") <-- You never use any classes to compile code, but you just plug it into the classpath at runtime,
    // and then it's somehow found on the classpath, probably by reflection or some other mechanism.
    // Example of use: when there is a separation between an API and an implementation of something. E.g., a logging
    // framework like SLF4J.

    // api("") <-- This is a scope you only have on Java libraries and not on applications for example.
    // It means that a dependency you add gets visible transitively during compilation.
    // Concretely it would mean: if we would change the dependency to common-lang here to api in the business-logic project,
    // commons-lang will also be seen in the app project, which depends on the business-logic project, because
    // it gets visible transitively.
    // You should be very careful with api, because if you use implementation you have components neatly isolated.
    // And there is less risk that something is used by accident because it's just visible transitive during compilation.
    // So the general rule of thumb would be, if something is on the public API, it should be also an api dependency.
}

// Uncomment if you need to explicitly use or customize configurations
// @Suppress("UnstableApiUsage")
// configurations {
//     compileClasspath // <- Compile time "view" (aka Variant)
//     runtimeClasspath // <- Runtime "view" (aka Variant)
// }

// There are other scopes, or other configurations, like compileOnly, that is only included in the compileClasspath, and
// runtimeOnly, that's only included in the runtimeClasspath
