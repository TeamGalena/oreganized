plugins {
    id("com.possible-triangle.core")
}

dependencies {
    api(project(":core"))

    modCompileOnly(pack.modrinth.supplementaries)
    modCompileOnly(libs.create) { isTransitive = false }

    dataApi(project(":core", configuration = "dataElements"))
}
