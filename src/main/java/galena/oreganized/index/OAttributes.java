package galena.oreganized.index;

import galena.oreganized.Oreganized;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Oreganized.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class OAttributes {

    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, Oreganized.MOD_ID);

    public static final RegistryObject<Attribute> KINETIC_DAMAGE = register("kinetic_damage", 0.0, 0.0, 30.0);

    private static RegistryObject<Attribute> register(String name, double defaultValue, double min, double max) {
        return ATTRIBUTES.register(name, () -> new RangedAttribute("attribute.%s.%s".formatted(Oreganized.MOD_ID, name), defaultValue, min, max));
    }

}
