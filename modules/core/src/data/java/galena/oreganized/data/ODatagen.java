package galena.oreganized.data;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.providers.loot.RegistrateLootTableProvider.LootType;
import galena.oreganized.OConstants;
import galena.oreganized.compat.ponder.PonderCompat;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
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
}
