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
val maven_group: String by extra
val forge_version: String by extra

val mod_version = System.getenv("RELEASE_VERSION") ?: extra["mod_version"] as String

plugins {
    java
    `maven-publish`
    alias(libs.plugins.forge)
    alias(libs.plugins.mixin)
    alias(libs.plugins.parchment)
    alias(libs.plugins.spotless)
    alias(libs.plugins.sonarqube)
    alias(libs.plugins.minotaur)
    alias(libs.plugins.cursegradle)
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
            workingDirectory("run/data")

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
    minecraft("net.minecraftforge:forge:${minecraft_version}-${forge_version}")
    annotationProcessor(variantOf(libs.mixin) { classifier("processor") })
    implementation(fg.deobf(libs.blueprint))

    compileOnly(annotationProcessor(libs.mixin.extras.common.get())!!)
    implementation(jarJar(libs.mixin.extras.forge.get().copy()) {
        version {
            strictly("[${version},)")
            prefer(version!!)
        }
    })

    implementation(fg.deobf(jarJar(libs.galena.hats.get().copy()) {
        version {
            strictly("[${version},)")
            prefer(version!!)
        }
    }))

    implementation(fg.deobf(libs.multikulti.core))
    implementation(fg.deobf(libs.multikulti.datagen))
    implementation(fg.deobf(jarJar(libs.multikulti.datagen.fix.get().copy()) {
        version {
            strictly("[${version},)")
            prefer(version!!)
        }
    }))

    // Compatibilities
    implementation(fg.deobf(pack.modrinth.farmers.delight))
    implementation(fg.deobf(pack.modrinth.nethers.delight))
    implementation(fg.deobf(pack.modrinth.shield.expansion))
    implementation(fg.deobf(variantOf(libs.create) {
        classifier("all")
    }))
    compileOnly(fg.deobf(libs.ponder))
    implementation(fg.deobf(pack.modrinth.supplementaries))

    // For dev testing
    runtimeOnly(fg.deobf(pack.modrinth.scannable))
    runtimeOnly(fg.deobf(pack.modrinth.architectury.api))
    runtimeOnly(fg.deobf(pack.modrinth.moonlight))
    runtimeOnly(fg.deobf(libs.dye.depot))
    runtimeOnly(fg.deobf(pack.modrinth.jade))

    compileOnly(fg.deobf(libs.jei.common.api))
    compileOnly(fg.deobf(libs.jei.forge.api))
    runtimeOnly(fg.deobf(libs.jei.forge))
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
    finalizedBy("reobfJar")
}

tasks.jarJar {
    archiveClassifier.set("")
    finalizedBy("reobfJarJar")
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
        addRelation("blueprint", Constants.RELATION_REQUIRED, "382216")
    }
}