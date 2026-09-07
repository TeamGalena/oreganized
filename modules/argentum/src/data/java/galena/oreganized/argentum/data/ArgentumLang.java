package galena.oreganized.argentum.data;

import static galena.oreganized.data.provider.OLangProvider.addAttribute;
import static galena.oreganized.data.provider.OLangProvider.addSubtitle;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import galena.oreganized.OConstants;
import galena.oreganized.argentum.index.ArgentumAttributes;
import galena.oreganized.argentum.index.ArgentumBlocks;
import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.argentum.index.TarnishedBlocks;
import galena.oreganized.data.provider.ODatagen;
import galena.oreganized.index.OTags;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.LanguageProvider;

@Mod(OConstants.MOD_ID)
public class ArgentumLang {

    public ArgentumLang() {
        ODatagen.addLangProvider(this::generate);
    }

    private void generate(RegistrateLangProvider provider) {
        provider.addItem(ArgentumItems.SILVER_INGOT::get);
        provider.addItem(ArgentumItems.SILVER_NUGGET::get);
        provider.addItem(ArgentumItems.RAW_SILVER::get);
        ArgentumItems.silverArmor().forEach(it -> provider.addItem(it::get));
        ArgentumItems.silverTools().forEach(it -> provider.addItem(it::get));
        provider.addItem(ArgentumItems.SILVER_MIRROR::get);
        provider.addItem(ArgentumItems.SCRIBE::get);

        provider.addBlock(ArgentumBlocks.SILVER_ORE::get);
        provider.addBlock(ArgentumBlocks.DEEPSLATE_SILVER_ORE::get);
        provider.addBlock(ArgentumBlocks.RAW_SILVER_BLOCK, "Block of Raw Silver");
        provider.addBlock(ArgentumBlocks.SILVER_BLOCKS.base(), "Block of Silver");
        provider.addBlock(ArgentumBlocks.SILVER_BLOCKS.blemished(), "Block of Blemished Silver");
        provider.addBlock(ArgentumBlocks.SILVER_BLOCKS.tarnished(), "Block of Tarnished Silver");
        addTarnished(provider, ArgentumBlocks.SILVER_PILLARS, "Silver Pillar");
        addTarnished(provider, ArgentumBlocks.CHISELED_SILVER, "Chiseled Silver");
        addTarnished(provider, ArgentumBlocks.SILVER_BARS, "Silver Bars");
        addTarnished(provider, ArgentumBlocks.SILVER_BULBS, "Silver Bulb");
        addTarnished(provider, ArgentumBlocks.CUT_SILVERS, "Cut Silver");
        addTarnished(provider, ArgentumBlocks.SILVER_LATTICES, "Silver Lattice");
        addTarnished(provider, ArgentumBlocks.CUT_SILVER_STAIRS, "Cut Silver Stairs");
        addTarnished(provider, ArgentumBlocks.CUT_SILVER_SLABS, "Cut Silver Slabs");
        addTarnished(provider, ArgentumBlocks.SILVER_DOORS, "Silver Door");
        addTarnished(provider, ArgentumBlocks.SILVER_TRAPDOORS, "Silver Door");
        provider.addBlock(ArgentumBlocks.GROOVED_ICE::get);
        provider.addBlock(ArgentumBlocks.GROOVED_PACKED_ICE::get);
        provider.addBlock(ArgentumBlocks.GROOVED_BLUE_ICE::get);

        provider.add("trim_material.oreganized.silver", "Silver material");

        addSubtitle(provider, "block", "tarnish", "Something tarnishes");
        addSubtitle(provider, "block", "polish", "Polishing");

        addAttribute(provider, ArgentumAttributes.INVINCIBILITY_FRAMES, "Invincibility");

        provider.add(OTags.Items.ENCHANTED_BOOK_LIKE, "Enchanted Books");
        provider.add(OTags.Items.NO_ANVIL_ENCHANT_COST, "Free Anvil Enchantables");

        provider.add(OTags.Blocks.INCORRECT_FOR_SILVER_TOOL, "Incorrect for Silver Tools");
        provider.add(OTags.Blocks.MINEABLE_WITH_SCRIBE, "Mineable with Scribe");
        provider.add(OTags.Blocks.SILKTOUCH_WITH_SCRIBE, "Silktouch-able with Scibe");
        provider.add(OTags.Blocks.SILKTOUCH_WITH_SCRIBE_BLACKLIST, "Not Silktouch-able with Scibe");

        provider.add(OTags.Entities.TARNISHABLE, "Can Tarnish");
    }

    private void addTarnished(LanguageProvider provider, TarnishedBlocks<?> blocks, String pristine) {
        provider.addBlock(blocks.base(), pristine);
        provider.addBlock(blocks.blemished(), "Blemished " + pristine);
        provider.addBlock(blocks.tarnished(), "Tarnished " + pristine);
    }

}
