package galena.oreganized.content.block;

import com.mojang.serialization.MapCodec;
import net.mehvahdjukaar.supplementaries.mixins.BrushItemMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class SilverBlock extends Block {
    public static final MapCodec<SilverBlock> CODEC = simpleCodec(SilverBlock::new);

    public SilverBlock(Properties prop) {
        super(prop);
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack p_316304_, BlockState p_316362_, Level p_316459_, BlockPos p_316366_, Player p_316132_, InteractionHand p_316595_, BlockHitResult p_316140_) {
        return super.useItemOn(p_316304_, p_316362_, p_316459_, p_316366_, p_316132_, p_316595_, p_316140_);
    }

    public boolean brush(BlockState state, BlockPos pos, Level level, ItemStack stack, Player livingEntity, HumanoidArm arm, BlockHitResult hit, Vec3 vec3) {
        var previous = TarnishManager.previous(state);
        if (previous != null) {
            //TODO: Sound
            level.setBlockAndUpdate(pos, previous);
            return true;
        }
        return false;
    }
}
