pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
}

plugins {
    id("com.possible-triangle.helper") version ("1.4")
    id("com.possible-triangle.packwiz") version ("1.4.+")
}

include(":core")
include(":combined")

file("modules").listFiles().forEach {
    include(":${it.name}")
    project(":${it.name}").projectDir = it
}
