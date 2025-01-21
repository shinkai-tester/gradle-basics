plugins {
    id("my-application")
}

application {
    mainClass.set("com.example.MyApplication")
}

dependencies {
    implementation(platform("com.example:platform"))

    implementation(project(":data-model"))
    implementation(project(":business-logic"))

    runtimeOnly("org.slf4j:slf4j-simple")
    //    runtimeOnly(libs.slf4j.simple)
}
