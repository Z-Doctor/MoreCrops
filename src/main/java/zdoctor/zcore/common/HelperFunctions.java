package zdoctor.zcore.common;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class HelperFunctions {
	public static boolean checkSurrondingsFor(Block block, World worldIn, boolean topIsAir, BlockPos pos) {
		if(lookForAtIn(worldIn, block, topIsAir, pos.north(), pos.east(), pos.south(), pos.west()))
			return true;
		else if(lookForAtIn(worldIn, block, topIsAir, pos.north().east(), pos.north().west(), pos.south(),
				pos.south().east(), pos.south().west()))
			return true;
		else
			return false;
	}
	public static boolean checkSurrondingsFor(Block block, World worldIn, BlockPos pos) {
		if(lookForAtIn(worldIn, block, false, pos.north(), pos.east(), pos.south(), pos.west()))
			return true;
		else if(lookForAtIn(worldIn, block, false, pos.north().east(), pos.north().west(), pos.south(),
				pos.south().east(), pos.south().west()))
			return true;
		else
			return false;
	}
	
	public static boolean lookForAtIn(World worldIn, Block block, boolean isAirTop, BlockPos...pos) {
		for(BlockPos bp : pos) {
			if (getAndCompareBlock(worldIn, bp, block))
				if(isAirTop && getAndCompareBlock(worldIn, bp.up(), Blocks.air))
					return true;
				else if(!isAirTop)
					return true;
				
		}
		return false;
	}
	
	public static Block getBlock(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getBlock();
	}
	
	public static boolean getAndCompareBlock(World worldIn, BlockPos pos, Block block){
		return getBlock(worldIn, pos) == block;
	}
	
	public static boolean isCovered(World worldIn, BlockPos pos) {
		if(getBlock(worldIn, pos.up()) != Blocks.air)
			return false;
		return true;
	}
}
