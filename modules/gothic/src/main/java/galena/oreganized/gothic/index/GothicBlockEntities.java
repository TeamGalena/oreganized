package galena.oreganized.gothic.index;

import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import galena.oreganized.OConstants;
import galena.oreganized.gothic.world.block.entity.GargoyleBlockEntity;
import java.util.Set;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
public class GothicBlockEntities {

    private static final BlockEntitySubRegistryHelper BLOCK_ENTITIES = OConstants.REGISTRY_HELPER.getBlockEntitySubHelper();

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GargoyleBlockEntity>> GARGOYLE = BLOCK_ENTITIES.createBlockEntity("gargoyle",
            GargoyleBlockEntity::new, () -> Set.of(GothicBlocks.GARGOYLE.get()));


}
