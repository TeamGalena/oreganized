package galena.oreganized.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

public class ModdedSmithingTemplateItem {
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;


    public static SmithingTemplateItem create(ResourceLocation type) {
        return new SmithingTemplateItem(
                Component.translatable(Util.makeDescriptionId("item", type.withPath("smithing_template.%s_upgrade.applies_to"::formatted))).withStyle(DESCRIPTION_FORMAT),
                Component.translatable(Util.makeDescriptionId("item", type.withPath("smithing_template.%s_upgrade.ingredients"::formatted))).withStyle(DESCRIPTION_FORMAT),
                Component.translatable(Util.makeDescriptionId("upgrade", type.withSuffix("_upgrade"))).withStyle(TITLE_FORMAT),
                Component.translatable(Util.makeDescriptionId("item", type.withPath("smithing_template.%s_upgrade.base_slot_description"::formatted))),
                Component.translatable(Util.makeDescriptionId("item", type.withPath("smithing_template.%s_upgrade.additions_slot_description"::formatted))),
                SmithingTemplateItem.createNetheriteUpgradeIconList(),
                SmithingTemplateItem.createNetheriteUpgradeMaterialList()
        );
    }

}
