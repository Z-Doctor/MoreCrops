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

public class IronCrop {
	public static void load() {
		EasyCrop IronPlant = new EasyCrop("IronCrop", MoreCrops.modid){
			@Override
			public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
				return canUseBonemeal();
			}
			
			@Override
			public boolean canUseBonemeal() {
				return !Config.disableBoneMeal.getBoolean(true);
			}
		};
		
		EasySeed IronSeed = new EasySeed("IronSeed", MoreCrops.modid) {
			@Override
			public boolean registerRecipe() {
				return !Config.disableSeeds.getBoolean(false);
			}
		};
		IronSeed.setRecipe(new Object[] {
			"III", "INI", "III", 'I', Blocks.iron_block, 'N', Items.nether_star
		});
		ZItems.IronSeed = IronSeed;
		
		IronPlant.setCrop(Items.iron_ingot, ZItems.IronSeed);
	}
}
