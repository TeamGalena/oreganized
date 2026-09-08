package galena.oreganized.plumbum.index;

import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import galena.oreganized.OConstants;
import galena.oreganized.plumbum.world.block.PushableBlockEntity;
import java.util.Set;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
public class PlumbumBlockEntities {

    private static final BlockEntitySubRegistryHelper BLOCK_ENTITIES = OConstants.REGISTRY_HELPER.getBlockEntitySubHelper();

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PushableBlockEntity>> PUSHABLE = BLOCK_ENTITIES.createBlockEntity("pushable", PushableBlockEntity::new, () ->
            Set.of(PlumbumBlocks.LEAD_DOOR.get(), PlumbumBlocks.LEAD_TRAPDOOR.get(), PlumbumBlocks.STURDY_LEVER.get(), PlumbumBlocks.STURDY_BUTTON.get()));



}
