import galena.gradle.moduleDependencies

moduleDependencies(
    "core",
)

dependencies {
    dataApi(project(":plumbum"))
}
