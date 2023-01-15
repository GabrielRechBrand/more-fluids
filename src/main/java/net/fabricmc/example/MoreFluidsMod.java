package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.fluid.AcidFluid;
import net.fabricmc.example.fluid.BloodFluid;
import net.fabricmc.example.fluid.CoffeeFluid;
import net.fabricmc.example.fluid.PetroleumFluid;
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
	private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder(new Identifier(MOD_ID, "fluids"))
			.icon(() -> new ItemStack(Items.BUCKET))
			.build();

	//PETROLEUM
	public static Block PETROLEUM;
	public static Item PETROLEUM_BUCKET;
	public static FlowableFluid STILL_PETROLEUM;
	public static FlowableFluid FLOWING_PETROLEUM;

	//COFFEE
	public static Block COFFEE;
	public static Item COFFEE_BUCKET;
	public static FlowableFluid STILL_COFFEE;
	public static FlowableFluid FLOWING_COFFEE;

	//BLOOD
	public static Block BLOOD;
	public static Item BLOOD_BUCKET;
	public static FlowableFluid STILL_BLOOD;
	public static FlowableFluid FLOWING_BLOOD;

	//ACID
	public static Block ACID;
	public static Item ACID_BUCKET;
	public static FlowableFluid STILL_ACID;
	public static FlowableFluid FLOWING_ACID;

	@Override
	public void onInitialize() {
		LOGGER.info("Running more-fluids");

		ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register(content -> {
			content.add(PETROLEUM_BUCKET);
			content.add(COFFEE_BUCKET);
			content.add(BLOOD_BUCKET);
			content.add(ACID_BUCKET);
		});

		STILL_PETROLEUM = Registry.register(Registries.FLUID, new Identifier(MOD_ID, "petroleum"), new PetroleumFluid.Still());
		FLOWING_PETROLEUM = Registry.register(Registries.FLUID, new Identifier(MOD_ID, "flowing_petroleum"), new PetroleumFluid.Flowing());
		PETROLEUM_BUCKET = Registry.register(Registries.ITEM, new Identifier(MOD_ID, "petroleum_bucket"), new BucketItem(STILL_PETROLEUM, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
		PETROLEUM = Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "petroleum"), new FluidBlock(STILL_PETROLEUM, FabricBlockSettings.copyOf(Blocks.WATER)){});

		STILL_COFFEE = Registry.register(Registries.FLUID, new Identifier(MOD_ID, "coffee"), new CoffeeFluid.Still());
		FLOWING_COFFEE = Registry.register(Registries.FLUID, new Identifier(MOD_ID, "flowing_coffee"), new CoffeeFluid.Flowing());
		COFFEE_BUCKET = Registry.register(Registries.ITEM, new Identifier(MOD_ID, "coffee_bucket"), new BucketItem(STILL_COFFEE, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
		COFFEE = Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "coffee"), new FluidBlock(STILL_COFFEE, FabricBlockSettings.copyOf(Blocks.WATER)){});

		STILL_BLOOD = Registry.register(Registries.FLUID, new Identifier(MOD_ID, "blood"), new BloodFluid.Still());
		FLOWING_BLOOD = Registry.register(Registries.FLUID, new Identifier(MOD_ID, "flowing_blood"), new BloodFluid.Flowing());
		BLOOD_BUCKET = Registry.register(Registries.ITEM, new Identifier(MOD_ID, "blood_bucket"), new BucketItem(STILL_BLOOD, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
		BLOOD = Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "blood"), new FluidBlock(STILL_BLOOD, FabricBlockSettings.copyOf(Blocks.WATER)){});

		STILL_ACID = Registry.register(Registries.FLUID, new Identifier(MOD_ID, "acid"), new AcidFluid.Still());
		FLOWING_ACID = Registry.register(Registries.FLUID, new Identifier(MOD_ID, "flowing_acid"), new AcidFluid.Flowing());
		ACID_BUCKET = Registry.register(Registries.ITEM, new Identifier(MOD_ID, "acid_bucket"), new BucketItem(STILL_ACID, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
		ACID = Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "acid"), new FluidBlock(STILL_ACID, FabricBlockSettings.copyOf(Blocks.WATER)){});
	}
}
