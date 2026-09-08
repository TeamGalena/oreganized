package galena.oreganized.data.extensions;

import java.util.Map;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;

public class ORegistryBootstrapExtensions {

    public static void registerTrim(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> key, Holder<Item> item, Style style, Map<Holder<ArmorMaterial>, String> overrides) {
        var location = key.location();
        context.register(key, new TrimMaterial(
                location.getNamespace() + "_" + location.getPath(),
                item,
                -1.0F,
                overrides,
                Component.translatable(Util.makeDescriptionId("trim_material", location)).withStyle(style))
        );
    }

}
