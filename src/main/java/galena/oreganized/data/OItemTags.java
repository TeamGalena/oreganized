package galena.oreganized.data;

import static galena.oreganized.index.OTags.Items.*;

import galena.oreganized.Oreganized;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OItems;
import galena.oreganized.index.OTags;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class OItemTags extends ItemTagsProvider {

    public OItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future, CompletableFuture<TagLookup<Block>> provider, @Nullable ExistingFileHelper helper) {
        super(output, future, provider, Oreganized.MOD_ID, helper);
    }

    @Override
    public String getName() {
        return "Oreganized Item Tags";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Oreganized
        copy(OTags.Blocks.CRYSTAL_GLASS, CRYSTAL_GLASS);
        copy(OTags.Blocks.CRYSTAL_GLASS_PANES, CRYSTAL_GLASS_PANES);
        tag(LIGHTER_THAN_LEAD).add(Items.IRON_BOOTS);
        copy(OTags.Blocks.STONE_TYPES_GLANCE, STONE_TYPES_GLANCE);

        // Oreganized Forge
        tag(RAW_MATERIALS_SILVER).add(OItems.RAW_SILVER.get());
        tag(RAW_MATERIALS_LEAD).add(OItems.RAW_LEAD.get());

        tag(INGOTS_SILVER).add(OItems.SILVER_INGOT.get());
        tag(INGOTS_LEAD).add(OItems.LEAD_INGOT.get());
        tag(INGOTS_ELECTRUM).add(OItems.ELECTRUM_INGOT.get());

        tag(NUGGETS_SILVER).add(OItems.SILVER_NUGGET.get());
        tag(NUGGETS_LEAD).add(OItems.LEAD_NUGGET.get());
        tag(NUGGETS_ELECTRUM).add(OItems.ELECTRUM_NUGGET.get());
        tag(NUGGETS_NETHERITE).add(OItems.NETHERITE_NUGGET.get());

        tag(BUCKETS_MOLTEN_LEAD).add(OItems.MOLTEN_LEAD_BUCKET.get());
        tag(TOOLS_BUSH_HAMMER).add(OItems.BUSH_HAMMER.get());


        copy(OTags.Blocks.ORES_SILVER, ORES_SILVER);
        copy(OTags.Blocks.ORES_LEAD, ORES_LEAD);

        copy(OTags.Blocks.STORAGE_BLOCKS_SILVER, STORAGE_BLOCKS_SILVER);
        copy(OTags.Blocks.STORAGE_BLOCKS_LEAD, STORAGE_BLOCKS_LEAD);
        copy(OTags.Blocks.STORAGE_BLOCKS_ELECTRUM, STORAGE_BLOCKS_ELECTRUM);

        copy(OTags.Blocks.STORAGE_BLOCKS_RAW_SILVER, STORAGE_BLOCKS_RAW_SILVER);
        copy(OTags.Blocks.STORAGE_BLOCKS_RAW_LEAD, STORAGE_BLOCKS_RAW_LEAD);

        // Vanilla
        copy(BlockTags.WALLS, ItemTags.WALLS);
        copy(BlockTags.STAIRS, ItemTags.STAIRS);
        copy(BlockTags.SLABS, ItemTags.SLABS);
        tag(ItemTags.BEACON_PAYMENT_ITEMS).add(OItems.ELECTRUM_INGOT.get());
        tag(ItemTags.MUSIC_DISCS).add(OItems.MUSIC_DISC_STRUCTURE.get());
        tag(ItemTags.TRIMMABLE_ARMOR).add(OItems.ELECTRUM_HELMET.get(), OItems.ELECTRUM_CHESTPLATE.get(), OItems.ELECTRUM_LEGGINGS.get(), OItems.ELECTRUM_BOOTS.get());
        tag(ItemTags.TRIM_MATERIALS).add(OItems.LEAD_INGOT.get(), OItems.SILVER_INGOT.get(), OItems.ELECTRUM_INGOT.get());

        // Forge
        tag(Tags.Items.NUGGETS).addTags(NUGGETS_SILVER, NUGGETS_LEAD, NUGGETS_ELECTRUM, NUGGETS_NETHERITE);
        tag(Tags.Items.INGOTS).addTags(INGOTS_SILVER, INGOTS_LEAD, INGOTS_ELECTRUM);
        tag(Tags.Items.ORES).addTags(ORES_SILVER, ORES_LEAD);
        tag(Tags.Items.STORAGE_BLOCKS).addTags(STORAGE_BLOCKS_SILVER, STORAGE_BLOCKS_LEAD, STORAGE_BLOCKS_ELECTRUM);
        tag(Tags.Items.GLASS).addTags(CRYSTAL_GLASS);
        tag(Tags.Items.GLASS_PANES).addTags(CRYSTAL_GLASS_PANES);
        tag(Tags.Items.RAW_MATERIALS).addTags(RAW_MATERIALS_SILVER, RAW_MATERIALS_LEAD);
        copy(Tags.Blocks.ORES_IN_GROUND_STONE, Tags.Items.ORES_IN_GROUND_STONE);
        copy(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE, Tags.Items.ORES_IN_GROUND_DEEPSLATE);

        tag(ItemTags.DOORS).add(OBlocks.LEAD_DOOR.get().asItem());
        tag(ItemTags.TRAPDOORS).add(OBlocks.LEAD_TRAPDOOR.get().asItem());

        tag(ItemTags.AXES).add(OItems.ELECTRUM_AXE.get());
        tag(ItemTags.PICKAXES).add(OItems.ELECTRUM_PICKAXE.get());
        tag(ItemTags.SWORDS).add(OItems.ELECTRUM_SWORD.get());
        tag(ItemTags.SHOVELS).add(OItems.ELECTRUM_SHOVEL.get());
        tag(ItemTags.HOES).add(OItems.ELECTRUM_HOE.get());
        tag(TOOLS_KNIVES).add(OItems.ELECTRUM_KNIFE.get());
        tag(TOOLS_KNIVES_FD).add(OItems.ELECTRUM_KNIFE.get());
        tag(SHIELDS).add(OItems.ELECTRUM_SHIELD.get());
        tag(SHIELDS_SE).add(OItems.ELECTRUM_SHIELD.get());
        tag(MACHETES).add(OItems.ELECTRUM_MACHETE.get());

        tag(HAS_KINETIC_DAMAGE).add(
                OItems.ELECTRUM_AXE.get(),
                OItems.ELECTRUM_HOE.get(),
                OItems.ELECTRUM_KNIFE.get(),
                OItems.ELECTRUM_MACHETE.get(),
                OItems.ELECTRUM_PICKAXE.get(),
                OItems.ELECTRUM_SHOVEL.get(),
                OItems.ELECTRUM_SWORD.get()
        );

        tag(OTags.Items.GARGOYLE_SNACK).addTags(INGOTS_SILVER);
    }
}
