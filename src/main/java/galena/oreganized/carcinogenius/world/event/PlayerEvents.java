package galena.oreganized.carcinogenius.world.event;

import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import galena.oreganized.carcinogenius.content.AsbestosCloud;
import galena.oreganized.carcinogenius.index.OCTags;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OreganizedCarcinogenius.MOD_ID)
public class PlayerEvents {

    @SubscribeEvent
    public static void onBlockBreak(final BlockEvent.BreakEvent event) {
        if (event.getState().is(OCTags.CREATES_ASBESTOS_CLOUD)) {
            AsbestosCloud.create(event.getPos(), event.getPlayer().level(), 4F, 0.02F, 120);
        }
    }

}
