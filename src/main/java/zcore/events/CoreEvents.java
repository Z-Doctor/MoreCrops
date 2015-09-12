package zcore.events;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zcore.common.EasyStuff.EventRegistry;
import zcore.config.Config;
import zcore.config.ConfigGui;
import zcore.config.ConfigGuiFactory;
import zcore.events.CustomEvents.FML;
import zcore.events.CustomEvents.Forge;
/**
 * Only mess with if you know what you are doing, which you obviously do. :)
 * @author Z_Doctor
 */
public class CoreEvents {
	/**
	 * A Class for debugging purposes.
	 * @author Z_Doctor
	 */
	public static class ErrorRegistry {
		private static List<String> errors = new ArrayList<String>();
		public static void errorReport() {
			if (errors.size() == 0) {
				System.out.println("No errors reported.");
			} else {
				System.out.println(errors.size() + " error");
				for(String report : errors){
					System.out.println("Error: " + report);
				}
			}
		}
		public static void reportError(String errorReport) {
			errors.add(errorReport);
		}
	}
	public static void load() {
		EventRegistry.registerToFMLBus(FML.class);
		EventRegistry.registerToForgeEBus(Forge.class);
		EventRegistry.registerToForgeEBus(CoreEvents.class);
	}
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent cfig) {
		if(cfig.modID == ConfigGui.getModID()){
			System.out.println("Config for " + cfig.modID + " has changed. Saving...");
			ConfigGuiFactory.syncConfig();
		}
	}
}