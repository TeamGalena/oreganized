import galena.gradle.moduleDependencies

plugins {
    id("com.possible-triangle.neoforge")
    id("module")
}

moduleDependencies(
    "core",
)

dependencies {
    modCompileOnly(libs.create) { isTransitive = false }

    dataApi(project(":plumbum"))
}
