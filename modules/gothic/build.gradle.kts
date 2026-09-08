dependencies {
    api(project(":core"))

    api(project(":argentum"))
    modImplementation(libs.create) { isTransitive = false }

    dataImplementation(project(":core", configuration = "dataElements"))
}
