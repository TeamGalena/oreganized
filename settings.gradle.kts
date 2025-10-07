pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
        maven { url = uri("https://registry.somethingcatchy.net/repository/maven-public/") }
    }
}

plugins {
    id("com.possible-triangle.packwiz") version ("0.3.0")
}
