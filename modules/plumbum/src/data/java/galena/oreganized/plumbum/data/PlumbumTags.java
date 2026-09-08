package galena.oreganized.plumbum.data;


import static galena.oreganized.index.OTags.Fluids.MOLTEN_LEAD;

import com.simibubi.create.AllTags;
import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import galena.oreganized.ModCompat;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.index.OTags;
import galena.oreganized.plumbum.index.PlumbumBlocks;
import galena.oreganized.plumbum.index.PlumbumFluids;
import galena.oreganized.plumbum.index.PlumbumItems;
import java.util.stream.Stream;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
public class PlumbumTags {

    public PlumbumTags() {
        ODatagen.addItemTagProvider(this::items);
        ODatagen.addBlockTagProvider(this::blocks);
        ODatagen.addEntityTagProvider(this::entities);
        ODatagen.addFluidTagProvider(this::fluids);
        ODatagen.addEnchantmentTagProvider(this::enchantments);
    }

    private void items(RegistrateItemTagsProvider provider) {
        provider.addTag(OTags.Items.RAW_MATERIALS_LEAD).add(PlumbumItems.RAW_LEAD.getKey());
        provider.addTag(OTags.Items.INGOTS_LEAD).add(PlumbumItems.LEAD_INGOT.getKey());
        provider.addTag(OTags.Items.NUGGETS_LEAD).add(PlumbumItems.LEAD_NUGGET.getKey());

        provider.addTag(Tags.Items.MUSIC_DISCS).add(PlumbumItems.MUSIC_DISC_STRUCTURE.getKey());
        provider.addTag(Tags.Items.BUCKETS).add(PlumbumItems.MOLTEN_LEAD_BUCKET.getKey());

        provider.copy(OTags.Blocks.ORES_LEAD, OTags.Items.ORES_LEAD);
        provider.copy(OTags.Blocks.STORAGE_BLOCKS_LEAD, OTags.Items.STORAGE_BLOCKS_LEAD);
        provider.copy(OTags.Blocks.STORAGE_BLOCKS_RAW_LEAD, OTags.Items.STORAGE_BLOCKS_RAW_LEAD);

        provider.addTag(ItemTags.TRIM_MATERIALS).add(PlumbumItems.LEAD_INGOT.getKey());

        var protectiveArmorParts = provider.addTag(OTags.Items.PROTECTIVE_ARMOR_PART);
        var protectiveHelmets = provider.addTag(OTags.Items.PROTECTIVE_HELMET);

        protectiveArmorParts
                .addOptional(ResourceLocation.fromNamespaceAndPath("thermal", "hazmat_helmet"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("thermal", "hazmat_chestplate"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("thermal", "hazmat_leggings"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("thermal", "hazmat_boots"));

        protectiveArmorParts
                .addOptional(ResourceLocation.fromNamespaceAndPath("alexscaves", "hazmat_mask"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("alexscaves", "hazmat_chestplate"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("alexscaves", "hazmat_leggings"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("alexscaves", "hazmat_boots"));

        protectiveHelmets
                .addOptional(ResourceLocation.fromNamespaceAndPath("createbigcannons", "gas_mask"));

        protectiveHelmets
                .addOptional(ResourceLocation.fromNamespaceAndPath("scguns", "anthralite_respirator"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("scguns", "netherite_respirator"));
    }

    private void blocks(RegistrateTagsProvider.IntrinsicImpl<Block> provider) {
        provider.addTag(OTags.Blocks.ORES_LEAD).add(PlumbumBlocks.LEAD_ORE.getKey(), PlumbumBlocks.DEEPSLATE_LEAD_ORE.getKey());
        provider.addTag(OTags.Blocks.STORAGE_BLOCKS_LEAD).add(PlumbumBlocks.LEAD_BLOCK.getKey());
        provider.addTag(OTags.Blocks.STORAGE_BLOCKS_RAW_LEAD).add(PlumbumBlocks.RAW_LEAD_BLOCK.getKey());

        provider.addTag(Tags.Blocks.ORES_IN_GROUND_STONE).add(PlumbumBlocks.LEAD_ORE.getKey());
        provider.addTag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE).add(PlumbumBlocks.DEEPSLATE_LEAD_ORE.getKey());

        provider.addTag(BlockTags.CAULDRONS).add(PlumbumBlocks.MOLTEN_LEAD_CAULDRON.getKey());
        provider.addTag(BlockTags.DOORS).add(PlumbumBlocks.LEAD_DOOR.getKey());
        provider.addTag(BlockTags.TRAPDOORS).add(PlumbumBlocks.LEAD_TRAPDOOR.getKey());
        provider.addTag(BlockTags.BUTTONS).add(PlumbumBlocks.STURDY_BUTTON.getKey());
        provider.addTag(BlockTags.SMALL_FLOWERS).add(PlumbumBlocks.WHITE_DATURA.getKey());
        provider.addTag(BlockTags.SMALL_FLOWERS).add(PlumbumBlocks.PURPLE_DATURA.getKey());

        Stream.of(
                PlumbumBlocks.LEAD_ORE,
                PlumbumBlocks.DEEPSLATE_LEAD_ORE,
                PlumbumBlocks.RAW_LEAD_BLOCK,

                PlumbumBlocks.LEAD_BLOCK,
                PlumbumBlocks.LEAD_BRICKS,
                PlumbumBlocks.LEAD_PILLAR,
                PlumbumBlocks.LEAD_BULB,
                PlumbumBlocks.CUT_LEAD,

                PlumbumBlocks.LEAD_DOOR,
                PlumbumBlocks.LEAD_TRAPDOOR,
                PlumbumBlocks.LEAD_BARS
        ).map(DeferredHolder::get).forEach(block -> {
            provider.addTag(BlockTags.NEEDS_STONE_TOOL).add(block);
            provider.addTag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
        });

        provider.addTag(BlockTags.MINEABLE_WITH_PICKAXE).add(PlumbumBlocks.MOLTEN_LEAD_CAULDRON.getKey());

        provider.addTag(OTags.Blocks.MELTS_LEAD)
                .add(Blocks.LAVA)
                .add(Blocks.MAGMA_BLOCK)
                .addTags(BlockTags.CAMPFIRES)
                .addTags(BlockTags.FIRE)
                .addOptionalTag(ResourceLocation.fromNamespaceAndPath(ModCompat.FARMERS_DELIGHT, "tray_heat_sources"))
                .addOptionalTag(ResourceLocation.fromNamespaceAndPath(ModCompat.CREATE, "passive_boiler_heaters"));

        provider.addTag(OTags.Blocks.CREATES_LEAD_CLOUD)
                .addTags(OTags.Blocks.ORES_LEAD)
                .addTags(OTags.Blocks.STORAGE_BLOCKS_RAW_LEAD);

        provider.addTag(OTags.Blocks.BLOWS_LEAD_CLOUD)
                .addTags(OTags.Blocks.CREATES_LEAD_CLOUD);

        provider.addTag(OTags.Blocks.PREVENTS_LEAD_CLOUD)
                .add(Blocks.WATER)
                .addOptional(ResourceLocation.fromNamespaceAndPath("spelunkery", "spring_water"));

        provider.addTag(OTags.Blocks.FIRE_HEAT_LEVEL)
                .addTags(BlockTags.FIRE)
                .addTags(BlockTags.CAMPFIRES);

        provider.addTag(OTags.Blocks.LAVA_HEAT_LEVEL)
                .add(Blocks.MAGMA_BLOCK)
                .add(Blocks.LAVA)
                .add(Blocks.LAVA_CAULDRON)
                .add(PlumbumBlocks.MOLTEN_LEAD.getKey())
                .add(PlumbumBlocks.MOLTEN_LEAD_CAULDRON.getKey());

        provider.addTag(OTags.Blocks.CARRY_ON_BLACKLIST)
                .add(PlumbumBlocks.LEAD_DOOR.getKey())
                .add(PlumbumBlocks.LEAD_TRAPDOOR.getKey());

        provider.addTag(AllTags.AllBlockTags.WRENCH_PICKUP.tag).add(PlumbumBlocks.STURDY_LEVER.getKey());
    }

    private void entities(RegistrateTagsProvider.IntrinsicImpl<EntityType<?>> provider) {
        provider.addTag(OTags.Entities.LIGHTER_THAN_LEAD).add(EntityType.IRON_GOLEM);
    }

    private void fluids(RegistrateTagsProvider.IntrinsicImpl<Fluid> provider) {
        provider.addTag(MOLTEN_LEAD).add(PlumbumFluids.MOLTEN_LEAD.getKey());
    }

    private void enchantments(RegistrateTagsProvider.Impl<Enchantment> provider) {
        provider.addTag(OTags.Enchantments.PREVENTS_LEAD_CLOUD).add(Enchantments.SILK_TOUCH);
        provider.addTag(OTags.Enchantments.HEAT_IMMUNITY).add(Enchantments.FROST_WALKER);
    }


}
