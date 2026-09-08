package galena.oreganized.armament.index;

import com.teamabnormals.blueprint.core.util.DataUtil;
import galena.oreganized.OConstants;
import java.util.stream.Stream;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber
public class ArmamentStructures {

    @SubscribeEvent
    private static void registerJigsawEntries(FMLCommonSetupEvent event) {
        Stream.of("lead_bolt_crates1", "lead_bolt_crates2").forEach(name -> {
            DataUtil.addToJigsawPattern(ResourceLocation.withDefaultNamespace("pillager_outpost/features"), $ -> {
                return StructurePoolElement.legacy(OConstants.MOD_ID + ":pillager_outpost/" + name).apply(StructureTemplatePool.Projection.RIGID);
            }, 1);
        });
    }

}
