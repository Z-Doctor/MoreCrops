package zdoctor.morecrops.crops.classes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import zdoctor.morecrops.MoreCrops;
import zdoctor.morecrops.gameregistry.ZItems;
import zdoctor.zcore.common.EasyCrop;
import zdoctor.zcore.common.EasySeed;
import zdoctor.zcore.common.HelperFunctions;

public class DiamondCrop {
	public static void load() {
		EasyCrop DiamondPlant = new EasyCrop("DiamondCrop", MoreCrops.modid){
			@Override
			public boolean canPlaceBlockAt(net.minecraft.world.World worldIn, net.minecraft.util.BlockPos pos) {
				return HelperFunctions.checkSurrondingsFor(Blocks.lava, worldIn, pos) && super.canPlaceBlockAt(worldIn, pos);
			};
		};
		DiamondPlant.growsOn(Blocks.coal_block);
		
		EasySeed DiamondSeed = new EasySeed("DiamondSeed", MoreCrops.modid) {
			@Override
			public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
					EnumFacing side, float hitX, float hitY, float hitZ) {
				if(HelperFunctions.checkSurrondingsFor(Blocks.lava, worldIn, pos))
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
