import galena.gradle.modModules
import org.gradle.internal.extensions.stdlib.capitalized

plugins {
    id("com.possible-triangle.core")
    id("com.possible-triangle.neoforge") apply false
}

withKotlin()

modModules.forEach {
    it.extra.set("mod_id", "${mod.id.get()}_${it.name}")
    it.extra.set("mod_name", "${mod.name.get()} ${it.name.capitalized()}")

    it.dependencies {
        "runtimeOnly"(rootProject.pack.modrinth.jade)
        "runtimeOnly"(rootProject.pack.modrinth.freecam)
        "runtimeOnly"(rootProject.libs.jei.neoforge)
        "runtimeOnly"(rootProject.libs.dye.depot)
    }
}

val runModuleData =
    tasks.register("runModuleData") {
        modModules.forEach {
            dependsOn(it.tasks.getByName("runData"))
        }
    }

enableSpotless()
