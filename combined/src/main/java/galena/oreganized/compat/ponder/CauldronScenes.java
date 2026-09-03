package galena.oreganized.compat.ponder;

import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OItems;
import galena.oreganized.plumbum.world.block.MoltenLeadCauldronBlock;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class CauldronScenes {

    static void registerScenes(PonderSceneRegistrationHelper<Holder<?>> registrar) {
        registrar
                .forComponents(Items.CAULDRON.builtInRegistryHolder(), OItems.MOLTEN_LEAD_BUCKET)
                .addStoryBoard("cauldron_lead_melting", CauldronScenes::leadMelting);
    }

    private static void leadMelting(SceneBuilder scene, SceneBuildingUtil util) {
        var cauldronPos = util.grid().at(1, 1, 1);
        var campfirePos = cauldronPos.below();

        scene.title("cauldron_lead_melting", "Lead Melting");
        scene.configureBasePlate(0, 0, 3);
        scene.showBasePlate();
        scene.idle(5);

        scene.world().showSection(util.select().position(cauldronPos), Direction.DOWN);
        scene.idle(10);

        scene.overlay().showControls(util.vector().topOf(cauldronPos), Pointing.DOWN, 50)
                .rightClick()
                .withItem(OBlocks.LEAD_BLOCK.toStack());
        setCauldronAge(scene, cauldronPos, 0);

        scene.overlay().showText(60)
                .text("You can melt a lead block in a cauldron by heating it up with a campfire")
                .placeNearTarget()
                .pointAt(util.vector().blockSurface(cauldronPos, Direction.WEST));
        scene.idle(80);

        scene.world().setBlock(campfirePos, Blocks.CAMPFIRE.defaultBlockState(), false);
        scene.idle(20);

        setCauldronAge(scene, cauldronPos, 1);
        scene.idle(20);

        setCauldronAge(scene, cauldronPos, 2);
        scene.idle(20);

        setCauldronAge(scene, cauldronPos, 3);
        scene.idle(20);
        scene.addKeyframe();

        scene.overlay().showControls(util.vector().topOf(cauldronPos), Pointing.DOWN, 50)
                .rightClick()
                .withItem(new ItemStack(Items.BUCKET));
        scene.world().setBlock(cauldronPos, Blocks.CAULDRON.defaultBlockState(), false);

        scene.overlay().showText(50)
                .text("You can then take out the molten lead using a bucket")
                .placeNearTarget()
                .pointAt(util.vector().blockSurface(cauldronPos, Direction.WEST));
        scene.idle(50);

        scene.markAsFinished();
    }

    private static void setCauldronAge(SceneBuilder scene, BlockPos cauldronPos, int age) {
        scene.world().setBlock(
                cauldronPos,
                OBlocks.MOLTEN_LEAD_CAULDRON.get().defaultBlockState().setValue(MoltenLeadCauldronBlock.AGE, age),
                false
        );
    }

}
