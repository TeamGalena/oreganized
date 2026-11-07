import galena.gradle.moduleDependencies

plugins {
    id("com.possible-triangle.neoforge")
    id("module")
}

moduleDependencies(
    "core",
    "argentum",
)

dependencies {
    dataImplementation(project(":armament"))
    dataImplementation(project(":electrum"))
}
