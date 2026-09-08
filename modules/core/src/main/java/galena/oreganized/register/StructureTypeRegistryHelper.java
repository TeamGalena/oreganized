package galena.oreganized.register;

import com.mojang.serialization.MapCodec;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class StructureTypeRegistryHelper extends SimpleRegistryHelper<StructureType<?>> {

    public StructureTypeRegistryHelper(RegistryHelper parent) {
        super(parent, Registries.STRUCTURE_TYPE);
    }

    public <T extends Structure> DeferredHolder<StructureType<?>, StructureType<T>> createStructureType(String name, MapCodec<T> codec) {
        return create(name, $ -> () -> codec);
    }

}
