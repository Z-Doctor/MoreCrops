package zdoctor.morecrops.crops.classes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import zdoctor.morecrops.MoreCrops;
import zdoctor.morecrops.gameregistry.ZItems;
import zdoctor.zcore.common.EasyCrop;
import zdoctor.zcore.common.EasySeed;

public class EmeraldCrop {
	public static void load() {
		EasyCrop EmeraldPlant = new EasyCrop("EmeraldCrop", MoreCrops.modid);
		
		EasySeed EmeraldSeed = new EasySeed("EmeraldSeed", MoreCrops.modid);
		EmeraldSeed.setRecipe(new Object[] {
				"EEE", "ENE", "EEE", 'E', Blocks.emerald_block, 'N', Items.nether_star
		});
		ZItems.EmeraldSeed = EmeraldSeed;
		
		EmeraldPlant.setCrop(Items.emerald, ZItems.EmeraldSeed);
				
	}
}
