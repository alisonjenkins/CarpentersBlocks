package carpentersblocks.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import carpentersblocks.CarpentersBlocks;
import carpentersblocks.data.Slab;
import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.BlockProperties;
import carpentersblocks.util.handler.IconHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCarpentersBlock extends BlockBase
{
	
    public BlockCarpentersBlock(int blockID)
    {
        super(blockID, Material.wood);
        this.setHardness(0.2F);
        this.setUnlocalizedName("blockCarpentersBlock");
		this.setCreativeTab(CarpentersBlocks.tabCarpentersBlocks);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister iconRegister)
    {
		this.blockIcon = IconHandler.icon_stairs;
    }
    
    @Override
	/**
	 * Alter type.
	 */
	protected boolean onHammerLeftClick(TECarpentersBlock TE, EntityPlayer entityPlayer)
    {
    	int data = BlockProperties.getData(TE);
    	
    	if (++data > Slab.SLAB_Z_POS)
    		data = Slab.BLOCK_FULL;
    	
		BlockProperties.setData(TE, data);
    	
		return true;
	}

    @Override
	/**
	 * Alternate between full 1m cube and slab.
	 */
	protected boolean onHammerRightClick(TECarpentersBlock TE, EntityPlayer entityPlayer, int side)
    {
    	int data = BlockProperties.getData(TE);
    	
    	if (data == Slab.BLOCK_FULL) {
    		switch (side)
    		{
    		case 0:
    			data = Slab.SLAB_Y_POS;
    			break;
    		case 1:
    			data = Slab.SLAB_Y_NEG;
    			break;
    		case 2:
    			data = Slab.SLAB_Z_POS;
    			break;
    		case 3:
    			data = Slab.SLAB_Z_NEG;
    			break;
    		case 4:
    			data = Slab.SLAB_X_POS;
    			break;
    		case 5:
    			data = Slab.SLAB_X_NEG;
    			break;
    		}
    	} else {
    		data = Slab.BLOCK_FULL;
    	}
    	
		BlockProperties.setData(TE, data);
    	
		return true;
	}
    
    @Override
	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
    	TECarpentersBlock TE = (TECarpentersBlock) world.getBlockTileEntity(x, y, z);

    	int data = BlockProperties.getData(TE);

    	float[] bounds = { 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F };

    	switch (data) {
	    	case Slab.SLAB_X_NEG:
	    		bounds = new float[] { 0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F };
	    		break;
	    	case Slab.SLAB_X_POS:
	    		bounds = new float[] { 0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F };
	    		break;
	    	case Slab.SLAB_Y_NEG:
	    		bounds = new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F };
	    		break;
	    	case Slab.SLAB_Y_POS:
	    		bounds = new float[] { 0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F };
	    		break;
	    	case Slab.SLAB_Z_NEG:
	    		bounds = new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F };
	    		break;
	    	case Slab.SLAB_Z_POS:
	    		bounds = new float[] { 0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F };
	    		break;
    	}

    	this.setBlockBounds(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
    }
    
    @Override
    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity entity)
    {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
    }
	
    @Override
	/**
	 * Called when the block is placed in the world.
	 */
	public void auxiliaryOnBlockPlacedBy(TECarpentersBlock TE, World world, int x, int y, int z, EntityLiving entityLiving, ItemStack itemStack)
	{
		int data = Slab.BLOCK_FULL;
		
		// If shift key is down, skip auto-orientation
		if (!entityLiving.isSneaking())
		{
			/*
			 * Match block type with adjacent type if possible
			 */
			TECarpentersBlock TE_YN = world.getBlockId(x, y - 1, z) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x, y - 1, z) : null;
			TECarpentersBlock TE_YP = world.getBlockId(x, y + 1, z) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x, y + 1, z) : null;
			TECarpentersBlock TE_XN = world.getBlockId(x - 1, y, z) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x - 1, y, z) : null;
			TECarpentersBlock TE_XP = world.getBlockId(x + 1, y, z) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x + 1, y, z) : null;
			TECarpentersBlock TE_ZN = world.getBlockId(x, y, z - 1) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x, y, z - 1) : null;
			TECarpentersBlock TE_ZP = world.getBlockId(x, y, z + 1) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x, y, z + 1) : null;
			
			if (TE_YN != null)
				data = BlockProperties.getData(TE_YN);
			else if (TE_YP != null)
				data = BlockProperties.getData(TE_YP);
			else if (TE_XN != null)
				data = BlockProperties.getData(TE_XN);
			else if (TE_XP != null)
				data = BlockProperties.getData(TE_XP);
			else if (TE_ZN != null)
				data = BlockProperties.getData(TE_ZN);
			else if (TE_ZP != null)
				data = BlockProperties.getData(TE_ZP);
		}
		
		BlockProperties.setData(TE, data);
	}
	
    @Override
    /**
     * Return true if the block is a normal, solid cube.  This
     * determines indirect power state, entity ejection from blocks, and a few
     * others.
     */
	public boolean isBlockNormalCube(World world, int x, int y, int z)
    {
    	TECarpentersBlock TE = (TECarpentersBlock) world.getBlockTileEntity(x, y, z);
    	int data = BlockProperties.getData(TE);

    	if (data == Slab.BLOCK_FULL) {
			return true;
    	} else {
    		return false;
    	}
    }
	
    @Override
	/**
	 * Checks if the block is a solid face on the given side, used by placement logic.
	 */
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
	{
		TECarpentersBlock TE = (TECarpentersBlock)world.getBlockTileEntity(x, y, z);

		if (isBlockSolid(world, x, y, z))
		{	
			int data = BlockProperties.getData(TE);

			if (data == Slab.BLOCK_FULL)
				return true;
			else if (data == Slab.SLAB_Y_NEG && side == ForgeDirection.DOWN)
				return true;
			else if (data == Slab.SLAB_Y_POS && side == ForgeDirection.UP)
				return true;
			else if (data == Slab.SLAB_Z_NEG && side == ForgeDirection.NORTH)
				return true;
			else if (data == Slab.SLAB_Z_POS && side == ForgeDirection.SOUTH)
				return true;
			else if (data == Slab.SLAB_X_NEG && side == ForgeDirection.WEST)
				return true;
			else if (data == Slab.SLAB_X_POS && side == ForgeDirection.EAST)
				return true;
		}

		return false;
	}
    
    @Override
	@SideOnly(Side.CLIENT)
    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: world, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
    	if (world.getBlockId(x, y, z) == this.blockID)
    	{
    		ForgeDirection side_adj = ForgeDirection.getOrientation(ForgeDirection.OPPOSITES[side]);

    		TECarpentersBlock TE_adj = (TECarpentersBlock) world.getBlockTileEntity(x, y, z);
    		TECarpentersBlock TE_src = (TECarpentersBlock) world.getBlockTileEntity(x + side_adj.offsetX, y + side_adj.offsetY, z + side_adj.offsetZ);

    		if (haveSharedFaces(TE_adj, TE_src, side))
    			return BlockProperties.shouldRenderSharedFaceBasedOnCovers(TE_adj, TE_src);
    		else
    			return true;
    	}
    	
    	return super.shouldSideBeRendered(world, x, y, z, side);
    }
    
    /**
     * Compares dimensions and coordinates of two opposite
     * sides to determine whether they share faces.
     */
    private boolean haveSharedFaces(TECarpentersBlock TE_adj, TECarpentersBlock TE_src, int side)
    {
    	Block block_src = Block.blocksList[TE_src.worldObj.getBlockId(TE_src.xCoord, TE_src.yCoord, TE_src.zCoord)];
    	
    	this.setBlockBoundsBasedOnState(TE_src.worldObj, TE_src.xCoord, TE_src.yCoord, TE_src.zCoord); 
    	
    	double[] bounds_src = new double[]
    	{
    		block_src.getBlockBoundsMinX(),
    		block_src.getBlockBoundsMinY(),
    		block_src.getBlockBoundsMinZ(),
    		block_src.getBlockBoundsMaxX(),
    		block_src.getBlockBoundsMaxY(),
    		block_src.getBlockBoundsMaxZ()
    	};   	
    	
    	this.setBlockBoundsBasedOnState(TE_adj.worldObj, TE_adj.xCoord, TE_adj.yCoord, TE_adj.zCoord);
    	
    	/*
    	 * Check whether faces meet and their dimensions match.
    	 */
    	switch (side)
    	{
	    	case 0:
	            /** -Y */
	    		return	this.maxY == 1.0D &&
	    				bounds_src[1] == 0.0D &&
	    				this.minX == bounds_src[0] &&
	    				this.maxX == bounds_src[3] &&
	    				this.minZ == bounds_src[2] &&
	    				this.maxZ == bounds_src[5];
	    	case 1:
	            /** +Y */
	    		return	this.minY == 0.0D &&
						bounds_src[4] == 1.0D &&
						this.minX == bounds_src[0] &&
						this.maxX == bounds_src[3] &&
						this.minZ == bounds_src[2] &&
						this.maxZ == bounds_src[5];
	    	case 2:
	            /** -Z */
	    		return	this.maxZ == 1.0D &&
						bounds_src[2] == 0.0D &&
						this.minX == bounds_src[0] &&
						this.maxX == bounds_src[3] &&
						this.minY == bounds_src[1] &&
						this.maxY == bounds_src[4];
	    	case 3:
	            /** +Z */
	    		return	this.minZ == 0.0D &&
						bounds_src[5] == 1.0D &&
						this.minX == bounds_src[0] &&
						this.maxX == bounds_src[3] &&
						this.minY == bounds_src[1] &&
						this.maxY == bounds_src[4];
	    	case 4:
	            /** -X */
	    		return	this.maxX == 1.0D &&
						bounds_src[0] == 0.0D &&
						this.minY == bounds_src[1] &&
						this.maxY == bounds_src[4] &&
						this.minZ == bounds_src[2] &&
						this.maxZ == bounds_src[5];
	    	case 5:
	            /** +X */
	    		return	this.minX == 0.0D &&
						bounds_src[3] == 1.0D &&
						this.minY == bounds_src[1] &&
						this.maxY == bounds_src[4] &&
						this.minZ == bounds_src[2] &&
						this.maxZ == bounds_src[5];
	    	default:
	    		return false;
    	}
    }
	
    @Override
    /**
     * Returns whether block can support cover on side.
     */
	public boolean canCoverSide(TECarpentersBlock TE, World world, int x, int y, int z, int side)
    {
    	return true;
    }
    
    @Override
	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType()
	{
		return CarpentersBlocks.carpentersBlockRenderID;
	}

}
