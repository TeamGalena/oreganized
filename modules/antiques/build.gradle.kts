dependencies {
    api(project(":core"))
    api(project(":argentum"))
    api(project(":armament"))
    api(project(":electrum"))

    dataApi(project(":core", configuration = "dataElements"))
    dataApi(project(":argentum", configuration = "dataElements"))
}
