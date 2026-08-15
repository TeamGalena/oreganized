package galena.oreganized.data;

import galena.oreganized.Oreganized;
import galena.oreganized.content.item.DeviceItem;
import galena.oreganized.content.item.SpeedometerItem;
import galena.oreganized.content.item.ThermometerItem;
import galena.oreganized.data.provider.OItemModelProvider;
import galena.oreganized.index.ODataComponents;
import galena.oreganized.index.OItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class OItemModels extends OItemModelProvider {

    public OItemModels(PackOutput output, ExistingFileHelper helper) {
        super(output, helper);
    }

    @Override
    public String getName() {
        return Oreganized.MOD_ID + " Item Models";
    }

    @Override
    protected void registerModels() {
        normalItem(OItems.MUSIC_DISC_STRUCTURE);
        normalItem(OItems.RAW_SILVER);
        normalItem(OItems.SILVER_INGOT);
        normalItem(OItems.SILVER_NUGGET);
        normalItem(OItems.RAW_LEAD);
        normalItem(OItems.LEAD_INGOT);
        normalItem(OItems.LEAD_NUGGET);
        normalItem(OItems.ELECTRUM_INGOT);
        normalItem(OItems.ELECTRUM_INGOT);
        normalItem(OItems.ELECTRUM_NUGGET);
        normalItem(OItems.NETHERITE_NUGGET);
        normalItem(OItems.MOLTEN_LEAD_BUCKET);
        OItems.electrumArmor().forEach(this::trimmableArmorItem);
        OItems.silverArmor().forEach(this::trimmableArmorItem);
        normalItem(OItems.SHRAPNEL_BOMB_MINECART);
        normalItem(OItems.SHRAPNEL_BOMB_MINECART);
        normalItem(OItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE);
        normalItem(OItems.LEAD_BOLT);
        normalItem(OItems.FLINT_AND_PEWTER);

        toolItem(OItems.BUSH_HAMMER);
        toolItem(OItems.SCRIBE);
        OItems.electrumTools().forEach(this::toolItem);
        toolItem(OItems.ELECTRUM_KNIFE);
        toolItem(OItems.ELECTRUM_MACHETE);
        shieldItem(OItems.ELECTRUM_SHIELD);
        OItems.silverTools().forEach(this::toolItem);

        crossbowOverwrite("crossbow_lead_bolt");

        leveledDevice(OItems.UNKNOWN_DEVICE, DeviceItem.FRAMES, ODataComponents.DEVICE_VALUE.getId());
        leveledDevice(OItems.THERMOMETER, ThermometerItem.HEAT_LEVELS, ODataComponents.HEAT_LEVEL.getId());
        leveledDevice(OItems.SPEEDOMETER, 16, SpeedometerItem.PROPERTY_KEY);
   }

}
