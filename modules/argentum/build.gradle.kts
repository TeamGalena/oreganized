import galena.gradle.moduleDependencies

moduleDependencies(
    "core",
)

dependencies {
    modCompileOnly(libs.create) { isTransitive = false }

    dataApi(project(":plumbum"))
}
