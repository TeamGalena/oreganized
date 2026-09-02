package galena.oreganized.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import galena.oreganized.content.item.ScribeItem;
import java.util.List;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Block.class)
public class BlockMixin {

    @WrapOperation(
            method = "getDrops(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;)Ljava/util/List;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getDrops(Lnet/minecraft/world/level/storage/loot/LootParams$Builder;)Ljava/util/List;")
    )
    private static List<ItemStack> modifyLootBuilder(BlockState state, LootParams.Builder builder, Operation<List<ItemStack>> original, @Local ItemStack stack, @Local ServerLevel level) {
        if (stack.getItem() instanceof ScribeItem scribe && scribe.dropsLikeSilktouch(stack, state)) {
            var enchantments = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
            enchantments.getHolder(Enchantments.SILK_TOUCH).ifPresent(silkTouch -> {
                var virtual = stack.copy();
                virtual.enchant(silkTouch, 1);
                builder.withParameter(LootContextParams.TOOL, virtual);
            });
        }
        return original.call(state, builder);
    }

}
