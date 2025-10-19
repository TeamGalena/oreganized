pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
        maven { url = uri("https://registry.somethingcatchy.net/repository/maven-public/") }
    }
}

plugins {
    id("com.possible-triangle.helper") version ("1.0.54")
    id("com.possible-triangle.packwiz") version ("1.0.54")
}
