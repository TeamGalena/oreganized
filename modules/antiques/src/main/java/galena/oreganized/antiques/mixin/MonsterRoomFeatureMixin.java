package galena.oreganized.antiques.mixin;

import galena.oreganized.antiques.index.AntiquesBlocks;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.MonsterRoomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MonsterRoomFeature.class)
public class MonsterRoomFeatureMixin {

    @Inject(
            method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
            at = @At("RETURN")
    )
    public void placeAntiques(FeaturePlaceContext<NoneFeatureConfiguration> context, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) return;

        var level = context.level();
        var origin = context.origin();
        var random = context.random();

        var amount = random.nextIntBetweenInclusive(2, 5);
        for (int i = 0; i < amount; i++) {
            var direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            var direction2 = direction.getClockWise();
            var pos = origin.relative(direction, 3).relative(direction2, random.nextIntBetweenInclusive(-2, 2));
            if (level.getBlockState(pos).isAir()) {
                level.setBlock(pos, AntiquesBlocks.ANTIQUES_PILE.value().defaultBlockState(), 2);
            }
        }
    }

}
