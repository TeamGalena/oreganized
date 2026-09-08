dependencies {
    api(project(":core"))

    modCompileOnly(libs.create) { isTransitive = false }

    dataImplementation(project(":core", configuration = "dataElements"))
}
