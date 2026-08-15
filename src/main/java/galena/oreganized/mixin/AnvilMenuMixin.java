package galena.oreganized.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import galena.oreganized.index.OTags;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AnvilMenu.class)
public class AnvilMenuMixin {

    @WrapOperation(
            method = "createResult()V",
            at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(II)I", ordinal = 1)
    )
    public int modifyEnchantmentCost(int a, int b, Operation<Integer> original, @Local(ordinal = 1) ItemStack subject, @Local(ordinal = 2) ItemStack book, @Share("no_enchant_cost") LocalBooleanRef flag) {
        // TODO support for quarks ancient tomes
        if (subject.is(OTags.Items.NO_ANVIL_ENCHANT_COST) && book.is(Items.ENCHANTED_BOOK)) {
            flag.set(true);
            return 0;
        }
        return original.call(a, b);
    }

    @Redirect(
            method = "createResult()V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/item/ItemStack;EMPTY:Lnet/minecraft/world/item/ItemStack;",
                    opcode = Opcodes.GETSTATIC,
                    ordinal = 4
            )
    )
    public ItemStack modifyEnchantmentCost(@Local(ordinal = 1) ItemStack subject, @Share("no_enchant_cost") LocalBooleanRef freeAllowed, @Share("no_anvil_cost") LocalBooleanRef free) {
        if (freeAllowed.get()) {
            free.set(true);
            return subject;
        }
        return ItemStack.EMPTY;
    }

    @WrapOperation(
            method = "createResult()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/inventory/AnvilMenu;calculateIncreasedRepairCost(I)I"
            )
    )
    public int modifyEnchantmentCost(int oldRepairCost, Operation<Integer> original, @Share("no_anvil_cost") LocalBooleanRef free) {
        if (free.get()) return oldRepairCost;
        return original.call(oldRepairCost);
    }

    @WrapOperation(
            method = "mayPickup(Lnet/minecraft/world/entity/player/Player;Z)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/DataSlot;get()I", ordinal = 1)
    )
    public int allowTakingWithoutCost(DataSlot instance, Operation<Integer> original) {
        return 1;
    }

}
