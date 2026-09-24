dependencies {
    api(project(":core"))

    dataApi(project(":plumbum"))
    dataApi(project(":core", configuration = "dataElements"))
}
