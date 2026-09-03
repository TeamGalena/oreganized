package galena.oreganized.plumbum.index;

import galena.oreganized.OConstants;
import galena.oreganized.register.CriterionTriggerRegistryHelper;
import galena.oreganized.world.critera.DummyCriterionTrigger;
import net.minecraft.advancements.CriterionTrigger;
import net.neoforged.neoforge.registries.DeferredHolder;

public class PlumbumCriterionTriggers {

    private static final CriterionTriggerRegistryHelper TRIGGERS = OConstants.REGISTRY_HELPER.getCriterionTriggerSubHelper();

    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> PROFOUND_BRAIN_DAMAGE = TRIGGERS.createDummy("profound_brain_damage");
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> IN_LEAD_CLOUD = TRIGGERS.createDummy("in_lead_cloud");
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> BROKEN_THERMOMETER = TRIGGERS.createDummy("broken_thermometer");
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> SHAKEN_THERMOMETER = TRIGGERS.createDummy("shaken_thermometer");

    public static void register() {
        // Load this class
    }

}
