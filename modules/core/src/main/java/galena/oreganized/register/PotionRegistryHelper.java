package galena.oreganized.register;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import java.util.function.Supplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;

public class PotionRegistryHelper extends SimpleRegistryHelper<Potion> {

    public PotionRegistryHelper(RegistryHelper parent) {
        super(parent, Registries.POTION);
    }

    public DeferredHolder<Potion, Potion> createPotion(String name, Supplier<MobEffectInstance> effect) {
        return create(name, id -> new Potion(id.getPath(), effect.get()));
    }

}
