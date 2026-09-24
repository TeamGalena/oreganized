dependencies {
    api(project(":core"))

    api(project(":plumbum"))

    dataApi(project(":core", configuration = "dataElements"))
}
