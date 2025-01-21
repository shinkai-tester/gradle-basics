plugins {
    id("java-platform") // Similar to BOM = Bill Of Material
}

group = "com.example"

javaPlatform.allowDependencies()
dependencies {
    api(platform("org.junit:junit-bom:5.11.4"))
    api(platform("com.fasterxml.jackson:jackson-bom:2.18.2"))
}

dependencies.constraints {
    api("org.apache.commons:commons-lang3:3.17.0")
    api("org.slf4j:slf4j-api:2.0.16")
    api("org.slf4j:slf4j-simple:2.0.16")

    // Adds a dependency with version constraints.
    // The `version` block provides fine-grained control over the versions used:
    // - `strictly`: Forces the use of a specific version, ensuring no other version can be selected.
    // - `reject`: Excludes specific versions from being used, even if they are compatible with other constraints.
    // Not standard project setup!
//    api("com.google.guava:guava:33.4.0-jre") {
//        version {
//            strictly("...") // Enforces this exact version.
//            reject("...")   // Excludes these versions explicitly.
//        }
//    }
}