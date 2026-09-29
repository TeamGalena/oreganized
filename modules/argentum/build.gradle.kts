dependencies {
    api(project(":core"))

    modCompileOnly(libs.create) { isTransitive = false }

    dataApi(project(":core", configuration = "dataElements"))
    dataApi(project(":plumbum"))
}
