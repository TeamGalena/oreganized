import galena.gradle.moduleDependencies

moduleDependencies(
    "core",
    "argentum",
)

dependencies {
    modImplementation(libs.create) { isTransitive = false }

    dataApi(project(":plumbum"))
}
