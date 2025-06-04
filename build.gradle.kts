import net.darkhax.curseforgegradle.Constants
import net.darkhax.curseforgegradle.TaskPublishCurseForge
import java.time.LocalDateTime

val repository: String by extra
val mod_name: String by extra
val mod_author: String by extra
val mod_version: String by extra
val mod_id: String by extra
val release_type: String by extra
val modrinth_project_id: String by extra
val curseforge_project_id: String by extra
val minecraft_version: String by extra
val maven_group: String by extra
val forge_version: String by extra
val blueprint_version: String by extra
val mixin_version: String by extra
val mixin_extras_version: String by extra
val farmersdelight_version: String by extra
val nethersdelight_version: String by extra
val shieldexpansion_version: String by extra
val create_version: String by extra
val ponder_version: String by extra
val supplementaries_version: String by extra
val scannable_version: String by extra
val architectury_version: String by extra
val moonlight_lib_version: String by extra
val dye_depot_version: String by extra
val jade_version: String by extra
val jei_version: String by extra
val galena_hats_version: String by extra

plugins {
    java
    `maven-publish`
    id("net.minecraftforge.gradle") version "[6.0,6.2)"
    id("org.spongepowered.mixin") version "0.7-SNAPSHOT"
    id("org.parchmentmc.librarian.forgegradle") version "1.+"
    id("com.diffplug.spotless") version "7.0.4"
    id("org.sonarqube") version "6.2.0.5505"
    id("com.modrinth.minotaur") version "2.+"
    id("net.darkhax.curseforgegradle") version "1.1.15"
}

base {
    archivesName = "$mod_name $minecraft_version-$mod_version"
}

mixin {
    add(sourceSets.main.get(), "${mod_id}.refmap.json")
    config("${mod_id}.mixins.json")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(17)
    withSourcesJar()
}

minecraft {
    mappings("parchment", "2023.09.03-1.20.1")

    accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))

    runs {
        create("client") {
            taskName = "Client"
        }

        create("server") {
            taskName = "Server"
            workingDirectory("run/server")
        }

        create("data") {
            taskName = "Data"

            val existingMods = listOf(
                "blueprint",
                "shieldexp",
                "dye_depot",
            )

            args(
                listOf(
                    "--mod",
                    mod_id,
                    "--all",
                    "--output",
                    file("src/generated/resources/"),
                    "--existing",
                    file("src/main/resources/"),
                ) + existingMods.flatMap {
                    listOf("--existing-mod", it)
                })
        }

        forEach {
            it.workingDirectory(project.file("run"))
            //it.ideaModule ("${rootProject.name}.main")
            it.args("-mixin.config=${mod_id}.mixins.json")
            it.mods {
                create(mod_id) {
                    source(sourceSets.main.get())
                }
            }
        }
    }
}

sourceSets.main {
    resources.srcDir("src/generated/resources")
}

repositories {
    mavenLocal()

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
        url = uri("https://api.modrinth.com/maven")
        content {
            includeGroup("maven.modrinth")
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
        url = uri("https://registry.somethingcatchy.net/repository/maven-releases/")
        content {
            includeGroup("dev.galena")
        }
    }
}

dependencies {
    minecraft("net.minecraftforge:forge:${minecraft_version}-${forge_version}")
    implementation(fg.deobf("com.teamabnormals:blueprint:${minecraft_version}-${blueprint_version}"))
    annotationProcessor("org.spongepowered:mixin:${mixin_version}:processor")

    compileOnly(annotationProcessor("io.github.llamalad7:mixinextras-common:${mixin_extras_version}")!!)
    implementation(jarJar("io.github.llamalad7:mixinextras-forge:${mixin_extras_version}") {
        version {
            strictly("[${mixin_extras_version},)")
            prefer(mixin_extras_version)
        }
    })

    val hatsVersion = "${minecraft_version}-${galena_hats_version}"
    implementation(fg.deobf(jarJar("dev.galena:hats-forge:${hatsVersion}") {
        version {
            strictly("[${hatsVersion},)")
            prefer(hatsVersion)
        }
    }))

    // Compatibilities
    implementation(fg.deobf("maven.modrinth:farmers-delight:${farmersdelight_version}"))
    implementation(fg.deobf("maven.modrinth:nethers-delight:${nethersdelight_version}"))
    implementation(fg.deobf("maven.modrinth:shield-expansion:${shieldexpansion_version}"))
    implementation(fg.deobf("com.simibubi.create:create-${minecraft_version}:${create_version}:all"))
    compileOnly(fg.deobf("net.createmod.ponder:Ponder-Forge-${minecraft_version}:${ponder_version}"))
    implementation(fg.deobf("maven.modrinth:supplementaries:${supplementaries_version}"))

    // For dev testing
    runtimeOnly(fg.deobf("maven.modrinth:scannable:${scannable_version}"))
    runtimeOnly(fg.deobf("maven.modrinth:architectury-api:${architectury_version}"))
    runtimeOnly(fg.deobf("maven.modrinth:moonlight:${moonlight_lib_version}"))
    runtimeOnly(fg.deobf("maven.modrinth:dye-depot:${dye_depot_version}"))
    runtimeOnly(fg.deobf("maven.modrinth:jade:${jade_version}"))

    compileOnly(fg.deobf("mezz.jei:jei-${minecraft_version}-common-api:${jei_version}"))
    compileOnly(fg.deobf("mezz.jei:jei-${minecraft_version}-forge-api:${jei_version}"))
    runtimeOnly(fg.deobf("mezz.jei:jei-${minecraft_version}-forge:${jei_version}"))

    runtimeOnly(fg.deobf("maven.modrinth:scannable:${scannable_version}"))
    runtimeOnly(fg.deobf("maven.modrinth:architectury-api:${architectury_version}"))
}

tasks.withType<Jar> {
    val now = LocalDateTime.now().toString()
    manifest {
        attributes(
            mapOf(
                "Specification-Title" to mod_name,
                "Specification-Vendor" to mod_author,
                "Specification-Version" to mod_version,
                "Implementation-Title" to mod_name,
                "Implementation-Version" to mod_version,
                "Implementation-Vendor" to mod_author,
                "Implementation-Timestamp" to now,
                "Built-On-Java" to "${System.getProperty("java.vm.version")} (${System.getProperty("java.vm.vendor")})",
                "Build-On-Minecraft" to minecraft_version
            )
        )
    }
}

tasks.withType<ProcessResources> {
    filesMatching(
        listOfNotNull(
            "META-INF/mods.toml",
            "META-INF/neoforge.mods.toml",
            "pack.mcmeta",
            "fabric.mod.json",
            "${mod_id}*.mixins.json",
        )
    ) {
        expand(
            mapOf(
                "mod_version" to mod_version,
                "mod_name" to mod_name,
                "mod_id" to mod_id,
                "mod_author" to mod_author,
                "repository" to repository,
            )
        )
    }
}

jarJar.enable()
tasks.jarJar {
    archiveClassifier.set("")
}

tasks.jar {
    archiveClassifier.set("raw")
    finalizedBy("reobfJar")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = maven_group
            artifactId = mod_id
            version = mod_version

            from(components["java"])

            pom.withXml {
                val node = asNode()
                val list = node.get("dependencies") as groovy.util.NodeList
                list.forEach { node.remove(it as groovy.util.Node) }
            }
        }
    }
    repositories {
        mavenLocal()

        val nexusToken = System.getenv("NEXUS_TOKEN")
        val nexusUser = System.getenv("NEXUS_USER")
        if (nexusToken != null && nexusUser != null) {
            maven {
                url = uri("https://registry.somethingcatchy.net/repository/maven-releases/")
                credentials {
                    username = nexusUser
                    password = nexusToken
                }
            }
        }
    }
}

spotless {
    java {
        importOrder()
        removeUnusedImports()
    }

    kotlinGradle {
        ktlint()
        suppressLintsFor { shortCode = "standard:property-naming" }
    }

    json {
        target("src/main/**/*.json")
        gson().indentWithSpaces(2)
    }
}

sonar {
    properties {
        property("sonar.projectKey", mod_id)
        property("sonar.gradle.skipCompile", "true")
        property("sonar.links.scm", "https://github.com/${repository}")
    }
}

val upload = tasks.jarJar.get().archiveFile.get()

modrinth {
    projectId = modrinth_project_id
    versionNumber = mod_version
    versionName = "$mod_name $mod_version"
    versionType = release_type
    uploadFile = upload
    gameVersions = listOf(minecraft_version)
    changelog = System.getenv("CHANGELOG")
    dependencies {
        required.project("blueprint")
    }
}

tasks.register<TaskPublishCurseForge>("curseforge") {
    apiToken = System.getenv("CURSEFORGE_TOKEN")
    upload(curseforge_project_id, upload) {
        changelogType = Constants.CHANGELOG_MARKDOWN
        changelog = System.getenv("CHANGELOG")
        releaseType = release_type
        version = mod_version
        displayName = "$mod_name $mod_version"
        addGameVersion(minecraft_version)
        addRelation("blueprint", Constants.RELATION_REQUIRED)
    }
}