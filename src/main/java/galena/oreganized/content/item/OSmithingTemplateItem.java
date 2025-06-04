package galena.oreganized.content.item;

import galena.oreganized.Oreganized;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

public class OSmithingTemplateItem extends SmithingTemplateItem {
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    private static final Component ELECTRUM_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", Oreganized.modLoc("electrum_upgrade"))).withStyle(TITLE_FORMAT);


    private static final Component ELECTRUM_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", Oreganized.modLoc("smithing_template.electrum_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component ELECTRUM_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", Oreganized.modLoc("smithing_template.electrum_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component ELECTRUM_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", Oreganized.modLoc("smithing_template.electrum_upgrade.base_slot_description")));
    private static final Component ELECTRUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", Oreganized.modLoc("smithing_template.electrum_upgrade.additions_slot_description")));
    private static final ResourceLocation EMPTY_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_HOE = new ResourceLocation("item/empty_slot_hoe");
    private static final ResourceLocation EMPTY_SLOT_AXE = new ResourceLocation("item/empty_slot_axe");
    private static final ResourceLocation EMPTY_SLOT_SWORD = new ResourceLocation("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL = new ResourceLocation("item/empty_slot_shovel");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = new ResourceLocation("item/empty_slot_pickaxe");
    private static final ResourceLocation EMPTY_SLOT_INGOT = new ResourceLocation("item/empty_slot_ingot");



    public OSmithingTemplateItem(Component applies_to, Component ingredients, Component title_description, Component base_slot_description, Component additions_slot_description, List<ResourceLocation> armor_icon_list, List<ResourceLocation> material_icon_list) {
        super(applies_to, ingredients, title_description, base_slot_description, additions_slot_description, armor_icon_list, material_icon_list);
    }

    public static SmithingTemplateItem createElectrumUpgradeTemplate() {
        return new SmithingTemplateItem(ELECTRUM_UPGRADE_APPLIES_TO, ELECTRUM_UPGRADE_INGREDIENTS, ELECTRUM_UPGRADE, ELECTRUM_UPGRADE_BASE_SLOT_DESCRIPTION, ELECTRUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createElectrumUpgradeIconList(), createElectrumUpgradeMaterialList());
    }

    private static List<ResourceLocation> createElectrumUpgradeIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
    }

    private static List<ResourceLocation> createElectrumUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }
}
