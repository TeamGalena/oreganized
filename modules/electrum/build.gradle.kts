dependencies {
    api(project(":core"))

    dataImplementation(project(":core", configuration = "dataElements"))
    dataImplementation(pack.modrinth.shield.expansion)
}
