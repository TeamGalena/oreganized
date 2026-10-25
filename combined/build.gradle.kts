import galena.gradle.modModules
import galena.gradle.requiresBlueprint
import galena.gradle.setBlossomModuleDependenencies

plugins {
    id("com.possible-triangle.neoforge")
    id("conventions")
}

neoforge {
    accessTransformer(project(":core"))
}

base {
    archivesName = "${mod.name.get()} ${mod.minecraftVersion.get()}-${mod.version.get()}"
}

modModules.forEach {
    evaluationDependsOn(it.path)
}

dependencies {
    modModules.forEach {
        api(project(it.path))
        jarJar(project(it.path))
    }

    modApi(libs.blueprint)

    // Compatibilities
    modImplementation(pack.modrinth.farmers.delight)
    // modApi(pack.modrinth.nethers.delight)
    modImplementation(pack.modrinth.shield.expansion)
    modImplementation(
        variantOf(libs.create) {
            classifier("all")
        },
    ) {
        isTransitive = false
    }
    modImplementation(pack.modrinth.supplementaries)

    // For dev testing
    // runtimeOnly(pack.modrinth.scannable)
    // runtimeOnly(pack.modrinth.architectury.api)
    modRuntimeOnly(pack.modrinth.moonlight)
    modRuntimeOnly(pack.modrinth.biolith)
    modRuntimeOnly(pack.modrinth.no.mans.land)
}

upload {
    maven {
        name = "${mod.id.get()}-${mod.minecraftVersion.get()}"
    }

    requiresBlueprint()
}

val moduleNames = modModules.map { it.name }.toTypedArray()
setBlossomModuleDependenencies(*moduleNames)
