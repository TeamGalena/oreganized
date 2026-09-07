package galena.oreganized.data.extensions;

import static galena.oreganized.ModCompat.SHIELD_EXPANSION_ID;
import static net.minecraft.resources.ResourceLocation.withDefaultNamespace;
import static net.neoforged.neoforge.client.model.generators.ModelProvider.ITEM_FOLDER;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.registries.DeferredItem;

public class OItemModelExtensions {

    public static ItemModelBuilder generatedItem(ItemModelProvider provider, ResourceLocation name, ResourceLocation texture, String type) {
        return provider
                .withExistingParent(name.getPath(), "item/generated")
                .texture("layer0", texture.withPrefix(type + "/"));
    }

    public static ItemModelBuilder normalItem(ItemModelProvider provider, DeferredItem<? extends Item> item) {
        return generatedItem(provider, item.getId(), item.getId(), ITEM_FOLDER);
    }

    public static ItemModelBuilder toolItem(ItemModelProvider provider, DeferredItem<? extends Item> item) {
        return provider
                .withExistingParent(item.getId().getPath(), withDefaultNamespace("item/handheld"))
                .texture("layer0", itemTexture(item.getId()));
    }

    public static ItemModelBuilder shieldItem(ItemModelProvider provider, DeferredItem<? extends Item> item) {
        var texture = itemTexture(item.getId());
        var name = item.getId().getPath();

        var blockingModel = provider.withExistingParent(name + "_blocking", ResourceLocation.fromNamespaceAndPath(SHIELD_EXPANSION_ID, "item/netherite_shield_blocking"))
                .guiLight(BlockModel.GuiLight.FRONT)
                .texture("1", texture)
                .texture("particle", texture);

        return provider.withExistingParent(name, ResourceLocation.fromNamespaceAndPath(SHIELD_EXPANSION_ID, "item/netherite_shield"))
                .guiLight(BlockModel.GuiLight.FRONT)
                .texture("1", texture)
                .texture("particle", texture)
                .override()
                .predicate(withDefaultNamespace("blocking"), 1.0F)
                .model(blockingModel)
                .end();
    }

    public static ItemModelBuilder leveledDevice(ItemModelProvider provider, DeferredItem<? extends Item> item, int levels, ResourceLocation property) {
        var model = provider.withExistingParent(item.getId().getPath(), "item/generated");

        for (int i = 0; i < levels; i++) {
            var subName = item.getId().withSuffix("_" + i);
            var subModel = generatedItem(provider, subName, subName, ITEM_FOLDER);
            model.override()
                    .model(subModel)
                    .predicate(property, i)
                    .end();
        }

        return model;
    }


    /**
     * copied from {@link com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider}
     */
    public static void trimmableArmorItem(ItemModelProvider provider, DeferredItem<? extends ArmorItem> item) {
        var id = item.getId();
        var model = generatedItem(provider, id, id, ITEM_FOLDER);
        int trimType = 1;
        for (var trim : new String[]{"quartz", "iron", "netherite", "redstone", "copper", "gold", "emerald", "diamond", "lapis", "amethyst"}) {
            var name = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "item/" + id.getPath() + "_" + trim + "_trim");
            model.override().model(new ModelFile.UncheckedModelFile(name)).predicate(withDefaultNamespace("trim_type"), (float) (trimType / 10.0));
            var texture = withDefaultNamespace("trims/items/" + item.value().getType().getName() + "_trim_" + trim);
            provider.existingFileHelper.trackGenerated(texture, PackType.CLIENT_RESOURCES, ".png", "textures");
            provider.withExistingParent(name.getPath(), "item/generated")
                    .texture("layer0", itemTexture(id))
                    .texture("layer1", texture);
            trimType++;
        }
    }

    public static ResourceLocation itemTexture(ResourceLocation id) {
        return id.withPrefix(ITEM_FOLDER + "/");
    }

}
