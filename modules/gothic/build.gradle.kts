dependencies {
    api(project(":core"))

    api(project(":argentum"))
    modImplementation(libs.create) { isTransitive = false }

    dataApi(project(":argentum", configuration = "dataElements"))
    dataApi(project(":core", configuration = "dataElements"))
}
