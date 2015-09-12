package zcore.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

/**
 * A place to add all your config options
 * @author Z_Doctor
 */
public class Config {
	public static void load(Configuration config) {
		disableBoneMeal = config.get(Configuration.CATEGORY_GENERAL, "Disable Bone Meal", true);
		disableBoneMeal.comment = "Does bone meal work on Material Plants?";
		disableSeeds = config.get(Configuration.CATEGORY_GENERAL, "Disable Seed Recipe", false);
		disableSeeds.setRequiresMcRestart(true);
		disableSeeds.comment = "Should material seeds be craftable";
		
		if(config.hasKey(Configuration.CATEGORY_GENERAL, "Allow Bone Meal Use")){
			Property temp = config.get(Configuration.CATEGORY_GENERAL, "Allow Bone Meal Use", false);
			temp.setShowInGui(false);
		}
	}
	
	public static Property disableBoneMeal;
	public static Property disableSeeds;
	
}