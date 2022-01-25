package bennett.orbit.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import bennett.orbit.fluid.OrbitFluids;
import net.minecraft.world.level.block.Blocks;

@Environment(EnvType.CLIENT)
public class OrbitClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientSpriteRegistryCallback.event(InventoryMenu.BLOCK_ATLAS).register((atlasTexture, registry) -> {
            registry.register(new ResourceLocation("orbit:block/acid_still"));
            registry.register(new ResourceLocation("orbit:block/acid_flow"));
            registry.register(new ResourceLocation("orbit:block/blackwood_log"));
            registry.register(new ResourceLocation("orbit:block/blackwood_log_top"));
            registry.register(new ResourceLocation("orbit:block/stripped_blackwood_log"));
            registry.register(new ResourceLocation("orbit:block/stripped_blackwood_log_top"));
            registry.register(new ResourceLocation("orbit:block/blackwood_planks"));


            BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), OrbitFluids.SOURCE_ACID, OrbitFluids.FLOWING_ACID);

        });
        FluidRenderHandlerRegistry.INSTANCE.register(OrbitFluids.SOURCE_ACID, OrbitFluids.FLOWING_ACID, new SimpleFluidRenderHandler(
                new ResourceLocation("orbit:block/acid_still"),
                new ResourceLocation("orbit:block/acid_flow")
        ));



    }
}
