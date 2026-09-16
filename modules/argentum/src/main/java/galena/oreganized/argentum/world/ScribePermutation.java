package galena.oreganized.argentum.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public record ScribePermutation(String propertyKey) {

    public ScribePermutation(Property<?> property) {
        this(property.getName());
    }

    public static final Codec<ScribePermutation> CODEC =
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.STRING.fieldOf("property").forGetter(ScribePermutation::propertyKey)
            ).apply(instance, ScribePermutation::new));

    public BlockState apply(BlockState state) {
        return getProperty(state).map(state::cycle).orElse(state);
    }

    private Optional<Property<?>> getProperty(BlockState state) {
        return state.getProperties().stream()
                .filter(it -> it.getName().equals(this.propertyKey()))
                .findAny();
    }

}
