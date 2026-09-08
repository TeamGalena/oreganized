dependencies {
    api(project(":core"))
    modImplementation(pack.modrinth.shield.expansion)
    modImplementation(pack.modrinth.farmers.delight)

    dataImplementation(project(":core", configuration = "dataElements"))
}
