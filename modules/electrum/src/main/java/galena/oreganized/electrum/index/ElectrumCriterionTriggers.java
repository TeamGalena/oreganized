package galena.oreganized.electrum.index;

import galena.oreganized.electrum.ElectrumModule;
import galena.oreganized.register.CriterionTriggerRegistryHelper;
import galena.oreganized.world.critera.DummyCriterionTrigger;
import net.minecraft.advancements.CriterionTrigger;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(ElectrumModule.MOD_ID)
public class ElectrumCriterionTriggers {

    private static final CriterionTriggerRegistryHelper TRIGGERS = ElectrumModule.REGISTRY_HELPER.getCriterionTriggerSubHelper();

    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> TERMINAL_VELOCITY = TRIGGERS.createDummy("terminal_velocity");

}
