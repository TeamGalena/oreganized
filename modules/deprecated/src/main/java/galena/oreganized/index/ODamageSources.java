package galena.oreganized.index;

import galena.oreganized.armament.index.ArmamentDamageTypes;
import galena.oreganized.plumbum.index.PlumbumDamageTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class ODamageSources {

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final ResourceKey<DamageType> MOLTEN_LEAD = PlumbumDamageTypes.MOLTEN_LEAD;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final ResourceKey<DamageType> LEAD_POISONING = PlumbumDamageTypes.LEAD_POISONING;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final ResourceKey<DamageType> LEAD_BOLT = ArmamentDamageTypes.LEAD_BOLT;

}
