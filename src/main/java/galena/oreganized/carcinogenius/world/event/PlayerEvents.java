package galena.oreganized.carcinogenius.world.event;

import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import galena.oreganized.carcinogenius.index.OCEffects;
import galena.oreganized.carcinogenius.index.OCParticleTypes;
import galena.oreganized.carcinogenius.index.OCBlocks;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OreganizedCarcinogenius.MOD_ID)
public class PlayerEvents {

    @SubscribeEvent
    public static void onBlockBreak(final BlockEvent.BreakEvent event) {
        if(event.getState().getBlock() == OCBlocks.RAW_ASBESTOS_BLOCK.get() ||
                event.getState().getBlock() == OCBlocks.ASBESTOS_BLOCK.get() ||
                event.getState().getBlock() == OCBlocks.DEEPSLATE_ASBESTOS_ORE.get() ||
                event.getState().getBlock() == OCBlocks.ASBESTOS_ORE.get() ){
            var vec = Vec3.atCenterOf(event.getPos());
            Level level = event.getPlayer().level();
            var cloud = new AreaEffectCloud(level, vec.x, vec.y, vec.z);

            cloud.addEffect(new MobEffectInstance(OCEffects.LUNG_DAMAGE.get(),50));
            cloud.setParticle(OCParticleTypes.ASBESTOS_CLOUD.get());
            cloud.setRadius(4F);
            cloud.setRadiusPerTick(-0.02F);
            cloud.setDuration((int) (120));
            level.addFreshEntity(cloud);

        }
    }

}
