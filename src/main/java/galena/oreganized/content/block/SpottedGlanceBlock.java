package galena.oreganized.content.block;

import galena.oreganized.index.OBlocks;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
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

    public SpottedGlanceBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState adjState, LevelAccessor world, BlockPos pos, BlockPos adjPos) {
        if (!world.isWaterAt(adjPos)) return super.updateShape(state, direction, adjState, world, pos, adjPos);

        dropLeadNuggets(world, pos);

        return OBlocks.GLANCE.get().defaultBlockState();
    }

    private void dropLeadNuggets(LevelAccessor world, BlockPos pos) {
        if (world instanceof ServerLevel) {
            LootTable lootTable = world.getServer().getLootData().getLootTable(new ResourceLocation("oreganized", "gameplay/spotted_glance"));

            LootParams.Builder builder = new LootParams.Builder((ServerLevel) world)
                    .withLuck(((ServerLevel) world).random.nextFloat())
                    .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos));

            var l = lootTable.getRandomItems(builder.create(LootContextParamSets.COMMAND));
            ItemStack leadNuggets = l.iterator().next();
            Containers.dropItemStack((Level) world, pos.getX(), pos.getY(), pos.getZ(), leadNuggets);
        }
    }
}
