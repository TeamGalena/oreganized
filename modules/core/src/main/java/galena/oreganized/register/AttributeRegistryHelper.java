package galena.oreganized.register;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredHolder;

public class AttributeRegistryHelper extends SimpleRegistryHelper<Attribute> {

    public AttributeRegistryHelper(RegistryHelper parent) {
        super(parent, Registries.ATTRIBUTE);
    }

    public DeferredHolder<Attribute, Attribute> createRanged(String name, double defaultValue, double min, double max) {
        return create(name, id -> new RangedAttribute("attribute.%s.%s".formatted(id.getNamespace(), id.getPath()), defaultValue, min, max));
    }

}
