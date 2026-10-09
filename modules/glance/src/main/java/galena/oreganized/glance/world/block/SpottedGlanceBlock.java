package galena.oreganized.glance.world.block;

import galena.oreganized.OConstants;

import galena.oreganized.glance.index.GlanceBlocks;
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

public class SpottedGlanceBlock extends Block {

    public static final ResourceKey<LootTable> WASH_LOOT_TABLE = ResourceKey.create(Registries.LOOT_TABLE, OConstants.modLoc("gameplay/spotted_glance"));

    public SpottedGlanceBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState adjState, LevelAccessor level, BlockPos pos, BlockPos adjPos) {
        if (!level.isWaterAt(adjPos)) return super.updateShape(state, direction, adjState, level, pos, adjPos);

        dropLeadNuggets(level, state, pos);

        return GlanceBlocks.GLANCE.block().value().withPropertiesOf(state);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);

        for(var direction : Direction.values()) {
            if (level.isWaterAt(pos.relative(direction))) {
                level.setBlockAndUpdate(pos, GlanceBlocks.GLANCE.block().value().withPropertiesOf(state));
                dropLeadNuggets(level, state, pos);

                break;
            }
        }
    }

    private void dropLeadNuggets(LevelAccessor level, BlockState state, BlockPos pos) {
        if (level instanceof ServerLevel serverLevel) {
            var lootTable = level.getServer().reloadableRegistries().getLootTable(WASH_LOOT_TABLE);

            var params = new LootParams.Builder(serverLevel)
                    .withLuck((serverLevel).random.nextFloat())
                    .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                    .withParameter(LootContextParams.BLOCK_STATE, state)
                    .withParameter(LootContextParams.TOOL, ItemStack.EMPTY)
                    .create(LootContextParamSets.BLOCK);

            var drops = lootTable.getRandomItems(params);
            drops.forEach(drop -> {
                Containers.dropItemStack(serverLevel, pos.getX(), pos.getY(), pos.getZ(), drop);
            });
        }
    }
}
