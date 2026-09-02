package galena.oreganized;


import com.mojang.serialization.MapCodec;
import galena.oreganized.world.AddItemLootModifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

@Mod(OConstants.MOD_ID)
public class CoreModule {

    private static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, OConstants.MOD_ID);

    public CoreModule(IEventBus modBus) {
        LOOT_MODIFIERS.register("add_item", () -> AddItemLootModifier.CODEC);
        LOOT_MODIFIERS.register(modBus);

        OConstants.REGISTRY_HELPER.register(modBus);
    }

}
