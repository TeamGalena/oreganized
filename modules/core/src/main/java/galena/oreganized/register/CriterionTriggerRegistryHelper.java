package galena.oreganized.register;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import galena.oreganized.world.critera.DummyCriterionTrigger;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;

public class CriterionTriggerRegistryHelper extends SimpleRegistryHelper<CriterionTrigger<?>> {

    public CriterionTriggerRegistryHelper(RegistryHelper parent) {
        super(parent, Registries.TRIGGER_TYPE);
    }

    public DeferredHolder<CriterionTrigger<?>, DummyCriterionTrigger> createDummy(String name) {
        return create(name, $ -> new DummyCriterionTrigger());
    }

}
