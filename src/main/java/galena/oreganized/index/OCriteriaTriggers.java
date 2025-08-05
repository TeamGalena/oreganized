package galena.oreganized.index;

import galena.oreganized.Oreganized;
import galena.oreganized.content.critera.DummyCriterionTrigger;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class OCriteriaTriggers {

    private static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, Oreganized.MOD_ID);

    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> SEE_GARGOYLE_GARGLE = TRIGGERS.register("see_gargoyle_gargle", DummyCriterionTrigger::new);
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> TERMINAL_VELOCITY = TRIGGERS.register("terminal_velocity", DummyCriterionTrigger::new);
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> KNOCKED_BANNER_OFF = TRIGGERS.register("knocked_banner_off", DummyCriterionTrigger::new);
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> PROFOUND_BRAIN_DAMAGE = TRIGGERS.register("profound_brain_damage", DummyCriterionTrigger::new);
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> IN_LEAD_CLOUD = TRIGGERS.register("in_lead_cloud", DummyCriterionTrigger::new);
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> BROKEN_THERMOMETER = TRIGGERS.register("broken_thermometer", DummyCriterionTrigger::new);
    public static final DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> SHAKEN_THERMOMETER = TRIGGERS.register("shaken_thermometer", DummyCriterionTrigger::new);

    public static void register(IEventBus modBus) {
        TRIGGERS.register(modBus);
    }

}
