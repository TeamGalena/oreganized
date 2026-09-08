dependencies {
    api(project(":core"))

    dataImplementation(project(":plumbum"))
    dataImplementation(project(":core", configuration = "dataElements"))
}
