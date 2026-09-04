package galena.oreganized.data;

import galena.oreganized.OConstants;
import galena.oreganized.index.OSoundEvents;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class OSoundDefinitions extends SoundDefinitionsProvider {

    public OSoundDefinitions(PackOutput output, ExistingFileHelper helper) {
        super(output, OConstants.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        add(OSoundEvents.MUSIC_DISC_STRUCTURE, definition().with(
                sound(OConstants.MOD_ID + ":music/disc/structure").stream()
        ));

        add(OSoundEvents.SHRAPNEL_BOMB_PRIMED, definition().with(
                sound("minecraft:random/fuse")
        ).subtitle("subtitles.entity.shrapnel_bomb.primed"));

        add(OSoundEvents.BOLT_HIT, definition().with(
                sound(OConstants.MOD_ID + ":entity/bolt_hit")
        ).subtitle("subtitles.entity.bolt_hit"));

        add(OSoundEvents.BOLT_HIT_ARMOR, definition().with(
                sound(OConstants.MOD_ID + ":entity/bolt_hit_armor")
        ).subtitle("subtitles.entity.bolt_hit_armor"));

        add(OSoundEvents.GARGOYLE_GROWL, definition().with(
                sound(OConstants.MOD_ID + ":block/gargoyle_growl_1"),
                sound(OConstants.MOD_ID + ":block/gargoyle_growl_2"),
                sound(OConstants.MOD_ID + ":block/gargoyle_growl_3")
        ).subtitle("subtitles.block.gargoyle.growl"));

        add(OSoundEvents.TARNISH, definition().with(
                sound(OConstants.MOD_ID + ":block/tarnish_1"),
                sound(OConstants.MOD_ID + ":block/tarnish_2"),
                sound(OConstants.MOD_ID + ":block/tarnish_3"),
                sound(OConstants.MOD_ID + ":block/tarnish_4"),
                sound(OConstants.MOD_ID + ":block/tarnish_5")
        ).subtitle("subtitles.block.tarnish"));

        add(OSoundEvents.POLISH, definition().with(
                sound(OConstants.MOD_ID + ":block/polish_1"),
                sound(OConstants.MOD_ID + ":block/polish_2"),
                sound(OConstants.MOD_ID + ":block/polish_3"),
                sound(OConstants.MOD_ID + ":block/polish_4")
        ).subtitle("subtitles.block.polish"));

        add(OSoundEvents.POLISH_FINISH, definition().with(
                sound(OConstants.MOD_ID + ":block/polish_finish_1"),
                sound(OConstants.MOD_ID + ":block/polish_finish_2"),
                sound(OConstants.MOD_ID + ":block/polish_finish_3"),
                sound(OConstants.MOD_ID + ":block/polish_finish_4"),
                sound(OConstants.MOD_ID + ":block/polish_finish_5")
        ));
    }
}
