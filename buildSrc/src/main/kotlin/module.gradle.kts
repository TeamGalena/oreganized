import com.possible_triangle.gradle.modifyReleaseMetadata
import com.possible_triangle.gradle.neoforge.NeoforgeExtension
import net.kyori.blossom.BlossomExtension
import org.gradle.internal.extensions.stdlib.capitalized
import org.gradle.kotlin.dsl.configure

plugins {
    id("conventions")
}

configure<NeoforgeExtension> {
    accessTransformer(project(":core"))

    dataGen {
        existing("blueprint")
        existing("shieldexp")
        existing("dye_depot")

        owner = project

        splitSourceSet()
    }
}

dependencies {
    // should be dataImplementation but gradle is a hater
    runtimeOnly(project(":data"))
    "dataCompileOnly"(project(":data"))
}

modifyReleaseMetadata {
    exclude = true
}
