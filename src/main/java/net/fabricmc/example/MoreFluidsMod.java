package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.liquid.SmokeFluid;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreFluidsMod implements ModInitializer {

	//MOD
	public static final String MOD_ID = "more-fluids";

	//LOGGER
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	//ITEM GROUP
	private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder(new Identifier(MOD_ID, "test_group"))
			.icon(() -> new ItemStack(Items.BUCKET))
			.build();

	//SMOKE
	public static FlowableFluid FLOWING_SMOKE;
	public static FlowableFluid STILL_SMOKE;
	public static Item SMOKE_BUCKET;
	public static Block SMOKE;

	@Override
	public void onInitialize() {
		LOGGER.info("Running more-fluids");

		ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register(content -> {
			content.add(SMOKE_BUCKET);
		});

		STILL_SMOKE = Registry.register(Registries.FLUID, new Identifier(MOD_ID, "smoke"), new SmokeFluid.Still());
		FLOWING_SMOKE = Registry.register(Registries.FLUID, new Identifier(MOD_ID, "flowing_smoke"), new SmokeFluid.Flowing());
		SMOKE_BUCKET = Registry.register(Registries.ITEM, new Identifier(MOD_ID, "smoke_bucket"), new BucketItem(STILL_SMOKE, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
		SMOKE = Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "smoke"), new FluidBlock(STILL_SMOKE, FabricBlockSettings.copyOf(Blocks.WATER)){});
	}
}
