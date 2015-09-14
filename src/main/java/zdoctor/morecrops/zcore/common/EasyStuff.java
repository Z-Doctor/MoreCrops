package zdoctor.morecrops.zcore.common;

import java.util.Iterator;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EasyStuff {
	public static class EventRegistry {

		public static void registerToFMLBus(Class obj) {
			try {
				FMLCommonHandler.instance().bus().register(obj.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		public static void registerToForgeEBus(Class obj) {
			try {
				MinecraftForge.EVENT_BUS.register(obj.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static class GameAdditions {	
		/** A Seed's rarity is 10. Things above are more common, things below are less common.
		 * @param iStack - What is to be dropped
		 * @param rarity - How rare or common the drop is
		 */
		public static void addGrassDrop(ItemStack iStack, int rarity) {
			MinecraftForge.addGrassSeed(iStack, rarity);
		}
		/**
		 * A Golden Apple's rarity is 1 and Bread is 100.
		 * @param iStack - What is to be found
		 * @param rariry - How rare or common this treasure is
		 * @param min
		 * @param max
		 */
		public static void addDungeonLoot(ItemStack iStack, int rariry, int min, int max) {
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(iStack, min, max, rariry));
		}
		
		/**
		 * A Golden Apple's rarity is 1 and Bread is 100.
		 * @param iStack - What is to be found
		 * @param rariry - How rare or common this treasure is
		 * @param min
		 * @param max
		 */
		public static void addVillageLoot(ItemStack iStack, int rariry, int min, int max) {
			ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(iStack, min, max, rariry));
		}
		/**
		 * A Golden Apple's rarity is 1 and Bread is 100.
		 * Use ChestGenHooks to pick a category
		 * @param catergory - The type of loot.
		 * @param iStack - What is to be found
		 * @param rariry - How rare or common this treasure is
		 * @param min
		 * @param max
		 */
		public static void addCustomLoot(String category, ItemStack iStack, int rariry, int min, int max) {
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(iStack, min, max, rariry));
		}
	}
	/**
	 * A collection of functions for quick game development
	 * @author Z_Doctor
	 */
	public static class GameModifications {
		/**
		 * Removes given item(s) from CraftingManager's Recipe List
		 * @param itemList - A single or a collection of items to be removed (item1, item2, item3, etc)
		 */
		public static void removeItemRecipe(Item...itemList) {
			for(Item item : itemList) {
				Iterator<IRecipe> RecipeRegistry = CraftingManager.getInstance().getRecipeList().iterator();
				while (RecipeRegistry.hasNext()){
					IRecipe recipe = RecipeRegistry.next();
					if(recipe == null)
						continue;
					ItemStack output = recipe.getRecipeOutput();
					if(output != null && item == output.getItem())
						RecipeRegistry.remove();
				}
			}
		}
	
		public static void registerItemRecipe(Item item, Object[] recipe, boolean shapeless) {
			if(item != null && recipe != null) {
				if(shapeless)
					GameRegistry.addShapelessRecipe(new ItemStack(item), recipe);
				else
					GameRegistry.addRecipe(new ItemStack(item), recipe);
			}
		}
	}

	public static class RegistryChecker {
		public static boolean checkForRecipe(Item item) {
			Iterator<IRecipe> RecipeRegistry = CraftingManager.getInstance().getRecipeList().iterator();
			while (RecipeRegistry.hasNext()){
				IRecipe recipe = RecipeRegistry.next();
				if(recipe == null)
					continue;
				ItemStack output = recipe.getRecipeOutput();
				if(output != null && item == output.getItem())
					return true;
			}
			return false;
		}
	}

}
