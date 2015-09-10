package zdoctor.morecrops.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Config {
	private static Configuration config;
	
	public static Configuration load(Configuration c) {
		config = c;
		disableBoneMeal = config.get(Configuration.CATEGORY_GENERAL, "Disable Bone Meal", true);
		disableBoneMeal.comment = "Does bone meal work on Material Plants?";
		disableSeeds = config.get(Configuration.CATEGORY_GENERAL, "Disable Seed Recipe", false);
		disableSeeds.setRequiresMcRestart(true);
		disableSeeds.comment = "Should material seeds be craftable";
		
		if(config.hasKey(Configuration.CATEGORY_GENERAL, "Allow Bone Meal Use")){
			Property temp = config.get(Configuration.CATEGORY_GENERAL, "Allow Bone Meal Use", false);
			temp.setShowInGui(false);
		}
		
		return config;
	}
	
	public static void synConfig() {
		if(config.hasChanged())
			config.save();
	}
	
	public static Property disableBoneMeal;
	public static Property disableSeeds;
	
}