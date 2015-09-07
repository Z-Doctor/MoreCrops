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
import net.minecraftforge.common.config.Configuration;
import zdoctor.morecrops.GuiFactoryMoreCrops;
import zdoctor.morecrops.MoreCrops;
import zdoctor.morecrops.gameregistry.ZItems;
import zdoctor.zcore.common.EasyCrop;
import zdoctor.zcore.common.EasySeed;
import zdoctor.zcore.common.HelperFunctions;

public class DiamondCrop {
	public static void load() {
		EasyCrop DiamondPlant = new EasyCrop("DiamondCrop", MoreCrops.modid){
			@Override
			public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
				return super.canPlaceBlockAt(worldIn, pos)
					&& HelperFunctions.checkSurrondingsFor(Blocks.lava, worldIn, true, pos);
			};
			@Override
			public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
				return super.canBlockStay(worldIn, pos, state) && HelperFunctions.checkSurrondingsFor(Blocks.lava, worldIn, true, pos.down());
			}
			@Override
			public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
				return GuiFactoryMoreCrops.config.getBoolean("Allow Bone Meal Use", Configuration.CATEGORY_GENERAL, 
						false, "Bone Meal Use");
			}
		};
		DiamondPlant.growsOn(Blocks.coal_block);
		
		EasySeed DiamondSeed = new EasySeed("DiamondSeed", MoreCrops.modid) {
			@Override
			public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
					EnumFacing side, float hitX, float hitY, float hitZ) {
				if(HelperFunctions.checkSurrondingsFor(Blocks.lava, worldIn, true, pos))
					return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
				return false;
			};
		};
		DiamondSeed.setRecipe(new Object[] {
			"DDD", "DND", "DDD", 'D', Blocks.diamond_block, 'N', Items.nether_star
		});
		ZItems.DiamondSeed = DiamondSeed;
		
		DiamondPlant.setCrop(Items.diamond, ZItems.DiamondSeed);
	}
}
