package zdoctor.morecrops;

import java.util.Random;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zcore.config.ConfigGui;
import zcore.proxy.CommonProxy;
import zdoctor.morecrops.crops.Crops;

@Mod(modid = MoreCrops.modid, version = MoreCrops.verid, name = MoreCrops.name, 
	dependencies = MoreCrops.depends,  guiFactory = MoreCrops.config)
public class MoreCrops {
	public static final  String modid = "morecrops";
	public static final String verid = "1.4";
	public static final String name = "More Crops";
	public static final String depends = "";
	public static final String config = "zcore.config.ConfigGuiFactory";
		
	@SidedProxy(clientSide="zcore.proxy.ClientProxy", serverSide="zcore.proxy.ServerProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preFore(FMLConstructionEvent e){
		ConfigGui.set(modid, name + " Config", getRandomGreeting());
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
	
	private String getRandomGreeting() {
		Random msg = new Random();
		switch (msg.nextInt(11)) {
		case 0:
			return "You want to do what to my config?";
		case 1:
			return "Was this text always here?";
		case 3:
			return "... Oh sorry, didn't see you come in.";
		case 4:
			return "Do I amuse you?";
		case 5:
			return "I'm so lonely. :(";
		case 6:
			return "Your back! :D";
		case 7:
			return "Can I take your order?";
		case 8:
			return "Hello there. want to see my config. ;)";
		case 9:
			return "Nope, I'm not magic. I was just born this way.";
		case 10:
			return "Why you no check for update! D:<";
		default:
			return "How did you see this text?";
		}
	}
}
