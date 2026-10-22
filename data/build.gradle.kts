import com.possible_triangle.gradle.modifyReleaseMetadata

plugins {
    id("com.possible-triangle.neoforge")
    id("conventions")
}

neoForge.runs.removeAll { true }
neoforge.accessTransformer(project(":core"))

dependencies {
    compileOnly(project(":core"))

    api(libs.registrate)
    api(libs.create) { isTransitive = false }
    api(libs.multikulti.datagen)
    api(libs.multikulti.registrate)
    api(pack.modrinth.farmers.delight)
}

modifyReleaseMetadata {
    exclude = true
}
