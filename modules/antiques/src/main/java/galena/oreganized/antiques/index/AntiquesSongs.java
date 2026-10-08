package galena.oreganized.antiques.index;

import galena.oreganized.OConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;

public class AntiquesSongs {

    public static final ResourceKey<JukeboxSong> RADIO = ResourceKey.create(Registries.JUKEBOX_SONG, OConstants.modLoc("radio"));

}
