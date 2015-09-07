package zdoctor.morecrops.crops.classes;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import zdoctor.morecrops.GuiFactoryMoreCrops;
import zdoctor.morecrops.MoreCrops;
import zdoctor.morecrops.gameregistry.ZItems;
import zdoctor.zcore.common.EasyCrop;
import zdoctor.zcore.common.EasySeed;

public class EmeraldCrop {
	public static void load() {
		EasyCrop EmeraldPlant = new EasyCrop("EmeraldCrop", MoreCrops.modid){
			@Override
			public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
				return GuiFactoryMoreCrops.config.getBoolean("Allow Bone Meal Use", Configuration.CATEGORY_GENERAL, 
						false, "Bone Meal Use");
			}
		};
		
		EasySeed EmeraldSeed = new EasySeed("EmeraldSeed", MoreCrops.modid);
		EmeraldSeed.setRecipe(new Object[] {
				"EEE", "ENE", "EEE", 'E', Blocks.emerald_block, 'N', Items.nether_star
		});
		ZItems.EmeraldSeed = EmeraldSeed;
		
		EmeraldPlant.setCrop(Items.emerald, ZItems.EmeraldSeed);
				
	}
}
