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
	}
		
		public static Block plantDiamond;
		public static Item diamondseed;
}
