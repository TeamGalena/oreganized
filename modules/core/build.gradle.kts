dependencies {
    modApi(libs.blueprint)
    modApi(libs.ponder)
    modApi(libs.flywheel)

    modImplementation(pack.modrinth.farmers.delight)

    dataApi(libs.registrate)
    dataApi(libs.create) { isTransitive = false }
    dataApi(libs.multikulti.datagen)
    dataApi(libs.multikulti.registrate)
    dataApi(pack.modrinth.farmers.delight)
}
