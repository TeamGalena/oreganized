package galena.oreganized.armament.index;

import galena.oreganized.OConstants;
import galena.oreganized.armament.world.block.ShrapnelBombBlock;
import galena.oreganized.register.BlockRegistryHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredBlock;

@Mod(OConstants.MOD_ID)
@EventBusSubscriber
public class ArmamentBlocks {

    private static final BlockRegistryHelper BLOCKS = OConstants.REGISTRY_HELPER.getBlockSubHelper();

    public static final DeferredBlock<Block> LEAD_BOLT_CRATE = BLOCKS.createBlock("lead_bolt_crate",
            () -> new Block(BlockBehaviour.Properties.of().strength(1.5F).sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> SHRAPNEL_BOMB = BLOCKS.createBlock("shrapnel_bomb",
            () -> new ShrapnelBombBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TNT)));

    @SubscribeEvent
    private static void registerFlammables(FMLCommonSetupEvent event) {
        var fire = (FireBlock) Blocks.FIRE;
        fire.setFlammable(ArmamentBlocks.SHRAPNEL_BOMB.value(), 15, 100);
    }

}
