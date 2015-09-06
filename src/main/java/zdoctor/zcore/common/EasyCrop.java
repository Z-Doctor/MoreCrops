package zdoctor.zcore.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zdoctor.zcore.proxy.CommonProxy;

public class EasyCrop extends BlockBush implements IGrowable, ISubEvent {
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
    
    protected Item cropSeed;
    protected Item crop;
    
    private Block farmLand = Blocks.farmland;
    
    // Basic info
 	protected final String cropModel;
 	protected final String modID;
 	// Constructors
 	public EasyCrop(String model, String mod) {
 		this(model, mod, (CreativeTabs)null);
 	}
 	public EasyCrop(String model, String mod, CreativeTabs tab) {
 		this.cropModel = model;
 		this.modID = mod;
 		 		
 		this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
		this.setTickRandomly(true);
		float f = 0.5F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
		this.setHardness(0.0F);
		this.setStepSound(soundTypeGrass);
		this.disableStats();
 		this.setCreativeTab(tab);
 		
 		this.setUnlocalizedName(this.getModelPath());
 		
 		CommonProxy.subEvent(this);
 	}
 	
 	public EasyCrop setCrop(Item crop) {
 		return this.setCrop(crop, (Item)null);
 	}
 	public EasyCrop setCrop(Item crop, Item seed) {
 		this.crop = crop;
 		this.cropSeed = seed;
 		return this;
 	}
 	
 	public EasyCrop growsOn(Block ground) {
 		this.farmLand = ground;
 		return this;
 	}
 	
 	/** Override to set a different path */
 	public String getModelPath() {
 		return "crop/" + this.cropModel;
 	}
 	/** Override to change meta */
 	public int getBlockMeta() {
 		return 0;
 	}
 	
 	public Item asItem() {
 		return Item.getItemFromBlock(this);
 	}
 	/** Override this and register you crops model files to change */
 	protected void registerRender() {
 		for(int i=0;i<=7;i++){
 			this.registerRender(this.getModelPath() + "_stage" + i);
 		}
 	}
 	protected void registerRender(String crop) {
 		System.out.println("Rendering: " + this.modID + ":" + crop);
 		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(this.asItem(), this.getBlockMeta(), 
 			new ModelResourceLocation(this.modID + ":" + crop));
 	}
 	
 	// Overrides
 	@Override
	public void fire(FMLPreInitializationEvent e) {
		System.out.println("Registering: " + this.modID + ":" + this.getModelPath());
		GameRegistry.registerBlock(this, this.getModelPath());
	}
	@Override
	public void fire(FMLInitializationEvent e) {
		if(e.getSide() == Side.CLIENT)
 			this.registerRender();
	}
	@Override
	public void fire(FMLPostInitializationEvent e) {
		((EasySeed) this.cropSeed).setCrop(this, this.farmLand);
	}
	
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return canPlaceBlockOn(worldIn.getBlockState(pos).getBlock());
	}
	
    /**
     * is the block grass, dirt or farmland
     * override to customize
     */
    protected boolean canPlaceBlockOn(Block ground)
    {
        return ground == this.farmLand;
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);

        if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
        {
            int i = ((Integer)state.getValue(AGE)).intValue();

            if (i < 7)
            {
                float f = getGrowthChance(this, worldIn, pos);

                if (rand.nextInt((int)(25.0F / f) + 1) == 0)
                {
                    worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(i + 1)), 2);
                }
            }
        }
    }

    public void grow(World worldIn, BlockPos pos, IBlockState state)
    {
        int i = ((Integer)state.getValue(AGE)).intValue() + MathHelper.getRandomIntegerInRange(worldIn.rand, 2, 5);

        if (i > 7)
        {
            i = 7;
        }

        worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(i)), 2);
    }

    protected static float getGrowthChance(Block blockIn, World worldIn, BlockPos pos)
    {
        float f = 1.0F;
        BlockPos blockpos1 = pos.down();

        for (int i = -1; i <= 1; ++i)
        {
            for (int j = -1; j <= 1; ++j)
            {
                float f1 = 0.0F;
                IBlockState iblockstate = worldIn.getBlockState(blockpos1.add(i, 0, j));

                if (iblockstate.getBlock().canSustainPlant(worldIn, blockpos1.add(i, 0, j), net.minecraft.util.EnumFacing.UP, (net.minecraftforge.common.IPlantable)blockIn))
                {
                    f1 = 1.0F;

                    if (iblockstate.getBlock().isFertile(worldIn, blockpos1.add(i, 0, j)))
                    {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0)
                {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos2 = pos.north();
        BlockPos blockpos3 = pos.south();
        BlockPos blockpos4 = pos.west();
        BlockPos blockpos5 = pos.east();
        boolean flag = blockIn == worldIn.getBlockState(blockpos4).getBlock() || blockIn == worldIn.getBlockState(blockpos5).getBlock();
        boolean flag1 = blockIn == worldIn.getBlockState(blockpos2).getBlock() || blockIn == worldIn.getBlockState(blockpos3).getBlock();

        if (flag && flag1)
        {
            f /= 2.0F;
        }
        else
        {
            boolean flag2 = blockIn == worldIn.getBlockState(blockpos4.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos5.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos5.south()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.south()).getBlock();

            if (flag2)
            {
                f /= 2.0F;
            }
        }

        return f;
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        return (worldIn.getLight(pos) >= 8 || worldIn.canSeeSky(pos)) && worldIn.getBlockState(pos.down()).getBlock().canSustainPlant(worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this)
			&& canPlaceBlockAt(worldIn, pos.down());
    }

    protected Item getSeed()
    {
        return this.cropSeed;
    }

    protected Item getCrop()
    {
        return this.crop;
    }

    /**
     * Spawns this Block's drops into the World as EntityItems.
     *  
     * @param chance The chance that each Item is actually spawned (1.0 = always, 0.0 = never)
     * @param fortune The player's fortune level
     */
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, 0);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     *  
     * @param fortune the level of the Fortune enchantment on the player's tool
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ((Integer)state.getValue(AGE)).intValue() == 7 ? this.getCrop() : this.getSeed();
    }

    /**
     * Whether this IGrowable can grow
     */
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return ((Integer)state.getValue(AGE)).intValue() < 7;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return this.getSeed();
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        this.grow(worldIn, pos, state);
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(AGE)).intValue();
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {AGE});
    }

    @Override
    public java.util.List<ItemStack> getDrops(net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        java.util.List<ItemStack> ret = super.getDrops(world, pos, state, fortune);
        int age = ((Integer)state.getValue(AGE)).intValue();
        Random rand = world instanceof World ? ((World)world).rand : new Random();

        if (age >= 7)
        {
            int k = 3 + fortune;

            for (int i = 0; i < 3 + fortune; ++i)
            {
                if (rand.nextInt(15) <= age)
                {
                    ret.add(new ItemStack(this.getSeed(), 1, 0));
                }
            }
        }
        return ret;
    }
}
