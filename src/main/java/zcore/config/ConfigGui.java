package zcore.config;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ConfigGui extends GuiConfig {
	private static String modID = "";
	private static String title = "";
	private static String subTitle = "";
	
	public static void setModId(String mod) {
		modID = mod;
	}
	public static void setTitle(String t) {
		title = t;
	}
	public static void setSubTitle(String subT) {
		subTitle = subT;
	}
	
	public static void set(String mod, String t, String subT) {
		modID = mod;
		title = t;
		subTitle = subT;
	}
	
	public static String getModID() {
		return modID;
	}

	public ConfigGui(GuiScreen parent) 
    {
        super(parent, new ConfigElement(
        		ConfigGuiFactory.cfig.getCategory(Configuration.CATEGORY_GENERAL))
        			.getChildElements(), 
        modID, false, false, title); titleLine2 = subTitle;
        
        //MoreMenu.createMenu(parent);
    }
    
    @Override
    public void initGui()
    {
        // You can add buttons and initialize fields here
        super.initGui();
    }

    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        // You can do things like create animations, draw additional elements, etc. here
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        // You can process any additional buttons you may have added here
        super.actionPerformed(button);
    }
}
