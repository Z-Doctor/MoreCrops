package zdoctor.morecrops.crops;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import zdoctor.morecrops.MoreCrops;
import zdoctor.zcore.common.EasyCrop;
import zdoctor.zcore.common.EasySeed;

public class Crops {
	public static void load() {
		plantDiamond = new EasyCrop("diamondcrop", MoreCrops.modid).setCrop(Items.diamond,
				new EasySeed("diamondseed", MoreCrops.modid));
		plantEmerald = new EasyCrop("Emeraldcrop", MoreCrops.modid).setCrop(Items.emerald,
				new EasySeed("Emeraldseed", MoreCrops.modid));
		plantIron = new EasyCrop("ironcrop", MoreCrops.modid).setCrop(Items.iron_ingot,
				new EasySeed("ironseed", MoreCrops.modid));
		plantGold = new EasyCrop("goldcrop", MoreCrops.modid).setCrop(Items.gold_ingot,
				new EasySeed("goldseed", MoreCrops.modid));
		
	}
	
	public static Block plantDiamond;
	public static Block plantEmerald;
	public static Block plantIron;
	public static Block plantGold;

}
