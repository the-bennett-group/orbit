package bennett.orbit;

import bennett.orbit.blocks.OrbitBlocks;
import bennett.orbit.fluid.OrbitFluids;
import bennett.orbit.items.OrbitItems;
import bennett.orbit.items.tabs.OrbitTabs;
import bennett.orbit.planets.casud.feature.OrbitFeatures;
import bennett.orbit.rules.OrbitRules;
import bennett.orbit.tags.OrbitTags;
import bennett.orbit.test.OrbitTest;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Orbit implements ModInitializer {
    public static final String MOD_ID = "orbit";
    public static final String MOD_NAME = "Orbit";

    public static Logger LOGGER = LogManager.getLogger();

    @Override
    public void onInitialize() {
        OrbitRules.initialize();
        OrbitTags.initialize();
        OrbitFluids.initialize();
        OrbitBlocks.initialize();
        OrbitTabs.initialize();
        OrbitItems.initialize();
        OrbitFeatures.initialize();
        OrbitTest.test();
        log("Orbit initialized!");

    }

    public static ResourceLocation newId(String name) {
        return new ResourceLocation(Orbit.MOD_ID, name);
    }

    public static void log(String message) {LOGGER.info("[" + MOD_NAME + "]"  + message);
    }

}
