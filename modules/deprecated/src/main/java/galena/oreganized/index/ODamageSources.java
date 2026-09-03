package galena.oreganized.index;

import galena.oreganized.OConstants;
import galena.oreganized.armament.index.ArmamentDamageTypes;
import galena.oreganized.plumbum.index.PlumbumDamageTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageType;

public class ODamageSources {

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final ResourceKey<DamageType> MOLTEN_LEAD = PlumbumDamageTypes.MOLTEN_LEAD;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final ResourceKey<DamageType> LEAD_POISONING = PlumbumDamageTypes.LEAD_POISONING;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final ResourceKey<DamageType> LEAD_BOLT = ArmamentDamageTypes.LEAD_BOLT;

    public static ResourceKey<DamageType> create(String key) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, OConstants.modLoc(key));
    }

    public static void bootStrap(BootstrapContext<DamageType> context) {
        context.register(MOLTEN_LEAD, new DamageType("molten_lead", 0.1F, DamageEffects.BURNING));
        context.register(LEAD_POISONING, new DamageType("lead_poisoning", 0.1F, DamageEffects.HURT));
        context.register(LEAD_BOLT, new DamageType("lead_bolt", 0.1F, DamageEffects.HURT));
    }
}
