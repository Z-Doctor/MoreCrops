package zdoctor.morecrops.crops;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zdoctor.morecrops.MoreCrops;
import zdoctor.zcore.common.EasyCrop;
import zdoctor.zcore.common.EasySeed;

public class Crops {
	public static void load() {
		plantDiamond = new EasyCrop("DiamondCrop", MoreCrops.modid).setCrop(Items.diamond,
				new EasySeed("DiamondSeed", MoreCrops.modid));
		plantEmerald = new EasyCrop("EmeraldCrop", MoreCrops.modid).setCrop(Items.emerald,
				new EasySeed("EmeraldSeed", MoreCrops.modid));
		plantIron = new EasyCrop("IronCrop", MoreCrops.modid).setCrop(Items.iron_ingot,
				new EasySeed("IronSeed", MoreCrops.modid));
		plantGold = new EasyCrop("GoldCrop", MoreCrops.modid).setCrop(Items.gold_ingot,
				new EasySeed("GoldSeed", MoreCrops.modid));
		
	}
	
	public static Block plantDiamond;
	public static Block plantEmerald;
	public static Block plantIron;
	public static Block plantGold;

}
