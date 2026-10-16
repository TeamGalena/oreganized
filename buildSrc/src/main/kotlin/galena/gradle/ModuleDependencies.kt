package galena.gradle

import net.kyori.blossom.BlossomExtension
import net.neoforged.moddevgradle.dsl.NeoForgeExtension
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

fun Project.moduleDependencies(vararg modules: String) {
    val projects = modules.map { rootProject.project(":$it") }
    dependencies {
        projects.forEach {
            add("api", project(it.path))
            // Does not work at runtime for some reason and I'm giving up of now, you win gradle
            // add("dataImplementation", project(it.path, configuration = "dataApiElements"))
        }
    }

    configure<SourceSetContainer> {
        named("main") {
            configure<BlossomExtension> {
                templateSets.configureEach {
                    properties.put("moduleDependencies", modules)
                }
            }
        }
    }

    configure<NeoForgeExtension> {
        runs.named("data") {
            projects.forEach { module ->
                val dirs = listOf("main", "generated").map { module.file("src/$it/resources").path }
                programArguments.addAll(dirs.flatMap { listOf("--existing", it) })
            }
        }
    }
}
