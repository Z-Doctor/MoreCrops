package zdoctor.morecrops.config;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zdoctor.morecrops.events.ConfigSaver;
import zdoctor.zcore.common.EasyStuff.EventRegistry;

public class GuiFactory implements IModGuiFactory {
	public static Configuration cfig;
	public static void load(FMLPreInitializationEvent e) {
		cfig = new Configuration(e.getSuggestedConfigurationFile());
		cfig.load();
		cfig = Config.load(cfig);
		cfig.save();
		EventRegistry.registerToFMLBus(ConfigSaver.class);
	}
	
	@Override
	public void initialize(Minecraft minecraftInstance) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return ConfigMenu.class;
	}
	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		// TODO Auto-generated method stub
		return null;
	}
}
