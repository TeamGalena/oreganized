import galena.gradle.moduleDependencies

plugins {
    id("com.possible-triangle.neoforge")
    id("module")
}

moduleDependencies(
    "core",
)

dependencies {
    modImplementation(libs.create) { isTransitive = false }

    compileOnly(project(":argentum"))
    dataImplementation(project(":argentum"))
    dataImplementation(project(":plumbum"))
}
