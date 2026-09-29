package galena.oreganized.ponder;

import net.createmod.catnip.animation.LerpedFloat.Chaser;
import net.createmod.ponder.foundation.PonderScene;
import net.createmod.ponder.foundation.PonderScene.SceneTransform;
import net.createmod.ponder.foundation.instruction.PonderInstruction;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class SpinSceneInstruction extends PonderInstruction {

    private final float speed;

    public SpinSceneInstruction(float speed) {
        this.speed = speed;
	}

	@Override
	public boolean isComplete() {
		return true;
	}

	@Override
	public void tick(PonderScene scene) {
		SceneTransform transform = scene.getTransform();
		float targetY =  transform.yRotation.getChaseTarget() + 360;
		transform.yRotation.chase(targetY, speed, Chaser.LINEAR);
	}

}
