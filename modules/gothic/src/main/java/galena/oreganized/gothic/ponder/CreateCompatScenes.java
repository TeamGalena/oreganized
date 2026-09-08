package galena.oreganized.gothic.ponder;

import com.simibubi.create.content.kinetics.mechanicalArm.ArmBlockEntity;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import com.simibubi.create.infrastructure.ponder.AllCreatePonderTags;
import galena.oreganized.gothic.index.GothicBlocks;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;

public class CreateCompatScenes {


    static void useArmOnGargoyle(SceneBuilder builder, SceneBuildingUtil util, BlockPos gargoylePos) {
        var scene = new CreateSceneBuilder(builder);

        var armPos = gargoylePos.south(3);
        var depotPos = gargoylePos.west(2).south(2);
        var cog = util.select().fromTo(armPos.south().west(), armPos.south().west().below());

        scene.idle(5);
        scene.world().showSection(util.select().position(depotPos), Direction.DOWN);
        scene.idle(5);
        scene.world().showSection(util.select().position(armPos), Direction.DOWN);
        scene.idle(5);
        scene.world().showSection(cog, Direction.NORTH);

        scene.overlay().showText(70)
                .sharedText("gargoyle_automate.mechanical_arm")
                .placeNearTarget()
                .pointAt(util.vector().blockSurface(armPos, Direction.SOUTH));
        scene.idle(90);

        var ingot = GargoyleScenes.gargoyleSnack();
        scene.world().createItemOnBeltLike(depotPos, Direction.EAST, ingot);
        scene.idle(20);

        scene.world().setKineticSpeed(cog, -32);
        scene.world().setKineticSpeed(util.select().position(armPos), 64);

        scene.world().instructArm(armPos, ArmBlockEntity.Phase.MOVE_TO_INPUT, ItemStack.EMPTY, 0);
        scene.idle(24);
        scene.world().removeItemsFromBelt(depotPos);

        scene.world().instructArm(armPos, ArmBlockEntity.Phase.SEARCH_OUTPUTS, ingot, -1);
        scene.idle(20);

        scene.world().instructArm(armPos, ArmBlockEntity.Phase.MOVE_TO_OUTPUT, ingot, 0);
        scene.idle(24);
        GargoyleScenes.activateGargoyle(scene, gargoylePos);

        scene.world().instructArm(armPos, ArmBlockEntity.Phase.SEARCH_INPUTS, ItemStack.EMPTY, -1);
        scene.idle(44);
    }

    static void addTags(PonderTagRegistrationHelper<Holder<?>> helper) {
        helper.addToTag(AllCreatePonderTags.ARM_TARGETS)
                .add(GothicBlocks.GARGOYLE);
    }
}
