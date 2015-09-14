package zdoctor.morecrops.zcore.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zdoctor.morecrops.zcore.common.EasyItem;
import zdoctor.morecrops.zcore.common.EasyStuff.GameModifications;
import zdoctor.morecrops.zcore.common.EasyStuff.RegistryChecker;
import zdoctor.morecrops.zcore.gameregistry.ZItems;

public class ConfigGuiFactory implements IModGuiFactory {
	public static Configuration cfig;
	public static void load(FMLPreInitializationEvent e) {
		cfig = new Configuration(e.getSuggestedConfigurationFile());
		cfig.load();
		Config.load(cfig);
		cfig.save();
	}
	
	public static void syncConfig() {
		if(cfig.hasChanged()) {
			cfig.save();
			Config.sync();
		}
	}

	@Override
	public void initialize(Minecraft minecraftInstance) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return ConfigGui.class;
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
