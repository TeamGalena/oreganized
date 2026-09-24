package galena.oreganized.device.data;

import static galena.oreganized.data.extensions.OTagExtensions.tagDyed;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import galena.oreganized.OConstants;
import galena.oreganized.argentum.data.ArgentumSets;
import galena.oreganized.data.ODatagen;
import galena.oreganized.gothic.index.GothicBlocks;
import galena.oreganized.gothic.index.GothicTags;
import galena.oreganized.index.CoreTags;
import java.util.stream.Stream;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

@Mod(OConstants.MOD_ID)
public class GothicTagsGen {

    public GothicTagsGen() {
        ODatagen.addItemTagProvider(this::items);
        ODatagen.addBlockTagProvider(this::blocks);
        ODatagen.addEntityTagProvider(this::entities);
        ODatagen.addFluidTagProvider(this::fluids);
    }

    private void items(RegistrateItemTagsProvider provider) {
        provider.addTag(GothicTags.Items.GARGOYLE_SNACK).addTags(CoreTags.Items.INGOTS_SILVER);

        tagDyed(provider, GothicBlocks.CRYSTAL_GLASS, GothicTags.Items.CRYSTAL_GLASS);
        tagDyed(provider, GothicBlocks.CRYSTAL_GLASS_PANES, GothicTags.Items.CRYSTAL_GLASS_PANES);

        provider.copy(Tags.Blocks.GLASS_BLOCKS, Tags.Items.GLASS_BLOCKS);
        provider.copy(Tags.Blocks.GLASS_PANES, Tags.Items.GLASS_PANES);
    }

    private void blocks(RegistrateTagsProvider.IntrinsicImpl<Block> provider) {
        tagDyed(provider, GothicBlocks.CRYSTAL_GLASS, GothicTags.Blocks.CRYSTAL_GLASS);
        tagDyed(provider, GothicBlocks.CRYSTAL_GLASS_PANES, GothicTags.Blocks.CRYSTAL_GLASS_PANES);

        var pickaxeMinable = provider.addTag(BlockTags.MINEABLE_WITH_PICKAXE);

        GothicBlocks.DARK_GRIMSTONE_BRICKS.keys().forEach(pickaxeMinable::add);
        GothicBlocks.PALE_GRIMSTONE_BRICKS.keys().forEach(pickaxeMinable::add);
        GothicBlocks.POLISHED_DARK_GRIMSTONE.keys().forEach(pickaxeMinable::add);
        GothicBlocks.POLISHED_PALE_GRIMSTONE.keys().forEach(pickaxeMinable::add);

        pickaxeMinable.add(
                GothicBlocks.GARGOYLE.getKey(),
                GothicBlocks.DARK_GRIMSTONE_PILLAR.getKey(),
                GothicBlocks.PALE_GRIMSTONE_PILLAR.getKey(),
                GothicBlocks.CHISELED_DARK_GRIMSTONE.getKey(),
                GothicBlocks.CHISELED_PALE_GRIMSTONE.getKey(),
                GothicBlocks.DARK_GRIMSTONE_SPYRE.getKey(),
                GothicBlocks.PALE_GRIMSTONE_SPYRE.getKey(),
                GothicBlocks.DARK_GRIMSTONE_SPYRE_BLOCK.getKey(),
                GothicBlocks.PALE_GRIMSTONE_SPYRE_BLOCK.getKey(),
                GothicBlocks.DARK_GRIMSTONE_SPYRE_FENCE.getKey(),
                GothicBlocks.PALE_GRIMSTONE_SPYRE_FENCE.getKey()
        );

        provider.addTag(Tags.Blocks.GLASS_BLOCKS).addTag(GothicTags.Blocks.CRYSTAL_GLASS);
        provider.addTag(Tags.Blocks.GLASS_PANES).addTag(GothicTags.Blocks.CRYSTAL_GLASS_PANES);
        provider.addTag(BlockTags.IMPERMEABLE).addTag(GothicTags.Blocks.CRYSTAL_GLASS);

        provider.addTag(BlockTags.FENCES).add(
                GothicBlocks.DARK_GRIMSTONE_SPYRE_FENCE.getKey(),
                GothicBlocks.PALE_GRIMSTONE_SPYRE_FENCE.getKey()
        );

        Stream.of(GothicBlocks.DARK_GRIMSTONE_BRICKS, GothicBlocks.PALE_GRIMSTONE_BRICKS, GothicBlocks.POLISHED_DARK_GRIMSTONE, GothicBlocks.POLISHED_PALE_GRIMSTONE).forEach(set -> {
            provider.addTag(BlockTags.SLABS).add(set.slab().getKey());
            provider.addTag(BlockTags.STAIRS).add(set.stairs().getKey());
            provider.addTag(BlockTags.WALLS).add(set.wall().getKey());
        });

        // TODO modular needs condition
        ArgentumSets.tarnishedBlocks().forEach(set -> {
            provider.addTag(GothicTags.Blocks.PALE_SPYRE_CATALYST).add(set.base().getKey());
            provider.addTag(GothicTags.Blocks.DARK_SPYRE_CATALYST).add(set.blemished().getKey());
            provider.addTag(GothicTags.Blocks.DARK_SPYRE_CATALYST).add(set.tarnished().getKey());
        });
    }

    private void entities(RegistrateTagsProvider.IntrinsicImpl<EntityType<?>> provider) {
        provider.addTag(GothicTags.Entities.SCARED_OF_GARGOYLE).addTags(EntityTypeTags.UNDEAD);
    }

    private void fluids(RegistrateTagsProvider.IntrinsicImpl<Fluid> provider) {
        provider.addTag(GothicTags.Fluids.SPYRE_CONVERSION_FLUID).add(Fluids.WATER);
    }

}
