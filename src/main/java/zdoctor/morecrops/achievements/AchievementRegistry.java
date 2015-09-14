package zdoctor.morecrops.achievements;

import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.fml.common.FMLCommonHandler;
import zdoctor.morecrops.achievements.classes.AchievementSeeds;

public class AchievementRegistry {
	public static void load() {
		AchievementSeeds.load();;
	}
}
