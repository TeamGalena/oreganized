package galena.oreganized.glance.index;

import galena.oreganized.OConstants;
import galena.oreganized.glance.world.structure.BoulderStructure;
import galena.oreganized.register.StructureTypeRegistryHelper;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
public class GlanceStructures {

    private static final StructureTypeRegistryHelper STRUCTURES = OConstants.REGISTRY_HELPER.getStructureTypeSubHelper();

    public static final DeferredHolder<StructureType<?>, StructureType<BoulderStructure>> BOULDER = STRUCTURES.createStructureType("boulder", BoulderStructure.CODEC);

}
