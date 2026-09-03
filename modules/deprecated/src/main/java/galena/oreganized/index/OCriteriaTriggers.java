package galena.oreganized.index;

import galena.oreganized.armament.index.ArmamentCriterionTriggers;
import galena.oreganized.electrum.index.ElectrumCriterionTriggers;
import galena.oreganized.gothic.index.GothicCriterionTriggers;
import galena.oreganized.plumbum.index.PlumbumCriterionTriggers;
import galena.oreganized.world.critera.DummyCriterionTrigger;
import net.minecraft.advancements.CriterionTrigger;
import net.neoforged.neoforge.registries.DeferredHolder;

public class OCriteriaTriggers {


    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> SEE_GARGOYLE_GARGLE = GothicCriterionTriggers.SEE_GARGOYLE_GARGLE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> TERMINAL_VELOCITY = ElectrumCriterionTriggers.TERMINAL_VELOCITY;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> KNOCKED_BANNER_OFF = ArmamentCriterionTriggers.KNOCKED_BANNER_OFF;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> PROFOUND_BRAIN_DAMAGE = PlumbumCriterionTriggers.PROFOUND_BRAIN_DAMAGE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> IN_LEAD_CLOUD = PlumbumCriterionTriggers.IN_LEAD_CLOUD;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> BROKEN_THERMOMETER = PlumbumCriterionTriggers.BROKEN_THERMOMETER;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> SHAKEN_THERMOMETER = PlumbumCriterionTriggers.SHAKEN_THERMOMETER;


}
