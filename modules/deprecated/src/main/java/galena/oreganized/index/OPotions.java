package galena.oreganized.index;

import galena.oreganized.plumbum.index.PlumbumPotions;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;

public class OPotions {

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<Potion, Potion> STUNNING = PlumbumPotions.STUNNING;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<Potion, Potion> LONG_STUNNING = PlumbumPotions.LONG_STUNNING;


}
