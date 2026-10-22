import galena.gradle.moduleDependencies

plugins {
    id("com.possible-triangle.neoforge")
    id("module")
}

moduleDependencies(
    "core",
)

dependencies {
    compileOnly(pack.modrinth.supplementaries)
    compileOnly(libs.create) { isTransitive = false }
}
