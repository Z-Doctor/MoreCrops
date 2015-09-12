package zdoctor.morecrops.crops.classes;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import zcore.common.EasyCrop;
import zcore.common.EasySeed;
import zcore.config.Config;
import zcore.gameregistry.ZItems;
import zdoctor.morecrops.MoreCrops;

public class EmeraldCrop {
	public static void load() {
		EasyCrop EmeraldPlant = new EasyCrop("EmeraldCrop", MoreCrops.modid){
			@Override
			public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
				return canUseBonemeal();
			}
			
			@Override
			public boolean canUseBonemeal() {
				return !Config.disableBoneMeal.getBoolean(true);
			}
		};
		
		EasySeed EmeraldSeed = new EasySeed("EmeraldSeed", MoreCrops.modid) {
			@Override
			public boolean registerRecipe() {
				return !Config.disableSeeds.getBoolean(false);
			}
		};
		EmeraldSeed.setRecipe(new Object[] {
				"EEE", "ENE", "EEE", 'E', Blocks.emerald_block, 'N', Items.nether_star
		});
		ZItems.EmeraldSeed = EmeraldSeed;
		
		EmeraldPlant.setCrop(Items.emerald, ZItems.EmeraldSeed);
				
	}
}
