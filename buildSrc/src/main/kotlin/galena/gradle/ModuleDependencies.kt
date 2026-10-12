package galena.gradle

import net.kyori.blossom.BlossomExtension
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

fun Project.moduleDependencies(vararg modules: String) {
    dependencies {
        modules.forEach {
            add("api", project(":$it"))
            add("dataApi", project(":$it", configuration = "dataElements"))
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
}
