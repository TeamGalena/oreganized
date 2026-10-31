package galena.gradle

import com.possible_triangle.gradle.ModVersionProperties
import com.possible_triangle.gradle.upload.SimpleVersionStrategy
import com.possible_triangle.gradle.upload.VersionStrategy
import com.possible_triangle.gradle.upload.WithMinecraftVersion
import com.possible_triangle.gradle.upload.WrappedVersionStrategy
import gradle.kotlin.dsl.accessors._e849eac7ebcc7a72f0018c78d68b6ce0.mod
import org.gradle.api.Project
import org.gradle.api.provider.Provider

class OreganizedVersionStrategy(private val project: Project) :
    WrappedVersionStrategy(WithMinecraftVersion(SimpleVersionStrategy())) {

    override fun artifactName(mod: ModVersionProperties): String {
        return super.artifactName(object : ModVersionProperties {
            override val id = project.rootProject.mod.id
            override val loader = mod.loader
            override val minecraftVersion = mod.minecraftVersion
            override val version = mod.version
        })
    }

}
