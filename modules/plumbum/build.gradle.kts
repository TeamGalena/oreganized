import galena.gradle.moduleDependencies

moduleDependencies(
    "core",
)

dependencies {
    compileOnly(pack.modrinth.supplementaries)
    compileOnly(libs.create) { isTransitive = false }
}
