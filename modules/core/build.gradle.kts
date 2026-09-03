dependencies {
    modApi(libs.blueprint)

    modImplementation(pack.modrinth.farmers.delight)
}

neoforge {
    injectInterfaces()
}
