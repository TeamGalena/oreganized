plugins {
    id("com.possible-triangle.forge")
    alias(libs.plugins.parchment)
}

mod {
    mods.include(libs.galena.hats)
    mods.include(libs.multikulti.datagen.fix)
}

forge {
    mappingChannel = "parchment"
    mappingVersion = "2023.09.03-1.20.1"

    enableMixins()

    dataGen {
        existing("blueprint")
        existing("shieldexp")
        existing("dye_depot")
    }
}

base {
    archivesName = "${mod.name.get()} ${mod.minecraftVersion.get()}-${mod.version.get()}"
}

minecraft {
    accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))
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
    modImplementation(libs.blueprint)

    modImplementation(libs.multikulti.core)
    modImplementation(libs.multikulti.datagen)

    // Compatibilities
    modImplementation(pack.modrinth.farmers.delight)
    modImplementation(pack.modrinth.nethers.delight)
    modImplementation(pack.modrinth.shield.expansion)
    modImplementation(
        variantOf(libs.create) {
            classifier("all")
        },
    )
    modCompileOnly(libs.ponder)
    modImplementation(pack.modrinth.supplementaries)

    // For dev testing
    modRuntimeOnly(pack.modrinth.scannable)
    modRuntimeOnly(pack.modrinth.architectury.api)
    modRuntimeOnly(pack.modrinth.moonlight)
    modRuntimeOnly(libs.dye.depot)
    modRuntimeOnly(pack.modrinth.jade)

    modCompileOnly(libs.jei.common.api)
    modCompileOnly(libs.jei.forge.api)
    modRuntimeOnly(libs.jei.forge)
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
        includeKotlinDependency = false
    }
}

enableSpotless()
enableSonarQube()
