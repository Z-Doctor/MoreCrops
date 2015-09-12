package zcore.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
public class EasyBlock extends Block implements ISubEvent {
	// Basic info
	protected final String blockModel;
	protected final String modID;
	// Advance
	protected Object[] recipe;
	protected boolean isShapeless;
	// Constructors
	public EasyBlock(String model, String mod) {
		this(model, mod, CreativeTabs.tabDecorations);
	}
	public EasyBlock(String model, String mod, CreativeTabs tab) {
		super(Material.rock);
		this.blockModel = model;
		this.modID = mod;
		
		this.setCreativeTab(tab);
		this.setUnlocalizedName(this.getModelPath());
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypePiston);
		
		CommonProxy.subEvent(this);	
	}
	
	/** Override to set a different path */
	public String getModelPath() {
		return this.blockModel;
	}
	/** Override to change meta */
	public int getBlockMeta() {
		return 0;
	}
	
	/** Shaped by default 
	 * @param recipe - The food's recipe object */
	public EasyBlock setRecipe(Object[] recipe) {
		return this.setRecipe(recipe, false);
	}
	/**
	 * @param recipe - The item's recipe
	 * @param isShapeless - Whether or not the food's recipe is shapeless
	 */
	public EasyBlock setRecipe(Object[] recipe, boolean isShapeless) {
		if(this.recipe == null) {
			this.recipe = recipe;
			this.isShapeless = isShapeless;
		}
		return this;
	}
	
	public Item asItem() {
		return Item.getItemFromBlock(this);
	}
	public EasyBlock setNoTab() {
		this.setCreativeTab((CreativeTabs)null);
		return this;
	}
	
	protected void registerRender() {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(this.asItem(), this.getBlockMeta(), 
				new ModelResourceLocation(this.modID + ":" + this.getModelPath(), "inventory"));
	}
	
	// Overrides
	@Override
	public void fire(FMLPreInitializationEvent e) {
		System.out.println(this.modID + ":" + this.getModelPath());
		GameRegistry.registerBlock (this, this.getModelPath());
	}
	@Override
	public void fire(FMLInitializationEvent e) {
		if(e.getSide() == Side.CLIENT)
			this.registerRender();
		if(this.recipe != null) {
			if(this.isShapeless)
				GameRegistry.addShapelessRecipe(new ItemStack(this), this.recipe);
			else
				GameRegistry.addRecipe(new ItemStack(this), this.recipe);
		}
	}
	@Override
	public void fire(FMLPostInitializationEvent e) {}
	/** Override to change material */
	@Override
	public Material getMaterial() {
		return super.getMaterial();
	}
}