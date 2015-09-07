package zdoctor.morecrops.crops;

import zdoctor.morecrops.crops.classes.DiamondCrop;
import zdoctor.morecrops.crops.classes.EmeraldCrop;
import zdoctor.morecrops.crops.classes.GoldCrop;
import zdoctor.morecrops.crops.classes.IronCrop;

public class Crops {
	public static void load() {
		DiamondCrop.load();
		EmeraldCrop.load();
		GoldCrop.load();
		IronCrop.load();
	}
}
