package bennett.orbit;

import bennett.orbit.blocks.OrbitBlocks;
import bennett.orbit.items.tabs.OrbitTabs;
import bennett.orbit.rules.OrbitRules;
import bennett.orbit.tags.OrbitTags;
import bennett.orbit.test.OrbitTest;
import net.minecraft.resources.ResourceLocation;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import bennett.orbit.fluid.OrbitFluids;
import bennett.orbit.items.OrbitItems;

public class Orbit implements ModInitializer {
    public static final String MOD_ID = "orbit";
    public static final String MOD_NAME = "Orbit";

    public static Logger LOGGER = LogManager.getLogger();

    public static ResourceLocation newId(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    @Override
    public void onInitialize() {
        OrbitRules.initialize();
        OrbitTags.initialize();
        OrbitTabs.initialize();
        OrbitFluids.initialize();
        OrbitBlocks.initialize();
        OrbitItems.initialize();
        OrbitTest.test();
        log("Orbit initialized");

    }
    public static void log(String message) {LOGGER.info("[" + MOD_NAME + "]"  + message);
    }

}
