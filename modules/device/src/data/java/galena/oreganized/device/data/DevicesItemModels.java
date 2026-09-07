package galena.oreganized.device.data;

import static galena.oreganized.data.extensions.OItemModelExtensions.leveledDevice;
import static galena.oreganized.data.extensions.OItemModelExtensions.normalItem;

import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.device.index.DeviceDataComponents;
import galena.oreganized.device.index.DeviceItems;
import galena.oreganized.device.world.item.DeviceItem;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class DevicesItemModels {

    public DevicesItemModels() {
        ODatagen.addItemModelProvider(this::generate);
    }

    private void generate(RegistrateItemModelProvider provider) {
        normalItem(provider, DeviceItems.NETHERITE_NUGGET);
        leveledDevice(provider, DeviceItems.UNKNOWN_DEVICE, DeviceItem.FRAMES, DeviceDataComponents.DEVICE_VALUE.getId());
    }

}
