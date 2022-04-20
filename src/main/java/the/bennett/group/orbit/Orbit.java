package the.bennett.group.orbit;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.lifecycle.api.event.ServerLifecycleEvents;
import org.slf4j.Logger;
import the.bennett.group.orbit.blocks.OrbitBlocks;
import the.bennett.group.orbit.fluid.OrbitFluids;
import the.bennett.group.orbit.items.OrbitItems;
import the.bennett.group.orbit.items.tabs.OrbitTabs;
import the.bennett.group.orbit.rules.OrbitRules;
import the.bennett.group.orbit.tags.OrbitTags;
import the.bennett.group.orbit.world.OrbitPlanetGenUtils;
import the.bennett.group.orbit.world.feature.OrbitFeatures;
import the.bennett.group.orbit.world.planets.OrbitPlanets;

public class Orbit implements ModInitializer {
    public static final String MOD_ID = "orbit";
    public static final String MOD_NAME = "Orbit";

    public static Logger LOGGER = LogUtils.getLogger();

    @Nullable
    private static MinecraftServer server;

    @Override
    public void onInitialize(ModContainer mod) {
        OrbitRules.initialize();
        OrbitTags.initialize();
        OrbitFluids.initialize();
        OrbitBlocks.initialize();
        OrbitTabs.initialize();
        OrbitItems.initialize();

        OrbitFeatures.initialize();
        log("Orbit initialized!");

        // seed is not available until server is starting, so we wait to register planets and chunkgenerators
        // until right before world load
        ServerLifecycleEvents.STARTING.register(Orbit::initializeWorldgen);
        ServerLifecycleEvents.STOPPING.register(Orbit::cleanUp);
    }

    public static void initializeWorldgen(MinecraftServer minecraftServer) {
        Orbit.server = minecraftServer;
        OrbitPlanetGenUtils.setSeed(Orbit.getServer().overworld().getSeed());
        System.out.println("Seed aquired: " + OrbitPlanetGenUtils.seed());
        OrbitPlanets.initialize(OrbitPlanetGenUtils.seed());
    }

    public static void cleanUp(MinecraftServer minecraftServer) {
        Orbit.server = null;
        OrbitPlanetGenUtils.nullifySeed();
    }

    public static ResourceLocation newId(String name) {
        return new ResourceLocation(Orbit.MOD_ID, name);
    }

    public static void log(String message) {LOGGER.info("[" + MOD_NAME + "]"  + message);}

    public static @Nullable MinecraftServer getServer() {
        return server;
    }




}
