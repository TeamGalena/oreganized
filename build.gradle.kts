plugins {
    id("com.possible-triangle.forge")
    alias(libs.plugins.parchment)
}

mod {
    val mod_version_suffix: String by extra
    env["RELEASE_VERSION"]?.let {
        version = it.replace("-$mod_version_suffix", "")
    }
}

forge {
    mappingChannel = "parchment"
    mappingVersion = "2023.09.03-1.20.1"

    accessTransformer()

    dataGen {
        existing("blueprint")
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
    nexus {
        content {
            includeGroup("dev.galena")
        }
    }
}

dependencies {
    modImplementation(libs.blueprint)
    modImplementation(libs.oreganized)

    if (!env.isCI) {
        modRuntimeOnly(pack.modrinth.jade)
        modRuntimeOnly(libs.jei.forge)
    }
}

upload {
    maven {
        nexus()
    }

    modrinth {
        dependencies {
            required("oreganized")
        }
    }

    curseforge {
        dependencies {
            required("oreganized")
        }
    }

    forEach {
        versionName = "${mod.name.get()} ${mod.version.get()}"
        // TODO remove
        includeKotlinDependency = false
    }
}

enableSpotless()