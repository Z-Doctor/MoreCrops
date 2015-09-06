package zdoctor.zcore.common;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class EasySeed extends EasyItem implements IPlantable {
	private Block crops = Blocks.wheat;
	/** BlockID of the block the seeds can be planted on. */
	private Block soilBlockID;
	    
	public EasySeed(String model, String mod) {
		this(model, mod, CreativeTabs.tabMaterials);
	}
	public EasySeed(String model, String mod, CreativeTabs tab) {
		super(model, mod, tab);
	}
	/*
	public EasySeed setPlantSpec(Block crop) {
		this.crops = crop;
		return this;
	}
	public EasySeed setPlantSpec(Block crop, Block soil) {
		this.crops = crop;
		this.setSoil(soil);
		return this;
	}*/
	
	protected EasySeed setCrop(Block crop){
		return this.setCrop(crop, null);
	}
	protected EasySeed setCrop(Block crop, Block soil){
		this.crops = crop;
		this.soilBlockID = soil == null ? Blocks.farmland : soil;
		return this;
	}
	
	public EasySeed getSeed() {
		return this;
	}
	
	private EasySeed setSoil(Block soil) {
		this.soilBlockID = soil;
		return this;
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (side != EnumFacing.UP)
        {
            return false;
        }
        else if (!playerIn.canPlayerEdit(pos.offset(side), side, stack))
        {
            return false;
        }
        else if (worldIn.getBlockState(pos).getBlock() == this.soilBlockID && worldIn.isAirBlock(pos.up()))
        {
            worldIn.setBlockState(pos.up(), this.crops.getDefaultState());
            --stack.stackSize;
            return true;
        }
        else
        {
            return false;
        }
    }

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Crop;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return this.crops.getDefaultState();
	}
	@Override
	public String getModelPath() {
		return "seed/" + this.itemModel;
	}

}
