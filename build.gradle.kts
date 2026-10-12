import com.possible_triangle.gradle.modifyReleaseMetadata
import com.possible_triangle.gradle.neoforge.NeoforgeExtension
import net.kyori.blossom.BlossomExtension
import net.neoforged.moddevgradle.dsl.NeoForgeExtension
import org.gradle.internal.extensions.stdlib.capitalized

plugins {
    id("com.possible-triangle.core")
    id("com.possible-triangle.neoforge-api") apply false
    id("com.possible-triangle.neoforge") apply false
}

withKotlin()

subprojects {
    plugins.apply("com.possible-triangle.core")

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

fun modules(block: Project.() -> Unit) = modules.forEach { it.block() }

modules {
    plugins.apply("com.possible-triangle.neoforge")
    plugins.apply("net.kyori.blossom")

    configure<NeoforgeExtension> {
        accessTransformer(project(":core"))
        createDataSourceSet()
    }

    configure<NeoForgeExtension> {
        runs.named("client") {
            ideName = "${project.name.capitalized()} Client"
        }
        runs.removeAll { it.name != "client" }
    }

    upload.forEach {
        file.unset()
    }

    modifyReleaseMetadata {
        exclude = true
    }

    sourceSets.main {
        configure<BlossomExtension> {
            javaSources {
                templates(rootProject.file("templates/java"))
            }

            resources {
                include(rootProject.file("templates/resources"))
            }

            templateSets.configureEach {
                property("modId", mod.id)
                property("modName", mod.name)
                property("module", project.name)
                property("moduleName", project.name.capitalized())
                property("moduleModId", $$"${mod_id}_$${project.name}")
                properties.put("moduleDependencies", emptyList<String>())
            }
        }
    }
}

enableSpotless {
    java {
        targetExclude("build/generated/**")
    }
}
