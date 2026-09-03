package galena.oreganized.index;

import galena.oreganized.argentum.index.ArgentumAttributes;
import galena.oreganized.electrum.index.ElectrumAttributes;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.neoforge.registries.DeferredHolder;

public class OAttributes {

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<Attribute, Attribute> INVINCIBILITY_FRAMES = ArgentumAttributes.INVINCIBILITY_FRAMES;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<Attribute, Attribute> KINETIC_DAMAGE = ElectrumAttributes.KINETIC_DAMAGE;

}
