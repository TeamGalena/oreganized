package galena.oreganized.data;

import galena.oreganized.Oreganized;
import galena.oreganized.index.OSoundEvents;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class OSoundDefinitions extends SoundDefinitionsProvider {

    public OSoundDefinitions(PackOutput output, ExistingFileHelper helper) {
        super(output, Oreganized.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        add(OSoundEvents.MUSIC_DISC_STRUCTURE, definition().with(
                sound(Oreganized.MOD_ID + ":music/disc/structure").stream()
        ));

        add(OSoundEvents.SHRAPNEL_BOMB_PRIMED, definition().with(
                sound("minecraft:random/fuse")
        ).subtitle("subtitles.entity.shrapnel_bomb.primed"));

        add(OSoundEvents.BOLT_HIT,  definition().with(
                sound(Oreganized.MOD_ID + ":entity/bolt_hit")
        ).subtitle("subtitles.entity.bolt_hit"));

        add(OSoundEvents.BOLT_HIT_ARMOR,  definition().with(
                sound(Oreganized.MOD_ID + ":entity/bolt_hit_armor")
        ).subtitle("subtitles.entity.bolt_hit_armor"));

        add(OSoundEvents.GARGOYLE_GROWL, definition().with(
                sound(Oreganized.MOD_ID + ":block/gargoyle_growl_1"),
                sound(Oreganized.MOD_ID + ":block/gargoyle_growl_2"),
                sound(Oreganized.MOD_ID + ":block/gargoyle_growl_3")
        ).subtitle("subtitles.block.gargoyle.growl"));
    }
}