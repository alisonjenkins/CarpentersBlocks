package carpentersblocks.data;

public class Stairs
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
								EXT_NEG_NE = 27;

	public static boolean haveMatchingOrientation(int data1, int data2)
	{
		return Stairs.arePositive(data1) == Stairs.arePositive(data2);
	}
	
	public static boolean areNormalStairs(int data)
	{
		return data > 3 && data < 12;
	}
	
	public static boolean areInteriorCorner(int data) {
		return data > 11 && data < 20;
	}
	
	public static boolean areExteriorCorner(int data) {
		return data > 19 && data < 28;
	}
		
	public static boolean areFacingNorth(int data)
	{
		if (
				data == 1 ||
				data == 3 ||
				data == 6 ||
				data == 10 ||
				(data > 13 && data < 18) ||
				(data > 19 && data < 22) ||
				(data > 25 && data < 28)				
			)
			return true;
		
		return false;
	}
		
	public static boolean areFacingSouth(int data)
	{
		if (
				data == 0 ||
				data == 2 ||
				data == 7 ||
				data == 11 ||
				(data > 11 && data < 14) ||
				(data > 17 && data < 20) ||
				(data > 21 && data < 26)
			)
			return true;
		
		return false;
	}
	
	public static boolean areFacingEast(int data)
	{
		if (
				(data > 0 && data < 3) ||
				data == 4 ||
				data == 8 ||
				(data > 11 && data < 16) ||
				(data > 23 && data < 28)
			)
			return true;
		
		return false;
	}

	public static boolean areFacingWest(int data)
	{
		if (
				data == 0 ||
				data == 3 ||
				data == 5 ||
				data == 9 ||
				(data > 15 && data < 24)
			)
			return true;
		
		return false;
	}
		
	public static boolean arePositive(int data)
	{
		if (
				(data > 7 && data < 12) ||
				(data > 11 && data < 28 && data % 2 == 0)
			)
			return true;
		
		return false;
	}
	
	public static boolean areNegative(int data)
	{
		return !arePositive(data);
	}
	
	public static boolean areSideStairs(int data)
	{
		return data < 4;
	}
	
	public static boolean arePositive(int data1, int data2)
	{
		return arePositive(data1) && arePositive(data2);
	}
	
	public static boolean isBottomFaceSolid(int data)
	{
		return arePositive(data) && !areSideStairs(data);
	}

	public static boolean isTopFaceSolid(int data)
	{
		return !arePositive(data) && !areSideStairs(data);
	}
		
	public static boolean isEastFaceSolid(int data)
	{
		if (
				data == 0 ||
				data == 3 ||
				data == 5 ||
				data == 9 ||
				(data > 15 && data < 20)
			)
			return true;

		return false;
	}
			
	public static boolean isWestFaceSolid(int data)
	{
		if (
				(data > 0 && data < 3) ||
				data == 4 ||
				data == 8 ||
				(data > 11 && data < 16)
			)
			return true;
		
		return false;
	}

	public static boolean isNorthFaceSolid(int data)
	{
		if (
				data == 0 ||
				data == 2 ||
				data == 7 ||
				data == 11 ||
				(data > 11 && data < 14) ||
				(data > 17 && data < 20)
			)
			return true;
		
		return false;
	}

	public static boolean isSouthFaceSolid(int data)
	{
		if (
				data == 1 ||
				data == 3 ||
				data == 6 ||
				data == 10 ||
				(data > 13 && data < 18)
			)
			return true;
				
		return false;
	}
	
}
