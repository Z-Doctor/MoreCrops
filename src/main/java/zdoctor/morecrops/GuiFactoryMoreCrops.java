package zdoctor.morecrops;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class GuiFactoryMoreCrops implements IModGuiFactory {
	public static Configuration config;
	public static void load(FMLPreInitializationEvent e) {
		config = new Configuration(e.getSuggestedConfigurationFile());
		config.load();
		
		boolean allowBoneMeal = config.get(Configuration.CATEGORY_GENERAL, "Allow Bone Meal Use", 
				false).getBoolean(false);
		
		config.save();
	}
	@Override
	public void initialize(Minecraft minecraftInstance) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return GuiConfigMoreCrops.class;
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
