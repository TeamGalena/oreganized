package galena.oreganized.electrum.index;

import galena.oreganized.OConstants;
import galena.oreganized.register.CriterionTriggerRegistryHelper;
import galena.oreganized.world.critera.DummyCriterionTrigger;
import net.minecraft.advancements.CriterionTrigger;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
public class ElectrumCriterionTriggers {

    private static final CriterionTriggerRegistryHelper TRIGGERS = OConstants.REGISTRY_HELPER.getCriterionTriggerSubHelper();

    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> TERMINAL_VELOCITY = TRIGGERS.createDummy("terminal_velocity");

}
