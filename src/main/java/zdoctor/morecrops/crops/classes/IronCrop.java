package zdoctor.morecrops.crops.classes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import zdoctor.morecrops.MoreCrops;
import zdoctor.morecrops.gameregistry.ZItems;
import zdoctor.zcore.common.EasyCrop;
import zdoctor.zcore.common.EasySeed;

public class IronCrop {
	public static void load() {
		EasyCrop IronPlant = new EasyCrop("IronCrop", MoreCrops.modid);
		
		EasySeed IronSeed = new EasySeed("IronSeed", MoreCrops.modid);
		IronSeed.setRecipe(new Object[] {
			"III", "INI", "III", 'I', Blocks.iron_block, 'N', Items.nether_star
		});
		ZItems.IronSeed = IronSeed;
		
		IronPlant.setCrop(Items.iron_ingot, ZItems.IronSeed);
	}
}
