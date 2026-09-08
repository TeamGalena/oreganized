dependencies {
    api(project(":core"))

    dataImplementation(project(":core", configuration = "dataElements"))
}
