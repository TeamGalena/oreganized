package galena.oreganized.compat.ponder;

import galena.oreganized.OConstants;
import galena.oreganized.ponder.OPonderPlugin;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Stream;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.createmod.ponder.api.registration.SharedTextRegistrationHelper;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;

public class OreganizedPonderPlugin implements PonderPlugin {

    private final Collection<OPonderPlugin> subPlugins;

    OreganizedPonderPlugin(Stream<OPonderPlugin> subPlugins) {
        this.subPlugins = subPlugins.toList();
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        var holderHelper = helper.withKeyFunction(byHolder());
        subPlugins.forEach(it -> it.registerScenes(holderHelper));
    }

    @Override
    public void registerTags(PonderTagRegistrationHelper<ResourceLocation> helper) {
        var holderHelper = helper.withKeyFunction(byHolder());
        subPlugins.forEach(it -> it.registerTags(holderHelper));
    }

    @Override
    public void registerSharedText(SharedTextRegistrationHelper helper) {
        subPlugins.forEach(it -> it.registerSharedText(helper));
    }

    @Override
    public String getModId() {
        return OConstants.MOD_ID;
    }

    private static Function<Holder<?>, ResourceLocation> byHolder() {
        return it -> it.getKey().location();
    }

}
