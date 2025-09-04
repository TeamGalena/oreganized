package galena.oreganized.carcinogenius.data.provider;

import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import java.util.function.Supplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public abstract class OBlockStateProvider extends BlockStateProvider {

    public OBlockStateProvider(PackOutput output, ExistingFileHelper help) {
        super(output, OreganizedCarcinogenius.NAMESPACE, help);
    }

    protected ResourceLocation texture(String name) {
        return modLoc(ModelProvider.BLOCK_FOLDER + "/" + name);
    }

    protected String name(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    protected String name(Supplier<? extends Block> block) {
        return name(block.get());
    }

    public void simpleBlock(Supplier<? extends Block> block) {
        simpleBlock(block.get());
    }

    public ModelFile cubeBottomTop(Supplier<? extends Block> block) {
        BlockModelBuilder model = models().getBuilder(name(block));
        model.parent(models().getExistingFile(ResourceLocation.withDefaultNamespace("block" + "/cube_bottom_top")));
        model.texture("top", texture(name(block) + "_top"));
        model.texture("bottom", texture(name(block) + "_bottom"));
        model.texture("side", texture(name(block) + "_side"));
        return model;
    }

}
