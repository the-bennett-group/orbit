package the.bennett.group.orbit;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import the.bennett.group.orbit.blocks.OrbitBlocks;
import the.bennett.group.orbit.blocks.fluid.OrbitFluids;
import the.bennett.group.orbit.items.OrbitItems;
import the.bennett.group.orbit.items.tabs.OrbitTabs;
import the.bennett.group.orbit.rules.OrbitRules;
import the.bennett.group.orbit.tags.OrbitTags;
import the.bennett.group.orbit.util.ContentCounter;
import the.bennett.group.orbit.world.feature.OrbitFeatures;
import the.bennett.group.orbit.world.gen.BaseOrbitChunkGenerator;
import the.bennett.group.orbit.world.gen.OrbitRuleSources;

public class Orbit implements ModInitializer {
    public static final String MOD_ID = "orbit";
    public static final String MOD_NAME = "Orbit";

    public static Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize(ModContainer mod) {
        ContentCounter.initialize();
        OrbitRules.initialize();
        OrbitTags.initialize();
        OrbitFluids.initialize();
        OrbitBlocks.initialize();
        OrbitTabs.initialize();
        OrbitItems.initialize();
        OrbitFeatures.initialize();
        BaseOrbitChunkGenerator.initialize();
        OrbitRuleSources.initialize();

        if(QuiltLoader.isDevelopmentEnvironment()) {
            log(ContentCounter.report());
        }
    }

    public static ResourceLocation newId(String name) {
        return new ResourceLocation(Orbit.MOD_ID, name);
    }

    public static void log(String message) {LOGGER.info(message);}


}
