package rbt.orbit;

import net.minecraft.resources.ResourceLocation;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rbt.orbit.blocks.OrbitBlocks;
import rbt.orbit.fluid.OrbitFluids;
import rbt.orbit.items.OrbitItems;
import rbt.orbit.items.tabs.OrbitTabs;
import rbt.orbit.rules.OrbitRules;

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
        OrbitTabs.initialize();
        OrbitFluids.initialize();
        OrbitBlocks.initialize();
        OrbitItems.initialize();
    }
    public static void log(Level level, String message) {LOGGER.log(level, "["+MOD_NAME+"]" + message);}

}
