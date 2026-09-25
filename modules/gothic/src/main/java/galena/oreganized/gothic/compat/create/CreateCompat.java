package galena.oreganized.gothic.compat.create;

import com.simibubi.create.api.registry.CreateBuiltInRegistries;
import com.simibubi.create.compat.jei.ConversionRecipe;
import com.simibubi.create.compat.jei.category.MysteriousItemConversionCategory;
import galena.oreganized.OConstants;
import galena.oreganized.gothic.index.GothicBlocks;
import java.util.stream.Stream;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreateCompat {

    public static void register(IEventBus modBus) {
        modBus.register(CreateCompat.class);

        var interactionPointTypes = DeferredRegister.create(CreateBuiltInRegistries.ARM_INTERACTION_POINT_TYPE.key(), OConstants.MOD_ID);

        interactionPointTypes.register("gargoyle", GargoyleArmPointType::new);

        interactionPointTypes.register(modBus);

    }

    @SubscribeEvent
    private static void setup(FMLCommonSetupEvent event) {
        var spyreConversion = Stream.of(GothicBlocks.PALE_GRIMSTONE_SPYRE, GothicBlocks.DARK_GRIMSTONE_SPYRE).map(it ->
                ConversionRecipe.create(
                        new ItemStack(Blocks.POINTED_DRIPSTONE),
                        new ItemStack(it)
                )
        ).toList();

        MysteriousItemConversionCategory.RECIPES.addAll(spyreConversion);
    }

}
