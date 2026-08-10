package galena.oreganized.client.extensions;

import java.util.function.BiFunction;
import java.util.function.Supplier;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public record CustomArmorModelExtensions(BiFunction<ModelPart, EquipmentSlot, HumanoidModel<?>> model, Supplier<LayerDefinition> layerDefinition) implements IClientItemExtensions {

    @Override
    public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
        return model.apply(layerDefinition.get().bakeRoot(), armorSlot);
    }

}
