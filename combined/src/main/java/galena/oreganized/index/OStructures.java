package galena.oreganized.index;

import com.mojang.serialization.MapCodec;
import galena.oreganized.Oreganized;
import galena.oreganized.world.gen.structure.BoulderStructure;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class OStructures {

    private static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, Oreganized.MOD_ID);

    public static final DeferredHolder<StructureType<?>, StructureType<BoulderStructure>> BOULDER = STRUCTURES.register("boulder", () -> explicitStructureTypeTyping(BoulderStructure.CODEC));

    private static <T extends Structure> StructureType<T> explicitStructureTypeTyping(MapCodec<T> structureCodec) {
        return () -> structureCodec;
    }

    public static void register(IEventBus modBus) {
        STRUCTURES.register(modBus);
    }

}