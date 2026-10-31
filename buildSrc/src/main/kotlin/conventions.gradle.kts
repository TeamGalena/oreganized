import gradle.kotlin.dsl.accessors._e849eac7ebcc7a72f0018c78d68b6ce0.main
import gradle.kotlin.dsl.accessors._e849eac7ebcc7a72f0018c78d68b6ce0.mod
import gradle.kotlin.dsl.accessors._e849eac7ebcc7a72f0018c78d68b6ce0.sourceSets
import net.kyori.blossom.BlossomExtension
import org.gradle.internal.extensions.stdlib.capitalized
import org.gradle.kotlin.dsl.configure

plugins {
    id("com.possible-triangle.core")
    id("net.kyori.blossom")
}

base {
    archivesName = "${mod.name.get()} ${mod.version.get()}"
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

upload.maven {
    nexus()
}

sourceSets.main {
    configure<BlossomExtension> {
        resources {
            include(rootProject.file("templates/resources"))
        }

        templateSets.configureEach {
            property("baseModId", rootProject.mod.id)
            property("baseModName", rootProject.mod.name)
            property("modId", mod.id)
            property("modName", mod.name)
            properties.put("moduleDependencies", emptyList<String>())
            property("slug", mod.id)
        }
    }
}
