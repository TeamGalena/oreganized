package galena.oreganized.gothic.index;

import galena.oreganized.OConstants;
import galena.oreganized.register.CriterionTriggerRegistryHelper;
import galena.oreganized.world.critera.DummyCriterionTrigger;
import net.minecraft.advancements.CriterionTrigger;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
public class GothicCriterionTriggers {

    private static final CriterionTriggerRegistryHelper TRIGGERS = OConstants.REGISTRY_HELPER.getCriterionTriggerSubHelper();

    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> SEE_GARGOYLE_GARGLE = TRIGGERS.createDummy("see_gargoyle_gargle");

}
