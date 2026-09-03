package galena.oreganized.gothic;

import galena.oreganized.OConstants;
import galena.oreganized.gothic.index.*;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

@Mod(OConstants.MOD_ID)
@EventBusSubscriber
public class GothicModule {

    public GothicModule() {
        GothicBlocks.register();
        GothicSounds.register();
        GothicBlockEntities.register();
        GothicParticles.register();
        GothicCriterionTriggers.register();
    }

    @SubscribeEvent
    public static void injectVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.MASON) {
            event.getTrades().get(5).add(new BasicItemListing(14, new ItemStack(GothicBlocks.GARGOYLE.get()), 5, 30, 0.05F));
        }
    }

}
