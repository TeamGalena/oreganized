package galena.oreganized.index;

import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import galena.oreganized.Oreganized;
import galena.oreganized.content.block.HeavyDoorBlockEntity;
import galena.oreganized.content.entity.GargoyleBlockEntity;
import java.util.Set;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class OBlockEntities {
    public static final BlockEntitySubRegistryHelper HELPER = Oreganized.REGISTRY_HELPER.getBlockEntitySubHelper();

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GargoyleBlockEntity>> GARGOYLE = HELPER.createBlockEntity("gargoyle", GargoyleBlockEntity::new, () -> Set.of(OBlocks.GARGOYLE.get()));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HeavyDoorBlockEntity>> HEAVY_DOOR = HELPER.createBlockEntity("heavy_door", HeavyDoorBlockEntity::new, () -> Set.of(OBlocks.LEAD_DOOR.get(), OBlocks.LEAD_TRAPDOOR.get()));

    public static void register() {
        // Load this class
    }

}
