package galena.oreganized.argentum.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

public record Tarnishable(Block nextStage) {

    public static final Codec<Tarnishable> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    BuiltInRegistries.BLOCK.byNameCodec().fieldOf("next_stage").forGetter(Tarnishable::nextStage)
            ).apply(instance, Tarnishable::new)
    );

}
