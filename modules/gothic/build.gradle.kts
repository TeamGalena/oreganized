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
    modImplementation(libs.create) { isTransitive = false }

    dataApi(project(":plumbum"))
}
