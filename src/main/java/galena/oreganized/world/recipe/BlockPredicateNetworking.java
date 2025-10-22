package galena.oreganized.world.recipe;

import com.google.common.collect.Sets;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.NbtPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.tags.TagKey;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockPredicateNetworking {

    public static void encode(FriendlyByteBuf buffer, BlockPredicate predicate) {
        if (predicate.tag != null) {
            buffer.writeBoolean(true);
            buffer.writeResourceLocation(predicate.tag.location());
        } else {
            buffer.writeBoolean(false);
            assert predicate.blocks != null;
            buffer.writeCollection(predicate.blocks, (buf, block) ->
                    buf.writeRegistryIdUnsafe(ForgeRegistries.BLOCKS, block)
            );
        }
    }

    public static BlockPredicate decode(FriendlyByteBuf buffer) {
        var properties = StatePropertiesPredicate.ANY;
        var nbt = NbtPredicate.ANY;
        if (buffer.readBoolean()) {
            var tag = TagKey.create(Registries.BLOCK, buffer.readResourceLocation());
            return new BlockPredicate(tag, null, properties, nbt);
        } else {
            var blocks = buffer.readCollection(Sets::newHashSetWithExpectedSize, buf ->
                    buf.readRegistryIdUnsafe(ForgeRegistries.BLOCKS)
            );
            return new BlockPredicate(null, blocks, properties, nbt);
        }
    }

}
