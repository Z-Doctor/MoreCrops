package zdoctor.morecrops.crops.classes;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import zcore.common.EasyCrop;
import zcore.common.EasySeed;
import zcore.common.HelperFunctions.Scanner;
import zcore.config.Config;
import zcore.gameregistry.ZItems;
import zdoctor.morecrops.MoreCrops;

public class DiamondCrop {
	public static void load() {
		EasyCrop DiamondPlant = new EasyCrop("DiamondCrop", MoreCrops.modid){
			@Override
			public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
				return super.canPlaceBlockAt(worldIn, pos)
					&& Scanner.checkSurrondingsFor(Blocks.lava, worldIn, pos, true);
			};
			@Override
			public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
				return super.canBlockStay(worldIn, pos, state) && Scanner.checkSurrondingsFor(Blocks.lava, worldIn, pos.down(), true);
			}
			@Override
			public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
				return canUseBonemeal();
			}
			
			@Override
			public boolean canUseBonemeal() {
				return !Config.disableBoneMeal.getBoolean(true);
			}
			
			
		};
		DiamondPlant.growsOn(Blocks.coal_block);
		
		EasySeed DiamondSeed = new EasySeed("DiamondSeed", MoreCrops.modid) {
			@Override
			public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
					EnumFacing side, float hitX, float hitY, float hitZ) {
				if(Scanner.checkSurrondingsFor(Blocks.lava, worldIn, pos, true))
					return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
				return false;
			};
		
			@Override
			public boolean registerRecipe() {
				return !Config.disableSeeds.getBoolean(false);
			}
		};
		DiamondSeed.setRecipe(new Object[] {
			"DDD", "DND", "DDD", 'D', Blocks.diamond_block, 'N', Items.nether_star
		});
		ZItems.DiamondSeed = DiamondSeed;
		
		DiamondPlant.setCrop(Items.diamond, ZItems.DiamondSeed);
	}
}
