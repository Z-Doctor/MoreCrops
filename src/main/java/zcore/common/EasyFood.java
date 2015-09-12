package zcore.common;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
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
public class EasyFood extends ItemFood implements ISubEvent {
	// Basic info
	protected final String foodModel;
	protected final String modID;
	// Advance
	protected Object[] recipe;
	protected boolean isShapeless;
	
    /** The amount this food item heals the player. */
	protected int healAmount;
	protected float saturationModifier;
    /** Whether wolves like this food (true for raw and cooked pork chop). */
	protected boolean isWolfsFavoriteMeat;
	
	// Constructors
	public EasyFood(String model, String mod) {
		this(model, mod, CreativeTabs.tabFood);
	}
	public EasyFood(String model, String mod, CreativeTabs tab) {
		super(4, 0.3F, false);
		this.foodModel = "food/" + model;
		this.modID = mod;
		
		this.setCreativeTab(tab);
		this.setUnlocalizedName(this.getModelPath());
		
		this.healAmount = 4;
		this.saturationModifier = 0.3F;
		this.isWolfsFavoriteMeat = false;
		
		CommonProxy.subEvent(this);	
	}
	
	// Food Stats
	public EasyFood setStats(int amount, float saturation){
		return this.setStats(amount, saturation, false);
	}
	public EasyFood setStats(int amount, float saturation, boolean isWolfFood){
		this.healAmount = amount;
		this.saturationModifier = saturation;
		this.isWolfsFavoriteMeat = isWolfFood;
		return this;
	}
	
	/** Override to set a different path */
	public String getModelPath() {
		return this.foodModel;
	}
	/** Override to change meta */
	public int getFoodMeta() {
		return 0;
	}
	
	/** Shaped by default 
	 * @param recipe - The food's recipe object */
	public EasyFood setRecipe(Object[] recipe) {
		return this.setRecipe(recipe, false);
	}
	/**
	 * @param recipe - The item's recipe
	 * @param isShapeless - Whether or not the food's recipe is shapeless
	 */
	public EasyFood setRecipe(Object[] recipe, boolean isShapeless) {
		if(this.recipe == null) {
			this.recipe = recipe;
			this.isShapeless = isShapeless;
		}
		return this;
	}
	
	public EasyFood setNoTab() {
		this.setCreativeTab((CreativeTabs)null);
		return this;
	}
	
	// Overrides
	@Override
	public void fire(FMLPreInitializationEvent e) {
		System.out.println(this.modID + ":" + this.getModelPath());
		GameRegistry.registerItem(this, this.getModelPath(), this.modID);
	}
	@Override
	public void fire(FMLInitializationEvent e) {
		if(e.getSide() == Side.CLIENT)
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(this, this.getFoodMeta(), 
				new ModelResourceLocation(this.modID + ":" + this.getModelPath(), "inventory"));
		if(this.recipe != null) {
			if(this.isShapeless)
				GameRegistry.addShapelessRecipe(new ItemStack(this), this.recipe);
			else
				GameRegistry.addRecipe(new ItemStack(this), this.recipe);
		}
	}
	@Override
	public void fire(FMLPostInitializationEvent e) {}
	
	@Override
	public boolean isWolfsFavoriteMeat() {
		return this.isWolfsFavoriteMeat;
	}
	@Override
	public int getHealAmount(ItemStack stack) {
		return this.healAmount;
	}
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		return super.onItemUseFinish(stack, worldIn, playerIn);
	}
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		super.onFoodEaten(stack, worldIn, player);
	}
	/** Override to make it take longer */
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return super.getMaxItemUseDuration(stack);
	}
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return super.getItemUseAction(stack);
	}
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		return super.onItemRightClick(itemStackIn, worldIn, playerIn);
	}
	@Override
	public float getSaturationModifier(ItemStack stack) {
		return this.saturationModifier;
	}
	@Override
	public ItemFood setPotionEffect(int id, int duration, int amplifier, float probability) {
		return super.setPotionEffect(id, duration, amplifier, probability);
	}
}
