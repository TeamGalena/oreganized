package galena.oreganized.index;

import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import galena.oreganized.Oreganized;
import galena.oreganized.content.block.HeavyDoorBlockEntity;
import galena.oreganized.content.entity.GargoyleBlockEntity;
import java.util.Set;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Oreganized.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OBlockEntities {
    public static final BlockEntitySubRegistryHelper HELPER = Oreganized.REGISTRY_HELPER.getBlockEntitySubHelper();

    public static final RegistryObject<BlockEntityType<GargoyleBlockEntity>> GARGOYLE = HELPER.createBlockEntity("gargoyle", GargoyleBlockEntity::new, () -> Set.of(OBlocks.GARGOYLE.get()));
    public static final RegistryObject<BlockEntityType<HeavyDoorBlockEntity>> HEAVY_DOOR = HELPER.createBlockEntity("heavy_door", HeavyDoorBlockEntity::new, () -> Set.of(OBlocks.LEAD_DOOR.get(), OBlocks.LEAD_TRAPDOOR.get()));

}
