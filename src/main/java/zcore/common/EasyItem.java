package zcore.common;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import zcore.proxy.CommonProxy;
/**
 * A class to automate the process of putting it into the game and getting its texture
 * @author Z_Doctor
 */
public class EasyItem extends Item implements ISubEvent {
	// Basic info
	protected final String itemModel;
	protected final String modID;
	// Advance
	protected Object[] recipe;
	protected boolean isShapeless;
	// Constructors
	public EasyItem(String model, String mod) {
		this(model, mod, CreativeTabs.tabMisc);
	}
	public EasyItem(String model, String mod, CreativeTabs tab) {
		this.itemModel = model;
		this.modID = mod;
		
		this.setCreativeTab(tab);
		this.setUnlocalizedName(this.getModelPath());
		
		CommonProxy.subEvent(this);	
	}
	
	/** Override to set a different path */
	public String getModelPath() {
		return this.itemModel;
	}
	/** Override to change meta */
	public int getItemMeta() {
		return 0;
	}
	
	/** Shaped by default 
	 * @param recipe - The food's recipe object */
	public EasyItem setRecipe(Object[] recipe) {
		return this.setRecipe(recipe, false);
	}
	/**
	 * @param recipe - The item's recipe
	 * @param isShapeless - Whether or not the food's recipe is shapeless
	 */
	public EasyItem setRecipe(Object[] recipe, boolean isShapeless) {
		if(this.recipe == null) {
			this.recipe = recipe;
			this.isShapeless = isShapeless;
		}
		return this;
	}
	
	public EasyItem setNoTab() {
		this.setCreativeTab((CreativeTabs)null);
		return this;
	}
	
	protected void registerRender() {
		System.out.println(this.modID + ":" + this.getModelPath());
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(this, this.getItemMeta(), 
				new ModelResourceLocation(this.modID + ":" + this.getModelPath(), "inventory"));
	}
	
	public boolean registerRecipe() {
		return true;
	}
	
	// Overrides
	@Override
	public void fire(FMLPreInitializationEvent e) {
		System.out.println("Registering: " + this.modID + ":" + this.getModelPath());
		GameRegistry.registerItem(this, this.getModelPath(), this.modID);
		
	}
	@Override
	public void fire(FMLInitializationEvent e) {
		if(e.getSide() == Side.CLIENT)
			registerRender();
		if(this.recipe != null && this.registerRecipe()) {
			if(this.isShapeless)
				GameRegistry.addShapelessRecipe(new ItemStack(this), this.recipe);
			else
				GameRegistry.addRecipe(new ItemStack(this), this.recipe);
		}
	}
	@Override
	public void fire(FMLPostInitializationEvent e) {}
}