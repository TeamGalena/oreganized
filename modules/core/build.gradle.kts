dependencies {
    modApi(libs.blueprint)

    modImplementation(pack.modrinth.farmers.delight)

    // TODO combine in dataApi?
    dataElements(libs.registrate)
    dataImplementation(libs.registrate)
    dataElements(libs.create) { isTransitive = false }
    dataImplementation(libs.create) { isTransitive = false }
    dataElements(libs.multikulti.datagen)
    dataImplementation(libs.multikulti.datagen)
    dataElements(pack.modrinth.farmers.delight)
    dataImplementation(pack.modrinth.farmers.delight)
}
