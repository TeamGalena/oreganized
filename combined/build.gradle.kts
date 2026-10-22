import galena.gradle.modModules

plugins {
    id("com.possible-triangle.neoforge")
    id("module")
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

    modrinth {
        dependencies {
            required("blueprint")
        }
    }

    curseforge {
        dependencies {
            required("blueprint", 382216)
        }
    }

    forEach {
        versionName = "${mod.name.get()} ${mod.version.get()}"
    }
}

modModules.forEach {
    modrinth.additionalFiles.add(it.modrinth.file)
}
