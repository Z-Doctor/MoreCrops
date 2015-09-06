/**
 * May seem a bit complicated, but creating the crops is quite easy, just adding more to them
 * without a separate class can be a bit confusing.
 */
package zdoctor.morecrops.crops;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import zdoctor.morecrops.MoreCrops;
import zdoctor.zcore.common.EasyCrop;
import zdoctor.zcore.common.EasySeed;
import zdoctor.zcore.common.HelperFunctions;

public class Crops {
	public static void load() {
		// Diamond Crop
		new EasyCrop("DiamondCrop", MoreCrops.modid){
			@Override
			public boolean canPlaceBlockAt(net.minecraft.world.World worldIn, net.minecraft.util.BlockPos pos) {
				return HelperFunctions.checkSurrondingsFor(Blocks.lava, worldIn, pos) && super.canPlaceBlockAt(worldIn, pos);
			};}.growsOn(Blocks.coal_block).setCrop(Items.diamond, new EasySeed("DiamondSeed", MoreCrops.modid) {
					@Override
					public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
							EnumFacing side, float hitX, float hitY, float hitZ) {
						if(HelperFunctions.checkSurrondingsFor(Blocks.lava, worldIn, pos))
								return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
						return false;
					}}.setRecipe(new Object[] {
						"DDD", "DND", "DDD", 'D', Blocks.diamond_block, 'N', Items.nether_star
				})).growsOn(Blocks.coal_block);
		// Emerald Crop
		new EasyCrop("EmeraldCrop", MoreCrops.modid).setCrop(Items.emerald,
				new EasySeed("EmeraldSeed", MoreCrops.modid).setRecipe(new Object[] {
						"EEE", "ENE", "EEE", 'E', Blocks.emerald_block, 'N', Items.nether_star
				}));
		//Iron Crop
		new EasyCrop("IronCrop", MoreCrops.modid).setCrop(Items.iron_ingot,
				new EasySeed("IronSeed", MoreCrops.modid).setRecipe(new Object[] {
						"III", "INI", "III", 'I', Blocks.iron_block, 'N', Items.nether_star
				}));
		//Gold Crop
		new EasyCrop("GoldCrop", MoreCrops.modid).setCrop(Items.gold_ingot,
				new EasySeed("GoldSeed", MoreCrops.modid).setRecipe(new Object[] {
						"GGG", "GNG", "GGG", 'G', Blocks.gold_block, 'N', Items.nether_star
				}));
	}
}
