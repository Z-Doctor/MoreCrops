package zdoctor.zcore.common;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class HelperFunctions {
	public static boolean checkSurrondingsFor(Block block, World worldIn, BlockPos pos) {
		if(block == getBlock(worldIn, pos.north()) || block == getBlock(worldIn, pos.east()) || block == getBlock(worldIn, pos.south())
				|| block == getBlock(worldIn, pos.west()))
			return true;
		else if(block == getBlock(worldIn, pos.north().east()) || block == getBlock(worldIn, pos.north().west())
			|| block == getBlock(worldIn, pos.south()) || block == getBlock(worldIn, pos.south().east()) 
			|| block == getBlock(worldIn, pos.south().west()))
			return true;
		else
			return false;
	}
	
	public static Block getBlock(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getBlock();
	}
}
