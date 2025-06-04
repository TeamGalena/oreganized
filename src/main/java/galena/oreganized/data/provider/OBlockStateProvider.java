package galena.oreganized.data.provider;

import galena.oreganized.OreganizedCarcinogenius;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

public abstract class OBlockStateProvider extends BlockStateProvider {

    public OBlockStateProvider(PackOutput output, ExistingFileHelper help) {
        super(output, OreganizedCarcinogenius.NAMESPACE, help);
    }

    protected ResourceLocation texture(String name) {
        return modLoc(BLOCK_FOLDER + "/" + name);
    }

    protected String name(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

    protected String name(Supplier<? extends Block> block) {
        return name(block.get());
    }

    public void simpleBlock(Supplier<? extends Block> block) {
        simpleBlock(block.get());
    }

    public ModelFile cubeBottomTop(Supplier<? extends Block> block) {
        BlockModelBuilder model = models().getBuilder(name(block));
        model.parent(models().getExistingFile(new ResourceLocation("minecraft", "block" + "/cube_bottom_top")));
        model.texture("top", texture(name(block) + "_top"));
        model.texture("bottom", texture(name(block) + "_bottom"));
        model.texture("side", texture(name(block) + "_side"));
        return model;
    }

}
