plugins {
    id("java")
    id("com.diffplug.spotless")
//    id("com.autonomousapps.dependency-analysis")
}

// Example of a component metadata rule (not a practical use case, provided for learning purposes).
// Demonstrates how to apply a custom rule to a specific module dependency.
// dependencies.components {
//    withModule<Slf4jSimpleRule>("org.slf4j:slf4j-simple")
// }

//sourceSets.main {
//    java.setSrcDirs(listOf(layout.projectDirectory.dir("sources")))
//}
//sourceSets.test

sourceSets.create("integrationTest")

tasks.register<Test>("integrationTest") {
    testClassesDirs = sourceSets.getByName("integrationTest").output.classesDirs
    classpath = sourceSets.getByName("integrationTest").runtimeClasspath

    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform {
        excludeTags("slow")
    }

    maxParallelForks = 4

    maxHeapSize = "1G"
}

tasks.register<Test>("testSlow") {
    testClassesDirs = sourceSets.test.get().output.classesDirs
    classpath = sourceSets.test.get().runtimeClasspath

    useJUnitPlatform{
        includeTags("slow")
    }
}

tasks.check {
    dependsOn(tasks.named("testSlow"))
}

//tasks.named<JavaCompile>("compileJava") {
//}
//
//tasks.compileTestJava {
//}
//
//
//tasks.javadoc{
//}