package galena.oreganized.plumbum.index;

import galena.oreganized.OConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class PlumbumDamageTypes {

    public static final ResourceKey<DamageType> MOLTEN_LEAD = create("molten_lead");
    public static final ResourceKey<DamageType> LEAD_POISONING = create("lead_poisoning");

    public static ResourceKey<DamageType> create(String key) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, OConstants.modLoc(key));
    }


}
