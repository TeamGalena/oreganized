package galena.oreganized.data.extensions;

import com.tterrag.registrate.providers.RegistrateAdvancementProvider;
import galena.oreganized.OConstants;
import java.util.Optional;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class OAdvancementExtensions {

    public static DisplayInfo info(RegistrateAdvancementProvider provider, ItemLike icon, String id, AdvancementType type, String title, String description) {
        return info(provider, new ItemStack(icon), id, type, title, description);
    }

    public static DisplayInfo info(RegistrateAdvancementProvider provider, ItemStack icon, String id, AdvancementType type, String title, String description) {
        return new DisplayInfo(
                icon,
                provider.title(OConstants.MOD_ID, id, title),
                provider.desc(OConstants.MOD_ID, id, description),
                Optional.empty(),
                type,
                true,
                true,
                false
        );
    }

    public static AdvancementHolder getAdv(String id) {
        return Advancement.Builder.advancement().build(ResourceLocation.parse(id));
    }

}
