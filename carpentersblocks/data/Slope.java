package carpentersblocks.data;

import static net.minecraftforge.common.ForgeDirection.DOWN;
import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.UP;
import static net.minecraftforge.common.ForgeDirection.WEST;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;
import carpentersblocks.CarpentersBlocks;
import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.BlockProperties;

public class Slope
{
	
	/*
	 * Type definitions
	 */
	public final static byte	SIDE_SW = 0,
								SIDE_NE = 1,
								SIDE_SE = 2,
								SIDE_NW = 3,
								NEG_EAST = 4,
								NEG_WEST = 5,
								NEG_NORTH = 6,
								NEG_SOUTH = 7,
								POS_EAST = 8,
								POS_WEST = 9,
								POS_NORTH = 10,
								POS_SOUTH = 11,
								INT_POS_SE = 12,
								INT_NEG_SE = 13,
								INT_POS_NE = 14,
								INT_NEG_NE = 15,
								INT_POS_NW = 16,
								INT_NEG_NW = 17,
								INT_POS_SW = 18,
								INT_NEG_SW = 19,
								EXT_POS_NW = 20,
								EXT_NEG_NW = 21,
								EXT_POS_SW = 22,
								EXT_NEG_SW = 23,
								EXT_POS_SE = 24,
								EXT_NEG_SE = 25,
								EXT_POS_NE = 26,
								EXT_NEG_NE = 27,
								INT_POS_OBL_SE = 28,
								INT_NEG_OBL_SE = 29,
								INT_POS_OBL_NE = 30,
								INT_NEG_OBL_NE = 31,
								INT_POS_OBL_NW = 32,
								INT_NEG_OBL_NW = 33,
								INT_POS_OBL_SW = 34,
								INT_NEG_OBL_SW = 35,
								EXT_POS_OBL_NW = 36,
								EXT_NEG_OBL_NW = 37,
								EXT_POS_OBL_SW = 38,
								EXT_NEG_OBL_SW = 39,
								EXT_POS_OBL_SE = 40,
								EXT_NEG_OBL_SE = 41,
								EXT_POS_OBL_NE = 42,
								EXT_NEG_OBL_NE = 43,
								HALF_POS_PYR = 44,
								HALF_NEG_PYR = 45;
	
	private static boolean bottomWedgeFacingSE(int slope)
	{
		switch (slope) {
			case Slope.SIDE_SE:
				return true;
			case Slope.INT_POS_OBL_SE:
				return true;
			case Slope.EXT_POS_OBL_SE:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean bottomWedgeFacingNE(int slope)
	{
		switch (slope) {
			case Slope.SIDE_NE:
				return true;
			case Slope.INT_POS_OBL_NE:
				return true;
			case Slope.EXT_POS_OBL_NE:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean bottomWedgeFacingNW(int slope)
	{
		switch (slope) {
			case Slope.SIDE_NW:
				return true;
			case Slope.INT_POS_OBL_NW:
				return true;
			case Slope.EXT_POS_OBL_NW:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean bottomWedgeFacingSW(int slope)
	{
		switch (slope) {
			case Slope.SIDE_SW:
				return true;
			case Slope.INT_POS_OBL_SW:
				return true;
			case Slope.EXT_POS_OBL_SW:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean topWedgeFacingSE(int slope)
	{
		switch (slope) {
			case Slope.SIDE_SE:
				return true;
			case Slope.INT_NEG_OBL_SE:
				return true;
			case Slope.EXT_NEG_OBL_SE:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean topWedgeFacingNE(int slope)
	{
		switch (slope) {
			case Slope.SIDE_NE:
				return true;
			case Slope.INT_NEG_OBL_NE:
				return true;
			case Slope.EXT_NEG_OBL_NE:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean topWedgeFacingNW(int slope)
	{
		switch (slope) {
			case Slope.SIDE_NW:
				return true;
			case Slope.INT_NEG_OBL_NW:
				return true;
			case Slope.EXT_NEG_OBL_NW:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean topWedgeFacingSW(int slope)
	{
		switch (slope) {
			case Slope.SIDE_SW:
				return true;
			case Slope.INT_NEG_OBL_SW:
				return true;
			case Slope.EXT_NEG_OBL_SW:
				return true;
			default:
				return false;
		}
	}
		
	private static boolean eastWedgeFacingPosN(int slope)
	{
		switch (slope) {
			case Slope.POS_NORTH:
				return true;
			case Slope.INT_POS_NE:
				return true;
			case Slope.EXT_POS_NW:
				return true;
			case Slope.INT_POS_OBL_NE:
				return true;
			case Slope.EXT_POS_OBL_NW:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean eastWedgeFacingPosS(int slope)
	{
		switch (slope) {
			case Slope.POS_SOUTH:
				return true;
			case Slope.INT_POS_SE:
				return true;
			case Slope.EXT_POS_SW:
				return true;
			case Slope.INT_POS_OBL_SE:
				return true;
			case Slope.EXT_POS_OBL_SW:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean eastWedgeFacingNegN(int slope)
	{
		switch (slope) {
			case Slope.NEG_NORTH:
				return true;
			case Slope.INT_NEG_NE:
				return true;
			case Slope.EXT_NEG_NW:
				return true;
			case Slope.INT_NEG_OBL_NE:
				return true;
			case Slope.EXT_NEG_OBL_NW:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean eastWedgeFacingNegS(int slope)
	{
		switch (slope) {
			case Slope.NEG_SOUTH:
				return true;
			case Slope.INT_NEG_SE:
				return true;
			case Slope.EXT_NEG_SW:
				return true;
			case Slope.INT_NEG_OBL_SE:
				return true;
			case Slope.EXT_NEG_OBL_SW:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean westWedgeFacingPosN(int slope)
	{
		switch (slope) {
			case Slope.POS_NORTH:
				return true;
			case Slope.INT_POS_NW:
				return true;
			case Slope.EXT_POS_NE:
				return true;
			case Slope.INT_POS_OBL_NW:
				return true;
			case Slope.EXT_POS_OBL_NE:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean westWedgeFacingPosS(int slope)
	{
		switch (slope) {
			case Slope.POS_SOUTH:
				return true;
			case Slope.INT_POS_SW:
				return true;
			case Slope.EXT_POS_SE:
				return true;
			case Slope.INT_POS_OBL_SW:
				return true;
			case Slope.EXT_POS_OBL_SE:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean westWedgeFacingNegN(int slope)
	{
		switch (slope) {
			case Slope.NEG_NORTH:
				return true;
			case Slope.INT_NEG_NW:
				return true;
			case Slope.EXT_NEG_NE:
				return true;
			case Slope.INT_NEG_OBL_NW:
				return true;
			case Slope.EXT_NEG_OBL_NE:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean westWedgeFacingNegS(int slope)
	{
		switch (slope) {
			case Slope.NEG_SOUTH:
				return true;
			case Slope.INT_NEG_SW:
				return true;
			case Slope.EXT_NEG_SE:
				return true;
			case Slope.INT_NEG_OBL_SW:
				return true;
			case Slope.EXT_NEG_OBL_SE:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean northWedgeFacingPosE(int slope)
	{
		switch (slope) {
			case Slope.POS_EAST:
				return true;
			case Slope.INT_POS_NE:
				return true;
			case Slope.EXT_POS_SE:
				return true;
			case Slope.INT_POS_OBL_NE:
				return true;
			case Slope.EXT_POS_OBL_SE:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean northWedgeFacingPosW(int slope)
	{
		switch (slope) {
			case Slope.POS_WEST:
				return true;
			case Slope.INT_POS_NW:
				return true;
			case Slope.EXT_POS_SW:
				return true;
			case Slope.INT_POS_OBL_NW:
				return true;
			case Slope.EXT_POS_OBL_SW:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean northWedgeFacingNegE(int slope)
	{
		switch (slope) {
			case Slope.NEG_EAST:
				return true;
			case Slope.INT_NEG_NE:
				return true;
			case Slope.EXT_NEG_SE:
				return true;
			case Slope.INT_NEG_OBL_NE:
				return true;
			case Slope.EXT_NEG_OBL_SE:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean northWedgeFacingNegW(int slope)
	{
		switch (slope) {
			case Slope.NEG_WEST:
				return true;
			case Slope.INT_NEG_NW:
				return true;
			case Slope.EXT_NEG_SW:
				return true;
			case Slope.INT_NEG_OBL_NW:
				return true;
			case Slope.EXT_NEG_OBL_SW:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean southWedgeFacingPosE(int slope)
	{
		switch (slope) {
			case Slope.POS_EAST:
				return true;
			case Slope.INT_POS_SE:
				return true;
			case Slope.EXT_POS_NE:
				return true;
			case Slope.INT_POS_OBL_SE:
				return true;
			case Slope.EXT_POS_OBL_NE:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean southWedgeFacingPosW(int slope)
	{
		switch (slope) {
			case Slope.POS_WEST:
				return true;
			case Slope.INT_POS_SW:
				return true;
			case Slope.EXT_POS_NW:
				return true;
			case Slope.INT_POS_OBL_SW:
				return true;
			case Slope.EXT_POS_OBL_NW:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean southWedgeFacingNegE(int slope)
	{
		switch (slope) {
			case Slope.NEG_EAST:
				return true;
			case Slope.INT_NEG_SE:
				return true;
			case Slope.EXT_NEG_NE:
				return true;
			case Slope.INT_NEG_OBL_SE:
				return true;
			case Slope.EXT_NEG_OBL_NE:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean southWedgeFacingNegW(int slope)
	{
		switch (slope) {
			case Slope.NEG_WEST:
				return true;
			case Slope.INT_NEG_SW:
				return true;
			case Slope.EXT_NEG_NW:
				return true;
			case Slope.INT_NEG_OBL_SW:
				return true;
			case Slope.EXT_NEG_OBL_NW:
				return true;
			default:
				return false;
		}
	}
	
	private static boolean connectsTo(int srcSlope, Block srcCover, IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		if (blockAccess.getBlockId(x, y, z) == CarpentersBlocks.blockCarpentersSlopeID)
		{
			TECarpentersBlock TE_adj = (TECarpentersBlock) blockAccess.getBlockTileEntity(x, y, z);
			Block adjCover = BlockProperties.getCoverBlock(TE_adj, 6);
			int adjSlope = BlockProperties.getData(TE_adj);

			if (
					srcCover.getRenderBlockPass() == adjCover.getRenderBlockPass() &&
					srcCover != CarpentersBlocks.blockCarpentersSlope &&
					srcCover != Block.ice &&
					srcCover.isOpaqueCube() == adjCover.isOpaqueCube() ||
					srcCover == adjCover
				)
			{
				switch (side)
				{
					case 0:
						return	bottomWedgeFacingSE(srcSlope) && topWedgeFacingSE(adjSlope) ||
								bottomWedgeFacingNE(srcSlope) && topWedgeFacingNE(adjSlope) ||
								bottomWedgeFacingNW(srcSlope) && topWedgeFacingNW(adjSlope) ||
								bottomWedgeFacingSW(srcSlope) && topWedgeFacingSW(adjSlope);
					case 1:
						return	topWedgeFacingSE(srcSlope) && bottomWedgeFacingSE(adjSlope) ||
								topWedgeFacingNE(srcSlope) && bottomWedgeFacingNE(adjSlope) ||
								topWedgeFacingNW(srcSlope) && bottomWedgeFacingNW(adjSlope) ||
								topWedgeFacingSW(srcSlope) && bottomWedgeFacingSW(adjSlope);
					case 2:
						return	westWedgeFacingPosN(srcSlope) && eastWedgeFacingPosN(adjSlope) ||
								westWedgeFacingPosS(srcSlope) && eastWedgeFacingPosS(adjSlope) ||
								westWedgeFacingNegN(srcSlope) && eastWedgeFacingNegN(adjSlope) ||
								westWedgeFacingNegS(srcSlope) && eastWedgeFacingNegS(adjSlope);
					case 3:
						return	eastWedgeFacingPosN(srcSlope) && westWedgeFacingPosN(adjSlope) ||
								eastWedgeFacingPosS(srcSlope) && westWedgeFacingPosS(adjSlope) ||
								eastWedgeFacingNegN(srcSlope) && westWedgeFacingNegN(adjSlope) ||
								eastWedgeFacingNegS(srcSlope) && westWedgeFacingNegS(adjSlope);
					case 4:
						return	southWedgeFacingPosE(srcSlope) && northWedgeFacingPosE(adjSlope) ||
								southWedgeFacingPosW(srcSlope) && northWedgeFacingPosW(adjSlope) ||
								southWedgeFacingNegE(srcSlope) && northWedgeFacingNegE(adjSlope) ||
								southWedgeFacingNegW(srcSlope) && northWedgeFacingNegW(adjSlope);
					case 5:
						return	northWedgeFacingPosE(srcSlope) && southWedgeFacingPosE(adjSlope) ||
								northWedgeFacingPosW(srcSlope) && southWedgeFacingPosW(adjSlope) ||
								northWedgeFacingNegE(srcSlope) && southWedgeFacingNegE(adjSlope) ||
								northWedgeFacingNegW(srcSlope) && southWedgeFacingNegW(adjSlope);
				}
			}
		}

		return false;
	}
	
	public static boolean hasMatchingVerticalOrientation(int type1, int type2)
	{
		return Slope.isPositive(type1) == Slope.isPositive(type2);
	}

	public static boolean shouldRenderTop(int srcSlope, Block srcBlock, IBlockAccess blockAccess, int x, int y, int z)
	{
		if (!connectsTo(srcSlope, srcBlock, blockAccess, x, y, z, 0)) {
			if ((Slope.isPositive(srcSlope) && Slope.isObliqueInteriorCorner(srcSlope)) || Slope.isSideSlope(srcSlope) || Slope.isNegative(srcSlope))
				return true;
		}
		return false;
	}

	public static boolean shouldRenderBottom(int srcSlope, Block srcBlock, IBlockAccess blockAccess, int x, int y, int z)
	{	
		if (!connectsTo(srcSlope, srcBlock, blockAccess, x, y, z, 1))
			if ((Slope.isNegative(srcSlope) && Slope.isObliqueInteriorCorner(srcSlope)) || Slope.isSideSlope(srcSlope) || Slope.isPositive(srcSlope))
				return true;
		return false;
	}

	public static boolean shouldRenderNorth(int srcSlope, Block srcBlock, IBlockAccess blockAccess, int x, int y, int z)
	{
		if (!connectsTo(srcSlope, srcBlock, blockAccess, x, y, z, 5)) {
			return	srcSlope == 0 ||
					srcSlope == 2 ||
					srcSlope == 4 ||
					srcSlope == 5 ||
					srcSlope > 6 && srcSlope < 10 ||
					srcSlope > 10 && srcSlope < 20 ||
					srcSlope > 21 && srcSlope < 26 ||
					srcSlope > 27 && srcSlope < 36 ||
					srcSlope > 37 && srcSlope < 42;
		} else {
			return false;
		}
	}

	public static boolean shouldRenderSouth(int srcSlope, Block srcBlock, IBlockAccess blockAccess, int x, int y, int z)
	{
		if (!connectsTo(srcSlope, srcBlock, blockAccess, x, y, z, 4)) {
			return	srcSlope == 1 ||
					srcSlope > 2 && srcSlope < 7 ||
					srcSlope > 7 && srcSlope < 11 ||
					srcSlope > 11 && srcSlope < 22 ||
					srcSlope > 25 && srcSlope < 38 ||
					srcSlope == 42 ||
					srcSlope == 43;
		} else {
			return false;
		}
	}

	public static boolean shouldRenderEast(int srcSlope, Block srcBlock, IBlockAccess blockAccess, int x, int y, int z)
	{
		if (!connectsTo(srcSlope, srcBlock, blockAccess, x, y, z, 3)) {
			return	srcSlope == 0 ||
					srcSlope == 3 ||
					srcSlope > 4 && srcSlope < 8 ||
					srcSlope > 8 && srcSlope < 24 ||
					srcSlope > 27 && srcSlope < 40;
		} else {
			return false;
		}
	}

	public static boolean shouldRenderWest(int srcSlope, Block srcBlock, IBlockAccess blockAccess, int x, int y, int z)
	{
		if (!connectsTo(srcSlope, srcBlock, blockAccess, x, y, z, 2)) {
			return	srcSlope == 1 ||
					srcSlope == 2 ||
					srcSlope == 4 ||
					srcSlope > 5 && srcSlope < 9 ||
					srcSlope > 9 && srcSlope < 20 ||
					srcSlope > 23 && srcSlope < 36 ||
					srcSlope > 39 && srcSlope < 44;
		} else {
			return false;
		}
	}

	public static boolean isNormalSlope(int slope)
	{
		return slope > 3 && slope < 12;
	}
	
	public static boolean isPositive(int slope)
	{
		return	slope > 7 && slope < 12 || slope > 11 && slope % 2 == 0;
	}
	
	public static boolean isPositive(int slope1, int slope2)
	{
		return isPositive(slope1) && isPositive(slope2);
	}
	
	public static boolean isNegative(int slope)
	{
		return	slope > 3 && slope < 8 || slope > 11 && slope % 2 != 0;
	}
	
	public static boolean isNegative(int slope1, int slope2)
	{
		return isNegative(slope1) && isNegative(slope2);
	}
	
	public static boolean isInteriorCorner(int slope)
	{
		return slope > 11 && slope < 20;
	}
	
	public static boolean isExteriorCorner(int slope)
	{
		return slope > 19 && slope < 28;
	}
	
	public static boolean isObliqueInteriorCorner(int slope)
	{
		return slope > 27 && slope < 36;
	}
	
	public static boolean isObliqueExteriorCorner(int slope)
	{
		return slope > 35 && slope < 44;
	}
	
	public static boolean isObliqueCorner(int slope)
	{
		return isObliqueInteriorCorner(slope) || isObliqueExteriorCorner(slope);
	}
	
	public static boolean isNormalCorner(int slope)
	{
		return isInteriorCorner(slope) || isExteriorCorner(slope);
	}
	
	public static boolean isPyramid(int slope)
	{
		return slope > 43 && slope < 46;
	}
	
	public static boolean isSlopingNorth(int slope)
	{
		return	slope == 6 ||
				slope == 10 ||
				slope > 13 && slope < 18 ||
				slope == 20 ||
				slope == 21 ||
				slope > 25 && slope < 28;
	}
	
	public static boolean isSlopingSouth(int slope)
	{		
		return	slope == 7 ||
				slope > 10 && slope < 14 ||
				slope == 18 ||
				slope == 19 ||
				slope > 21 && slope < 26;
	}

	public static boolean isSlopingEast(int slope)
	{
		return	slope == 4 ||
				slope == 8 ||
				slope > 11 && slope < 16 ||
				slope > 23 && slope < 28;
	}

	public static boolean isSlopingWest(int slope)
	{	
		return	slope == 5 ||
				slope == 9 ||
				slope > 15 && slope < 24;
	}
	
	public static boolean isSideSlope(int slope)
	{
		return slope < 4;
	}
	
	public static boolean isSideSlopingEast(int slope)
	{
		return slope == 2 || slope == 1;
	}
	
	public static boolean isSideSlopingWest(int slope)
	{
		return slope == 0 || slope == 3;
	}
	
	public static boolean isSideSlopingNorth(int slope)
	{
		return slope == 1 || slope == 3;
	}
	
	public static boolean isSideSlopingSouth(int slope)
	{
		return slope == 0 || slope == 2;
	}
	
	public static boolean isBottomFaceSolid(int slope)
	{
		return	slope > 7 && slope < 13 ||
				slope > 13 && slope < 35 && slope % 2 == 0 ||
				slope == 44;
	}
	
	public static boolean isTopFaceSolid(int slope)
	{
		return	slope > 3 && slope < 8 ||
				slope > 12 && slope < 36 && slope % 2 != 0 ||
				slope == 45;
	}
	
	public static boolean isEastFaceSolid(int slope)
	{
		return	slope == 0 ||
				slope == 3 ||
				slope == 5 ||
				slope == 9 ||
				slope > 15 && slope < 20 ||
				slope > 31 && slope < 36;
	}
	
	public static boolean isWestFaceSolid(int slope)
	{
		return	slope == 1 ||
				slope == 2 ||
				slope == 4 ||
				slope == 8 ||
				slope > 11 && slope < 16 ||
				slope > 27 && slope < 32;
	}
	
	public static boolean isNorthFaceSolid(int slope)
	{
		return	slope == 0 ||
				slope == 2 ||
				slope == 7 ||
				slope > 10 && slope < 14 ||
				slope == 18 ||
				slope == 19 ||
				slope == 28 ||
				slope == 29 ||
				slope == 34 ||
				slope == 35;
	}
	
	public static boolean isSouthFaceSolid(int slope)
	{
		return	slope == 1 ||
				slope == 3 ||
				slope == 6 ||
				slope == 10 ||
				slope > 13 && slope < 18 ||
				slope > 29 && slope < 34;
	}
	
	public static boolean isSideWedge(TECarpentersBlock TE, ForgeDirection side)
	{
		switch (BlockProperties.getData(TE))
		{
			case 0:
				return side.equals(DOWN) || side.equals(UP);
			case 1:
				return side.equals(DOWN) || side.equals(UP);
			case 2:
				return side.equals(DOWN) || side.equals(UP);
			case 3:
				return side.equals(DOWN) || side.equals(UP);
			case 4:
				return side.equals(WEST) || side.equals(EAST);
			case 5:
				return side.equals(WEST) || side.equals(EAST);
			case 6:
				return side.equals(NORTH) || side.equals(SOUTH);
			case 7:
				return side.equals(NORTH) || side.equals(SOUTH);
			case 8:
				return side.equals(WEST) || side.equals(EAST);
			case 9:
				return side.equals(WEST) || side.equals(EAST);
			case 10:
				return side.equals(NORTH) || side.equals(SOUTH);
			case 11:
				return side.equals(NORTH) || side.equals(SOUTH);
			case 12:
				return side.equals(NORTH) || side.equals(EAST);
			case 13:
				return side.equals(NORTH) || side.equals(EAST);
			case 14:
				return side.equals(NORTH) || side.equals(WEST);
			case 15:
				return side.equals(NORTH) || side.equals(WEST);
			case 16:
				return side.equals(SOUTH) || side.equals(WEST);
			case 17:
				return side.equals(SOUTH) || side.equals(WEST);
			case 18:
				return side.equals(SOUTH) || side.equals(EAST);
			case 19:
				return side.equals(SOUTH) || side.equals(EAST);
			case 20:
				return side.equals(NORTH) || side.equals(EAST);
			case 21:
				return side.equals(NORTH) || side.equals(EAST);
			case 22:
				return side.equals(NORTH) || side.equals(WEST);
			case 23:
				return side.equals(NORTH) || side.equals(WEST);
			case 24:
				return side.equals(SOUTH) || side.equals(WEST);
			case 25:
				return side.equals(SOUTH) || side.equals(WEST);
			case 26:
				return side.equals(SOUTH) || side.equals(EAST);
			case 27:
				return side.equals(SOUTH) || side.equals(EAST);
			case 28:
				return side.equals(DOWN) || side.equals(NORTH) || side.equals(EAST);
			case 29:
				return side.equals(UP) || side.equals(NORTH) || side.equals(EAST);
			case 30:
				return side.equals(DOWN) || side.equals(NORTH) || side.equals(WEST);
			case 31:
				return side.equals(UP) || side.equals(NORTH) || side.equals(WEST);
			case 32:
				return side.equals(DOWN) || side.equals(SOUTH) || side.equals(WEST);
			case 33:
				return side.equals(UP) || side.equals(SOUTH) || side.equals(WEST);
			case 34:
				return side.equals(DOWN) || side.equals(SOUTH) || side.equals(EAST);
			case 35:
				return side.equals(UP) || side.equals(SOUTH) || side.equals(EAST);
			case 36:
				return side.equals(DOWN) || side.equals(NORTH) || side.equals(EAST);
			case 37:
				return side.equals(UP) || side.equals(NORTH) || side.equals(EAST);
			case 38:
				return side.equals(DOWN) || side.equals(NORTH) || side.equals(WEST);
			case 39:
				return side.equals(UP) || side.equals(NORTH) || side.equals(WEST);
			case 40:
				return side.equals(DOWN) || side.equals(SOUTH) || side.equals(WEST);
			case 41:
				return side.equals(UP) || side.equals(SOUTH) || side.equals(WEST);
			case 42:
				return side.equals(DOWN) || side.equals(SOUTH) || side.equals(EAST);
			case 43:
				return side.equals(UP) || side.equals(SOUTH) || side.equals(EAST);
			default:
				return false;
		}
	}

}
