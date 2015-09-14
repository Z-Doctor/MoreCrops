package zdoctor.morecrops.achievements.classes;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import zdoctor.morecrops.zcore.gameregistry.ZItems;

public class AchievementSeeds {
	public static Achievement GetWitherSkull = new Achievement("", "GetWitherSkull", 0, 0, new ItemStack(Items.skull, 1, 1), (Achievement)null).setIndependent();
	public static Achievement GetNetherStar = new Achievement("", "GetNetherStar", 2, 0, Items.nether_star, GetWitherSkull);
	public static Achievement CraftDiamondSeed = new Achievement("", "CraftDiamondSeed", 3, -2, ZItems.DiamondSeed, GetNetherStar).setSpecial();
	public static Achievement CraftEmeraldSeed = new Achievement("", "CraftEmeraldSeed", 3, -1, ZItems.EmeraldSeed, GetNetherStar);
	public static Achievement CraftGoldSeed = new Achievement("", "CraftGoldSeed", 3, 1, ZItems.GoldSeed, GetNetherStar);
	public static Achievement CraftIronSeed = new Achievement("", "CraftIronSeed", 3, 2, ZItems.IronSeed, GetNetherStar);
	
	public static Achievement GetStrawberry = new Achievement("", "GetStrawberry", -1, 0, ZItems.Strawberry, (Achievement)null).setIndependent();
			
	public static AchievementPage MoreCrops = new AchievementPage("More Crops", GetStrawberry, GetWitherSkull, GetNetherStar, CraftDiamondSeed, 
			CraftEmeraldSeed, CraftGoldSeed, CraftIronSeed);
	
	public static void load() {
		/*GetStrawberry.registerStat();
		GetWitherSkull.registerStat();
		GetNetherStar.registerStat();
		CraftDiamondSeed.registerStat();
		CraftEmeraldSeed.registerStat();
		CraftGoldSeed.registerStat();
		CraftIronSeed.registerStat();*/
		AchievementPage.registerAchievementPage(MoreCrops);
		
	}
}
