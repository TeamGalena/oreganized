package galena.oreganized.index;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class OPaintingVariants {

    public static final ResourceKey<PaintingVariant> VINDICATING_BAD = ResourceKey.create(Registries.PAINTING_VARIANT, OConstants.modLoc("vindicating_bad"));

    public static void bootstrap(BootstrapContext<PaintingVariant> context) {
        context.register(VINDICATING_BAD,  new PaintingVariant(2, 3, VINDICATING_BAD.location()));
    }

}
