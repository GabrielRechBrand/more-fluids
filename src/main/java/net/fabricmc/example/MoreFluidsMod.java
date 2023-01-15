package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.liquid.PetroleumFluid;
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
import net.minecraft.world.gen.feature.LakeFeature;
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

	//PETROLEUM
	public static LakeFeature PETROLEUM_LAKE;
	public static FlowableFluid FLOWING_PETROLEUM;
	public static FlowableFluid STILL_PETROLEUM;
	public static Item PETROLEUM_BUCKET;
	public static Block PETROLEUM;

	@Override
	public void onInitialize() {
		LOGGER.info("Running more-fluids");

		ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register(content -> {
			content.add(PETROLEUM_BUCKET);
		});

		STILL_PETROLEUM = Registry.register(Registries.FLUID, new Identifier(MOD_ID, "petroleum"), new PetroleumFluid.Still());
		FLOWING_PETROLEUM = Registry.register(Registries.FLUID, new Identifier(MOD_ID, "flowing_petroleum"), new PetroleumFluid.Flowing());
		PETROLEUM_BUCKET = Registry.register(Registries.ITEM, new Identifier(MOD_ID, "petroleum_bucket"), new BucketItem(STILL_PETROLEUM, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
		PETROLEUM = Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "petroleum"), new FluidBlock(STILL_PETROLEUM, FabricBlockSettings.copyOf(Blocks.WATER)){});
	}
}
