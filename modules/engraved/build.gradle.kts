dependencies {
    api(project(":core"))

    api(project(":plumbum"))

    dataImplementation(project(":core", configuration = "dataElements"))
}
