package bennett.orbit;

import bennett.orbit.blocks.OrbitBlocks;
import bennett.orbit.fluid.OrbitFluids;
import bennett.orbit.items.OrbitItems;
import bennett.orbit.items.tabs.OrbitTabs;
import bennett.orbit.planets.OrbitWorldGenUtils;
import bennett.orbit.planets.casud.feature.OrbitFeatures;
import bennett.orbit.rules.OrbitRules;
import bennett.orbit.tags.OrbitTags;
import bennett.orbit.util.OrbitUtils;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public class Orbit implements ModInitializer {
    public static final String MOD_ID = "orbit";
    public static final String MOD_NAME = "Orbit";

    public static Logger LOGGER = LogUtils.getLogger();

    @Nullable
    private static MinecraftServer server;


    public static void preInitialize() {
        OrbitBlocks.preInitialize();
        OrbitFeatures.preInitialize();
    }
    @Override
    public void onInitialize() {
        preInitialize();
        OrbitUtils.initialize();
        OrbitRules.initialize();
        OrbitTags.initialize();
        OrbitFluids.initialize();
        OrbitBlocks.initialize();
        OrbitTabs.initialize();
        OrbitItems.initialize();
        OrbitFeatures.initialize();
        OrbitWorldGenUtils.initialize();
        //OrbitPlanets.initialize();
        log("Orbit initialized!");

        ServerLifecycleEvents.SERVER_STARTING.register((minecraftServer)->{
            Orbit.server = minecraftServer;
            OrbitWorldGenUtils.setSeed(Orbit.getServer().overworld().getSeed());
        });

        ServerLifecycleEvents.SERVER_STOPPING.register((minecraftServer)->{
            Orbit.server = null;
            OrbitWorldGenUtils.nullifySeed();
        });

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
