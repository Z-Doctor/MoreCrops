package zdoctor.zcore.common;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class HelperFunctions {
	/** Looks around the specified block pos for a certain block
	 * @param block - The block being looked for
	 * @param worldIn - The world to look in
	 * @param pos - The position of the block to look around
	 * @param topIsAir - Can the block being looked for be covered
	 */
	public static boolean checkSurrondingsFor(Block block, World worldIn, BlockPos pos, boolean topIsAir) {
		if(lookForABlockAt(block, worldIn, topIsAir, pos.north(), pos.east(), pos.south(), pos.west()))
			return true;
		else if(lookForABlockAt(block, worldIn, topIsAir, pos.north().east(), pos.north().west(), pos.south(),
				pos.south().east(), pos.south().west()))
			return true;
		else
			return false;
	}
	/**
	 * 
	 * @param worldIn - The world to look in
	 * @param block - The block being looked for
	 * @param topIsAir - Can the block be covered
	 * @param pos - The position(s) to look in
	 */
	public static boolean lookForABlockAt(Block block, World worldIn, boolean topIsAir, BlockPos...pos) {
		for(BlockPos bp : pos) {
			if (getAndCompareBlock(worldIn, bp, block))
				if(topIsAir && getAndCompareBlock(worldIn, bp.up(), Blocks.air))
					return true;
				else if(!topIsAir)
					return true;
				
		}
		return false;
	}
	/**
	 * Gets the block and checks against a list of blocks
	 * @param worldIn - The world to look in
	 * @param pos - The position of the block
	 * @param blockList - The blocks to look for
	 */
	public static boolean getAndCompareBlock(World worldIn, BlockPos pos, Block...blockList){
		Block block = getBlock(worldIn, pos);
		for(Block b : blockList) {
			if(block == b)
				return true;
		}
		return false;
	}
	
	/** Returns the block from the position and world given
	 * @param worldIn - The world to check in 
	 * @param pos - The position of the block
	 */
	public static Block getBlock(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getBlock();
	}

	/**
	 * Checks to see if top block is air
	 * @param worldIn - The world to look in
	 * @param posList - The position(s) to check
	 */
	public static boolean isCovered(World worldIn, BlockPos...posList) {
		for(BlockPos pos : posList) {
			if(getBlock(worldIn, pos.up())!= Blocks.air)
				return false;
		}
		return true;
	}
	/**
	 * Checks if the block is covered by a certain block (from a list of blocks)
	 * @param worldIn
	 * @param pos
	 * @param blockList
	 */
	public static boolean isCoveredBy(World worldIn, BlockPos pos, Block...blockList) {
		Block temp = getBlock(worldIn, pos.up());
		for(Block block : blockList) {
			if(temp == block)
				return true;
		}
		return false;
	}
	/** Changes the blocks within a certain radius to another
	 * @param worldIn - The world to look in
	 * @param center - The position of the center block
	 * @param newState - The state to change them to
	 * @param radius - Blocks from the center
	 * @param changeCenter - Should the center block be changed
	 * @param blockList - The blocks to change
	 * @return The number of blocks changed
	 */
	public static int changeBlocksInRadiusTo(World worldIn, BlockPos center, IBlockState newState, int radius, 
			boolean changeCenter, Block...blockList) {
		ArrayList<BlockPos> posList = scanRadiusFor(worldIn, center, radius, blockList, changeCenter);
		changeTheseTo(worldIn, newState, posList);
		return posList.size();
	}
	/** Changes the blocks within a certain radius to another
	 * @param worldIn - The world to look in
	 * @param center - The position of the center block
	 * @param block - The block to change them to
	 * @param radius - Blocks from the center
	 * @param changeCenter - Should the center block be changed
	 * @param blockList - The blocks to change
	 * @return The number of blocks changed
	 */
	public static int changeBlocksInRadiusTo(World worldIn, BlockPos center, Block block, int radius, 
			boolean changeCenter, Block...blockList) {
		ArrayList<BlockPos> posList = scanRadiusFor(worldIn, center, radius, blockList, changeCenter);
		changeTheseTo(worldIn, block.getDefaultState(), posList);
		return posList.size();
	}
	// Scans the radius for a certain block 
	protected static ArrayList<BlockPos> scanRadiusFor(World worldIn, BlockPos center, int r, Block[] blockList,
			boolean includeCenter) {
		ArrayList<BlockPos> newList = new ArrayList<BlockPos>();
		int d = r*2+1;
		for(int y=d-1; y>=0; y--){
			for(int x=d-1; x>=0; x--){
				BlockPos temp = center.north(y-r).west(x-r);
				if(y-r ==0 && x-r == 0) {
					if(includeCenter)
						newList.add(temp);
				}
				else if(blockInList(getBlock(worldIn, temp), blockList))
					newList.add(temp);
			}
		}
		return newList;
	}
	/** Changes the blocks in the position(s) to the new state
	 * @param worldIn - The world to look in
	 * @param posList - The position(s)
	 * @param newState - The block state to change them to
	 */
	public static void changeTheseTo(World worldIn, IBlockState newState, ArrayList<BlockPos> posList) {
		for(BlockPos bp : posList){
			worldIn.setBlockState(bp, newState);
		}
		
	}
	/** Changes the blocks in the position(s) to the new state
	 * @param worldIn - The world to look in
	 * @param posList - The position(s)
	 * @param block - The block to change them to
	 */
	public static void changeTheseTo(World worldIn, Block block, ArrayList<BlockPos> posList) {
		changeTheseTo(worldIn, block.getDefaultState(), posList);
	}
	/** Changes the blocks in the position(s) to the new state
	 * @param worldIn - The world to look in
	 * @param posList - The position(s)
	 * @param block - The block to change them to
	 */
	public static void changeTheseTo(World worldIn, Block block, BlockPos...posList) {
		for(BlockPos bp : posList){
			worldIn.setBlockState(bp, block.getDefaultState());
		}
	}
	/** Changes the blocks in the position
	 * @param worldIn - The world to look in
	 * @param newState - The Block State to change them to
	 * @param block - The block to change it into
	 */
	public static void changeThisTo(World worldIn, IBlockState newState, BlockPos pos) {
		worldIn.setBlockState(pos, newState);
	}
	/** Re
	/** Changes the blocks in the position
	 * @param worldIn - The world to look in
	 * @param posList - The position
	 * @param block - The block to change it into
	 */
	public static void changeThisTo(World worldIn, Block block, BlockPos pos) {
		changeThisTo(worldIn, block.getDefaultState(), pos);
	}
	/** Returns whether the block was found in the list
	 * @param block - The block to check
	 * @param blockList - The list to check against
	 */
	public static boolean blockInList(Block block, Block...blockList) {
		for(Block b : blockList) {
			if(block == b) {
				return true;
			}
		}
		return false;
	}
	public static int getBlockProperty(World worldIn, BlockPos pos, PropertyInteger property) {
		return (Integer)(worldIn.getBlockState(pos).getValue(property));
	}
	public static void setBlockProperty(World worldIn, BlockPos pos, PropertyInteger property, int value) {
		worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(property, value));
	}
}
