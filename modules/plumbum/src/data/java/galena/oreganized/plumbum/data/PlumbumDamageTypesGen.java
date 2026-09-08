package galena.oreganized.plumbum.data;

import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.plumbum.index.PlumbumDamageTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class PlumbumDamageTypesGen {

    public PlumbumDamageTypesGen() {
        ODatagen.addDataRegistryEntries(Registries.DAMAGE_TYPE, this::bootstrap);
    }

    private void bootstrap(BootstrapContext<DamageType> context) {
        context.register(PlumbumDamageTypes.MOLTEN_LEAD, new DamageType("molten_lead", 0.1F, DamageEffects.BURNING));
        context.register(PlumbumDamageTypes.LEAD_POISONING, new DamageType("lead_poisoning", 0.1F, DamageEffects.HURT));
    }

}
