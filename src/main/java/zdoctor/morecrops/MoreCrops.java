package zdoctor.morecrops;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zdoctor.morecrops.config.Config;
import zdoctor.morecrops.config.ConfigMenu;
import zdoctor.morecrops.crops.Crops;
import zdoctor.zcore.proxy.CommonProxy;

@Mod(modid = MoreCrops.modid, version = MoreCrops.verid, name = MoreCrops.name, 
	dependencies = MoreCrops.depends,  guiFactory = MoreCrops.config)
public class MoreCrops {
	public static final  String modid = "morecrops";
	public static final String verid = "1.3";
	public static final String name = "More Crops";
	public static final String depends = "";
	public static final String config = "zdoctor." + modid +".config.GuiFactory";
		
	@SidedProxy(clientSide="zdoctor.zcore.proxy.ClientProxy", serverSide="zdoctor.zcore.proxy.ServerProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preFore(FMLConstructionEvent e){
		ConfigMenu.set(modid, name + " Config", "Time to work some magic.");
		Crops.load();
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
	    this.proxy.preInit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
	    this.proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
	    this.proxy.postInit(e);
	}
}
