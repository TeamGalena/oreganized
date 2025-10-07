import net.darkhax.curseforgegradle.Constants
import net.darkhax.curseforgegradle.TaskPublishCurseForge
import java.time.LocalDateTime

val repository: String by extra
val mod_name: String by extra
val mod_author: String by extra
val mod_id: String by extra
val release_type: String by extra
val modrinth_project_id: String by extra
val curseforge_project_id: String by extra
val minecraft_version: String by extra
val parchment_version: String by extra
val maven_group: String by extra
val neoforge_version: String by extra

val mod_version = System.getenv("RELEASE_VERSION") ?: extra["mod_version"] as String

plugins {
    java
    `maven-publish`
    alias(libs.plugins.neoforge)
    alias(libs.plugins.spotless)
    alias(libs.plugins.sonarqube)
    alias(libs.plugins.minotaur)
    alias(libs.plugins.cursegradle)
}

base {
    archivesName = "$mod_name $minecraft_version-$mod_version"
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
    withSourcesJar()
}

minecraft {
    accessTransformers {
        file("src/main/resources/META-INF/accesstransformer.cfg")
    }
}

subsystems {
    parchment {
        minecraftVersion = minecraft_version
        mappingsVersion = parchment_version
    }
}

runs {
    create("client")

    create("server") {
        workingDirectory("run/server")
    }

    create("data") {
        workingDirectory("run/data")

        val existingMods = listOf(
            "blueprint",
            "shieldexp",
            "dye_depot",
        )

        arguments(
            listOf(
                "--mod",
                mod_id,
                "--all",
                "--output",
                file("src/generated/resources/").path,
                "--existing",
                file("src/main/resources/").path,
            ) + existingMods.flatMap {
                listOf("--existing-mod", it)
            })
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
        url = uri("https://registry.somethingcatchy.net/repository/maven-public/")
        content {
            includeGroup("dev.galena")
            includeGroup("com.possible-triangle")
            includeGroup("com.ninni.dye_depot")
        }
    }
}

jarJar.enable()

dependencies {
    implementation("net.neoforged:neoforge:${neoforge_version}")
    implementation(libs.blueprint)

    implementation(jarJar(libs.galena.hats.get().copy()) {
        version {
            strictly("[${version},)")
            prefer(version!!)
        }
    })

    implementation(libs.multikulti.datagen)

    // Compatibilities
    implementation(pack.modrinth.supplementaries)
    // implementation(pack.modrinth.nethers.delight)
    // implementation(pack.modrinth.shield.expansion)
    implementation(variantOf(libs.create) {
        classifier("all")
    }) {
        isTransitive = false
    }
    implementation(pack.modrinth.supplementaries)

    // For dev testing
    // runtimeOnly(pack.modrinth.scannable)
    // runtimeOnly(pack.modrinth.architectury.api)
    runtimeOnly(pack.modrinth.moonlight)
    runtimeOnly(libs.dye.depot)
    runtimeOnly(pack.modrinth.jade)
    runtimeOnly(pack.modrinth.no.mans.land)

    compileOnly(libs.jei.common.api)
    compileOnly(libs.jei.neoforge.api)
    runtimeOnly(libs.jei.neoforge)
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

tasks.jar {
    archiveClassifier.set("slim")
}

tasks.jarJar {
    archiveClassifier.set("")
}

val upload = tasks.jarJar.get().archiveFile.get()

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = maven_group
            artifactId = mod_id
            version = mod_version

            artifact(tasks.getByName("sourcesJar"))
            artifact(tasks.jar)
            artifact(tasks.jarJar)

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

tasks.withType<GenerateModuleMetadata> {
    enabled = false
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
        displayName = "$mod_name $mod_version"
        addGameVersion(minecraft_version)
        addModLoader("NeoForge")
        addRelation("blueprint", Constants.RELATION_REQUIRED, "382216")
    }
}
