package galena.oreganized.armament.index;

import galena.oreganized.OConstants;
import galena.oreganized.register.CriterionTriggerRegistryHelper;
import galena.oreganized.world.critera.DummyCriterionTrigger;
import net.minecraft.advancements.CriterionTrigger;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
public class ArmamentCriterionTriggers {

    private static final CriterionTriggerRegistryHelper TRIGGERS = OConstants.REGISTRY_HELPER.getCriterionTriggerSubHelper();

    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> KNOCKED_BANNER_OFF = TRIGGERS.createDummy("knocked_banner_off");

}
