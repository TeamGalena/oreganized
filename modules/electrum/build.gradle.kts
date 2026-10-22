import galena.gradle.moduleDependencies

plugins {
    id("com.possible-triangle.neoforge")
    id("module")
}

moduleDependencies(
    "core",
)

dependencies {
    modImplementation(pack.modrinth.shield.expansion)
    modImplementation(pack.modrinth.farmers.delight)

    dataApi(project(":argentum"))
}
