package galena.oreganized.plumbum.world.block;

import com.mojang.serialization.MapCodec;
import galena.oreganized.OConstants;
import galena.oreganized.index.OTags;
import galena.oreganized.plumbum.config.PlumbumConfigs;
import galena.oreganized.plumbum.index.PlumbumBlocks;
import galena.oreganized.plumbum.index.PlumbumFluids;
import java.util.function.ToIntFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.RegisterCauldronFluidContentEvent;

@EventBusSubscriber
public class MoltenLeadCauldronBlock extends AbstractCauldronBlock {

    private static final MapCodec<MoltenLeadCauldronBlock> CODEC = simpleCodec(MoltenLeadCauldronBlock::new);

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> codec() {
        return CODEC;
    }

    public static final CauldronInteraction.InteractionMap INTERACTION_MAP = CauldronInteraction.newInteractionMap(OConstants.MOD_ID + ":lead");

    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

    public MoltenLeadCauldronBlock(BlockBehaviour.Properties properties) {
        super(properties.lightLevel(moltenStageEmission()), INTERACTION_MAP);
        registerDefaultState(getStateDefinition().any().setValue(AGE, 0));
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return new ItemStack(Items.CAULDRON);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    protected double getContentHeight(BlockState state) {
        return 0.9375D;
    }

    public static ToIntFunction<BlockState> moltenStageEmission() {
        return (state) -> state.getValue(AGE) * 2;
    }

    public boolean isFull(BlockState state) {
        return true;
    }

    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext ctx) {
        if (!state.getValue(AGE).equals(3)) return Shapes.block();
        return super.getShape(state, world, pos, ctx);
    }

    public void entityInside(BlockState state, Level level, BlockPos blockPos, Entity entity) {
        if (this.isEntityInsideContent(state, blockPos, entity)) {
            entity.setRemainingFireTicks(10);
        }
    }

    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos blockPos) {
        return state.getValue(AGE) + 1;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        this.tick(state, world, pos, random);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!PlumbumConfigs.COMMON.cauldronLeadMelting.get()) return;
        if (!world.isAreaLoaded(pos, 1))
            return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        int max_age = AGE.getPossibleValues().size() - 1;
        int age = state.getValue(AGE);
        if (age < max_age && random.nextInt(1) == 0) {
            BlockState below = world.getBlockState(pos.below());
            if ((below.is(OTags.Blocks.MELTS_LEAD))) {
                world.setBlockAndUpdate(pos, state.setValue(AGE, age + 1));
            } else {
                world.setBlockAndUpdate(pos, state.setValue(AGE, Mth.clamp(age - 1, 0, max_age)));
            }
        }
    }

    @SubscribeEvent
    private static void registerContent(RegisterCauldronFluidContentEvent event) {
        event.register(PlumbumBlocks.MOLTEN_LEAD_CAULDRON.value(), PlumbumFluids.MOLTEN_LEAD.value(), FluidType.BUCKET_VOLUME, null);
    }

}
