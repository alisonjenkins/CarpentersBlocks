package carpentersblocks.renderer.helper;

import net.minecraft.block.BlockGrass;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class VertexHelper {
	
	/**
	 * Offset used when drawing opposite block faces to reduce z-fighting.
	 * Currently ugly, so the offset is set to zero.  Will investigate
	 * fixes in the future.
	 */
	protected static double offset = 0.0D;
	
	/**
	 * Sets offset for drawing face.
	 */
	public static void setOffset(double render_offset)
	{
		offset = render_offset;
	}
	
	/**
	 * Clears offset.
	 */
	public static void clearOffset()
	{
		offset = 0.0D;
	}
	
	/**
	 * Returns whether icon is draping overlay type.
	 * This will set the render helpers to translate the icon
	 * down with yMax.
	 */
	protected final static boolean iconHasFloatingHeight(Icon icon)
	{
		return	icon == BlockGrass.getIconSideOverlay() ||
				icon.getIconName().startsWith("overlay_") && icon.getIconName().endsWith("_side");
	}

	/**
	 * Applies brightness, color, and adds vertex through tessellator
	 */
	public static void setupVertex(RenderBlocks renderBlocks, double x, double y, double z, double u, double v, int numVertex)
	{
		Tessellator tessellator = Tessellator.instance;
		
		/*
		 * Apply lighting to faces if ambient occlusion is enabled
		 */
		if (renderBlocks.enableAO) {
			switch(numVertex) {
			case 0:
				tessellator.setColorOpaque_F(renderBlocks.colorRedTopLeft, renderBlocks.colorGreenTopLeft, renderBlocks.colorBlueTopLeft);
				tessellator.setBrightness(renderBlocks.brightnessTopLeft);
				break;
			case 1:
				tessellator.setColorOpaque_F(renderBlocks.colorRedBottomLeft, renderBlocks.colorGreenBottomLeft, renderBlocks.colorBlueBottomLeft);
				tessellator.setBrightness(renderBlocks.brightnessBottomLeft);
				break;
			case 2:
				tessellator.setColorOpaque_F(renderBlocks.colorRedBottomRight, renderBlocks.colorGreenBottomRight, renderBlocks.colorBlueBottomRight);
				tessellator.setBrightness(renderBlocks.brightnessBottomRight);
				break;
			case 3:
				tessellator.setColorOpaque_F(renderBlocks.colorRedTopRight, renderBlocks.colorGreenTopRight, renderBlocks.colorBlueTopRight);
				tessellator.setBrightness(renderBlocks.brightnessTopRight);
				break;
			}
		}

		/*
		 * Draw vertex
		 */
		tessellator.addVertexWithUV(x, y, z, u, v);
	}
	
}
