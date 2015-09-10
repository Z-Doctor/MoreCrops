package zdoctor.morecrops.events;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.minecart.MinecartEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zdoctor.morecrops.config.Config;
import zdoctor.morecrops.config.ConfigMenu;
import zdoctor.zcore.common.EasyCrop;

public class ConfigSaver {
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if(eventArgs.modID == ConfigMenu.getModID()){
			System.out.println("Config for " + eventArgs.modID + " has changed. Saving...");
			Config.synConfig();
		}
	}
}
