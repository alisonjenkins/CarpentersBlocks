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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import carpentersblocks.CarpentersBlocks;
import carpentersblocks.data.Slope;
import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.BlockProperties;

public class BlockCarpentersSlope extends BlockBase
{

	private final int hitboxPrecision = CarpentersBlocks.hitboxPrecision;
	
	public BlockCarpentersSlope(int blockID)
	{
		super(blockID, Material.wood);
		this.setHardness(0.2F);
		this.setUnlocalizedName("blockCarpentersSlope");
		this.setCreativeTab(CarpentersBlocks.tabCarpentersBlocks);
        this.func_111022_d("carpentersblocks:slope/slope");
	}
    
    @Override
	/**
	 * Alters block direction.
	 */
	protected boolean onHammerLeftClick(TECarpentersBlock TE, EntityPlayer entityPlayer)
	{
    	int data = BlockProperties.getData(TE);
    	
		/*
		 * Cycle between slope types based on current slope
		 */
		if (Slope.isSideSlope(data)) {
			if (++data > 3)
				data = 0;
		} else if (Slope.isNormalSlope(data) && Slope.isNegative(data)) {
			if (++data > 7)
				data = 4;
		} else if (Slope.isNormalSlope(data) && Slope.isPositive(data)) {
			if (++data > 11)
				data = 8;
		} else if (Slope.isInteriorCorner(data) && Slope.isNegative(data)) {
			if ((data += 2) > 19)
				data = 13;
		} else if (Slope.isExteriorCorner(data) && Slope.isNegative(data)) {
			if ((data += 2) > 27)
				data = 21;
		} else if (Slope.isInteriorCorner(data) && Slope.isPositive(data)) {
			if ((data += 2) > 18)
				data = 12;
		} else if (Slope.isExteriorCorner(data) && Slope.isPositive(data)) {
			if ((data += 2) > 26)
				data = 20;
		} else if (Slope.isObliqueInteriorCorner(data) && Slope.isNegative(data)) {
			if ((data += 2) > 35)
				data = 29;
		} else if (Slope.isObliqueExteriorCorner(data) && Slope.isNegative(data)) {
			if ((data += 2) > 43)
				data = 37;
		} else if (Slope.isObliqueInteriorCorner(data) && Slope.isPositive(data)) {
			if ((data += 2) > 34)
				data = 28;
		} else if (Slope.isObliqueExteriorCorner(data) && Slope.isPositive(data)) {
			if ((data += 2) > 42)
				data = 36;
		} else if (Slope.isPyramid(data)) {
			switch (data) {
			case Slope.HALF_POS_PYR:
				data = Slope.HALF_NEG_PYR;
				break;
			case Slope.HALF_NEG_PYR:
				data = Slope.HALF_POS_PYR;
				break;
			}
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
		 * Transform slope to next type
		 */
		if (Slope.isSideSlope(data)) {
			data = 8;
		} else if (Slope.isNormalSlope(data) && Slope.isPositive(data)) {
			data -= 4;
		} else if (Slope.isNormalSlope(data) && Slope.isNegative(data)) {
			data = 12;
		} else if (Slope.isInteriorCorner(data)) {
			if (Slope.isPositive(data)) {
				data += 1;
			} else {
				if (data == 13 || data == 15) {
					data += 11;
				} else {
					data +=3;
				}
			}
		} else if (Slope.isExteriorCorner(data)) {
			if (Slope.isPositive(data)) {
				data += 1;
			} else {
				if (data == 21 || data == 23) {
					data += 11;
				} else {
					data += 3;
				}
			}
		} else if (Slope.isObliqueInteriorCorner(data)) {
			if (Slope.isPositive(data)) {
				data += 1;
			} else {
				if (data == 29 || data == 31) {
					data += 11;
				} else {
					data += 3;
				}
			}
		} else if (Slope.isObliqueExteriorCorner(data)) {
			if (Slope.isPositive(data)) {
				data += 1;
			} else {
				data = 44;
			}
		} else { // Slope.isPyramid(type)
			data = 0;
		}
		
		BlockProperties.setData(TE, data);

		return true;
	}

	/**
	 * Will return slope boundaries for all slopes
	 */
	private float[] genBounds(int slope, int slice, int precision, int pass)
	{
		++pass;

		// For oblique exterior corners
		float zeroPassOffset = (float) (pass - 1) / getNumPasses(slope);
		float onePassOffset = (float) pass / getNumPasses(slope);

		// Includes 0.0F -> 0.99_F
		float zeroOffset = (float) slice / (float) precision;

		// Includes 0.01_F -> 1.0F
		float oneOffset = (float) (slice + 1) / (float) precision;

		switch (slope) {

		// Side slopes
		case Slope.SIDE_NE:
			return new float[] { zeroOffset, 0.0F, 1.0F - oneOffset, 1.0F, 1.0F, 1.0F };
		case Slope.SIDE_NW:
			return new float[] { zeroOffset, 0.0F, 0.0F, 1.0F, 1.0F, oneOffset };
		case Slope.SIDE_SE:
			return new float[] { 0.0F, 0.0F, zeroOffset, oneOffset, 1.0F, 1.0F };
		case Slope.SIDE_SW:
			return new float[] { 0.0F, 0.0F, 0.0F, oneOffset, 1.0F, 1.0F - zeroOffset };

		// Normal slopes
		case Slope.POS_EAST:
			return new float[] { 0.0F, 0.0F, zeroOffset, 1.0F, oneOffset, 1.0F };
		case Slope.POS_NORTH:
			return new float[] { zeroOffset, 0.0F, 0.0F, 1.0F, oneOffset, 1.0F };
		case Slope.POS_SOUTH:
			return new float[] { 0.0F, 0.0F, 0.0F, oneOffset, 1.0F - zeroOffset, 1.0F };
		case Slope.POS_WEST:
			return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 1.0F - zeroOffset, oneOffset };
		case Slope.NEG_EAST:
			return new float[] { 0.0F, 1.0F - oneOffset, zeroOffset, 1.0F, 1.0F, 1.0F };
		case Slope.NEG_NORTH:
			return new float[] { zeroOffset, 1.0F - oneOffset, 0.0F, 1.0F, 1.0F, 1.0F };
		case Slope.NEG_SOUTH:
			return new float[] { 0.0F, zeroOffset, 0.0F, oneOffset, 1.0F, 1.0F };
		case Slope.NEG_WEST:
			return new float[] { 0.0F, zeroOffset, 0.0F, 1.0F, 1.0F, oneOffset };

		// Interior normal corners
		case Slope.INT_POS_NE:
			switch (pass) {
			case 1:
				return new float[] { zeroOffset, 0.0F, 0.0F, 1.0F, oneOffset, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.0F, zeroOffset, 1.0F, oneOffset, 1.0F };
			}
		case Slope.INT_POS_NW:
			switch (pass) {
			case 1:
				return new float[] { zeroOffset, 0.0F, 0.0F, 1.0F, oneOffset, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 1.0F - zeroOffset, oneOffset };
			}
		case Slope.INT_POS_SE:
			switch (pass) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.0F, oneOffset, 1.0F - zeroOffset, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.0F, zeroOffset, 1.0F, oneOffset, 1.0F };
			}
		case Slope.INT_POS_SW:
			switch (pass) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.0F, oneOffset, 1.0F - zeroOffset, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 1.0F - zeroOffset, oneOffset };
			}
		case Slope.INT_NEG_NE:
			switch (pass) {
			case 1:
				return new float[] { zeroOffset, 1.0F - oneOffset, 0.0F, 1.0F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.0F, 1.0F - oneOffset, zeroOffset, 1.0F, 1.0F, 1.0F };
			}
		case Slope.INT_NEG_NW:
			switch (pass) {
			case 1:
				return new float[] { zeroOffset, 1.0F - oneOffset, 0.0F, 1.0F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.0F, zeroOffset, 0.0F, 1.0F, 1.0F, oneOffset };
			}
		case Slope.INT_NEG_SE:
			switch (pass) {
			case 1:
				return new float[] { 0.0F, zeroOffset, 0.0F, oneOffset, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.0F, 1.0F - oneOffset, zeroOffset, 1.0F, 1.0F, 1.0F };
			}
		case Slope.INT_NEG_SW:
			switch (pass) {
			case 1:
				return new float[] { 0.0F, zeroOffset, 0.0F, oneOffset, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.0F, zeroOffset, 0.0F, 1.0F, 1.0F, oneOffset };
			}

		// Exterior normal corners
		case Slope.EXT_POS_NE:
			return new float[] { zeroOffset, 0.0F, zeroOffset, 1.0F, oneOffset, 1.0F };
		case Slope.EXT_POS_NW:
			return new float[] { zeroOffset, 0.0F, 0.0F, 1.0F, oneOffset, 1.0F - zeroOffset };
		case Slope.EXT_POS_SE:
			return new float[] { 0.0F, 0.0F, zeroOffset, 1.0F - zeroOffset, oneOffset, 1.0F };
		case Slope.EXT_POS_SW:
			return new float[] { 0.0F, 0.0F, 0.0F, 1.0F - zeroOffset, oneOffset, 1.0F - zeroOffset };
		case Slope.EXT_NEG_NE:
			return new float[] { zeroOffset, 1.0F - oneOffset, zeroOffset, 1.0F, 1.0F, 1.0F };
		case Slope.EXT_NEG_NW:
			return new float[] { zeroOffset, 1.0F - oneOffset, 0.0F, 1.0F, 1.0F, 1.0F - zeroOffset };
		case Slope.EXT_NEG_SE:
			return new float[] { 0.0F, 1.0F - oneOffset, zeroOffset, 1.0F - zeroOffset, 1.0F, 1.0F };
		case Slope.EXT_NEG_SW:
			return new float[] { 0.0F, 1.0F - oneOffset, 0.0F, 1.0F - zeroOffset, 1.0F, 1.0F - zeroOffset };

		// Obliques, with interiors using low precision
		case Slope.EXT_POS_OBL_NE:
			return new float[] { zeroPassOffset + zeroOffset * (1.0F - zeroPassOffset), 0.0F, 1.0F - oneOffset * (1.0F - zeroPassOffset), 1.0F, onePassOffset, 1.0F };
		case Slope.EXT_POS_OBL_NW:
			return new float[] { zeroPassOffset + zeroOffset * (1.0F - zeroPassOffset), 0.0F, 0.0F, 1.0F, onePassOffset, oneOffset * (1.0F - zeroPassOffset) };
		case Slope.EXT_POS_OBL_SE:
			return new float[] { 0.0F, 0.0F, zeroPassOffset + zeroOffset * (1.0F - zeroPassOffset), oneOffset * (1.0F - zeroPassOffset), onePassOffset, 1.0F };
		case Slope.EXT_POS_OBL_SW:
			return new float[] { 0.0F, 0.0F, 0.0F, oneOffset * (1.0F - zeroPassOffset), onePassOffset, 1.0F - zeroPassOffset - zeroOffset * (1.0F - zeroPassOffset), };
		case Slope.INT_POS_OBL_NE:
			switch (pass) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F };
			case 2:
				return new float[] { 0.5F, 0.5F, 0.0F, 1.0F, 1.0F, 0.5F };
			case 3:
				return new float[] { 0.0F, 0.5F, 0.5F, 1.0F, 1.0F, 1.0F };
			}
		case Slope.INT_POS_OBL_NW:
			switch (pass) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 0.5F };
			case 3:
				return new float[] { 0.5F, 0.5F, 0.5F, 1.0F, 1.0F, 1.0F };
			}
		case Slope.INT_POS_OBL_SE:
			switch (pass) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.5F, 0.0F, 0.5F, 1.0F, 0.5F };
			case 3:
				return new float[] { 0.0F, 0.5F, 0.5F, 1.0F, 1.0F, 1.0F };
			}
		case Slope.INT_POS_OBL_SW:
			switch (pass) {
			case 1:
				return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 0.5F };
			case 3:
				return new float[] { 0.0F, 0.5F, 0.5F, 0.5F, 1.0F, 1.0F };
			}
		case Slope.EXT_NEG_OBL_NE:
			return new float[] { zeroPassOffset + zeroOffset * (1.0F - zeroPassOffset), 1.0F - onePassOffset, 1.0F - oneOffset * (1.0F - zeroPassOffset), 1.0F, 1.0F, 1.0F };
		case Slope.EXT_NEG_OBL_NW:
			return new float[] { zeroPassOffset + zeroOffset * (1.0F - zeroPassOffset), 1.0F - onePassOffset, 0.0F, 1.0F, 1.0F, oneOffset * (1.0F - zeroPassOffset) };
		case Slope.EXT_NEG_OBL_SE:
			return new float[] { 0.0F, 1.0F - onePassOffset, zeroPassOffset + zeroOffset * (1.0F - zeroPassOffset), oneOffset * (1.0F - zeroPassOffset), 1.0F, 1.0F };
		case Slope.EXT_NEG_OBL_SW:
			return new float[] { 0.0F, 1.0F - onePassOffset, 0.0F, oneOffset * (1.0F - zeroPassOffset), 1.0F, 1.0F - zeroPassOffset - zeroOffset * (1.0F - zeroPassOffset) };
		case Slope.INT_NEG_OBL_NE:
			switch (pass) {
			case 1:
				return new float[] { 0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.5F, 0.0F, 0.0F, 1.0F, 0.5F, 0.5F };
			case 3:
				return new float[] { 0.0F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F };
			}
		case Slope.INT_NEG_OBL_NW:
			switch (pass) {
			case 1:
				return new float[] { 0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 0.5F };
			case 3:
				return new float[] { 0.5F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F };
			}
		case Slope.INT_NEG_OBL_SE:
			switch (pass) {
			case 1:
				return new float[] { 0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 0.5F };
			case 3:
				return new float[] { 0.0F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F };
			}
		case Slope.INT_NEG_OBL_SW:
			switch (pass) {
			case 1:
				return new float[] { 0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F };
			case 2:
				return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 0.5F };
			case 3:
				return new float[] { 0.0F, 0.0F, 0.5F, 0.5F, 0.5F, 1.0F };
			}

		// Pyramids
		case Slope.HALF_POS_PYR:
			return new float[] { (0.5F * zeroOffset), 0.0F, (0.5F * zeroOffset), 1.0F - (0.5F * zeroOffset), oneOffset * 0.5F, 1.0F - (0.5F * zeroOffset) };
		default: //Slope.NEG_PYR:
			return new float[] { (0.5F * zeroOffset), 1.0F - (oneOffset * 0.5F), (0.5F * zeroOffset), 1.0F - (0.5F * zeroOffset), 1.0F, 1.0F - (0.5F * zeroOffset) };
		}
	}

	/**
	 * Return number of boxes that need to be constructed for slope
	 */
	private int getPrecision(int slope)
	{
		if (Slope.isObliqueInteriorCorner(slope)) {
			return 3;
		} else if (Slope.isPyramid(slope)) {
			return hitboxPrecision / 2;
		} else {
			return hitboxPrecision;
		}
	}

	/**
	 * Return number of passes required for slope box generation
	 */
	private int getNumPasses(int slope)
	{
		return Slope.isObliqueCorner(slope) ? getPrecision(slope) : Slope.isInteriorCorner(slope) ? 2 : 1;
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

		int precision = getPrecision(data);
		int numPasses = getNumPasses(data);

		double currDist = 0.0D;
		double maxDist = 0.0D;

		// Determine if ray trace is a hit on slope
		for (int pass = 0; pass < numPasses; ++pass) {
			for (int slice = 0; slice < precision; ++slice) {
				float[] box = genBounds(data, slice, precision, pass);
				this.setBlockBounds(box[0], box[1], box[2], box[3], box[4], box[5]);
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
			if (Slope.isObliqueExteriorCorner(data))
				--precision;
		}

		this.setBlockBoundsBasedOnState(world, x, y, z);
		return finalTrace;
	}
    
    @Override
	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
    	TECarpentersBlock TE = (TECarpentersBlock) world.getBlockTileEntity(x, y, z);

    	int data = BlockProperties.getData(TE);

    	if (data == Slope.HALF_NEG_PYR)
    		this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
    	else if (data == Slope.HALF_POS_PYR)
    		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    	else
    		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
	/**
	 * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
	 * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
	 */
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity entity)
	{
		AxisAlignedBB box = null;

		TECarpentersBlock TE = (TECarpentersBlock)world.getBlockTileEntity(x, y, z);

		int data = BlockProperties.getData(TE);
		
		int precision = getPrecision(data);
		int numPasses = getNumPasses(data);

		// Construct bounding area for entity collision
		for (int pass = 0; pass < numPasses; ++pass) {
			for (int slice = 0; slice < precision; ++slice) {
				float[] dim = genBounds(data, slice, precision, pass);

				if (dim != null)
					box = AxisAlignedBB.getAABBPool().getAABB(x + dim[0], y + dim[1], z + dim[2], x + dim[3], y + dim[4], z + dim[5]);

				if (box != null && axisAlignedBB.intersectsWith(box)) {
					box.contract(0.11D, 0.11D, 0.11D);
					list.add(box);
				}
			}

			if (Slope.isObliqueExteriorCorner(data))
				--precision;
		}
	}

    @Override
	/**
	 * Checks if the block is a solid face on the given side, used by placement logic.
	 */
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
	{
		TECarpentersBlock TE = (TECarpentersBlock) world.getBlockTileEntity(x, y, z);

		if (isBlockSolid(world, x, y, z))
		{
			int data = BlockProperties.getData(TE);

			/*
			 * Slopes were made before use of ForgeDirection to help
			 * clarify direction.  This is why directions here and in
			 * most of slope's dependencies won't make sense.
			 */
			return	Slope.isBottomFaceSolid(data) && side == ForgeDirection.DOWN ||
					Slope.isTopFaceSolid(data) && side == ForgeDirection.UP ||
					Slope.isEastFaceSolid(data) && side == ForgeDirection.NORTH ||
					Slope.isWestFaceSolid(data) && side == ForgeDirection.SOUTH ||
					Slope.isNorthFaceSolid(data) && side == ForgeDirection.WEST ||
					Slope.isSouthFaceSolid(data) && side == ForgeDirection.EAST;
		}

		return false;
	}

    @Override
	/**
	 * Called when block is placed in world.
	 * Sets slope angle depending on click coordinates on block face.
	 *
	 *	Metadata values:
	 * 	 0 - 11	-	Identifies slope angle in x, y, z space.
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
					data = data == 12 ? Slope.NEG_EAST : Slope.POS_EAST;
					break;
				}
				case 1: {
					data = data == 12 ? Slope.NEG_SOUTH : Slope.POS_SOUTH;
					break;
				}
				case 2: {
					data = data == 12 ? Slope.NEG_WEST : Slope.POS_WEST;
					break;
				}
				case 3: {
					data = data == 12 ? Slope.NEG_NORTH : Slope.POS_NORTH;
					break;
				}
			}
		}

		// If shift key is down, skip auto-orientation
		if (!entityLiving.isSneaking())
		{
			/*
			 * Check if slope should form into a corner
			 */

			TECarpentersBlock TE_XN = world.getBlockId(x - 1, y, z) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x - 1, y, z) : null;
			TECarpentersBlock TE_XP = world.getBlockId(x + 1, y, z) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x + 1, y, z) : null;
			TECarpentersBlock TE_YP = world.getBlockId(x, y + 1, z) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x, y + 1, z) : null;
			TECarpentersBlock TE_YN = world.getBlockId(x, y - 1, z) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x, y - 1, z) : null;
			TECarpentersBlock TE_ZN = world.getBlockId(x, y, z - 1) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x, y, z - 1) : null;
			TECarpentersBlock TE_ZP = world.getBlockId(x, y, z + 1) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x, y, z + 1) : null;
			TECarpentersBlock TE_XYNN = world.getBlockId(x - 1, y - 1, z) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x - 1, y - 1, z) : null;
			TECarpentersBlock TE_XYPN = world.getBlockId(x + 1, y - 1, z) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x + 1, y - 1, z) : null;
			TECarpentersBlock TE_YZNN = world.getBlockId(x, y - 1, z - 1) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x, y - 1, z - 1) : null;
			TECarpentersBlock TE_YZNP = world.getBlockId(x, y - 1, z + 1) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x, y - 1, z + 1) : null;
			TECarpentersBlock TE_XYNP = world.getBlockId(x - 1, y + 1, z) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x - 1, y + 1, z) : null;
			TECarpentersBlock TE_XYPP = world.getBlockId(x + 1, y + 1, z) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x + 1, y + 1, z) : null;
			TECarpentersBlock TE_YZPN = world.getBlockId(x, y + 1, z - 1) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x, y + 1, z - 1) : null;
			TECarpentersBlock TE_YZPP = world.getBlockId(x, y + 1, z + 1) == this.blockID ? (TECarpentersBlock)world.getBlockTileEntity(x, y + 1, z + 1) : null;
			
			// Gather neighboring slope types
			int data_XN = TE_XN != null ? BlockProperties.getData(TE_XN) : -1;
			int data_XP = TE_XP != null ? BlockProperties.getData(TE_XP) : -1;
			int data_ZN = TE_ZN != null ? BlockProperties.getData(TE_ZN) : -1;
			int data_ZP = TE_ZP != null ? BlockProperties.getData(TE_ZP) : -1;
			int data_YP = TE_YP != null ? BlockProperties.getData(TE_YP) : -1;
			int data_YN = TE_YN != null ? BlockProperties.getData(TE_YN) : -1;

			// Store old slope values (to check against new values at end of function)
			int temp_data_XN = TE_XN != null ? data_XN : -1;
			int temp_data_XP = TE_XP != null ? data_XP : -1;
			int temp_data_YP = TE_YP != null ? data_YP : -1;
			int temp_data_YN = TE_YN != null ? data_YN : -1;
			int temp_data_ZN = TE_ZN != null ? data_ZN : -1;
			int temp_data_ZP = TE_ZP != null ? data_ZP : -1;

			// Check if indirectly should transform to corner (in other words, this must transform to corner to match side)
			if (Slope.isNormalSlope(data))
			{
				if (TE_XN != null) {
					if (Slope.isSlopingNorth(data)) {
						if (Slope.isSlopingWest(data_XN) && !Slope.isSlopingSouth(data_XN))
							data = Slope.isPositive(data_XN) ? Slope.INT_POS_NW : Slope.INT_NEG_NW;
						if (Slope.isSlopingEast(data_XN) && !Slope.isSlopingSouth(data_XN))
							data = Slope.isPositive(data_XN) ? Slope.INT_POS_NE : Slope.INT_NEG_NE;
					}
					if (Slope.isSlopingSouth(data)) {
						if (Slope.isSlopingWest(data_XN) && !Slope.isSlopingSouth(data_XN))
							data = Slope.isPositive(data_XN) ? Slope.EXT_POS_SW : Slope.EXT_NEG_SW;
						if (Slope.isSlopingEast(data_XN) && !Slope.isSlopingSouth(data_XN))
							data = Slope.isPositive(data_XN) ? Slope.EXT_POS_SE : Slope.EXT_NEG_SE;
					}
				}
				if (TE_XP != null) {
					if (Slope.isSlopingNorth(data)) {
						if (Slope.isSlopingWest(data_XP) && !Slope.isSlopingNorth(data_XP))
							data = Slope.isPositive(data_XP) ? Slope.EXT_POS_NW : Slope.EXT_NEG_NW;
						if (Slope.isSlopingEast(data_XP) && !Slope.isSlopingNorth(data_XP))
							data = Slope.isPositive(data_XP) ? Slope.EXT_POS_NE : Slope.EXT_NEG_NE;
					}
					if (Slope.isSlopingSouth(data)) {
						if (Slope.isSlopingWest(data_XP) && !Slope.isSlopingNorth(data_XP))
							data = Slope.isPositive(data_XP) ? Slope.INT_POS_SW : Slope.INT_NEG_SW;
						if (Slope.isSlopingEast(data_XP) && !Slope.isSlopingNorth(data_XP))
							data = Slope.isPositive(data_XP) ? Slope.INT_POS_SE : Slope.INT_NEG_SE;
					}
				}
				if (TE_ZN != null) {
					if (Slope.isSlopingEast(data)) {
						if (Slope.isSlopingSouth(data_ZN) && !Slope.isSlopingWest(data_ZN))
							data = Slope.isPositive(data_ZN) ? Slope.INT_POS_SE : Slope.INT_NEG_SE;
						if (Slope.isSlopingNorth(data_ZN) && !Slope.isSlopingWest(data_ZN))
							data = Slope.isPositive(data_ZN) ? Slope.INT_POS_NE : Slope.INT_NEG_NE;
					}
					if (Slope.isSlopingWest(data)) {
						if (Slope.isSlopingSouth(data_ZN) && !Slope.isSlopingWest(data_ZN))
							data = Slope.isPositive(data_ZN) ? Slope.EXT_POS_SW : Slope.EXT_NEG_SW;
						if (Slope.isSlopingNorth(data_ZN) && !Slope.isSlopingWest(data_ZN))
							data = Slope.isPositive(data_ZN) ? Slope.EXT_POS_NW : Slope.EXT_NEG_NW;
					}
				}
				if (TE_ZP != null) {
					if (Slope.isSlopingEast(data)) {
						if (Slope.isSlopingSouth(data_ZP) && !Slope.isSlopingEast(data_ZP))
							data = Slope.isPositive(data_ZP) ? Slope.EXT_POS_SE : Slope.EXT_NEG_SE;
						if (Slope.isSlopingNorth(data_ZP) && !Slope.isSlopingEast(data_ZP))
							data = Slope.isPositive(data_ZP) ? Slope.EXT_POS_NE : Slope.EXT_NEG_NE;
					}
					if (Slope.isSlopingWest(data)) {
						if (Slope.isSlopingSouth(data_ZP) && !Slope.isSlopingEast(data_ZP))
							data = Slope.isPositive(data_ZP) ? Slope.INT_POS_SW : Slope.INT_NEG_SW;
						if (Slope.isSlopingNorth(data_ZP) && !Slope.isSlopingEast(data_ZP))
							data = Slope.isPositive(data_ZP) ? Slope.INT_POS_NW : Slope.INT_NEG_NW;
					}
				}
			}

			// Check if should directly transform to corner (in other words, two sides are already a valid corner pair)
			if (TE_ZN != null) {
				if (TE_XP != null) {
					if (Slope.isSlopingSouth(data_ZN) && Slope.isSlopingEast(data_XP))
						data = Slope.isPositive(data_XP, data_ZN) ? Slope.INT_POS_SE : Slope.INT_NEG_SE;
					if (Slope.isSlopingNorth(data_ZN) && Slope.isSlopingWest(data_XP))
						data = Slope.isPositive(data_XP, data_ZN) ? Slope.EXT_POS_NW : Slope.EXT_NEG_NW;
				}
				if (TE_XN != null) {
					if (Slope.isSlopingNorth(data_ZN) && Slope.isSlopingEast(data_XN))
						data = Slope.isPositive(data_XN, data_ZN) ? Slope.INT_POS_NE : Slope.INT_NEG_NE;
					if (Slope.isSlopingSouth(data_ZN) && Slope.isSlopingWest(data_XN))
						data = Slope.isPositive(data_XN, data_ZN) ? Slope.EXT_POS_SW : Slope.EXT_NEG_SW;
				}
			}
			if (TE_ZP != null) {
				if (TE_XN != null) {
					if (Slope.isSlopingNorth(data_ZP) && Slope.isSlopingWest(data_XN))
						data = Slope.isPositive(data_XN, data_ZP) ? Slope.INT_POS_NW : Slope.INT_NEG_NW;
					if (Slope.isSlopingSouth(data_ZP) && Slope.isSlopingEast(data_XN))
						data = Slope.isPositive(data_XN, data_ZP) ? Slope.EXT_POS_SE : Slope.EXT_NEG_SE;
				}
				if (TE_XP != null) {
					if (Slope.isSlopingSouth(data_ZP) && Slope.isSlopingWest(data_XP))
						data = Slope.isPositive(data_XP, data_ZP) ? Slope.INT_POS_SW : Slope.INT_NEG_SW;
					if (Slope.isSlopingNorth(data_ZP) && Slope.isSlopingEast(data_XP))
						data = Slope.isPositive(data_XP, data_ZP) ? Slope.EXT_POS_NE : Slope.EXT_NEG_NE;
				}
			}

			/*
			 *  Change neighboring slopes to matching corners where applicable
			 */

			if (Slope.isSlopingNorth(data)) {
				if (TE_ZN != null && Slope.hasMatchingVerticalOrientation(data, data_ZN)) {
					if (Slope.isSlopingEast(data_ZN))
						BlockProperties.setData(TE_ZN, Slope.isPositive(data) ? Slope.EXT_POS_NE : Slope.EXT_NEG_NE);
					if (Slope.isSlopingWest(data_ZN))
						BlockProperties.setData(TE_ZN, Slope.isPositive(data) ? Slope.INT_POS_NW : Slope.INT_NEG_NW);
				}
				if (TE_ZP != null && Slope.hasMatchingVerticalOrientation(data, data_ZP)) {
					if (Slope.isSlopingWest(data_ZP))
						BlockProperties.setData(TE_ZP, Slope.isPositive(data) ? Slope.EXT_POS_NW : Slope.EXT_NEG_NW);
					if (Slope.isSlopingEast(data_ZP))
						BlockProperties.setData(TE_ZP, Slope.isPositive(data) ? Slope.INT_POS_NE : Slope.INT_NEG_NE);
				}
			}
			if (Slope.isSlopingSouth(data)) {
				if (TE_ZN != null && Slope.hasMatchingVerticalOrientation(data, data_ZN)) {
					if (Slope.isSlopingEast(data_ZN))
						BlockProperties.setData(TE_ZN, Slope.isPositive(data) ? Slope.EXT_POS_SE : Slope.EXT_NEG_SE);
					if (Slope.isSlopingWest(data_ZN))
						BlockProperties.setData(TE_ZN, Slope.isPositive(data) ? Slope.INT_POS_SW : Slope.INT_NEG_SW);
				}
				if (TE_ZP != null && Slope.hasMatchingVerticalOrientation(data, data_ZP)) {
					if (Slope.isSlopingWest(data_ZP))
						BlockProperties.setData(TE_ZP, Slope.isPositive(data) ? Slope.EXT_POS_SW : Slope.EXT_NEG_SW);
					if (Slope.isSlopingEast(data_ZP))
						BlockProperties.setData(TE_ZP, Slope.isPositive(data) ? Slope.INT_POS_SE : Slope.INT_NEG_SE);
				}
			}
			if (Slope.isSlopingEast(data)) {
				if (TE_XN != null && Slope.hasMatchingVerticalOrientation(data, data_XN)) {
					if (Slope.isSlopingNorth(data_XN))
						BlockProperties.setData(TE_XN, Slope.isPositive(data) ? Slope.EXT_POS_NE : Slope.EXT_NEG_NE);
					if (Slope.isSlopingSouth(data_XN))
						BlockProperties.setData(TE_XN, Slope.isPositive(data) ? Slope.INT_POS_SE : Slope.INT_NEG_SE);
				}
				if (TE_XP != null && Slope.hasMatchingVerticalOrientation(data, data_XP)) {
					if (Slope.isSlopingSouth(data_XP))
						BlockProperties.setData(TE_XP, Slope.isPositive(data) ? Slope.EXT_POS_SE : Slope.EXT_NEG_SE);
					if (Slope.isSlopingNorth(data_XP))
						BlockProperties.setData(TE_XP, Slope.isPositive(data) ? Slope.INT_POS_NE : Slope.INT_NEG_NE);
				}
			}
			if (Slope.isSlopingWest(data)) {
				if (TE_XN != null && Slope.hasMatchingVerticalOrientation(data, data_XN)) {
					if (Slope.isSlopingNorth(data_XN))
						BlockProperties.setData(TE_XN, Slope.isPositive(data) ? Slope.EXT_POS_NW : Slope.EXT_NEG_NW);
					if (Slope.isSlopingSouth(data_XN))
						BlockProperties.setData(TE_XN, Slope.isPositive(data) ? Slope.INT_POS_SW : Slope.INT_NEG_SW);
				}
				if (TE_XP != null && Slope.hasMatchingVerticalOrientation(data, data_XP)) {
					if (Slope.isSlopingSouth(data_XP))
						BlockProperties.setData(TE_XP, Slope.isPositive(data) ? Slope.EXT_POS_SW : Slope.EXT_NEG_SW);
					if (Slope.isSlopingNorth(data_XP))
						BlockProperties.setData(TE_XP, Slope.isPositive(data) ? Slope.INT_POS_NW : Slope.INT_NEG_NW);
				}
			}

			/*
			 * Check if slope should form into a pyramid
			 */

			if (TE_XYNN != null && TE_XYPN != null && TE_YZNN != null && TE_YZNP != null) {
				if (	BlockProperties.getData(TE_XYNN) == Slope.POS_NORTH &&
						BlockProperties.getData(TE_XYPN) == Slope.POS_SOUTH &&
						BlockProperties.getData(TE_YZNN) == Slope.POS_EAST &&
						BlockProperties.getData(TE_YZNP) == Slope.POS_WEST
					)
						data = Slope.HALF_POS_PYR;
			}
			if (TE_XYNP != null && TE_XYPP != null && TE_YZPN != null && TE_YZPP != null) {
				if (	BlockProperties.getData(TE_XYNP) == Slope.NEG_NORTH &&
						BlockProperties.getData(TE_XYPP) == Slope.NEG_SOUTH &&
						BlockProperties.getData(TE_YZPN) == Slope.NEG_EAST &&
						BlockProperties.getData(TE_YZPP) == Slope.NEG_WEST
					)
						data = Slope.HALF_NEG_PYR;
			}

			/*
			 *  Check if slope should transform into side slope
			 *  Also will switch block above or below from normal interior corner to an oblique interior corner
			 */

			// Check if slope below is interior corner, change to oblique if it is, and change this to side slope
			if (TE_YP != null) {
				if (data_YP == Slope.INT_NEG_NE) {
					data = Slope.SIDE_NE;
					BlockProperties.setData(TE_YP, Slope.INT_NEG_OBL_NE);
				} else if (data_YP == Slope.INT_NEG_NW) {
					data = Slope.SIDE_NW;
					BlockProperties.setData(TE_YP, Slope.INT_NEG_OBL_NW);
				} else if (data_YP == Slope.INT_NEG_SE) {
					data = Slope.SIDE_SE;
					BlockProperties.setData(TE_YP, Slope.INT_NEG_OBL_SE);
				} else if (data_YP == Slope.INT_NEG_SW) {
					data = Slope.SIDE_SW;
					BlockProperties.setData(TE_YP, Slope.INT_NEG_OBL_SW);
				}
			}
			if (TE_YN != null) {
				if (data_YN == Slope.INT_POS_NE) {
					data = Slope.SIDE_NE;
					BlockProperties.setData(TE_YN, Slope.INT_POS_OBL_NE);
				} else if (data_YN == Slope.INT_POS_NW) {
					data = Slope.SIDE_NW;
					BlockProperties.setData(TE_YN, Slope.INT_POS_OBL_NW);
				} else if (data_YN == Slope.INT_POS_SE) {
					data = Slope.SIDE_SE;
					BlockProperties.setData(TE_YN, Slope.INT_POS_OBL_SE);
				} else if (data_YN == Slope.INT_POS_SW) {
					data = Slope.SIDE_SW;
					BlockProperties.setData(TE_YN, Slope.INT_POS_OBL_SW);
				}
			}

			// Check if slope above or below is side slope or oblique interior slope, and, if so, make this a continuation
			if (TE_YP != null) {
				if (Slope.isSideSlope(data_YP)) {
					data = data_YP;
				} else if (Slope.isObliqueInteriorCorner(data_YP)) {
					switch (data_YP) {
					case Slope.INT_NEG_OBL_NE:
						data = Slope.SIDE_NE;
						break;
					case Slope.INT_NEG_OBL_NW:
						data = Slope.SIDE_NW;
						break;
					case Slope.INT_NEG_OBL_SE:
						data = Slope.SIDE_SE;
						break;
					default: // Slope.INT_NEG_OBL_SW
						data = Slope.SIDE_SW;
						break;
					}
				}
			}
			if (TE_YN != null) {
				if (Slope.isSideSlope(data_YN)) {
					data = data_YN;
				} else if (Slope.isObliqueInteriorCorner(data_YN)) {
					switch (data_YN) {
					case Slope.INT_POS_OBL_NE:
						data = Slope.SIDE_NE;
						break;
					case Slope.INT_POS_OBL_NW:
						data = Slope.SIDE_NW;
						break;
					case Slope.INT_POS_OBL_SE:
						data = Slope.SIDE_SE;
						break;
					default: // Slope.INT_POS_OBL_SW
						data = Slope.SIDE_SW;
						break;
					}
				}
			}

			/*
			 * Check if slope should form into exterior oblique corner
			 */

			if (
					TE_YP != null && (Slope.isObliqueExteriorCorner(data_YP) || Slope.isSideSlope(data_YP)) ||
					TE_YN != null && (Slope.isObliqueExteriorCorner(data_YN) || Slope.isSideSlope(data_YN))
				)
			{
				if (TE_XP != null && TE_ZN != null) {
					
					if (Slope.isNormalSlope(data_XP) && Slope.isNormalSlope(data_ZN) && Slope.hasMatchingVerticalOrientation(data_XP, data_ZN))
						if (Slope.isSlopingWest(data_XP) && Slope.isSlopingNorth(data_ZN))
							data = Slope.isPositive(data_XP) ? Slope.EXT_POS_OBL_NW : Slope.EXT_NEG_OBL_NW;
					
				} else if (TE_ZN != null && TE_XN != null) {
					
					if (Slope.isNormalSlope(data_ZN) && Slope.isNormalSlope(data_XN) && (Slope.isPositive(data_ZN) == Slope.isPositive(data_XN)))
						if (Slope.isSlopingSouth(data_ZN) && Slope.isSlopingWest(data_XN))
							data = Slope.isPositive(data_ZN) ? Slope.EXT_POS_OBL_SW : Slope.EXT_NEG_OBL_SW;
					
				} else if (TE_XN != null && TE_ZP != null) {
					
					if (Slope.isNormalSlope(data_XN) && Slope.isNormalSlope(data_ZP) && (Slope.isPositive(data_XN) == Slope.isPositive(data_ZP)))
						if (Slope.isSlopingEast(data_XN) && Slope.isSlopingSouth(data_ZP))
							data = Slope.isPositive(data_XN) ? Slope.EXT_POS_OBL_SE : Slope.EXT_NEG_OBL_SE;
					
				} else if (TE_ZP != null && TE_XP != null) {
					
					if (Slope.isNormalSlope(data_ZP) && Slope.isNormalSlope(data_XP) && (Slope.isPositive(data_ZP) == Slope.isPositive(data_XP)))
						if (Slope.isSlopingNorth(data_ZP) && Slope.isSlopingEast(data_XP))
							data = Slope.isPositive(data_ZP) ? Slope.EXT_POS_OBL_NE : Slope.EXT_NEG_OBL_NE;
					
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
				if (data_YN != temp_data_YN)
					BlockProperties.setData(TE_YN, data_YN);
				if (data_YP != temp_data_YP)
					BlockProperties.setData(TE_YP, data_YP);
			}
		}
					
		BlockProperties.setData(TE, data);
	}

	@Override
	public boolean canCoverSide(TECarpentersBlock TE, World world, int x, int y, int z, int side)
	{
		return this.isBlockSolidOnSide(world, x, y, z, ForgeDirection.getOrientation(side));
	}

    @Override
	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType()
	{
		return CarpentersBlocks.carpentersSlopeRenderID;
	}

}
