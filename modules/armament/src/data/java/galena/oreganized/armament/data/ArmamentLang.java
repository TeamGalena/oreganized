package galena.oreganized.armament.data;

import static galena.oreganized.data.provider.OLangProvider.*;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import galena.oreganized.OConstants;
import galena.oreganized.armament.index.ArmamentBlocks;
import galena.oreganized.armament.index.ArmamentEntities;
import galena.oreganized.armament.index.ArmamentItems;
import galena.oreganized.armament.index.ArmamentTags;
import galena.oreganized.data.ODatagen;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ArmamentLang {

    public ArmamentLang() {
        ODatagen.addLangProvider(this::generate);
    }

    private void generate(RegistrateLangProvider provider) {
        provider.addItem(ArmamentItems.SHRAPNEL_BOMB_MINECART, "Minecart with Shrapnel Bomb");
        provider.addItem(ArmamentItems.LEAD_BOLT::get);

        provider.addBlock(ArmamentBlocks.SHRAPNEL_BOMB::get);
        provider.addBlock(ArmamentBlocks.LEAD_BOLT_CRATE, "Crate of Lead Bolts");

        provider.addItem(ArmamentItems.FLINT_AND_PEWTER, "Flint and Pewter");

        addSubtitle(provider, "entity", "shrapnel_bomb.primed", "Shrapnel Bomb fizzes");
        addSubtitle(provider, "entity", "bolt_hit", "Bolt hits");

        addDeath(provider, "lead_bolt", "%1$s was shot by %2$s");
        addDeath(provider, "lead_bolt.item", "%1$s was shot by %2$s using %3$s");

        provider.add(ArmamentTags.Entities.BOLT_RESISTANT, "Bolt Resistant");
        provider.add(ArmamentTags.Entities.BOLT_SUSCEPTIBLE, "Bolt Susceptible");

        provider.addEntityType(ArmamentEntities.LEAD_BOLT::get);
        provider.addEntityType(ArmamentEntities.SHRAPNEL_BOMB::get);
        provider.addEntityType(ArmamentEntities.SHRAPNEL_BOMB_MINECART, "Minecart with Shrapnel Bomb");

        addPainting(provider, ArmamentPaintingVariants.VINDICATING_BAD, "Vindicating Bad", "Xaidee");
    }

}
