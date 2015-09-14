package zdoctor.morecrops.crops.classes;

import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import zdoctor.morecrops.MoreCrops;
import zdoctor.morecrops.zcore.common.EasyCrop;
import zdoctor.morecrops.zcore.common.EasyFood;
import zdoctor.morecrops.zcore.common.EasySeed;
import zdoctor.morecrops.zcore.common.EasyStuff.GameAdditions;
import zdoctor.morecrops.zcore.gameregistry.ZItems;

public class StrawberryCrop {
	public static void load() {
		EasyFood Strawberry = new EasyFood("Strawberry", MoreCrops.modid){
			int potionDur = 6;
			@Override
			protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
				Random rand = new Random();
				int num = rand.nextInt(21);
				if(num > 15 && !worldIn.isRemote)
					player.addPotionEffect(new PotionEffect(1, this.potionDur*20));
				super.onFoodEaten(stack, worldIn, player);
			}
		};
		Strawberry.setStats(1, 0.3F);
		ZItems.Strawberry = Strawberry;
		
		EasyCrop StrawberryCrop = new EasyCrop("StrawberryCrop", MoreCrops.modid) {
			@Override
			public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
				List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
				int age = ((Integer)state.getValue(AGE)).intValue();
				Random rand = world instanceof World ? ((World)world).rand : new Random();
		        if (age >= 7){
		        	ret.add(new ItemStack(ZItems.Strawberry, 4));
		        }
				return ret;
			}
		};
		
		
		EasySeed StrawberrySeed = new EasySeed("StrawberrySeed", MoreCrops.modid){
			
		}.setRecipe(new Object[]{ZItems.Strawberry}, true);
		ZItems.StrawberrySeed = StrawberrySeed;
		
		StrawberryCrop.setCrop(ZItems.Strawberry, ZItems.StrawberrySeed);
		
		GameAdditions.addGrassDrop(new ItemStack(ZItems.Strawberry, 1), 4);
	}
}
