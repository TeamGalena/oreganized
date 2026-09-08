package galena.oreganized.electrum.data;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.electrum.index.ElectrumBlocks;
import galena.oreganized.electrum.index.ElectrumItems;
import galena.oreganized.index.OTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

@Mod(OConstants.MOD_ID)
public class ElectrumTags {

    public ElectrumTags() {
        ODatagen.addItemTagProvider(this::items);
        ODatagen.addBlockTagProvider(this::blocks);
    }

    private void items(RegistrateItemTagsProvider provider) {
        provider.addTag(OTags.Items.INGOTS_ELECTRUM).add(ElectrumItems.ELECTRUM_INGOT.getKey());
        provider.addTag(OTags.Items.NUGGETS_ELECTRUM).add(ElectrumItems.ELECTRUM_NUGGET.getKey());

        provider.addTag(ItemTags.BEACON_PAYMENT_ITEMS).add(ElectrumItems.ELECTRUM_INGOT.getKey());

        provider.copy(OTags.Blocks.STORAGE_BLOCKS_ELECTRUM, OTags.Items.STORAGE_BLOCKS_ELECTRUM);

        provider.addTag(ItemTags.HEAD_ARMOR).add(ElectrumItems.ELECTRUM_HELMET.getKey());
        provider.addTag(ItemTags.CHEST_ARMOR).add(ElectrumItems.ELECTRUM_CHESTPLATE.getKey());
        provider.addTag(ItemTags.LEG_ARMOR).add(ElectrumItems.ELECTRUM_LEGGINGS.getKey());
        provider.addTag(ItemTags.FOOT_ARMOR).add(ElectrumItems.ELECTRUM_BOOTS.getKey());

        provider.addTag(ItemTags.TRIM_MATERIALS).add(ElectrumItems.ELECTRUM_INGOT.getKey());

        provider.addTag(ItemTags.AXES).add(ElectrumItems.ELECTRUM_AXE.getKey());
        provider.addTag(ItemTags.PICKAXES).add(ElectrumItems.ELECTRUM_PICKAXE.getKey());
        provider.addTag(ItemTags.SWORDS).add(ElectrumItems.ELECTRUM_SWORD.getKey());
        provider.addTag(ItemTags.SHOVELS).add(ElectrumItems.ELECTRUM_SHOVEL.getKey());
        provider.addTag(ItemTags.HOES).add(ElectrumItems.ELECTRUM_HOE.getKey());
        provider.addTag(OTags.Items.TOOLS_KNIVES).add(ElectrumItems.ELECTRUM_KNIFE.getKey());
        provider.addTag(OTags.Items.FORGE_TOOLS_KNIVES).add(ElectrumItems.ELECTRUM_KNIFE.getKey());
        provider.addTag(OTags.Items.SHIELDS).add(ElectrumItems.ELECTRUM_SHIELD.getKey());
        provider.addTag(OTags.Items.SHIELDS_SE).add(ElectrumItems.ELECTRUM_SHIELD.getKey());
        provider.addTag(OTags.Items.MACHETES).add(ElectrumItems.ELECTRUM_MACHETE.getKey());

        var kineticDamage = provider.addTag(OTags.Items.HAS_KINETIC_DAMAGE);
        ElectrumItems.electrumTools().forEach(it -> kineticDamage.add(it.getKey()));

        provider.addTag(Tags.Items.MELEE_WEAPON_TOOLS)
                .add(ElectrumItems.ELECTRUM_AXE.getKey())
                .add(ElectrumItems.ELECTRUM_SWORD.getKey())
                .add(ElectrumItems.ELECTRUM_MACHETE.getKey());
    }

    private void blocks(RegistrateTagsProvider.IntrinsicImpl<Block> provider) {
        provider.addTag(BlockTags.NEEDS_STONE_TOOL).add(ElectrumBlocks.ELECTRUM_BLOCK.getKey());
        provider.addTag(BlockTags.MINEABLE_WITH_PICKAXE).add(ElectrumBlocks.ELECTRUM_BLOCK.getKey());

        provider.addTag(OTags.Blocks.STORAGE_BLOCKS_ELECTRUM).add(ElectrumBlocks.ELECTRUM_BLOCK.getKey());

        provider.addTag(BlockTags.BEACON_BASE_BLOCKS).addTag(OTags.Blocks.STORAGE_BLOCKS_ELECTRUM);

        provider.addTag(OTags.Blocks.INCORRECT_FOR_ELECTRUM_TOOL).addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
    }

}
