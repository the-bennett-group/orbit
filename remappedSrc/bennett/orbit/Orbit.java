package bennett.orbit;

import bennett.orbit.blocks.OrbitBlocks;
import bennett.orbit.fluid.OrbitFluids;
import bennett.orbit.items.OrbitItems;
import bennett.orbit.items.tabs.OrbitTabs;
import bennett.orbit.planets.casud.feature.OrbitFeatures;
import bennett.orbit.rules.OrbitRules;
import bennett.orbit.tags.OrbitTags;
import bennett.orbit.util.OrbitUtils;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.util.OptionalLong;

public class Orbit implements ModInitializer {
    public static final String MOD_ID = "orbit";
    public static final String MOD_NAME = "Orbit";

    public static Logger LOGGER = LogManager.getLogger();

    @Nullable
    private static MinecraftServer server;
    public static OptionalLong SEED;

    @Override
    public void onInitialize() {
        OrbitUtils.initialize();
        OrbitRules.initialize();
        OrbitTags.initialize();
        OrbitFluids.initialize();
        OrbitBlocks.initialize();
        OrbitTabs.initialize();
        OrbitItems.initialize();
        OrbitFeatures.initialize();
        //OrbitPlanets.initialize();
        log("Orbit initialized!");
/*
        ServerLifecycleEvents.SERVER_STARTING.register((minecraftServer)->{
            Orbit.server = minecraftServer;
            Orbit.SEED = OptionalLong.of(Orbit.getServer().overworld().getSeed());
        });

        ServerLifecycleEvents.SERVER_STOPPING.register((minecraftServer)->{
            Orbit.server = null;
            Orbit.SEED = OptionalLong.empty();
        });
*/
    }

    public static ResourceLocation newId(String name) {
        return new ResourceLocation(Orbit.MOD_ID, name);
    }

    public static void log(String message) {LOGGER.info("[" + MOD_NAME + "]"  + message);}

    @Nullable
    public static MinecraftServer getServer() {
        return server;
    }




}
