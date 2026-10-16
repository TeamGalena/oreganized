dependencies {
    modInclude(libs.galena.hats)

    modInclude(libs.ponder)
    modInclude(libs.flywheel)

    modApi(libs.blueprint)

    modApi(libs.jei.common.api)
    modApi(libs.jei.neoforge.api)

    modCompileOnlyApi(pack.modrinth.farmers.delight)
    modCompileOnlyApi(pack.modrinth.no.mans.land)
}
