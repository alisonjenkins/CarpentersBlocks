package carpentersblocks.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import carpentersblocks.block.BlockBase;
import carpentersblocks.tileentity.TECarpentersBlock;

public class HingedBase extends BlockHandlerBase
{
	/**
	 * Returns whether hinged frame should render.
	 */
	protected boolean shouldRenderFrame(TECarpentersBlock TE, RenderBlocks renderBlocks, Block coverBlock, int renderPass)
	{
		if (renderAlphaOverride) {
			return renderPass == 1;
		} else {
			return	renderBlocks.hasOverrideBlockTexture() ||
					coverBlock.getRenderBlockPass() == renderPass ||
					coverBlock instanceof BlockBase && renderPass == 0 ||
					shouldRenderPattern(TE, renderPass);
		}
	}
	
	/**
	 * Returns whether hinged screen, glass or handles should render.
	 */
	protected boolean shouldRenderPieces(TECarpentersBlock TE, RenderBlocks renderBlocks, Block coverBlock, int renderPass)
	{
		/*
		 * This should always render on the solid pass because alpha
		 * pass will render each face front and back.  An alpha
		 * override can be ignored because these elements don't inherit
		 * overlays or chisel patterns.
		 */
		return renderPass == 0;
	}
	
}
