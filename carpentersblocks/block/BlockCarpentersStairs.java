package carpentersblocks.block;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import carpentersblocks.CarpentersBlocks;
import carpentersblocks.data.Stairs;
import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.BlockProperties;

public class BlockCarpentersStairs extends BlockBase
{

	public BlockCarpentersStairs(int blockID)
	{
		super(blockID, Material.wood);
		this.setHardness(0.2F);
		this.setUnlocalizedName("blockCarpentersStairs");
		this.setCreativeTab(CarpentersBlocks.tabCarpentersBlocks);
        this.func_111022_d("carpentersblocks:stairs/stairs");
	}

    @Override
	/**
	 * Alters block direction.
	 */
	protected boolean onHammerLeftClick(TECarpentersBlock TE, EntityPlayer entityPlayer)
	{
    	int data = BlockProperties.getData(TE);
    	
		/*
		 * Cycle between stair directions based on current type
		 */
		if (Stairs.areSideStairs(data)) {
			if (++data > 3)
				data = 0;
		} else if (Stairs.areNormalStairs(data)) {
			if (Stairs.areNegative(data)) {
				if (++data > 7)
					data = 4;
			} else {
				if (++data > 11)
					data = 8;
			}
		} else if (Stairs.areInteriorCorner(data) && Stairs.areNegative(data)) {
			if ((data += 2) > 19)
				data = 13;
		} else if (Stairs.areExteriorCorner(data) && Stairs.areNegative(data)) {
			if ((data += 2) > 27)
				data = 21;
		} else if (Stairs.areInteriorCorner(data) && Stairs.arePositive(data)) {
			if ((data += 2) > 18)
				data = 12;
		} else if (Stairs.areExteriorCorner(data) && Stairs.arePositive(data)) {
			if ((data += 2) > 26)
				data = 20;
		}
		
		BlockProperties.setData(TE, data);
				
		return true;
	}
	
    @Override
	/**
	 * Alters block type.
	 */
	protected boolean onHammerRightClick(TECarpentersBlock TE, EntityPlayer entityPlayer, int side)
	{
    	int data = BlockProperties.getData(TE);
    	
		/*
		 * Transform stairs to next type
		 */
		if (Stairs.areSideStairs(data)) {
			data = 8;
		} else if (Stairs.areNormalStairs(data) && Stairs.arePositive(data)) {
			data -= 4;
		} else if (Stairs.areNormalStairs(data) && Stairs.areNegative(data)) {
			data = 12;
		} else if (Stairs.areInteriorCorner(data)) {
			if (Stairs.arePositive(data)) {
				data += 1;
			} else {
				if (data == 13 || data == 15) {
					data += 11;
				} else {
					data += 3;
				}
			}
		} else if (Stairs.areExteriorCorner(data)) {
			if (Stairs.arePositive(data)) {
				data += 1;
			} else {
				data = 0;
			}
		}
		
		BlockProperties.setData(TE, data);
		
		return true;
	}
		
	/**
	 * Will return stairs boundaries for data.
	 */
	public float[] genBounds(int box, int data)
	{
		++box;
		
		switch (data) {
		case Stairs.POS_EAST:
			switch (box) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 0.5F };
			default:
				return null;
			}
		case Stairs.POS_NORTH:
			switch (box) {
			case 1:
				return new float[] { 0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 1.0F };
			default:
				return null;
			}
		case Stairs.POS_SOUTH:
			switch (box) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.5F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F };
			default:
				return null;
			}
		case Stairs.POS_WEST:
			switch (box) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F };
			case 2:
				return new float[] { 0.0F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F };
			default:
				return null;
			}
		case Stairs.NEG_EAST:
			switch (box) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 0.5F };
			default:
				return null;
			}
		case Stairs.NEG_NORTH:
			switch (box) {
			case 1:
				return new float[] { 0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.5F, 0.0F, 0.5F, 1.0F, 1.0F };
			default:
				return null;
			}
		case Stairs.NEG_SOUTH:
			switch (box) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.5F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F };
			default:
				return null;
			}
		case Stairs.NEG_WEST:
			switch (box) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F };
			case 2:
				return new float[] { 0.0F, 0.5F, 0.5F, 1.0F, 1.0F, 1.0F };
			default:
				return null;
			}
		case Stairs.INT_POS_NE:
			switch (box) {
			case 1:
				return new float[] { 0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.0F, 0.5F, 0.5F, 1.0F, 1.0F };
			case 3:
				return new float[] { 0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 0.5F };
			default:
				return null;
			}
		case Stairs.INT_POS_NW:
			switch (box) {
			case 1:
				return new float[] { 0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 0.5F };
			case 3:
				return new float[] { 0.0F, 0.0F, 0.5F, 0.5F, 0.5F, 1.0F };
			default:
				return null;
			}
		case Stairs.INT_POS_SE:
			switch (box) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.5F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F };
			case 3:
				return new float[] { 0.5F, 0.0F, 0.0F, 1.0F, 0.5F, 0.5F };
			default:
				return null;
			}
		case Stairs.INT_POS_SW:
			switch (box) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F };
			case 3:
				return new float[] { 0.5F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F };
			default:
				return null;
			}
		case Stairs.INT_NEG_NE:
			switch (box) {
			case 1:
				return new float[] { 0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.0F, 0.5F, 0.5F, 1.0F, 1.0F };
			case 3:
				return new float[] { 0.0F, 0.5F, 0.0F, 0.5F, 1.0F, 0.5F };
			default:
				return null;
			}
		case Stairs.INT_NEG_NW:
			switch (box) {
			case 1:
				return new float[] { 0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 0.5F };
			case 3:
				return new float[] { 0.0F, 0.5F, 0.5F, 0.5F, 1.0F, 1.0F };
			default:
				return null;
			}
		case Stairs.INT_NEG_SE:
			switch (box) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.5F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F };
			case 3:
				return new float[] { 0.5F, 0.5F, 0.0F, 1.0F, 1.0F, 0.5F };
			default:
				return null;
			}
		case Stairs.INT_NEG_SW:
			switch (box) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F };
			case 3:
				return new float[] { 0.5F, 0.5F, 0.5F, 1.0F, 1.0F, 1.0F };
			default:
				return null;
			}
		case Stairs.EXT_POS_NE:
			switch (box) {
			case 1:
				return new float[] { 0.5F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 1.0F };
			case 3:
				return new float[] { 0.5F, 0.0F, 0.0F, 1.0F, 0.5F, 0.5F };
			default:
				return null;
			}
		case Stairs.EXT_POS_NW:
			switch (box) {
			case 1:
				return new float[] { 0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F };
			case 2:
				return new float[] { 0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 1.0F };
			case 3:
				return new float[] { 0.5F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F };
			default:
				return null;
			}
		case Stairs.EXT_POS_SE:
			switch (box) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.5F, 0.5F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.5F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F };
			case 3:
				return new float[] { 0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 0.5F };
			default:
				return null;
			}
		case Stairs.EXT_POS_SW:
			switch (box) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 0.5F };
			case 2:
				return new float[] { 0.5F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F };
			case 3:
				return new float[] { 0.0F, 0.0F, 0.5F, 0.5F, 0.5F, 1.0F };
			default:
				return null;
			}
		case Stairs.EXT_NEG_NE:
			switch (box) {
			case 1:
				return new float[] { 0.5F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.5F, 0.0F, 0.5F, 1.0F, 1.0F };
			case 3:
				return new float[] { 0.5F, 0.5F, 0.0F, 1.0F, 1.0F, 0.5F };
			default:
				return null;
			}
		case Stairs.EXT_NEG_NW:
			switch (box) {
			case 1:
				return new float[] { 0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F };
			case 2:
				return new float[] { 0.0F, 0.5F, 0.0F, 0.5F, 1.0F, 1.0F };
			case 3:
				return new float[] { 0.5F, 0.5F, 0.5F, 1.0F, 1.0F, 1.0F };
			default:
				return null;
			}
		case Stairs.EXT_NEG_SE:
			switch (box) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.5F, 0.5F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.5F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F };
			case 3:
				return new float[] { 0.0F, 0.5F, 0.0F, 0.5F, 1.0F, 0.5F };
			default:
				return null;
			}
		case Stairs.EXT_NEG_SW:
			switch (box) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 0.5F };
			case 2:
				return new float[] { 0.5F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F };
			case 3:
				return new float[] { 0.0F, 0.5F, 0.5F, 0.5F, 1.0F, 1.0F };
			default:
				return null;
			}
		case Stairs.SIDE_NW:
			switch (box) {
			case 1:
				return new float[] { 0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 0.5F };
			default:
				return null;
			}
		case Stairs.SIDE_NE:
			switch (box) {
			case 1:
				return new float[] { 0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.0F, 0.5F, 0.5F, 1.0F, 1.0F };
			default:
				return null;
			}
		case Stairs.SIDE_SE:
			switch (box) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.5F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F };
			default:
				return null;
			}
		default: // Stairs.SIDE_SW
			switch (box) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F };
			default:
				return null;
			}
		}
	}
	
    @Override
	/**
	 * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
	 * x, y, z, startVec, endVec
	 */
	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec)
	{
		TECarpentersBlock TE = (TECarpentersBlock)world.getBlockTileEntity(x, y, z);

		MovingObjectPosition finalTrace = null;
		
		int data = BlockProperties.getData(TE);

		double currDist = 0.0D;
		double maxDist = 0.0D;

		// Determine if ray trace is a hit on stairs
		for (int box = 0; box < 3; ++box)
		{
			float[] bounds = genBounds(box, data);
			
			if (bounds != null)
			{
				this.setBlockBounds(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
				MovingObjectPosition traceResult = super.collisionRayTrace(world, x, y, z, startVec, endVec);
	
				if (traceResult != null)
				{
					currDist = traceResult.hitVec.squareDistanceTo(endVec);
					if (currDist > maxDist) {
						finalTrace = traceResult;
						maxDist = currDist;
					}
				}
			}
		}

		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		return finalTrace;
	}
	
    @Override
	/**
	 * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
	 * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
	 */
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity entity)
	{
		AxisAlignedBB colBox = null;

		TECarpentersBlock TE = (TECarpentersBlock)world.getBlockTileEntity(x, y, z);

		int data = BlockProperties.getData(TE);
		
		// Construct bounding area for entity collision
		for (int box = 0; box < 3; ++box) {
			float[] bounds = genBounds(box, data);

			if (bounds != null)
				colBox = AxisAlignedBB.getAABBPool().getAABB(x + bounds[0], y + bounds[1], z + bounds[2], x + bounds[3], y + bounds[4], z + bounds[5]);
				
			if (colBox != null && axisAlignedBB.intersectsWith(colBox))
				list.add(colBox);
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

			return	Stairs.isBottomFaceSolid(data) && side == ForgeDirection.DOWN ||
					Stairs.isTopFaceSolid(data) && side == ForgeDirection.UP ||
					Stairs.isEastFaceSolid(data) && side == ForgeDirection.NORTH ||
					Stairs.isWestFaceSolid(data) && side == ForgeDirection.SOUTH ||
					Stairs.isNorthFaceSolid(data) && side == ForgeDirection.WEST ||
					Stairs.isSouthFaceSolid(data) && side == ForgeDirection.EAST;
		}

		return false;
	}
	
    @Override
	/**
	 * Called when block is placed in world.
	 * Sets stairs angle depending on click coordinates on block face.
	 *
	 *	Metadata values:
	 * 	 0 - 11	-	Identifies stair angle in x, y, z space.
	 * 	12 - 13	-	Top or bottom side of block clicked.  onBlockPlacedBy() determines
	 * 				direction and sets interpolated value from 0 - 11.
	 */
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		// Normalize face coordinates
		switch (side) {
		case 2:
			hitX = 1.0F - hitX;
			break;
		case 4:
			hitX = hitZ;
			break;
		case 5:
			hitX = 1.0F - hitZ;
			break;
		}

		if (side > 1 && side < 6) {
			if (hitY > 0.5F && hitX > (1.0F - hitY) && hitX < hitY) {
				return side + 2;
			} else if (hitY < 0.5F && hitX < (1.0F - hitY) && hitX > hitY) {
				return side + 6;
			} else if (hitX < 0.2F) {
				return side == 2 ? 1 : side == 3 ? 0 : side == 4 ? 3 : 2;
			} else if (hitX > 0.8F){
				return side == 2 ? 2 : side == 3 ? 3 : side == 4 ? 1 : 0;
			} else if (hitY > 0.5F) {
				return side + 2;
			} else { // hitY < 0.5F
				return side + 6;
			}
		} else {
			return side + 12;
		}
	}

    @Override
	/**
	 * Called when the block is placed in the world.
	 * Uses cardinal direction to adjust metadata if player clicks top or bottom face of block.
	 */
	public void auxiliaryOnBlockPlacedBy(TECarpentersBlock TE, World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
	{
		int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		BlockProperties.setData(TE, world.getBlockMetadata(x, y, z));
		int data = BlockProperties.getData(TE);

		if (data > 11)
		{
			switch (facing) {
				case 0: {
					data = data == 12 ? Stairs.NEG_EAST : Stairs.POS_EAST;
					break;
				}
				case 1: {
					data = data == 12 ? Stairs.NEG_SOUTH : Stairs.POS_SOUTH;
					break;
				}
				case 2: {
					data = data == 12 ? Stairs.NEG_WEST : Stairs.POS_WEST;
					break;
				}
				case 3: {
					data = data == 12 ? Stairs.NEG_NORTH : Stairs.POS_NORTH;
					break;
				}
			}
		}

		// If shift key is down, skip auto-orientation
		if (!entityLiving.isSneaking())
		{
			/*
			 * Check if stairs should form into a corner
			 */

			TECarpentersBlock TE_XN = world.getBlockId(x - 1, y, z) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x - 1, y, z) : null;
			TECarpentersBlock TE_XP = world.getBlockId(x + 1, y, z) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x + 1, y, z) : null;
			TECarpentersBlock TE_YN = world.getBlockId(x, y - 1, z) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x, y - 1, z) : null;
			TECarpentersBlock TE_YP = world.getBlockId(x, y + 1, z) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x, y + 1, z) : null;
			TECarpentersBlock TE_ZN = world.getBlockId(x, y, z - 1) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x, y, z - 1) : null;
			TECarpentersBlock TE_ZP = world.getBlockId(x, y, z + 1) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x, y, z + 1) : null;
			
			// Gather neighboring stair types
			int data_XN = TE_XN != null ? BlockProperties.getData(TE_XN) : -1;
			int data_XP = TE_XP != null ? BlockProperties.getData(TE_XP) : -1;
			int data_YN = TE_YN != null ? BlockProperties.getData(TE_YN) : -1;
			int data_YP = TE_YP != null ? BlockProperties.getData(TE_YP) : -1;
			int data_ZN = TE_ZN != null ? BlockProperties.getData(TE_ZN) : -1;
			int data_ZP = TE_ZP != null ? BlockProperties.getData(TE_ZP) : -1;

			// Store old stair values (to check against new values at end of function)
			int temp_data_XN = TE_XN != null ? data_XN : -1;
			int temp_data_XP = TE_XP != null ? data_XP : -1;
			int temp_data_ZN = TE_ZN != null ? data_ZN : -1;
			int temp_data_ZP = TE_ZP != null ? data_ZP : -1;

			// Check if indirectly should transform to corner (in other words, this must transform to corner to match side)
			if (Stairs.areNormalStairs(data))
			{
				if (TE_XN != null) {
					if (Stairs.areFacingNorth(data)) {
						if (Stairs.areFacingWest(data_XN) && !Stairs.areFacingSouth(data_XN))
							data = Stairs.arePositive(data_XN) ? Stairs.INT_POS_NW : Stairs.INT_NEG_NW;
						if (Stairs.areFacingEast(data_XN) && !Stairs.areFacingSouth(data_XN))
							data = Stairs.arePositive(data_XN) ? Stairs.INT_POS_NE : Stairs.INT_NEG_NE;
					}
					if (Stairs.areFacingSouth(data)) {
						if (Stairs.areFacingWest(data_XN) && !Stairs.areFacingSouth(data_XN))
							data = Stairs.arePositive(data_XN) ? Stairs.EXT_POS_SW : Stairs.EXT_NEG_SW;
						if (Stairs.areFacingEast(data_XN) && !Stairs.areFacingSouth(data_XN))
							data = Stairs.arePositive(data_XN) ? Stairs.EXT_POS_SE : Stairs.EXT_NEG_SE;
					}
				}
				if (TE_XP != null) {
					if (Stairs.areFacingNorth(data)) {
						if (Stairs.areFacingWest(data_XP) && !Stairs.areFacingNorth(data_XP))
							data = Stairs.arePositive(data_XP) ? Stairs.EXT_POS_NW : Stairs.EXT_NEG_NW;
						if (Stairs.areFacingEast(data_XP) && !Stairs.areFacingNorth(data_XP))
							data = Stairs.arePositive(data_XP) ? Stairs.EXT_POS_NE : Stairs.EXT_NEG_NE;
					}
					if (Stairs.areFacingSouth(data)) {
						if (Stairs.areFacingWest(data_XP) && !Stairs.areFacingNorth(data_XP))
							data = Stairs.arePositive(data_XP) ? Stairs.INT_POS_SW : Stairs.INT_NEG_SW;
						if (Stairs.areFacingEast(data_XP) && !Stairs.areFacingNorth(data_XP))
							data = Stairs.arePositive(data_XP) ? Stairs.INT_POS_SE : Stairs.INT_NEG_SE;
					}
				}
				if (TE_ZN != null) {
					if (Stairs.areFacingEast(data)) {
						if (Stairs.areFacingSouth(data_ZN) && !Stairs.areFacingWest(data_ZN))
							data = Stairs.arePositive(data_ZN) ? Stairs.INT_POS_SE : Stairs.INT_NEG_SE;
						if (Stairs.areFacingNorth(data_ZN) && !Stairs.areFacingWest(data_ZN))
							data = Stairs.arePositive(data_ZN) ? Stairs.INT_POS_NE : Stairs.INT_NEG_NE;
					}
					if (Stairs.areFacingWest(data)) {
						if (Stairs.areFacingSouth(data_ZN) && !Stairs.areFacingWest(data_ZN))
							data = Stairs.arePositive(data_ZN) ? Stairs.EXT_POS_SW : Stairs.EXT_NEG_SW;
						if (Stairs.areFacingNorth(data_ZN) && !Stairs.areFacingWest(data_ZN))
							data = Stairs.arePositive(data_ZN) ? Stairs.EXT_POS_NW : Stairs.EXT_NEG_NW;
					}
				}
				if (TE_ZP != null) {
					if (Stairs.areFacingEast(data)) {
						if (Stairs.areFacingSouth(data_ZP) && !Stairs.areFacingEast(data_ZP))
							data = Stairs.arePositive(data_ZP) ? Stairs.EXT_POS_SE : Stairs.EXT_NEG_SE;
						if (Stairs.areFacingNorth(data_ZP) && !Stairs.areFacingEast(data_ZP))
							data = Stairs.arePositive(data_ZP) ? Stairs.EXT_POS_NE : Stairs.EXT_NEG_NE;
					}
					if (Stairs.areFacingWest(data)) {
						if (Stairs.areFacingSouth(data_ZP) && !Stairs.areFacingEast(data_ZP))
							data = Stairs.arePositive(data_ZP) ? Stairs.INT_POS_SW : Stairs.INT_NEG_SW;
						if (Stairs.areFacingNorth(data_ZP) && !Stairs.areFacingEast(data_ZP))
							data = Stairs.arePositive(data_ZP) ? Stairs.INT_POS_NW : Stairs.INT_NEG_NW;
					}
				}
			}

			// Check if should directly transform to corner (in other words, two sides are already a valid corner pair)
			if (TE_ZN != null) {
				if (TE_XP != null) {
					if (Stairs.areFacingSouth(data_ZN) && Stairs.areFacingEast(data_XP))
						data = Stairs.arePositive(data_XP, data_ZN) ? Stairs.INT_POS_SE : Stairs.INT_NEG_SE;
					if (Stairs.areFacingNorth(data_ZN) && Stairs.areFacingWest(data_XP))
						data = Stairs.arePositive(data_XP, data_ZN) ? Stairs.EXT_POS_NW : Stairs.EXT_NEG_NW;
				}
				if (TE_XN != null) {
					if (Stairs.areFacingNorth(data_ZN) && Stairs.areFacingEast(data_XN))
						data = Stairs.arePositive(data_XN, data_ZN) ? Stairs.INT_POS_NE : Stairs.INT_NEG_NE;
					if (Stairs.areFacingSouth(data_ZN) && Stairs.areFacingWest(data_XN))
						data = Stairs.arePositive(data_XN, data_ZN) ? Stairs.EXT_POS_SW : Stairs.EXT_NEG_SW;
				}
			}
			if (TE_ZP != null) {
				if (TE_XN != null) {
					if (Stairs.areFacingNorth(data_ZP) && Stairs.areFacingWest(data_XN))
						data = Stairs.arePositive(data_XN, data_ZP) ? Stairs.INT_POS_NW : Stairs.INT_NEG_NW;
					if (Stairs.areFacingSouth(data_ZP) && Stairs.areFacingEast(data_XN))
						data = Stairs.arePositive(data_XN, data_ZP) ? Stairs.EXT_POS_SE : Stairs.EXT_NEG_SE;
				}
				if (TE_XP != null) {
					if (Stairs.areFacingSouth(data_ZP) && Stairs.areFacingWest(data_XP))
						data = Stairs.arePositive(data_XP, data_ZP) ? Stairs.INT_POS_SW : Stairs.INT_NEG_SW;
					if (Stairs.areFacingNorth(data_ZP) && Stairs.areFacingEast(data_XP))
						data = Stairs.arePositive(data_XP, data_ZP) ? Stairs.EXT_POS_NE : Stairs.EXT_NEG_NE;
				}
			}

			/*
			 *  Change neighboring stairs to matching corners where applicable
			 */

			if (Stairs.areFacingNorth(data)) {
				if (TE_ZN != null && Stairs.haveMatchingOrientation(data, data_ZN)) {
					if (Stairs.areFacingEast(data_ZN))
						BlockProperties.setData(TE_ZN, Stairs.arePositive(data) ? Stairs.EXT_POS_NE : Stairs.EXT_NEG_NE);
					if (Stairs.areFacingWest(data_ZN))
						BlockProperties.setData(TE_ZN, Stairs.arePositive(data) ? Stairs.INT_POS_NW : Stairs.INT_NEG_NW);
				}
				if (TE_ZP != null && Stairs.haveMatchingOrientation(data, data_ZP)) {
					if (Stairs.areFacingWest(data_ZP))
						BlockProperties.setData(TE_ZP, Stairs.arePositive(data) ? Stairs.EXT_POS_NW : Stairs.EXT_NEG_NW);
					if (Stairs.areFacingEast(data_ZP))
						BlockProperties.setData(TE_ZP, Stairs.arePositive(data) ? Stairs.INT_POS_NE : Stairs.INT_NEG_NE);
				}
			}
			if (Stairs.areFacingSouth(data)) {
				if (TE_ZN != null && Stairs.haveMatchingOrientation(data, data_ZN)) {
					if (Stairs.areFacingEast(data_ZN))
						BlockProperties.setData(TE_ZN, Stairs.arePositive(data) ? Stairs.EXT_POS_SE : Stairs.EXT_NEG_SE);
					if (Stairs.areFacingWest(data_ZN))
						BlockProperties.setData(TE_ZN, Stairs.arePositive(data) ? Stairs.INT_POS_SW : Stairs.INT_NEG_SW);
				}
				if (TE_ZP != null && Stairs.haveMatchingOrientation(data, data_ZP)) {
					if (Stairs.areFacingWest(data_ZP))
						BlockProperties.setData(TE_ZP, Stairs.arePositive(data) ? Stairs.EXT_POS_SW : Stairs.EXT_NEG_SW);
					if (Stairs.areFacingEast(data_ZP))
						BlockProperties.setData(TE_ZP, Stairs.arePositive(data) ? Stairs.INT_POS_SE : Stairs.INT_NEG_SE);
				}
			}
			if (Stairs.areFacingEast(data)) {
				if (TE_XN != null && Stairs.haveMatchingOrientation(data, data_XN)) {
					if (Stairs.areFacingNorth(data_XN))
						BlockProperties.setData(TE_XN, Stairs.arePositive(data) ? Stairs.EXT_POS_NE : Stairs.EXT_NEG_NE);
					if (Stairs.areFacingSouth(data_XN))
						BlockProperties.setData(TE_XN, Stairs.arePositive(data) ? Stairs.INT_POS_SE : Stairs.INT_NEG_SE);
				}
				if (TE_XP != null && Stairs.haveMatchingOrientation(data, data_XP)) {
					if (Stairs.areFacingSouth(data_XP))
						BlockProperties.setData(TE_XP, Stairs.arePositive(data) ? Stairs.EXT_POS_SE : Stairs.EXT_NEG_SE);
					if (Stairs.areFacingNorth(data_XP))
						BlockProperties.setData(TE_XP, Stairs.arePositive(data) ? Stairs.INT_POS_NE : Stairs.INT_NEG_NE);
				}
			}
			if (Stairs.areFacingWest(data)) {
				if (TE_XN != null && Stairs.haveMatchingOrientation(data, data_XN)) {
					if (Stairs.areFacingNorth(data_XN))
						BlockProperties.setData(TE_XN, Stairs.arePositive(data) ? Stairs.EXT_POS_NW : Stairs.EXT_NEG_NW);
					if (Stairs.areFacingSouth(data_XN))
						BlockProperties.setData(TE_XN, Stairs.arePositive(data) ? Stairs.INT_POS_SW : Stairs.INT_NEG_SW);
				}
				if (TE_XP != null && Stairs.haveMatchingOrientation(data, data_XP)) {
					if (Stairs.areFacingSouth(data_XP))
						BlockProperties.setData(TE_XP, Stairs.arePositive(data) ? Stairs.EXT_POS_SW : Stairs.EXT_NEG_SW);
					if (Stairs.areFacingNorth(data_XP))
						BlockProperties.setData(TE_XP, Stairs.arePositive(data) ? Stairs.INT_POS_NW : Stairs.INT_NEG_NW);
				}
			}
			
			// Check if stairs above or below are side stairs and, if so, make this a continuation
			if (TE_YP != null) {
				if (Stairs.areSideStairs(data_YP)) {
					data = data_YP;
				}
			}
			if (TE_YN != null) {
				if (Stairs.areSideStairs(data_YN)) {
					data = data_YN;
				}
			}
			
			/*
			 * Server should update clients when adjacent
			 * slopes are altered.
			 */
			if (!world.isRemote)
			{
				if (data_XN != temp_data_XN)
					BlockProperties.setData(TE_XN, data_XN);
				if (data_XP != temp_data_XP)
					BlockProperties.setData(TE_XP, data_XP);
				if (data_ZN != temp_data_ZN)
					BlockProperties.setData(TE_ZN, data_ZN);
				if (data_ZP != temp_data_ZP)
					BlockProperties.setData(TE_ZP, data_ZP);
			}
		}
		
		BlockProperties.setData(TE, data);
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
		return CarpentersBlocks.carpentersStairsRenderID;
	}

}
