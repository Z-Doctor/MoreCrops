package zdoctor.morecrops.crops.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import zdoctor.morecrops.MoreCrops;
import zdoctor.morecrops.zcore.common.EasyCrop;
import zdoctor.morecrops.zcore.common.EasySeed;
import zdoctor.morecrops.zcore.common.HelperFunctions.Scanners;
import zdoctor.morecrops.zcore.config.Config;
import zdoctor.morecrops.zcore.gameregistry.ZItems;

public class GoldCrop {
	public static void load() {
		EasyCrop GoldPlant = new EasyCrop("GoldCrop", MoreCrops.modid){
			@Override
			public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
				return !Config.disableBoneMeal.getBoolean(true) && willItGrow(worldIn, pos, state);
			}
			@Override
			public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
				return this.getFarmland() == worldIn.getBlockState(pos.down()).getBlock();
			}
			@Override
			public boolean willItGrow(World worldIn, BlockPos pos, IBlockState state) {
				boolean check = Config.growsInPeaceful.getBoolean(false) || worldIn.getDifficulty() != EnumDifficulty.PEACEFUL;
				check = check && (!Config.growsOnlyInNether.getBoolean(true) || worldIn.provider.getDimensionId() == -1);
				check = check && super.canBlockStay(worldIn, pos, state) && Scanners.checkSurrondingsFor(Blocks.lava, worldIn, pos.down(), true);
				return check;
			}
			@Override
			public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
				if(willItGrow(worldIn, pos, state))
					super.updateTick(worldIn, pos, state, rand);
			}
			@Override
			public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
				java.util.List<ItemStack> ret = new ArrayList<ItemStack>();
				int age = ((Integer)state.getValue(AGE)).intValue();
				Random rand = world instanceof World ? ((World)world).rand : new Random();
				
				ret.add(new ItemStack(this.getSeed(), 1));
				
				if (age >= 7) {
					ret.add(new ItemStack(Items.gold_ingot, 1));
					if (rand.nextInt(100)+1 <= Config.gSeedDropRate.getInt(40)){
						ret.add(new ItemStack(this.getSeed(), 1));
						ret.add(new ItemStack(Items.gold_nugget, 1));
					}
				}
				return ret;
			}
		};
		GoldPlant.setFarmland(Blocks.netherrack);
		
		EasySeed GoldSeed = new EasySeed("GoldSeed", MoreCrops.modid) {
			@Override
			public boolean registerRecipe() {
				return !Config.disableGSeeds.getBoolean(false);
			}
		};
		GoldSeed.setRecipe(new Object[] {
			"GGG", "GNG", "GGG", 'G', Blocks.gold_block, 'N', Items.nether_star
		});
		ZItems.GoldSeed = GoldSeed;
		
		GoldPlant.setCrop(Items.gold_ingot, ZItems.GoldSeed);
	}
}
