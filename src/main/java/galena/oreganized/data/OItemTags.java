package galena.oreganized.data;

import galena.oreganized.Oreganized;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OItems;
import galena.oreganized.index.OTags;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class OItemTags extends ItemTagsProvider {

    public OItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future, CompletableFuture<TagLookup<Block>> provider, @Nullable ExistingFileHelper helper) {
        super(output, future, provider, Oreganized.MOD_ID, helper);
    }

    @Override
    public String getName() {
        return "Oreganized Item Tags";
    }

    private static TagKey<Item> dyedTag(DyeColor color) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "dyed/" + color.getSerializedName()));
    }

    private void tagDyed(Map<DyeColor, ? extends Supplier<? extends ItemLike>> values, TagKey<Item>... keys) {
        values.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry -> {
            var item = entry.getValue().get().asItem();
            var id = BuiltInRegistries.ITEM.getKey(item);
            for (var key : keys) {
                tag(key).addOptional(id);
            }
            tag(Tags.Items.DYED).addOptional(id);
            tag(dyedTag(entry.getKey())).addOptional(id);
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Oreganized
        tagDyed(OBlocks.CRYSTAL_GLASS, OTags.Items.CRYSTAL_GLASS);
        tagDyed(OBlocks.CRYSTAL_GLASS_PANES, OTags.Items.CRYSTAL_GLASS_PANES);
        tag(OTags.Items.LIGHTER_THAN_LEAD).add(Items.IRON_BOOTS);
        copy(OTags.Blocks.STONE_TYPES_GLANCE, OTags.Items.STONE_TYPES_GLANCE);

        // Oreganized Forge
        tag(OTags.Items.RAW_MATERIALS_SILVER).add(OItems.RAW_SILVER.get());
        tag(OTags.Items.RAW_MATERIALS_LEAD).add(OItems.RAW_LEAD.get());

        tag(OTags.Items.INGOTS_SILVER).add(OItems.SILVER_INGOT.get());
        tag(OTags.Items.INGOTS_LEAD).add(OItems.LEAD_INGOT.get());
        tag(OTags.Items.INGOTS_ELECTRUM).add(OItems.ELECTRUM_INGOT.get());

        tag(OTags.Items.NUGGETS_SILVER).add(OItems.SILVER_NUGGET.get());
        tag(OTags.Items.NUGGETS_LEAD).add(OItems.LEAD_NUGGET.get());
        tag(OTags.Items.NUGGETS_ELECTRUM).add(OItems.ELECTRUM_NUGGET.get());
        tag(OTags.Items.NUGGETS_NETHERITE).add(OItems.NETHERITE_NUGGET.get());

        tag(OTags.Items.BUCKETS_MOLTEN_LEAD).add(OItems.MOLTEN_LEAD_BUCKET.get());
        tag(Tags.Items.BUCKETS).addTags(OTags.Items.BUCKETS_MOLTEN_LEAD);
        tag(OTags.Items.TOOLS_BUSH_HAMMER).add(OItems.BUSH_HAMMER.get());
        tag(Tags.Items.TOOLS).add(OItems.FLINT_AND_PEWTER.get());
        tag(ItemTags.DURABILITY_ENCHANTABLE).add(OItems.FLINT_AND_PEWTER.get());
        tag(ItemTags.VANISHING_ENCHANTABLE).add(OItems.FLINT_AND_PEWTER.get());

        tag(ItemTags.PIGLIN_LOVED).add(OItems.SILVER_MIRROR.get());

        tag(Tags.Items.MUSIC_DISCS).add(OItems.MUSIC_DISC_STRUCTURE.get());

        copy(OTags.Blocks.ORES_SILVER, OTags.Items.ORES_SILVER);
        copy(OTags.Blocks.ORES_LEAD, OTags.Items.ORES_LEAD);

        copy(OTags.Blocks.STORAGE_BLOCKS_SILVER, OTags.Items.STORAGE_BLOCKS_SILVER);
        copy(OTags.Blocks.STORAGE_BLOCKS_LEAD, OTags.Items.STORAGE_BLOCKS_LEAD);
        copy(OTags.Blocks.STORAGE_BLOCKS_ELECTRUM, OTags.Items.STORAGE_BLOCKS_ELECTRUM);

        copy(OTags.Blocks.STORAGE_BLOCKS_RAW_SILVER, OTags.Items.STORAGE_BLOCKS_RAW_SILVER);
        copy(OTags.Blocks.STORAGE_BLOCKS_RAW_LEAD, OTags.Items.STORAGE_BLOCKS_RAW_LEAD);

        // Vanilla
        copy(BlockTags.WALLS, ItemTags.WALLS);
        copy(BlockTags.STAIRS, ItemTags.STAIRS);
        copy(BlockTags.SLABS, ItemTags.SLABS);
        tag(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(OItems.SILVER_INGOT.get())
                .add(OItems.ELECTRUM_INGOT.get());

        tag(ItemTags.HEAD_ARMOR).add(OItems.ELECTRUM_HELMET.get());
        tag(ItemTags.CHEST_ARMOR).add(OItems.ELECTRUM_CHESTPLATE.get());
        tag(ItemTags.LEG_ARMOR).add(OItems.ELECTRUM_LEGGINGS.get());
        tag(ItemTags.FOOT_ARMOR).add(OItems.ELECTRUM_BOOTS.get());

        tag(ItemTags.TRIM_MATERIALS).add(OItems.LEAD_INGOT.get(), OItems.SILVER_INGOT.get(), OItems.ELECTRUM_INGOT.get());
        tag(ItemTags.SMALL_FLOWERS).add(OBlocks.WHITE_DATURA.get().asItem());
        tag(ItemTags.SMALL_FLOWERS).add(OBlocks.PURPLE_DATURA.get().asItem());
        // Forge
        tag(Tags.Items.NUGGETS).addTags(OTags.Items.NUGGETS_SILVER, OTags.Items.NUGGETS_LEAD, OTags.Items.NUGGETS_ELECTRUM, OTags.Items.NUGGETS_NETHERITE);
        tag(Tags.Items.INGOTS).addTags(OTags.Items.INGOTS_SILVER, OTags.Items.INGOTS_LEAD, OTags.Items.INGOTS_ELECTRUM);
        tag(Tags.Items.ORES).addTags(OTags.Items.ORES_SILVER, OTags.Items.ORES_LEAD);
        tag(Tags.Items.STORAGE_BLOCKS)
                .addTags(
                        OTags.Items.STORAGE_BLOCKS_SILVER,
                        OTags.Items.STORAGE_BLOCKS_LEAD,
                        OTags.Items.STORAGE_BLOCKS_ELECTRUM,
                        OTags.Items.STORAGE_BLOCKS_RAW_SILVER,
                        OTags.Items.STORAGE_BLOCKS_RAW_LEAD
                )
                .add(OBlocks.LEAD_BOLT_CRATE.asItem());
        tag(Tags.Items.GLASS_BLOCKS).addTags(OTags.Items.CRYSTAL_GLASS);
        tag(Tags.Items.GLASS_PANES).addTags(OTags.Items.CRYSTAL_GLASS_PANES);
        tag(Tags.Items.RAW_MATERIALS).addTags(OTags.Items.RAW_MATERIALS_SILVER, OTags.Items.RAW_MATERIALS_LEAD);
        copy(Tags.Blocks.ORES_IN_GROUND_STONE, Tags.Items.ORES_IN_GROUND_STONE);
        copy(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE, Tags.Items.ORES_IN_GROUND_DEEPSLATE);

        tag(ItemTags.DOORS).add(OBlocks.LEAD_DOOR.get().asItem());
        tag(ItemTags.TRAPDOORS).add(OBlocks.LEAD_TRAPDOOR.get().asItem());

        tag(ItemTags.AXES).add(OItems.ELECTRUM_AXE.get());
        tag(ItemTags.PICKAXES).add(OItems.ELECTRUM_PICKAXE.get());
        tag(ItemTags.SWORDS).add(OItems.ELECTRUM_SWORD.get());
        tag(ItemTags.SHOVELS).add(OItems.ELECTRUM_SHOVEL.get());
        tag(ItemTags.HOES).add(OItems.ELECTRUM_HOE.get());
        tag(OTags.Items.TOOLS_KNIVES).add(OItems.ELECTRUM_KNIFE.get());
        tag(OTags.Items.SHIELDS).add(OItems.ELECTRUM_SHIELD.get());
        tag(OTags.Items.SHIELDS_SE).add(OItems.ELECTRUM_SHIELD.get());
        tag(OTags.Items.MACHETES).add(OItems.ELECTRUM_MACHETE.get());

        tag(OTags.Items.HAS_KINETIC_DAMAGE).add(
                OItems.ELECTRUM_AXE.get(),
                OItems.ELECTRUM_HOE.get(),
                OItems.ELECTRUM_KNIFE.get(),
                OItems.ELECTRUM_MACHETE.get(),
                OItems.ELECTRUM_PICKAXE.get(),
                OItems.ELECTRUM_SHOVEL.get(),
                OItems.ELECTRUM_SWORD.get()
        );

        tag(Tags.Items.MELEE_WEAPON_TOOLS)
                .add(OItems.ELECTRUM_AXE.get())
                .add(OItems.ELECTRUM_SWORD.get())
                .add(OItems.ELECTRUM_MACHETE.get());

        tag(OTags.Items.GARGOYLE_SNACK).addTags(OTags.Items.INGOTS_SILVER);

        var protectiveArmorParts = tag(OTags.Items.PROTECTIVE_ARMOR_PART);
        var protectiveHelmets = tag(OTags.Items.PROTECTIVE_HELMET);

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

        tagDyed(OBlocks.WAXED_CONCRETE_POWDER);
    }
}
