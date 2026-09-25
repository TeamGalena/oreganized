package galena.oreganized.argentum.compat.create;


import com.mojang.datafixers.util.Pair;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import galena.oreganized.argentum.compat.ItemTarnishing;
import galena.oreganized.argentum.index.ArgentumParticles;
import galena.oreganized.argentum.index.ArgentumTags;
import galena.oreganized.argentum.world.Tarnishable;
import java.util.List;
import net.createmod.catnip.math.VecHelper;
import net.createmod.catnip.theme.Color;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class TarnishingFanProcessingType implements FanProcessingType {

    @Override
    public boolean isValidAt(Level level, BlockPos pos) {
        if (level.getFluidState(pos).is(ArgentumTags.Fluids.FAN_PROCESSING_CATALYST_TARNISHING)) {
            return true;
        }

        var state = level.getBlockState(pos);
        if (state.is(ArgentumTags.Blocks.FAN_PROCESSING_CATALYST_TARNISHING)) {
            var be = level.getBlockEntity(pos);

            if (be instanceof SpawnerBlockEntity spawner) {
                // TODO check if undead can/was summoned?
            }

            return true;
        }

        return false;
    }

    @Override
    public int getPriority() {
        return 300;
    }

    @Override
    public boolean canProcess(ItemStack stack, Level level) {
        return ItemTarnishing.asTarnishable(stack).isPresent();
    }

    @Override
    public @Nullable List<ItemStack> process(ItemStack stack, Level level) {
        return ItemTarnishing.asTarnishable(stack)
                .map(Pair::getSecond)
                .map(Tarnishable::nextStage)
                .map(Block::asItem)
                .map(Item::getDefaultInstance)
                .map(List::of)
                .orElse(null);
    }

    @Override
    public void spawnProcessingParticles(Level level, Vec3 pos) {
        if (level.random.nextInt(8) != 0) return;
        pos = pos.add(VecHelper.offsetRandomly(Vec3.ZERO, level.random, 1)
                .multiply(1, 0.05F, 1)
                .normalize()
                .scale(0.15F));
        level.addParticle(ArgentumParticles.TARNISH.value(), pos.x, pos.y + .45F, pos.z, 0, 0, 0);
    }

    @Override
    public void morphAirFlow(AirFlowParticleAccess particle, RandomSource random) {
        particle.setColor(Color.mixColors(0x433450, 0xaebaa4, random.nextFloat()));
        particle.setAlpha(1F);
        if (random.nextFloat() < 1 / 64F)
            particle.spawnExtraParticle(ArgentumParticles.TARNISH.value(), .125F);
    }

    @Override
    public void affectEntity(Entity entity, Level level) {
        // TODO tarnish mobs?
    }
}
