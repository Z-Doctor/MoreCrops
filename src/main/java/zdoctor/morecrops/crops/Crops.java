package zdoctor.morecrops.crops;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import zdoctor.zcore.ZCore;
import zdoctor.zcore.common.EasyCrop;
import zdoctor.zcore.common.EasySeed;

public class Crops {
	public static void load() {
		plantDiamond = new EasyCrop("DiamondCrop", ZCore.modid).setCrop(Items.diamond,
				new EasySeed("DiamondSeed", ZCore.modid).setRecipe(new Object[] {
						"DDD", "DND", "DDD", 'D', Blocks.diamond_block, 'N', Items.nether_star
				}));
		plantEmerald = new EasyCrop("EmeraldCrop", ZCore.modid).setCrop(Items.emerald,
				new EasySeed("EmeraldSeed", ZCore.modid).setRecipe(new Object[] {
						"EEE", "ENE", "EEE", 'E', Blocks.emerald_block, 'N', Items.nether_star
				}));
		plantIron = new EasyCrop("IronCrop", ZCore.modid).setCrop(Items.iron_ingot,
				new EasySeed("IronSeed", ZCore.modid).setRecipe(new Object[] {
						"III", "INI", "III", 'I', Blocks.iron_block, 'N', Items.nether_star
				}));
		plantGold = new EasyCrop("GoldCrop", ZCore.modid).setCrop(Items.gold_ingot,
				new EasySeed("GoldSeed", ZCore.modid).setRecipe(new Object[] {
						"G", "GNG", "GGG", 'G', Blocks.gold_block, 'N', Items.nether_star
				}));
		
	}
	
	public static Block plantDiamond;
	public static Block plantEmerald;
	public static Block plantIron;
	public static Block plantGold;

}
