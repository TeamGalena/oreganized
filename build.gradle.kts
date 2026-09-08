import com.possible_triangle.gradle.neoforge.NeoforgeExtension
import net.neoforged.moddevgradle.dsl.NeoForgeExtension

plugins {
    id("com.possible-triangle.core")
    id("com.possible-triangle.neoforge-api") apply false
    id("com.possible-triangle.neoforge") apply false
}

subprojects {
    apply(plugin = "com.possible-triangle.core")

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
        maven {
            url = uri("https://mvn.devos.one/snapshots")
            content {
                includeGroup("com.tterrag.registrate")
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

    upload.maven.nexus()
}

val modules =
    subprojects
        .filter { it.projectDir.relativeTo(it.rootDir).startsWith("modules/") }

modules.forEach {
    it.apply(plugin = "com.possible-triangle.neoforge")

    it.configure<NeoforgeExtension> {
        accessTransformer(project(":core"))
        createDataSourceSet()
    }

    it.configure<NeoForgeExtension> {
        runs.removeAll { true }
    }

    it.upload.forEach {
        file.unset()
    }
}

enableSpotless()
enableSonarQube()
