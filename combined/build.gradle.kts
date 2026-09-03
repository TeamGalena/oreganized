plugins {
    id("com.possible-triangle.neoforge")
}

val modules =
    rootProject
        .file("modules")
        .listFiles { it.isDirectory }
        .map { project(":${it.name}") }

neoforge {
    dataGen {
        existing("blueprint")
        existing("shieldexp")
        existing("dye_depot")

        owner = project

        splitSourceSet()
    }

    accessTransformer(project(":core"))

    modules.forEach(::dependOn)
}

base {
    archivesName = "${mod.name.get()} ${mod.minecraftVersion.get()}-${mod.version.get()}"
}

dependencies {
    modInclude(libs.galena.hats)

    // TODO modular try again
    // interfaceInjectionData(project(":core"))
    // interfaceInjectionData(project(":plumbum"))
    // interfaceInjectionData(project(":electrum"))

    modApi(libs.blueprint)

    modImplementation(libs.multikulti.datagen)

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
    modIncludeCompileOnly(libs.ponder)
    modIncludeCompileOnly(libs.flywheel)

    // For dev testing
    // runtimeOnly(pack.modrinth.scannable)
    // runtimeOnly(pack.modrinth.architectury.api)
    modRuntimeOnly(pack.modrinth.moonlight)
    modRuntimeOnly(libs.dye.depot)
    modRuntimeOnly(pack.modrinth.jade)
    modRuntimeOnly(pack.modrinth.biolith)
    modRuntimeOnly(pack.modrinth.no.mans.land)
    modRuntimeOnly(pack.modrinth.freecam)

    modCompileOnly(libs.jei.common.api)
    modCompileOnly(libs.jei.neoforge.api)
    modRuntimeOnly(libs.jei.neoforge)
}

upload {
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
