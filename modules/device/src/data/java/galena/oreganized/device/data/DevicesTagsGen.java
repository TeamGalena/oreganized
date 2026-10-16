package galena.oreganized.device.data;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import galena.oreganized.data.ODatagen;
import galena.oreganized.device.DeviceModule;
import galena.oreganized.device.index.DeviceItems;
import galena.oreganized.device.index.DevicesTags;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

@Mod(DeviceModule.MOD_ID)
public class DevicesTagsGen {

    public DevicesTagsGen() {
        ODatagen.addItemTagProvider(this::items);
    }

    private void items(RegistrateItemTagsProvider provider) {
        provider.addTag(Tags.Items.NUGGETS).addTag(DevicesTags.Items.NUGGETS_NETHERITE);

        provider.addTag(DevicesTags.Items.NUGGETS_NETHERITE).add(DeviceItems.NETHERITE_NUGGET.getKey());
    }

}
