package galena.oreganized.index;

import galena.oreganized.Oreganized;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OPaintingVariants {

    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, Oreganized.MOD_ID);

    public static final RegistryObject<PaintingVariant> VINDICATING_BAD = PAINTING_VARIANTS.register("vindicating_bad", () -> new PaintingVariant(32, 48));

    public static void register(IEventBus modBus) {
        PAINTING_VARIANTS.register(modBus);
    }
}
