import com.possible_triangle.gradle.modifyReleaseMetadata
import com.possible_triangle.gradle.neoforge.NeoforgeExtension
import net.kyori.blossom.BlossomExtension
import org.gradle.internal.extensions.stdlib.capitalized
import org.gradle.kotlin.dsl.configure

plugins {
    id("conventions")
    id("net.kyori.blossom")
}

configure<NeoforgeExtension> {
    accessTransformer(project(":core"))

    dataGen {
        existing("blueprint")
        existing("shieldexp")
        existing("dye_depot")

        owner = project

        splitSourceSet()
    }
}

dependencies {
    // should be dataImplementation but gradle is a hater
    runtimeOnly(project(":data"))
    "dataCompileOnly"(project(":data"))
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
            property("baseModId", rootProject.mod.id)
            property("baseModName", rootProject.mod.name)
            properties.put("moduleDependencies", emptyList<String>())
        }
    }
}
