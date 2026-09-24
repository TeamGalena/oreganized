dependencies {
    api(project(":core"))
    modImplementation(pack.modrinth.shield.expansion)
    modImplementation(pack.modrinth.farmers.delight)

    dataApi(project(":core", configuration = "dataElements"))
}
