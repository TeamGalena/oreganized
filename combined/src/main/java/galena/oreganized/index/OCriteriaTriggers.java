package galena.oreganized.index;

import galena.oreganized.gothic.index.GothicCriterionTriggers;
import galena.oreganized.plumbum.index.PlumbumCriterionTriggers;
import galena.oreganized.world.critera.DummyCriterionTrigger;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class OCriteriaTriggers {

    private static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, OConstants.MOD_ID);

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> SEE_GARGOYLE_GARGLE = GothicCriterionTriggers.SEE_GARGOYLE_GARGLE;

    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> TERMINAL_VELOCITY = TRIGGERS.register("terminal_velocity", DummyCriterionTrigger::new);
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> KNOCKED_BANNER_OFF = TRIGGERS.register("knocked_banner_off", DummyCriterionTrigger::new);

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> PROFOUND_BRAIN_DAMAGE = PlumbumCriterionTriggers.PROFOUND_BRAIN_DAMAGE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> IN_LEAD_CLOUD = PlumbumCriterionTriggers.IN_LEAD_CLOUD;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> BROKEN_THERMOMETER = PlumbumCriterionTriggers.BROKEN_THERMOMETER;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> SHAKEN_THERMOMETER = PlumbumCriterionTriggers.SHAKEN_THERMOMETER;

    public static void register(IEventBus modBus) {
        TRIGGERS.register(modBus);
    }

}
