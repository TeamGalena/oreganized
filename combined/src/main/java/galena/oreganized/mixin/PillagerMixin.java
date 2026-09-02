package galena.oreganized.mixin;

import galena.oreganized.OreganizedConfig;
import galena.oreganized.index.OItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Pillager.class)
public class PillagerMixin {

    @Inject(
            method = "populateDefaultEquipmentSlots(Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/DifficultyInstance;)V",
            at = @At("HEAD")
    )
    private void addBoltToOffhand(RandomSource random, DifficultyInstance difficulty, CallbackInfo ci) {
        if (!OreganizedConfig.COMMON.pillagerSpawnWithBolts.get()) return;

        var self = (Pillager) (Object) this;
        var chance = difficulty.getDifficulty().getId() * 0.15;
        if (random.nextDouble() < chance) {
            self.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(OItems.LEAD_BOLT.get()));
        }
    }

}
