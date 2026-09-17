package galena.oreganized.electrum.data;

import static galena.oreganized.data.extensions.OTagExtensions.*;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.electrum.index.ElectrumBlocks;
import galena.oreganized.electrum.index.ElectrumItems;
import galena.oreganized.electrum.index.ElectrumTags;
import galena.oreganized.index.CoreTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

@Mod(OConstants.MOD_ID)
public class ElectrumTagsGen {

    public ElectrumTagsGen() {
        ODatagen.addItemTagProvider(this::items);
        ODatagen.addBlockTagProvider(this::blocks);
    }

    private void items(RegistrateItemTagsProvider provider) {
        provider.addTag(CoreTags.Items.INGOTS_ELECTRUM).add(ElectrumItems.ELECTRUM_INGOT.getKey());
        provider.addTag(CoreTags.Items.NUGGETS_ELECTRUM).add(ElectrumItems.ELECTRUM_NUGGET.getKey());

        provider.addTag(ItemTags.BEACON_PAYMENT_ITEMS).add(ElectrumItems.ELECTRUM_INGOT.getKey());

        provider.copy(CoreTags.Blocks.STORAGE_BLOCKS_ELECTRUM, CoreTags.Items.STORAGE_BLOCKS_ELECTRUM);

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
        tagKnife(provider, ElectrumItems.ELECTRUM_KNIFE);
        tagShield(provider, ElectrumItems.ELECTRUM_SHIELD);
        tagMachete(provider, ElectrumItems.ELECTRUM_MACHETE);

        var kineticDamage = provider.addTag(ElectrumTags.Items.HAS_KINETIC_DAMAGE);
        ElectrumSets.electrumTools().forEach(it -> kineticDamage.add(it.getKey()));

        provider.addTag(Tags.Items.MELEE_WEAPON_TOOLS)
                .add(ElectrumItems.ELECTRUM_AXE.getKey())
                .add(ElectrumItems.ELECTRUM_SWORD.getKey())
                .add(ElectrumItems.ELECTRUM_MACHETE.getKey());
    }

    private void blocks(RegistrateTagsProvider.IntrinsicImpl<Block> provider) {
        provider.addTag(BlockTags.NEEDS_STONE_TOOL).add(ElectrumBlocks.ELECTRUM_BLOCK.getKey());
        provider.addTag(BlockTags.MINEABLE_WITH_PICKAXE).add(ElectrumBlocks.ELECTRUM_BLOCK.getKey());

        provider.addTag(CoreTags.Blocks.STORAGE_BLOCKS_ELECTRUM).add(ElectrumBlocks.ELECTRUM_BLOCK.getKey());

        provider.addTag(BlockTags.BEACON_BASE_BLOCKS).addTag(CoreTags.Blocks.STORAGE_BLOCKS_ELECTRUM);

        provider.addTag(ElectrumTags.Blocks.INCORRECT_FOR_ELECTRUM_TOOL).addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
    }

}
