package galena.oreganized.antiques.world.block;

import galena.oreganized.OConstants;
import galena.oreganized.argentum.index.ArgentumSounds;
import galena.oreganized.argentum.network.TarnishParticlePacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.network.PacketDistributor;

public class AntiquesPileBlock extends Block {

    public static final ResourceKey<LootTable> POLISH_LOOT_TABLE = ResourceKey.create(Registries.LOOT_TABLE, OConstants.modLoc("gameplay/antiques_polishing"));

    private static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 7.0, 14.0);

    public AntiquesPileBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var belowPos = pos.below();
        var below = level.getBlockState(belowPos);
        return below.isFaceSturdy(level, belowPos, Direction.UP);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (!canSurvive(state, level, pos)) return Blocks.AIR.defaultBlockState();
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    public void dropLoot(LevelAccessor level, BlockState state, BlockPos pos, LivingEntity user, ItemStack brush) {
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.playSound(null, user, ArgentumSounds.POLISH_FINISH.get(), SoundSource.BLOCKS, 1F, 1F);
            PacketDistributor.sendToPlayersInDimension(serverLevel, new TarnishParticlePacket(pos, true));

            var lootTable = level.getServer().reloadableRegistries().getLootTable(POLISH_LOOT_TABLE);

            var params = new LootParams.Builder(serverLevel)
                    .withLuck((serverLevel).random.nextFloat())
                    .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                    .withParameter(LootContextParams.BLOCK_STATE, state)
                    .withParameter(LootContextParams.TOOL, brush)
                    .withParameter(LootContextParams.THIS_ENTITY, user)
                    .create(LootContextParamSets.BLOCK);

            var drops = lootTable.getRandomItems(params);
            drops.forEach(drop -> {
                Containers.dropItemStack(serverLevel, pos.getX(), pos.getY(), pos.getZ(), drop);
            });
        }
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
