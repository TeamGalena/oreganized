package galena.oreganized.armament.index;

import galena.oreganized.OConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ArmamentDamageTypes {

    public static final ResourceKey<DamageType> LEAD_BOLT = create("lead_bolt");

    public static ResourceKey<DamageType> create(String key) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, OConstants.modLoc(key));
    }


}
