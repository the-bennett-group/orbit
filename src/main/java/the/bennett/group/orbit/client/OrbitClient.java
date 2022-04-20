package the.bennett.group.orbit.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.material.FlowingFluid;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;
import the.bennett.group.orbit.Orbit;
import the.bennett.group.orbit.fluid.OrbitFluids;

@Environment(EnvType.CLIENT)
public class OrbitClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(ModContainer mod) {
        ClientSpriteRegistryCallback.event(InventoryMenu.BLOCK_ATLAS).register((atlasTexture, registry) -> {
            registerBlockTexture(registry, "acid_still");
            registerBlockTexture(registry, "acid_flow");
            registerBlockTexture(registry, "blackwood_log");
            registerBlockTexture(registry, "blackwood_log_top");
            registerBlockTexture(registry, "stripped_blackwood_log");
            registerBlockTexture(registry, "stripped_blackwood_log_top");
            registerBlockTexture(registry, "blackwood_planks");

            createFluidRenderLayer(OrbitFluids.SOURCE_ACID, OrbitFluids.FLOWING_ACID, "acid_still", "acid_flow");
        });

    }

    public static void registerBlockTexture(ClientSpriteRegistryCallback.Registry registry, String name) {
        registry.register(Orbit.newId("block/" + name));
    }

    public static void createFluidRenderLayer(FlowingFluid SOURCE, FlowingFluid FLOWING, String sourceTexture, String flowingTexture) {
        BlockRenderLayerMap.put(RenderType.translucent(), SOURCE, FLOWING);
        FluidRenderHandlerRegistry.INSTANCE.register(SOURCE, FLOWING, new SimpleFluidRenderHandler(
                Orbit.newId("block/"+sourceTexture), Orbit.newId("block/"+flowingTexture)));
    }
}
