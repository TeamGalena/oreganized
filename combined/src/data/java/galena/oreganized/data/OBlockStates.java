package galena.oreganized.data;

import galena.oreganized.compat.ColorCompat;
import galena.oreganized.data.provider.OBlockStateProvider;
import galena.oreganized.index.OBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class OBlockStates extends OBlockStateProvider {

    public OBlockStates(PackOutput output, ExistingFileHelper helper) {
        super(output, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        block(OBlocks.GLANCE);
        block(OBlocks.POLISHED_GLANCE);
        block(OBlocks.GLANCE_BRICKS);
        block(OBlocks.CHISELED_GLANCE);
        slabBlock(OBlocks.GLANCE.get(), OBlocks.GLANCE_SLAB.get());
        slabBlock(OBlocks.POLISHED_GLANCE.get(), OBlocks.POLISHED_GLANCE_SLAB.get());
        slabBlock(OBlocks.GLANCE_BRICKS.get(), OBlocks.GLANCE_BRICK_SLAB.get());
        stairsBlock(OBlocks.GLANCE.get(), OBlocks.GLANCE_STAIRS.get());
        stairsBlock(OBlocks.POLISHED_GLANCE.get(), OBlocks.POLISHED_GLANCE_STAIRS.get());
        stairsBlock(OBlocks.GLANCE_BRICKS.get(), OBlocks.GLANCE_BRICK_STAIRS.get());
        wallBlock(OBlocks.GLANCE.get(), OBlocks.GLANCE_WALL.get());
        wallBlock(OBlocks.GLANCE_BRICKS.get(), OBlocks.GLANCE_BRICK_WALL.get());
        block(OBlocks.SPOTTED_GLANCE);
        waxedBlock(OBlocks.WAXED_SPOTTED_GLANCE, OBlocks.SPOTTED_GLANCE.get());
        block(OBlocks.SILVER_ORE);
        block(OBlocks.DEEPSLATE_SILVER_ORE);
        block(OBlocks.LEAD_ORE);
        block(OBlocks.DEEPSLATE_LEAD_ORE);
        block(OBlocks.RAW_SILVER_BLOCK);
        block(OBlocks.RAW_LEAD_BLOCK);
        meltableBlock(OBlocks.LEAD_BLOCK, (n, t) -> models().cubeAll(n, t));
        meltableBlock(OBlocks.LEAD_BRICKS, (n, t) -> models().cubeAll(n, t));
        meltablePillar(OBlocks.LEAD_PILLAR);
        meltablePillar(OBlocks.CUT_LEAD);
        meltableLamp(OBlocks.LEAD_BULB);
        block(OBlocks.ELECTRUM_BLOCK);
        cubeBottomTopBlock(OBlocks.SHRAPNEL_BOMB);

        meltableDoor(OBlocks.LEAD_DOOR);
        meltableTrapdoor(OBlocks.LEAD_TRAPDOOR);
        meltableBars(OBlocks.LEAD_BARS);

        sturdyLever(OBlocks.STURDY_LEVER);
        sturdyButton(OBlocks.STURDY_BUTTON);

        OBlocks.WAXED_CONCRETE_POWDER.forEach((color, block) -> {
            var unwaxed = ColorCompat.getColoredBlock("concrete_powder", color);
            waxedBlock(block, unwaxed);
        });

        cubeBottomTopBlock(OBlocks.LEAD_BOLT_CRATE);

        moltenCauldron(OBlocks.MOLTEN_LEAD_CAULDRON, OBlocks.LEAD_BLOCK);

        OBlocks.CRYSTAL_GLASS.forEach((color, block) -> crystalGlassBlock(block));
        OBlocks.CRYSTAL_GLASS_PANES.forEach((color, block) -> crystalGlassPaneBlock(color, block, OBlocks.CRYSTAL_GLASS.get(color)));

        block(OBlocks.GROOVED_ICE);
        block(OBlocks.GROOVED_PACKED_ICE);
        block(OBlocks.GROOVED_BLUE_ICE);

        gargoyleBlock(OBlocks.GARGOYLE);

        crossBlockWithPot(OBlocks.PURPLE_DATURA, OBlocks.POTTED_PURPLE_DATURA);
        crossBlockWithPot(OBlocks.WHITE_DATURA, OBlocks.POTTED_WHITE_DATURA);

        OBlocks.SILVER_BULBS.all().forEach(it -> lamp(it.get()));
        OBlocks.CUT_SILVERS.all().forEach(this::block);
        OBlocks.SILVER_LATTICES.all().forEach(this::block);
        OBlocks.SILVER_BLOCKS.all().forEach(this::block);
        OBlocks.SILVER_BARS.all().forEach(this::ironBarsBlock);
        OBlocks.SILVER_PILLARS.all().forEach(this::logBlock);
        OBlocks.CHISELED_SILVER.all().forEach(this::block);
        OBlocks.CUT_SILVER_SLABS.indexed().forEach(it -> slabBlock(OBlocks.CUT_SILVERS.get(it.getSecond()).get(), it.getFirst().get()));
        OBlocks.CUT_SILVER_STAIRS.indexed().forEach(it -> stairsBlock(OBlocks.CUT_SILVERS.get(it.getSecond()).get(), it.getFirst().get()));
        OBlocks.SILVER_DOORS.all().forEach(it -> doorBlock(it.get()));
        OBlocks.SILVER_TRAPDOORS.all().forEach(it -> trapDoorBlock(it.get()));
    }

}
