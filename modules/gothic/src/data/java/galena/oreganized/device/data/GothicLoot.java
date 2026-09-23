package galena.oreganized.device.data;


import static galena.oreganized.data.ConditionalData.dyed;
import static galena.oreganized.data.extensions.OBlockLootExtensions.*;

import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.gothic.index.GothicBlocks;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class GothicLoot {

    public GothicLoot() {
        ODatagen.addBlockLootProvider(this::generate);
    }

    private void generate(RegistrateBlockLootTables provider) {
        dropSelf(provider, GothicBlocks.GARGOYLE);

        GothicBlocks.CRYSTAL_GLASS.forEach((c, b) -> dyed(c, provider, () -> dropAsSilk(provider, b)));
        GothicBlocks.CRYSTAL_GLASS_PANES.forEach((c, b) -> dyed(c, provider, () -> dropAsSilk(provider, b)));

        dropSelf(provider, GothicBlocks.DARK_GRIMSTONE_BRICKS);
        dropSelf(provider, GothicBlocks.PALE_GRIMSTONE_BRICKS);
        dropSelf(provider, GothicBlocks.POLISHED_DARK_GRIMSTONE);
        dropSelf(provider, GothicBlocks.POLISHED_PALE_GRIMSTONE);
        dropSelf(provider, GothicBlocks.DARK_GRIMSTONE_PILLAR);
        dropSelf(provider, GothicBlocks.PALE_GRIMSTONE_PILLAR);
        dropSelf(provider, GothicBlocks.CHISELED_DARK_GRIMSTONE);
        dropSelf(provider, GothicBlocks.CHISELED_PALE_GRIMSTONE);
        dropSlab(provider, GothicBlocks.DARK_GRIMSTONE_SLAB);
        dropSlab(provider, GothicBlocks.PALE_GRIMSTONE_SLAB);
        dropSelf(provider, GothicBlocks.DARK_GRIMSTONE_SPYRE);
        dropSelf(provider, GothicBlocks.PALE_GRIMSTONE_SPYRE);
        dropSelf(provider, GothicBlocks.DARK_GRIMSTONE_SPYRE_BLOCK);
        dropSelf(provider, GothicBlocks.PALE_GRIMSTONE_SPYRE_BLOCK);
        dropSelf(provider, GothicBlocks.DARK_GRIMSTONE_SPYRE_FENCE);
        dropSelf(provider, GothicBlocks.PALE_GRIMSTONE_SPYRE_FENCE);
    }

}
