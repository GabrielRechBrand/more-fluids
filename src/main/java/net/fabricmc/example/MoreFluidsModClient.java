package net.fabricmc.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.util.Identifier;

public class MoreFluidsModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        //PETROLEUM
        FluidRenderHandlerRegistry.INSTANCE.register(MoreFluidsMod.STILL_PETROLEUM, MoreFluidsMod.FLOWING_PETROLEUM, new SimpleFluidRenderHandler(
                new Identifier("minecraft:block/water_still"),
                new Identifier("minecraft:block/water_flow"),
                0x171717
        ));

        //COFFEE
        FluidRenderHandlerRegistry.INSTANCE.register(MoreFluidsMod.STILL_COFFEE, MoreFluidsMod.FLOWING_COFFEE, new SimpleFluidRenderHandler(
                new Identifier("minecraft:block/water_still"),
                new Identifier("minecraft:block/water_flow"),
                0x563119
        ));

        //BLOOD
        FluidRenderHandlerRegistry.INSTANCE.register(MoreFluidsMod.STILL_BLOOD, MoreFluidsMod.FLOWING_BLOOD, new SimpleFluidRenderHandler(
                new Identifier("minecraft:block/water_still"),
                new Identifier("minecraft:block/water_flow"),
                0x5B0001
        ));

    }

}
