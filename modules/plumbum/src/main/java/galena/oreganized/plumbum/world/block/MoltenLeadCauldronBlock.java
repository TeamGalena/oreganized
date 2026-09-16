package galena.oreganized.plumbum.world.block;

import com.mojang.serialization.MapCodec;
import galena.oreganized.plumbum.index.PlumbumBlocks;
import galena.oreganized.plumbum.index.PlumbumDamageTypes;
import galena.oreganized.plumbum.index.PlumbumFluids;

import galena.oreganized.plumbum.world.MeltingCauldronInteractions;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.RegisterCauldronFluidContentEvent;

@EventBusSubscriber
public class MoltenLeadCauldronBlock extends HeatReactingCauldronBlock {

    public MoltenLeadCauldronBlock(Properties properties) {
        super(properties, MeltingCauldronInteractions.MOLTEN_LEAD);
    }

    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos blockPos) {
        return 4;
    }

    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (isEntityInsideContent(state, pos, entity)) {
            entity.hurt(level.damageSources().source(PlumbumDamageTypes.MOLTEN_LEAD), 1F);
        }
    }

    @Override
    void heatUp(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // at max heat already
    }

    @Override
    void coolDown(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.setBlockAndUpdate(pos, PlumbumBlocks.MELTING_LEAD_CAULDRON.get().withPropertiesOf(state).setValue(MeltingLeadCauldronBlock.AGE, MeltingLeadCauldronBlock.MAX_AGE));
    }

    @SubscribeEvent
    private static void registerContent(RegisterCauldronFluidContentEvent event) {
        event.register(PlumbumBlocks.MOLTEN_LEAD_CAULDRON.value(), PlumbumFluids.MOLTEN_LEAD.value(), FluidType.BUCKET_VOLUME, null);
    }

    private static final MapCodec<MoltenLeadCauldronBlock> CODEC = simpleCodec(MoltenLeadCauldronBlock::new);

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> codec() {
        return CODEC;
    }

}
