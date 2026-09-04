package galena.oreganized.argentum.data;

import galena.oreganized.OConstants;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber
public class ArgentumData {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        OConstants.LOGGER.info("Argentum Datagen");
    }

}
