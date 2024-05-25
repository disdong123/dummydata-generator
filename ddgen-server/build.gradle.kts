dependencies {
    implementation(project(":ddgen-persistence"))
    implementation(project(":ddgen-common"))
    implementation(libs.spring.boot.starter.web)
}

tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}
