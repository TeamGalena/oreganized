package galena.oreganized.armament.data;

import galena.oreganized.OConstants;
import galena.oreganized.armament.index.ArmamentDamageTypes;
import galena.oreganized.data.ODatagen;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ArmamentDamageTypesGen {

    public ArmamentDamageTypesGen() {
        ODatagen.addDataRegistryEntries(Registries.DAMAGE_TYPE, this::bootstrap);
    }

    private void bootstrap(BootstrapContext<DamageType> context) {
        context.register(ArmamentDamageTypes.LEAD_BOLT, new DamageType("lead_bolt", 0.1F, DamageEffects.HURT));
    }

}
