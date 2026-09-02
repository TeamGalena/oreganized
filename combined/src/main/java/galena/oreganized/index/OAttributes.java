package galena.oreganized.index;

import galena.oreganized.Oreganized;
import galena.oreganized.argentum.index.ArgentumAttributes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class OAttributes {

    private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, Oreganized.MOD_ID);

    public static final DeferredHolder<Attribute, Attribute> KINETIC_DAMAGE = register("kinetic_damage", 0.0, 0.0, 30.0);

    private static DeferredHolder<Attribute, Attribute> register(String name, double defaultValue, double min, double max) {
        return ATTRIBUTES.register(name, () -> new RangedAttribute("attribute.%s.%s".formatted(Oreganized.MOD_ID, name), defaultValue, min, max));
    }

    public static void register(IEventBus modBus) {
        ATTRIBUTES.register(modBus);
    }

    // deprecated

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<Attribute, Attribute> INVINCIBILITY_FRAMES = ArgentumAttributes.INVINCIBILITY_FRAMES;

}
