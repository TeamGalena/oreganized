package galena.oreganized.index;

import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import galena.oreganized.OConstants;
import galena.oreganized.gothic.index.GothicBlockEntities;
import galena.oreganized.gothic.world.block.entity.GargoyleBlockEntity;
import galena.oreganized.plumbum.index.PlumbumBlockEntities;
import galena.oreganized.plumbum.world.block.PushableBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class OBlockEntities {
    private static final BlockEntitySubRegistryHelper HELPER = OConstants.REGISTRY_HELPER.getBlockEntitySubHelper();

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GargoyleBlockEntity>> GARGOYLE = GothicBlockEntities.GARGOYLE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PushableBlockEntity>> PUSHABLE = PlumbumBlockEntities.PUSHABLE;

}
