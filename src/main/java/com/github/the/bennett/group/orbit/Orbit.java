package com.github.the.bennett.group.orbit;

import com.github.the.bennett.group.orbit.blocks.OrbitBlocks;
import com.github.the.bennett.group.orbit.fluid.OrbitFluids;
import com.github.the.bennett.group.orbit.items.OrbitItems;
import com.github.the.bennett.group.orbit.items.tabs.OrbitTabs;
import com.github.the.bennett.group.orbit.rules.OrbitRules;
import com.github.the.bennett.group.orbit.tags.OrbitTags;
import com.github.the.bennett.group.orbit.world.OrbitPlanetGenUtils;
import com.github.the.bennett.group.orbit.world.feature.OrbitFeatures;
import com.github.the.bennett.group.orbit.world.planets.OrbitPlanets;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.lifecycle.api.event.ServerLifecycleEvents;
import org.slf4j.Logger;

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
        OrbitPlanets.initialize();
        log("Orbit initialized!");

        ServerLifecycleEvents.STARTING.register((minecraftServer)->{
            Orbit.server = minecraftServer;
            OrbitPlanetGenUtils.setSeed(Orbit.getServer().overworld().getSeed());
            System.out.println("Seed aquired: " + OrbitPlanetGenUtils.seed());
        });

        ServerLifecycleEvents.STOPPING.register((minecraftServer)->{
            Orbit.server = null;
            OrbitPlanetGenUtils.nullifySeed();
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
