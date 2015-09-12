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

public class GoldCrop {
	public static void load() {
		EasyCrop GoldPlant = new EasyCrop("GoldCrop", MoreCrops.modid){
			@Override
			public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
				return canUseBonemeal();
			}
			
			@Override
			public boolean canUseBonemeal() {
				return !Config.disableBoneMeal.getBoolean(true);
			}
		};
		
		EasySeed GoldSeed = new EasySeed("GoldSeed", MoreCrops.modid) {
			@Override
			public boolean registerRecipe() {
				return !Config.disableSeeds.getBoolean(false);
			}
		};
		GoldSeed.setRecipe(new Object[] {
			"GGG", "GNG", "GGG", 'G', Blocks.gold_block, 'N', Items.nether_star
		});
		ZItems.GoldSeed = GoldSeed;
		
		GoldPlant.setCrop(Items.gold_ingot, ZItems.GoldSeed);
	}
}
