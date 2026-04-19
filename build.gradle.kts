plugins {
    id("com.possible-triangle.neoforge")
}

neoforge {
    dataGen {
        existing("blueprint")
        existing("shieldexp")
        existing("dye_depot")

        splitSourceSet()
    }
}

base {
    archivesName = "${mod.name.get()} ${mod.minecraftVersion.get()}-${mod.version.get()}"
}

repositories {
    maven {
        url = uri("https://maven.teamabnormals.com/")
        content {
            includeGroup("com.teamabnormals")
        }
    }
    maven {
        url = uri("https://maven.blamejared.com/")
        content {
            includeGroup("mezz.jei")
        }
    }
    maven {
        url = uri("https://maven.tterrag.com/")
        content {
            includeGroup("com.tterrag.registrate")
        }
    }
    maven {
        url = uri("https://maven.createmod.net")
        content {
            includeGroup("com.simibubi.create")
            includeGroup("net.createmod.ponder")
            includeGroup("dev.engine-room.flywheel")
        }
    }

    nexus {
        content {
            includeGroup("dev.galena")
            includeGroup("com.possible-triangle")
            includeGroup("com.ninni.dye_depot")
        }
    }
}

dependencies {
    modInclude(libs.galena.hats)

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
    modCompileOnly(libs.ponder)

    // For dev testing
    // runtimeOnly(pack.modrinth.scannable)
    // runtimeOnly(pack.modrinth.architectury.api)
    modRuntimeOnly(pack.modrinth.moonlight)
    modRuntimeOnly(libs.dye.depot)
    modRuntimeOnly(pack.modrinth.jade)
    modRuntimeOnly(pack.modrinth.biolith)
    modRuntimeOnly(pack.modrinth.no.mans.land)

    modCompileOnly(libs.jei.common.api)
    modCompileOnly(libs.jei.neoforge.api)
    modRuntimeOnly(libs.jei.neoforge)
}

upload {
    maven {
        nexus()
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

enableSpotless()
enableSonarQube()
