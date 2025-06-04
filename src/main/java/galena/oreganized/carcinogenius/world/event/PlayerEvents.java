package galena.oreganized.carcinogenius.world.event;

import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import galena.oreganized.carcinogenius.index.OEffects;
import galena.oreganized.carcinogenius.index.OParticleTypes;
import galena.oreganized.content.entity.GargoyleBlockEntity;
import galena.oreganized.content.item.ScribeItem;
import galena.oreganized.index.*;
import galena.oreganized.carcinogenius.index.OBlocks;

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
        if(event.getState().getBlock() == OBlocks.RAW_ASBESTOS_BLOCK.get() ||
                event.getState().getBlock() == OBlocks.ASBESTOS_BLOCK.get() ||
                event.getState().getBlock() == OBlocks.DEEPSLATE_ASBESTOS_ORE.get() ||
                event.getState().getBlock() == OBlocks.ASBESTOS_ORE.get() ){
            var vec = Vec3.atCenterOf(event.getPos());
            Level level = event.getPlayer().level();
            var cloud = new AreaEffectCloud(level, vec.x, vec.y, vec.z);

            cloud.addEffect(new MobEffectInstance(OEffects.LUNG_DAMAGE.get(),50));
            cloud.setParticle(OParticleTypes.ASBESTOS_CLOUD.get());
            cloud.setRadius(4F);
            cloud.setRadiusPerTick(-0.02F);
            cloud.setDuration((int) (120));
            level.addFreshEntity(cloud);

        }
    }

}
