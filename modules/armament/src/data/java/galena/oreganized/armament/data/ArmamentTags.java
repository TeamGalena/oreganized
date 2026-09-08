package galena.oreganized.armament.data;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import galena.oreganized.OConstants;
import galena.oreganized.armament.index.ArmamentBlocks;
import galena.oreganized.armament.index.ArmamentItems;
import galena.oreganized.data.ODatagen;
import galena.oreganized.index.OTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

@Mod(OConstants.MOD_ID)
public class ArmamentTags {

    public ArmamentTags() {
        ODatagen.addItemTagProvider(this::items);
        ODatagen.addBlockTagProvider(this::blocks);
        ODatagen.addEntityTagProvider(this::entities);
        ODatagen.addPaintingTagProvider(this::paintings);
    }

    private void items(RegistrateItemTagsProvider provider) {
        provider.addTag(Tags.Items.TOOLS).add(ArmamentItems.FLINT_AND_PEWTER.getKey());
        provider.addTag(ItemTags.DURABILITY_ENCHANTABLE).add(ArmamentItems.FLINT_AND_PEWTER.getKey());
        provider.addTag(ItemTags.VANISHING_ENCHANTABLE).add(ArmamentItems.FLINT_AND_PEWTER.getKey());

        provider.addTag(Tags.Items.STORAGE_BLOCKS)
                .add(ArmamentBlocks.LEAD_BOLT_CRATE.asItem());
    }

    private void blocks(RegistrateTagsProvider.IntrinsicImpl<Block> provider) {
        provider.addTag(Tags.Blocks.STORAGE_BLOCKS).add(ArmamentBlocks.LEAD_BOLT_CRATE.getKey());

        provider.addTag(BlockTags.MINEABLE_WITH_AXE).add(ArmamentBlocks.LEAD_BOLT_CRATE.getKey());

        provider.addTag(OTags.Blocks.BOMB_BREAKABLE).add(ArmamentBlocks.SHRAPNEL_BOMB.getKey());
        provider.addTag(OTags.Blocks.CANNON_TNTS).add(ArmamentBlocks.SHRAPNEL_BOMB.getKey());
    }

    private void entities(RegistrateTagsProvider.IntrinsicImpl<EntityType<?>> provider) {
        provider.addTag(OTags.Entities.BOLT_SUSCEPTIBLE)
                .add(EntityType.IRON_GOLEM)
                .addOptional(ResourceLocation.fromNamespaceAndPath("caverns_and_chasms", "copper_golem"));

        provider.addTag(OTags.Entities.BOLT_RESISTANT)
                .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "mimicube"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("caverns_and_chasms", "mime"));
    }

    private void paintings(RegistrateTagsProvider.IntrinsicImpl<PaintingVariant> provider) {
        provider.addTag(PaintingVariantTags.PLACEABLE).add(ArmamentPaintingVariants.VINDICATING_BAD);
    }

}
