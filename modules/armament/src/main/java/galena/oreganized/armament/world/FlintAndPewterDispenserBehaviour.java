package galena.oreganized.armament.world;

import galena.oreganized.plumbum.world.block.LeadOreBlock;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;

public class FlintAndPewterDispenserBehaviour implements DispenseItemBehavior {

    @Override
    public ItemStack dispense(BlockSource source, ItemStack stack) {
        var facing = source.state().getValue(DispenserBlock.FACING);
        var pos = source.pos().relative(facing);
        var cloud = LeadOreBlock.spawnCloud(source.level(), pos, 1F);
        cloud.setWaitTime(0);

        stack.hurtAndBreak(1, source.level(), null, $ -> {
        });

        return stack;
    }

}
