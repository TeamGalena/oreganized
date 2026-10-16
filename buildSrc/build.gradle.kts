plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    maven("https://registry.somethingcatchy.net/repository/maven-public/")
}

dependencies {
    api(libs.blossom)
    implementation("com.possible-triangle:neoforge:1.4-SNAPSHOT")
}
