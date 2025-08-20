package galena.oreganized.carcinogenius.index;

import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class OCTags {

    public static final TagKey<Block> CREATES_ASBESTOS_CLOUD = TagKey.create(Registries.BLOCK, OreganizedCarcinogenius.modLoc("creates_asbestos_cloud"));

}
