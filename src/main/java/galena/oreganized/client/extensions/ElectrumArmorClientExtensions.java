package galena.oreganized.client.extensions;

import galena.oreganized.client.model.ElectrumArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class ElectrumArmorClientExtensions implements IClientItemExtensions {

    @Override
    public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
        return new ElectrumArmorModel<>(ElectrumArmorModel.createBodyLayer().bakeRoot(), armorSlot);
    }

}
