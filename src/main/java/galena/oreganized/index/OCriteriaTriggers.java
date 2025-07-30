package galena.oreganized.index;

import galena.oreganized.Oreganized;
import galena.oreganized.content.critera.DummyCriterionTrigger;
import net.minecraft.advancements.CriteriaTriggers;

@EventBusSubscriber(modid = Oreganized.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class OCriteriaTriggers {

    public static final DummyCriterionTrigger SEE_GARGOYLE_GARGLE = CriteriaTriggers.register(new DummyCriterionTrigger(Oreganized.modLoc("see_gargoyle_gargle")));
    public static final DummyCriterionTrigger TERMINAL_VELOCITY = CriteriaTriggers.register(new DummyCriterionTrigger(Oreganized.modLoc("terminal_velocity")));

    public static final DummyCriterionTrigger KNOCKED_BANNER_OFF = CriteriaTriggers.register(new DummyCriterionTrigger(Oreganized.modLoc("knocked_banner_off")));
    public static final DummyCriterionTrigger PROFOUND_BRAIN_DAMAGE = CriteriaTriggers.register(new DummyCriterionTrigger(Oreganized.modLoc("profound_brain_damage")));
    public static final DummyCriterionTrigger IN_LEAD_CLOUD = CriteriaTriggers.register(new DummyCriterionTrigger(Oreganized.modLoc("in_lead_cloud")));
    public static final DummyCriterionTrigger BROKEN_THERMOMETER = CriteriaTriggers.register(new DummyCriterionTrigger(Oreganized.modLoc("broken_thermometer")));
    public static final DummyCriterionTrigger SHAKEN_THERMOMETER = CriteriaTriggers.register(new DummyCriterionTrigger(Oreganized.modLoc("shaken_thermometer")));

}
