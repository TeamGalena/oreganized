package galena.oreganized.world.critera;

import com.mojang.serialization.Codec;
import java.util.Optional;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class DummyCriterionTrigger extends SimpleCriterionTrigger<DummyCriterionTrigger.TriggerInstance> {

    public void trigger(ServerPlayer player) {
        this.trigger(player, instance -> true);
    }

    @Override
    public Codec<TriggerInstance> codec() {
        return Codec.unit(TriggerInstance::new);
    }

    public TriggerInstance instance() {
        return new TriggerInstance();
    }

    public Criterion<TriggerInstance> createCriterion() {
        return createCriterion(instance());
    }

    public static class TriggerInstance implements SimpleInstance {

        @Override
        public Optional<ContextAwarePredicate> player() {
            return Optional.empty();
        }

    }

}
