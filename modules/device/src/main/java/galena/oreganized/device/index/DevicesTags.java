package galena.oreganized.device.index;

import galena.oreganized.register.TagHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class DevicesTags {

    public static final class Items {
        private static final TagHelper<Item> HELPER = new TagHelper<>(Registries.ITEM);

        public static final TagKey<Item> NUGGETS_NETHERITE = HELPER.commonTag("nuggets/netherite");
    }

}
