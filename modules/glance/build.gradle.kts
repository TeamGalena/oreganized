import galena.gradle.moduleDependencies

plugins {
    id("com.possible-triangle.neoforge")
    id("module")
}

moduleDependencies(
    "core",
)

dependencies {
    dataImplementation(project(":plumbum"))
    compileOnly(project(":plumbum"))
}
