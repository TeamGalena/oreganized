package galena.oreganized.content.block;

import galena.oreganized.Oreganized;
import galena.oreganized.index.OBlocks;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class SpottedGlanceBlock extends Block {

    public static final ResourceKey<LootTable> WASH_LOOT_TABLE = ResourceKey.create(Registries.LOOT_TABLE, Oreganized.modLoc("gameplay/spotted_glance"));

    public SpottedGlanceBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState adjState, LevelAccessor world, BlockPos pos, BlockPos adjPos) {
        if (!world.isWaterAt(adjPos)) return super.updateShape(state, direction, adjState, world, pos, adjPos);

        dropLeadNuggets(world, pos);

        return OBlocks.GLANCE.get().defaultBlockState();
    }

    private void dropLeadNuggets(LevelAccessor level, BlockPos pos) {
        if (level instanceof ServerLevel) {
            LootTable lootTable = level.getServer().reloadableRegistries().getLootTable(WASH_LOOT_TABLE);

            LootParams params = new LootParams.Builder((ServerLevel) level)
                    .withLuck(((ServerLevel) level).random.nextFloat())
                    .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                    .withParameter(LootContextParams.BLOCK_STATE, level.getBlockState(pos))
                    .withParameter(LootContextParams.TOOL, ItemStack.EMPTY)
                    .create(LootContextParamSets.BLOCK);

            var drops = lootTable.getRandomItems(params);
            drops.forEach(drop -> {
                Containers.dropItemStack((Level) level, pos.getX(), pos.getY(), pos.getZ(), drop);
            });
        }
    }
}
