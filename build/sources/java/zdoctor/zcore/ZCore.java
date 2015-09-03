package zdoctor.zcore;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zdoctor.zcore.client.RenderGame;
import zdoctor.zcore.common.reference.References;

@Mod(modid = References.MODID, version = References.VERSION)
public class ZCore {
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		// This section is intentionally empty
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		new RenderGame(event);
	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		// This section is intentionally empty
	}
}
