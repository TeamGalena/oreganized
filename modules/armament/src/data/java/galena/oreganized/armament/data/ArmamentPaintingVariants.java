package galena.oreganized.armament.data;

import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ArmamentPaintingVariants {

    public static final ResourceKey<PaintingVariant> VINDICATING_BAD = ResourceKey.create(Registries.PAINTING_VARIANT, OConstants.modLoc("vindicating_bad"));

    public ArmamentPaintingVariants() {
        ODatagen.addDataRegistryEntries(Registries.PAINTING_VARIANT, this::bootstrap);
    }

    private void bootstrap(BootstrapContext<PaintingVariant> context) {
        context.register(VINDICATING_BAD, new PaintingVariant(2, 3, VINDICATING_BAD.location()));
    }

}
