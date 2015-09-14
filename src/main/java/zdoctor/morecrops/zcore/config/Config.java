package zdoctor.morecrops.zcore.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.FMLModContainer;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage.ModList;
import net.minecraftforge.fml.relauncher.ModListHelper;
import zdoctor.morecrops.zcore.common.EasyItem;
import zdoctor.morecrops.zcore.common.EasyStuff.GameModifications;
import zdoctor.morecrops.zcore.common.EasyStuff.RegistryChecker;
import zdoctor.morecrops.zcore.gameregistry.ZItems;

/**
 * A place to add all your config options
 * @author Z_Doctor
 */
public class Config {
	public static Property disableBoneMeal;
	
	public static Property disableDSeeds;
	public static Property disableGSeeds;
	public static Property disableISeeds;
	public static Property disableESeeds;
	
	public static Property dSeedDropRate;
	public static Property eSeedDropRate;
	public static Property gSeedDropRate;
	public static Property iSeedDropRate;
	
	public static Property HealthyCraftTrial;
	
	public static Property growsInPeaceful;
	public static Property growsOnlyInNether;
	
	public static void load(Configuration config) {
		if(config.hasKey(Configuration.CATEGORY_GENERAL, "Allow Bone Meal Use")){
			config.renameProperty(Configuration.CATEGORY_GENERAL, "Allow Bone Meal Use", "Disable Bone Meal");
		}
		if(config.hasKey(Configuration.CATEGORY_GENERAL, "Disable Seed Recipe")){
			config.renameProperty(Configuration.CATEGORY_GENERAL, "Disable Seed Recipe", "Try Healthy Craft");
		}
		
		disableBoneMeal = config.get(Configuration.CATEGORY_GENERAL, "Disable Bone Meal", true);
		disableBoneMeal.comment = "Does bone meal work on Material Plants?";
		
		disableDSeeds = config.get(Configuration.CATEGORY_GENERAL, "Disable Diamond Seeds", false);
		disableDSeeds.comment = "Are Diamond Seeds craftable";
		
		disableESeeds = config.get(Configuration.CATEGORY_GENERAL, "Disable Emerald Seeds", false);
		disableESeeds.comment = "Are Emerald Seeds craftable";
		
		disableGSeeds = config.get(Configuration.CATEGORY_GENERAL, "Disable Gold Seeds", false);
		disableGSeeds.comment = "Are Gold Seeds craftable";
		
		disableISeeds = config.get(Configuration.CATEGORY_GENERAL, "Disable Iron Seeds", false);
		disableISeeds.comment = "Are Iron Seeds craftable";
		
		HealthyCraftTrial = config.get(Configuration.CATEGORY_GENERAL, "Try Healthy Craft", true);
		HealthyCraftTrial.setRequiresMcRestart(true);
		HealthyCraftTrial.comment = "Try out my other mod Healthy Craft before commiting to add it";
		if(Loader.isModLoaded("healthycraft")){
			HealthyCraftTrial.set(false);
			HealthyCraftTrial.setShowInGui(false);
		}
		
		dSeedDropRate = config.get(Configuration.CATEGORY_GENERAL, "Diamond Seed Drop Rate", 10);
		dSeedDropRate.comment = "Percent of the time a second seed will drop.";
		dSeedDropRate.setMaxValue(100);
		dSeedDropRate.setMinValue(0);
		
		eSeedDropRate = config.get(Configuration.CATEGORY_GENERAL, "Emerald Seed Drop Rate", 15);
		eSeedDropRate.comment = "Percent of the time a second seed will drop.";
		eSeedDropRate.setMaxValue(100);
		eSeedDropRate.setMinValue(0);
		
		gSeedDropRate = config.get(Configuration.CATEGORY_GENERAL, "Gold Seed Drop Rate", 40);
		gSeedDropRate.comment = "Percent of the time a second seed will drop.";
		gSeedDropRate.setMaxValue(100);
		gSeedDropRate.setMinValue(0);
		
		iSeedDropRate = config.get(Configuration.CATEGORY_GENERAL, "Iron Seed Drop Rate", 50);
		iSeedDropRate.comment = "Percent of the time a second seed will drop.";
		iSeedDropRate.setMaxValue(100);
		iSeedDropRate.setMinValue(0);
		
		growsInPeaceful = config.get(Configuration.CATEGORY_GENERAL, "Grows in Peaceful", false);
		growsInPeaceful.comment = "Do the Material Plants (i.e. the Diamond Plant) grow in peaceful?";
		
		growsOnlyInNether = config.get(Configuration.CATEGORY_GENERAL, "Grows only in Nether", true);
		growsOnlyInNether.comment = "Do the Material Plants (except the Diamond Plant) grow only in the nether?";
		
	}

	public static void sync() {
		List<Item> reloadList = new ArrayList<Item>();
		List<Item> removeList = new ArrayList<Item>();
		boolean check;
		check = RegistryChecker.checkForRecipe(ZItems.DiamondSeed);
		if(!Config.disableDSeeds.getBoolean(false) && !check)
			reloadList.add(ZItems.DiamondSeed);
		else if(Config.disableDSeeds.getBoolean(false) && check)
			removeList.add(ZItems.DiamondSeed);
		
		check = RegistryChecker.checkForRecipe(ZItems.EmeraldSeed);
		if(!Config.disableESeeds.getBoolean(false) && !check)
			reloadList.add(ZItems.EmeraldSeed);
		else if (Config.disableESeeds.getBoolean(false) && check)
			removeList.add(ZItems.EmeraldSeed);
		
		check = RegistryChecker.checkForRecipe(ZItems.GoldSeed);
		if(!Config.disableGSeeds.getBoolean(false) && !check)
			reloadList.add(ZItems.GoldSeed);
		else if(Config.disableGSeeds.getBoolean(false) && check)
			removeList.add(ZItems.GoldSeed);
		
		check = RegistryChecker.checkForRecipe(ZItems.IronSeed);
		if(!Config.disableISeeds.getBoolean(false) && !check)
			reloadList.add(ZItems.IronSeed);
		else if(Config.disableISeeds.getBoolean(false) && check)
			removeList.add(ZItems.IronSeed);
		
		if(!reloadList.isEmpty())
			for(Item item : reloadList) {
				GameModifications.registerItemRecipe(item, ((EasyItem)item).getRecipe(), false);
			}
		if(!removeList.isEmpty())
			for(Item item : removeList) {
				GameModifications.removeItemRecipe(item);
			}
	}
}