package galena.oreganized.device.data;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import galena.oreganized.data.ODatagen;
import galena.oreganized.device.DeviceModule;
import galena.oreganized.device.index.DeviceItems;
import galena.oreganized.index.CoreTags;
import net.neoforged.fml.common.Mod;

@Mod(DeviceModule.MOD_ID)
public class DevicesTags {

    public DevicesTags() {
        ODatagen.addItemTagProvider(this::items);
    }

    private void items(RegistrateItemTagsProvider provider) {
        provider.addTag(CoreTags.Items.NUGGETS_NETHERITE).add(DeviceItems.NETHERITE_NUGGET.getKey());
    }

}
