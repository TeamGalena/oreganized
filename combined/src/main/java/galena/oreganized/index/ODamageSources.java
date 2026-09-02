package galena.oreganized.index;

import galena.oreganized.Oreganized;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageType;

public class ODamageSources {

    public static final ResourceKey<DamageType> MOLTEN_LEAD = create("molten_lead");
    public static final ResourceKey<DamageType> LEAD_POISONING = create("lead_poisoning");

    public static final ResourceKey<DamageType> LEAD_BOLT = create("lead_bolt");

    public static ResourceKey<DamageType> create(String key) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, Oreganized.modLoc(key));
    }

    public static void bootStrap(BootstrapContext<DamageType> context) {
        context.register(MOLTEN_LEAD, new DamageType("molten_lead", 0.1F, DamageEffects.BURNING));
        context.register(LEAD_POISONING, new DamageType("lead_poisoning", 0.1F, DamageEffects.HURT));
        context.register(LEAD_BOLT, new DamageType("lead_bolt", 0.1F, DamageEffects.HURT));
    }
}
