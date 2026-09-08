package galena.oreganized.data;

import com.possible_triangle.multikulti.registrate.platform.service.ForgeRegistrateBuilders;
import com.possible_triangle.multikulti.registrate.provider.RegistrateSoundsProvider;
import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.providers.*;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.providers.loot.RegistrateLootTableProvider.LootType;
import galena.oreganized.OConstants;
import galena.oreganized.client.OResourcePacks;
import galena.oreganized.compat.ponder.PonderCompat;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.DetectedVersion;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(OConstants.MOD_ID)
@EventBusSubscriber
public class ODatagen {

    private static final AbstractRegistrate<?> REGISTRATE = ORegistrate.create(OConstants.MOD_ID);

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void generatePonderLang(GatherDataEvent event) {
        PonderCompat.register();
        PonderIndex.getLangAccess().provideLang(OConstants.MOD_ID, REGISTRATE::addRawLang);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void generatePackMetadata(GatherDataEvent event) {
        event.createProvider((output, lookup) -> new PackMetadataGenerator(output).add(PackMetadataSection.TYPE, new PackMetadataSection(
                Component.literal("Oreganized resources"),
                DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES),
                Optional.empty()
        )));

        var createCompat = event.getGenerator().getBuiltinDatapack(true, "create_compat");
        var createCompatOutput = new PackOutput(event.getGenerator().getPackOutput().getOutputFolder().resolve(OResourcePacks.CREATE_COMPAT));
        createCompat.addProvider($ -> new PackMetadataGenerator(createCompatOutput).add(PackMetadataSection.TYPE, new PackMetadataSection(
                Component.literal("Textures for other mods to fit Oreganized's color palettes for its materials"),
                DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES),
                Optional.empty()
        )));
    }

    public static void addLangProvider(Consumer<RegistrateLangProvider> consumer) {
        REGISTRATE.addDataGenerator(ProviderType.LANG, consumer::accept);
    }

    public static void addRecipeProvider(Consumer<RecipeOutput> consumer) {
        REGISTRATE.addDataGenerator(ProviderType.RECIPE, consumer::accept);
    }

    public static void addBlockLootProvider(Consumer<RegistrateBlockLootTables> consumer) {
        REGISTRATE.addDataGenerator(ProviderType.LOOT, provider -> {
            provider.addLootAction(LootType.BLOCK, consumer::accept);
        });
    }

    public static void addLootProvider(LootContextParamSet set, Consumer<BiConsumer<ResourceKey<LootTable>, LootTable.Builder>> consumer) {
        REGISTRATE.addDataGenerator(ProviderType.LOOT, provider -> {
            provider.addLootAction(set, consumer);
        });
    }

    public static void addBlockStateProvider(Consumer<RegistrateBlockstateProvider> consumer) {
        REGISTRATE.addDataGenerator(ProviderType.BLOCKSTATE, consumer::accept);
    }

    public static void addItemModelProvider(Consumer<RegistrateItemModelProvider> consumer) {
        REGISTRATE.addDataGenerator(ProviderType.ITEM_MODEL, consumer::accept);
    }

    public static void addAdvancementProvider(Consumer<RegistrateAdvancementProvider> consumer) {
        REGISTRATE.addDataGenerator(ProviderType.ADVANCEMENT, consumer::accept);
    }

    public static void addSoundDefinitionProvider(Consumer<RegistrateSoundsProvider> consumer) {
        REGISTRATE.addDataGenerator(ForgeRegistrateBuilders.getSOUNDS(), consumer::accept);
    }
}
