package zdoctor.morecrops;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import zdoctor.morecrops.crops.Crops;
import zdoctor.zcore.proxy.CommonProxy;

@Mod(modid = MoreCrops.modid, version = MoreCrops.verid, name = MoreCrops.name, dependencies = MoreCrops.depends)
public class MoreCrops {
	public static final  String modid = "morecrops";
	public static final String verid = "1.0";
	public static final String name = "More Crops";
	public static final String depends = "";
	
	@EventHandler
	public void preFore(FMLConstructionEvent e){
		Crops.load();
	}
}
