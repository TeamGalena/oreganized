package galena.oreganized.index;

import com.mojang.serialization.Codec;
import galena.oreganized.Oreganized;
import galena.oreganized.world.gen.structure.BoulderStructure;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class OStructures {

    private static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, Oreganized.MOD_ID);

    public static final RegistryObject<StructureType<BoulderStructure>> BOULDER = STRUCTURES.register("boulder", () -> explicitStructureTypeTyping(BoulderStructure.CODEC));

    private static <T extends Structure> StructureType<T> explicitStructureTypeTyping(Codec<T> structureCodec) {
        return () -> structureCodec;
    }

    public static void register(IEventBus modBus) {
        STRUCTURES.register(modBus);
    }
}