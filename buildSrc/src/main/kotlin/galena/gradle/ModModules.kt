package galena.gradle

import org.gradle.api.Project

val Project.modModules
    get() =
        rootProject.subprojects
            .filter { it.projectDir.relativeTo(it.rootDir).startsWith("modules/") }
