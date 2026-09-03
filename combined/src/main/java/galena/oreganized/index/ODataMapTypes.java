package galena.oreganized.index;

import galena.oreganized.OConstants;
import galena.oreganized.api.Tarnishable;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

@EventBusSubscriber
public class ODataMapTypes {

    public static final DataMapType<Block, Tarnishable> TARNISHABLES = DataMapType.builder(
            OConstants.modLoc("tarnishables"),
            Registries.BLOCK,
            Tarnishable.CODEC
    ).build();

    @SubscribeEvent
    public static void registerDataMapTypes(RegisterDataMapTypesEvent event) {
        event.register(TARNISHABLES);
    }

}
