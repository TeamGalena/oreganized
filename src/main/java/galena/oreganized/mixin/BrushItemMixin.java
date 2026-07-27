package galena.oreganized.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import galena.oreganized.index.OSoundEvents;
import galena.oreganized.network.packet.TarnishParticlePacket;
import galena.oreganized.world.TarnishManager;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BrushItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrushItem.class)
public class BrushItemMixin {

    @Inject(
            method = "onUseTick(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;)V"
            )
    )
    public void oreganized$finishPolishing(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration, CallbackInfo ci, @Local BlockPos pos, @Local(ordinal = 1) int useTick) {
        if (useTick < 20) return;
        if (TarnishManager.tryPolishing(pos, level)) {
            var slot = stack.equals(livingEntity.getItemBySlot(EquipmentSlot.OFFHAND)) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
            stack.hurtAndBreak(1, livingEntity, slot);
            livingEntity.releaseUsingItem();
        }
    }

    @WrapWithCondition(
            method = "onUseTick(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/BrushItem;spawnDustParticles(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/phys/BlockHitResult;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/entity/HumanoidArm;)V"
            )
    )
    public boolean oreganized$spawnPolishingParticle(BrushItem instance, Level level, BlockHitResult hitResult, BlockState state, Vec3 vec, HumanoidArm arm, @Local BlockPos pos) {
        if (TarnishManager.canPolish(state.getBlockHolder())) {
            if (level instanceof ServerLevel serverLevel)
                PacketDistributor.sendToPlayersInDimension(serverLevel, new TarnishParticlePacket(pos, false));
            return false;
        }

        return true;
    }

    @WrapWithCondition(
            method = "onUseTick(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;)V"
            )
    )
    public boolean oreganized$playPolishingSound(Level instance, Player player, BlockPos pos, SoundEvent soundEvent, SoundSource source, @Local BlockState state) {
        if (TarnishManager.canPolish(state.getBlockHolder())) {
            instance.playSound(player, pos, OSoundEvents.POLISH.get(), source);
            return false;
        }

        return true;
    }

}
