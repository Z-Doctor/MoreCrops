package zdoctor.morecrops.zcore.events;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zdoctor.morecrops.zcore.common.EasyStuff.EventRegistry;
import zdoctor.morecrops.zcore.config.ConfigGui;
import zdoctor.morecrops.zcore.config.ConfigGuiFactory;
import zdoctor.morecrops.zcore.events.CustomEvents.FML;
import zdoctor.morecrops.zcore.events.CustomEvents.Forge;

/**
 * Only mess with if you know what you are doing, which you obviously do. :)
 * 
 * @author Z_Doctor
 */
public class CoreEvents {
	/**
	 * A Class for debugging purposes.
	 * 
	 * @author Z_Doctor
	 */
	public static class ErrorRegistry {
		private static List<String> errors = new ArrayList<String>();

		public static void errorReport() {
			if (errors.size() == 0) {
				System.out.println("No errors reported.");
			} else {
				System.out.println(errors.size() + " error");
				for (String report : errors) {
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
		EventRegistry.registerToFMLBus(configSaver.class);
		EventRegistry.registerToForgeEBus(Forge.class);
	}
	public static class configSaver {
		@SubscribeEvent
		public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent cfig) {
			if (cfig.modID == ConfigGui.getModID()) {
				System.out.println("Config for " + cfig.modID + " has changed. Saving...");
				ConfigGuiFactory.syncConfig();
			}
		}
	}
}