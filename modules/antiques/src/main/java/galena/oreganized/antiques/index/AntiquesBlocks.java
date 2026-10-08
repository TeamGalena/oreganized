package galena.oreganized.antiques.index;

import galena.oreganized.OConstants;
import galena.oreganized.antiques.world.block.AntiquesPileBlock;
import galena.oreganized.argentum.index.ArgentumSounds;
import galena.oreganized.register.BlockRegistryHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;

@Mod(OConstants.MOD_ID)
public class AntiquesBlocks {

    private static final BlockRegistryHelper BLOCKS = OConstants.REGISTRY_HELPER.getBlockSubHelper();

    public static final DeferredBlock<Block> ANTIQUES_PILE = BLOCKS.createBlock("antiques_pile",
            () -> new AntiquesPileBlock(BlockBehaviour.Properties.of().sound(ArgentumSounds.SILVER).noOcclusion().strength(0.2F)));

}
