package zdoctor.morecrops.crops.classes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import zdoctor.morecrops.MoreCrops;
import zdoctor.morecrops.gameregistry.ZItems;
import zdoctor.zcore.common.EasyCrop;
import zdoctor.zcore.common.EasySeed;

public class GoldCrop {
	public static void load() {
		EasyCrop GoldPlant = new EasyCrop("GoldCrop", MoreCrops.modid);
		
		EasySeed GoldSeed = new EasySeed("GoldSeed", MoreCrops.modid);
		GoldSeed.setRecipe(new Object[] {
			"GGG", "GNG", "GGG", 'G', Blocks.gold_block, 'N', Items.nether_star
		});
		ZItems.GoldSeed = GoldSeed;
		
		GoldPlant.setCrop(Items.gold_ingot, ZItems.GoldSeed);
	}
}
