package carpentersblocks.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

import org.lwjgl.opengl.GL11;

import carpentersblocks.data.Slope;
import carpentersblocks.renderer.helper.RenderHelperCorner;
import carpentersblocks.renderer.helper.RenderHelperOblique;
import carpentersblocks.renderer.helper.RenderHelperPyramid;
import carpentersblocks.renderer.helper.RenderHelperSlope;
import carpentersblocks.renderer.helper.VertexHelper;
import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.BlockProperties;

public class BlockHandlerCarpentersSlope extends BlockHandlerBase {

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks) {
    	
    	Tessellator tessellator = Tessellator.instance;
    	GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
    	GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    	
    	renderBlocks.setRenderBounds(0, 0, 0, 1.0D, 1.0D, 1.0D);

    	tessellator.startDrawingQuads();
    	tessellator.setNormal(0.0F, -1.0F, 0.0F);
    	RenderHelperSlope.renderFaceYNeg(renderBlocks, Slope.POS_NORTH, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(0));
    	tessellator.draw();
    	tessellator.startDrawingQuads();
    	tessellator.setNormal(0.0F, 1.0F, 0.0F);
    	RenderHelperSlope.renderFaceYPos(renderBlocks, Slope.POS_NORTH, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(1));
    	tessellator.draw();
    	tessellator.startDrawingQuads();
    	tessellator.setNormal(0.0F, 0.0F, -1.0F);
    	RenderHelperSlope.renderFaceZNeg(renderBlocks, Slope.POS_NORTH, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(2));
    	tessellator.draw();
    	tessellator.startDrawingQuads();
    	tessellator.setNormal(0.0F, 0.0F, 1.0F);
    	RenderHelperSlope.renderFaceZPos(renderBlocks, Slope.POS_NORTH, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(2));
    	tessellator.draw();
    	tessellator.startDrawingQuads();
    	tessellator.setNormal(1.0F, 0.0F, 0.0F);
    	RenderHelperSlope.renderFaceXPos(renderBlocks, Slope.POS_NORTH, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(2));
    	tessellator.draw();
    	
    	GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }

    /**
     * Sets slope-specific variables and calls prepareRender().
     */
    private void prepareSlopeRender(TECarpentersBlock TE, RenderBlocks renderBlocks, Block coverBlock, Block srcBlock, int side, int renderID, boolean isSloped, int x, int y, int z, float lightness)
    {
		isFaceSloped = isSloped;
		slopeRenderID = renderID;
		
		prepareRender(TE, renderBlocks, coverBlock, srcBlock, side, x, y, z, lightness);
    }

    @Override
    /**
     * Renders side.
     */
    protected void renderSide(TECarpentersBlock TE, RenderBlocks renderBlocks, int side, double offset, int x, int y, int z, Icon icon)
    {
    	if (isSideCover) {
    		
    		super.renderSide(TE, renderBlocks, side, offset, x, y, z, icon);
    		
    	} else {
    	
	    	if (offset > 0)
	    		VertexHelper.setOffset(offset);
	
	    	int slope = BlockProperties.getData(TE);
	
	    	switch (slopeRenderID) {
	    	case 1:
	    		RenderHelperOblique.renderFaceYNeg(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 2:
	    		RenderHelperOblique.renderSlopeYNeg(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 3:
	    		RenderHelperOblique.renderFaceYPos(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 4:
	    		RenderHelperOblique.renderSlopeYPos(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 5:
	    		RenderHelperOblique.renderFaceZNeg(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 6:
	    		RenderHelperOblique.renderFaceZPos(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 7:
	    		RenderHelperOblique.renderFaceXNeg(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 8:
	    		RenderHelperOblique.renderFaceXPos(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 9:
	    		RenderHelperSlope.renderFaceYNeg(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 10:
	    		RenderHelperSlope.renderFaceYPos(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 11:
	    		RenderHelperSlope.renderFaceZNeg(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 12:
	    		RenderHelperSlope.renderFaceZPos(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 13:
	    		RenderHelperSlope.renderFaceXNeg(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 14:
	    		RenderHelperSlope.renderFaceXPos(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 15:
	    		RenderHelperCorner.renderFaceYNegZNegZPos(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 16:
	    		RenderHelperCorner.renderFaceYNegXNegXPos(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 17:
	    		RenderHelperCorner.renderFaceYPosZNegZPos(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 18:
	    		RenderHelperCorner.renderFaceYPosXNegXPos(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 19:
	    		RenderHelperCorner.renderFaceZNeg(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 20:
	    		RenderHelperCorner.renderFaceZPos(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 21:
	    		RenderHelperCorner.renderFaceXNeg(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 22:
	    		RenderHelperCorner.renderFaceXPos(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 23:
	    		RenderHelperPyramid.renderFaceYNegZNeg(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 24:
	    		RenderHelperPyramid.renderFaceYNegZPos(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 25:
	    		RenderHelperPyramid.renderFaceYNegXNeg(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 26:
	    		RenderHelperPyramid.renderFaceYNegXPos(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 27:
	    		RenderHelperPyramid.renderFaceYPosZNeg(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 28:
	    		RenderHelperPyramid.renderFaceYPosZPos(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 29:
	    		RenderHelperPyramid.renderFaceYPosXNeg(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	case 30:
	    		RenderHelperPyramid.renderFaceYPosXPos(renderBlocks, slope, x, y, z, icon);
	    		break;
	    	}
	
	    	VertexHelper.clearOffset();
    	
    	}
    }

    @Override
    /**
     * Render slope using Ambient Occlusion (both minimum and maximum are handled here)
     */
	public boolean renderStandardSlopeWithAmbientOcclusion(TECarpentersBlock TE, RenderBlocks renderBlocks, Block coverBlock, Block srcBlock, int x, int y, int z, float red, float green, float blue, boolean useMaxSmoothLighting)
    {
    	int data = BlockProperties.getData(TE);
    	
        renderBlocks.enableAO = true;
        int mixedBrightness = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(983055);

        boolean canBlockGrassXYNN;
        boolean canBlockGrassXYPN;
        boolean canBlockGrassYZNN;
        boolean canBlockGrassYZNP;
        float aoLightValue;
        int offsetBrightness;
        
        float lightnessTop = 1.0F;
        float lightnessBottom = 0.5F;
        float lightnessEast = 0.8F;
        float lightnessWest = 0.8F;
        float lightnessNorth = 0.6F;
        float lightnessSouth = 0.6F;
        
        // Lightness values for positive and negative slopes
        float lightnessBottomEastWest = 0.65F;
        float lightnessBottomNorthSouth = 0.55F;
        float lightnessTopEastWest = 0.9F;
        float lightnessTopNorthSouth = 0.8F;
        
        // Lightness value for oblique slopes
        float lightnessPosOblique = 0.85F;
        float lightnessNegOblique = 0.6F;
        
        // Lightness value for sideways slopes
        float lightnessSide = 0.7F;
        
        Icon icon;

        /*
         * Store ambient occlusion and brightness values for each face and each corner
         */
        float aoBottomTopLeft = 0;
        float aoBottomTopRight = 0;
        float aoBottomBottomRight = 0;
        float aoBottomBottomLeft = 0;
        int brightnessBottomTopLeft = 0;
        int brightnessBottomTopRight = 0;
        int brightnessBottomBottomRight = 0;
        int brightnessBottomBottomLeft = 0;
        float aoTopTopLeft = 0;
        float aoTopTopRight = 0;
        float aoTopBottomRight = 0;
        float aoTopBottomLeft = 0;
        int brightnessTopTopLeft = 0;
        int brightnessTopTopRight = 0;
        int brightnessTopBottomRight = 0;
        int brightnessTopBottomLeft = 0;
        float aoEastTopLeft = 0;
        float aoEastTopRight = 0;
        float aoEastBottomRight = 0;
        float aoEastBottomLeft = 0;
        int brightnessEastTopLeft = 0;
        int brightnessEastTopRight = 0;
        int brightnessEastBottomRight = 0;
        int brightnessEastBottomLeft = 0;
        float aoWestTopLeft = 0;
        float aoWestTopRight = 0;
        float aoWestBottomRight = 0;
        float aoWestBottomLeft = 0;
        int brightnessWestTopLeft = 0;
        int brightnessWestTopRight = 0;
        int brightnessWestBottomRight = 0;
        int brightnessWestBottomLeft = 0;
        float aoNorthTopLeft = 0;
        float aoNorthTopRight = 0;
        float aoNorthBottomRight = 0;
        float aoNorthBottomLeft = 0;
        int brightnessNorthTopLeft = 0;
        int brightnessNorthTopRight = 0;
        int brightnessNorthBottomRight = 0;
        int brightnessNorthBottomLeft = 0;
        float aoSouthTopLeft = 0;
        float aoSouthTopRight = 0;
        float aoSouthBottomRight = 0;
        float aoSouthBottomLeft = 0;
        int brightnessSouthTopLeft = 0;
        int brightnessSouthTopRight = 0;
        int brightnessSouthBottomRight = 0;
        int brightnessSouthBottomLeft = 0;
        
        /*
         * Store offset ambient occlusion and brightness values for each face and each corner.
         * Used for shading slopes.
         */
        float aoBottomTopLeftOffset = 0;
        float aoBottomTopRightOffset = 0;
        float aoBottomBottomRightOffset = 0;
        float aoBottomBottomLeftOffset = 0;
        int brightnessBottomTopLeftOffset = 0;
        int brightnessBottomTopRightOffset = 0;
        int brightnessBottomBottomRightOffset = 0;
        int brightnessBottomBottomLeftOffset = 0;
        float aoTopTopLeftOffset = 0;
        float aoTopTopRightOffset = 0;
        float aoTopBottomRightOffset = 0;
        float aoTopBottomLeftOffset = 0;
        int brightnessTopTopLeftOffset = 0;
        int brightnessTopTopRightOffset = 0;
        int brightnessTopBottomRightOffset = 0;
        int brightnessTopBottomLeftOffset = 0;
        float aoEastTopLeftOffset = 0;
        float aoEastTopRightOffset = 0;
        float aoEastBottomRightOffset = 0;
        float aoEastBottomLeftOffset = 0;
        int brightnessEastTopLeftOffset = 0;
        int brightnessEastTopRightOffset = 0;
        int brightnessEastBottomRightOffset = 0;
        int brightnessEastBottomLeftOffset = 0;
        float aoWestTopLeftOffset = 0;
        float aoWestTopRightOffset = 0;
        float aoWestBottomRightOffset = 0;
        float aoWestBottomLeftOffset = 0;
        int brightnessWestTopLeftOffset = 0;
        int brightnessWestTopRightOffset = 0;
        int brightnessWestBottomRightOffset = 0;
        int brightnessWestBottomLeftOffset = 0;
        float aoNorthTopLeftOffset = 0;
        float aoNorthTopRightOffset = 0;
        float aoNorthBottomRightOffset = 0;
        float aoNorthBottomLeftOffset = 0;
        int brightnessNorthTopLeftOffset = 0;
        int brightnessNorthTopRightOffset = 0;
        int brightnessNorthBottomRightOffset = 0;
        int brightnessNorthBottomLeftOffset = 0;
        float aoSouthTopLeftOffset = 0;
        float aoSouthTopRightOffset = 0;
        float aoSouthBottomRightOffset = 0;
        float aoSouthBottomLeftOffset = 0;
        int brightnessSouthTopLeftOffset = 0;
        int brightnessSouthTopRightOffset = 0;
        int brightnessSouthBottomRightOffset = 0;
        int brightnessSouthBottomLeftOffset = 0;

        /*
         * Compute ambient occlusion and brightness for bottom face
         */
        for (int offset = 1; offset > -1; offset--)
        {
        	if (offset == 0)
        		--y;

        	renderBlocks.aoBrightnessXYNN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x - 1, y, z);
        	renderBlocks.aoBrightnessYZNN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z - 1);
        	renderBlocks.aoBrightnessYZNP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z + 1);
        	renderBlocks.aoBrightnessXYPN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x + 1, y, z);
        	renderBlocks.aoLightValueScratchXYNN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x - 1, y, z);
        	renderBlocks.aoLightValueScratchYZNN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y, z - 1);
        	renderBlocks.aoLightValueScratchYZNP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y, z + 1);
        	renderBlocks.aoLightValueScratchXYPN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x + 1, y, z);
        	canBlockGrassXYPN = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x + 1, y - 1, z)];
        	canBlockGrassXYNN = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x - 1, y - 1, z)];
        	canBlockGrassYZNP = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x, y - 1, z + 1)];
        	canBlockGrassYZNN = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x, y - 1, z - 1)];

        	if (!canBlockGrassYZNN && !canBlockGrassXYNN) {
        		renderBlocks.aoLightValueScratchXYZNNN = renderBlocks.aoLightValueScratchXYNN;
        		renderBlocks.aoBrightnessXYZNNN = renderBlocks.aoBrightnessXYNN;
        	} else {
        		renderBlocks.aoLightValueScratchXYZNNN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x - 1, y, z - 1);
        		renderBlocks.aoBrightnessXYZNNN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x - 1, y, z - 1);
        	}

        	if (!canBlockGrassYZNP && !canBlockGrassXYNN) {
        		renderBlocks.aoLightValueScratchXYZNNP = renderBlocks.aoLightValueScratchXYNN;
        		renderBlocks.aoBrightnessXYZNNP = renderBlocks.aoBrightnessXYNN;
        	} else {
        		renderBlocks.aoLightValueScratchXYZNNP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x - 1, y, z + 1);
        		renderBlocks.aoBrightnessXYZNNP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x - 1, y, z + 1);
        	}

        	if (!canBlockGrassYZNN && !canBlockGrassXYPN) {
        		renderBlocks.aoLightValueScratchXYZPNN = renderBlocks.aoLightValueScratchXYPN;
        		renderBlocks.aoBrightnessXYZPNN = renderBlocks.aoBrightnessXYPN;
        	} else {
        		renderBlocks.aoLightValueScratchXYZPNN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x + 1, y, z - 1);
        		renderBlocks.aoBrightnessXYZPNN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x + 1, y, z - 1);
        	}

        	if (!canBlockGrassYZNP && !canBlockGrassXYPN) {
        		renderBlocks.aoLightValueScratchXYZPNP = renderBlocks.aoLightValueScratchXYPN;
        		renderBlocks.aoBrightnessXYZPNP = renderBlocks.aoBrightnessXYPN;
        	} else {
        		renderBlocks.aoLightValueScratchXYZPNP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x + 1, y, z + 1);
        		renderBlocks.aoBrightnessXYZPNP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x + 1, y, z + 1);
        	}

        	if (offset == 0)
        		++y;

        	offsetBrightness = mixedBrightness;

        	if (!renderBlocks.blockAccess.isBlockOpaqueCube(x, y - 1, z))
        		offsetBrightness = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, offset == 0 ? (y - 1) : y, z);

        	aoLightValue = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, offset == 0 ? (y - 1) : y, z);

        	if (offset == 1) {
        		aoBottomTopLeftOffset = (renderBlocks.aoLightValueScratchXYZNNP + renderBlocks.aoLightValueScratchXYNN + renderBlocks.aoLightValueScratchYZNP + aoLightValue) / 4.0F;
        		aoBottomTopRightOffset = (renderBlocks.aoLightValueScratchYZNP + aoLightValue + renderBlocks.aoLightValueScratchXYZPNP + renderBlocks.aoLightValueScratchXYPN) / 4.0F;
        		aoBottomBottomRightOffset = (aoLightValue + renderBlocks.aoLightValueScratchYZNN + renderBlocks.aoLightValueScratchXYPN + renderBlocks.aoLightValueScratchXYZPNN) / 4.0F;
        		aoBottomBottomLeftOffset = (renderBlocks.aoLightValueScratchXYNN + renderBlocks.aoLightValueScratchXYZNNN + aoLightValue + renderBlocks.aoLightValueScratchYZNN) / 4.0F;
        		brightnessBottomTopLeftOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZNNP, renderBlocks.aoBrightnessXYNN, renderBlocks.aoBrightnessYZNP, offsetBrightness);
        		brightnessBottomTopRightOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZNP, renderBlocks.aoBrightnessXYZPNP, renderBlocks.aoBrightnessXYPN, offsetBrightness);
        		brightnessBottomBottomRightOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZNN, renderBlocks.aoBrightnessXYPN, renderBlocks.aoBrightnessXYZPNN, offsetBrightness);
        		brightnessBottomBottomLeftOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYNN, renderBlocks.aoBrightnessXYZNNN, renderBlocks.aoBrightnessYZNN, offsetBrightness);
        	} else {
        		aoBottomTopLeft = (renderBlocks.aoLightValueScratchXYZNNP + renderBlocks.aoLightValueScratchXYNN + renderBlocks.aoLightValueScratchYZNP + aoLightValue) / 4.0F;
        		aoBottomTopRight = (renderBlocks.aoLightValueScratchYZNP + aoLightValue + renderBlocks.aoLightValueScratchXYZPNP + renderBlocks.aoLightValueScratchXYPN) / 4.0F;
        		aoBottomBottomRight = (aoLightValue + renderBlocks.aoLightValueScratchYZNN + renderBlocks.aoLightValueScratchXYPN + renderBlocks.aoLightValueScratchXYZPNN) / 4.0F;
        		aoBottomBottomLeft = (renderBlocks.aoLightValueScratchXYNN + renderBlocks.aoLightValueScratchXYZNNN + aoLightValue + renderBlocks.aoLightValueScratchYZNN) / 4.0F;
        		brightnessBottomTopLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZNNP, renderBlocks.aoBrightnessXYNN, renderBlocks.aoBrightnessYZNP, offsetBrightness);
        		brightnessBottomTopRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZNP, renderBlocks.aoBrightnessXYZPNP, renderBlocks.aoBrightnessXYPN, offsetBrightness);
        		brightnessBottomBottomRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZNN, renderBlocks.aoBrightnessXYPN, renderBlocks.aoBrightnessXYZPNN, offsetBrightness);
        		brightnessBottomBottomLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYNN, renderBlocks.aoBrightnessXYZNNN, renderBlocks.aoBrightnessYZNN, offsetBrightness);
        	}
        }

        /*
         * Compute ambient occlusion and brightness for top face
         */
        for (int offset = 1; offset > -1; offset--)
        {
        	if (offset == 0)
        		++y;

        	renderBlocks.aoBrightnessXYNP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x - 1, y, z);
        	renderBlocks.aoBrightnessXYPP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x + 1, y, z);
        	renderBlocks.aoBrightnessYZPN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z - 1);
        	renderBlocks.aoBrightnessYZPP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z + 1);
        	renderBlocks.aoLightValueScratchXYNP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x - 1, y, z);
        	renderBlocks.aoLightValueScratchXYPP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x + 1, y, z);
        	renderBlocks.aoLightValueScratchYZPN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y, z - 1);
        	renderBlocks.aoLightValueScratchYZPP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y, z + 1);
        	canBlockGrassXYPN = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x + 1, y + 1, z)];
        	canBlockGrassXYNN = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x - 1, y + 1, z)];
        	canBlockGrassYZNP = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x, y + 1, z + 1)];
        	canBlockGrassYZNN = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x, y + 1, z - 1)];

        	if (!canBlockGrassYZNN && !canBlockGrassXYNN) {
        		renderBlocks.aoLightValueScratchXYZNPN = renderBlocks.aoLightValueScratchXYNP;
        		renderBlocks.aoBrightnessXYZNPN = renderBlocks.aoBrightnessXYNP;
        	} else {
        		renderBlocks.aoLightValueScratchXYZNPN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x - 1, y, z - 1);
        		renderBlocks.aoBrightnessXYZNPN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x - 1, y, z - 1);
        	}

        	if (!canBlockGrassYZNN && !canBlockGrassXYPN) {
        		renderBlocks.aoLightValueScratchXYZPPN = renderBlocks.aoLightValueScratchXYPP;
        		renderBlocks.aoBrightnessXYZPPN = renderBlocks.aoBrightnessXYPP;
        	} else {
        		renderBlocks.aoLightValueScratchXYZPPN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x + 1, y, z - 1);
        		renderBlocks.aoBrightnessXYZPPN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x + 1, y, z - 1);
        	}

        	if (!canBlockGrassYZNP && !canBlockGrassXYNN) {
        		renderBlocks.aoLightValueScratchXYZNPP = renderBlocks.aoLightValueScratchXYNP;
        		renderBlocks.aoBrightnessXYZNPP = renderBlocks.aoBrightnessXYNP;
        	} else {
        		renderBlocks.aoLightValueScratchXYZNPP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x - 1, y, z + 1);
        		renderBlocks.aoBrightnessXYZNPP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x - 1, y, z + 1);
        	}

        	if (!canBlockGrassYZNP && !canBlockGrassXYPN) {
        		renderBlocks.aoLightValueScratchXYZPPP = renderBlocks.aoLightValueScratchXYPP;
        		renderBlocks.aoBrightnessXYZPPP = renderBlocks.aoBrightnessXYPP;
        	} else {
        		renderBlocks.aoLightValueScratchXYZPPP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x + 1, y, z + 1);
        		renderBlocks.aoBrightnessXYZPPP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x + 1, y, z + 1);
        	}

        	if (offset == 0)
        		--y;

        	offsetBrightness = mixedBrightness;

        	if (!renderBlocks.blockAccess.isBlockOpaqueCube(x, y + 1, z))
        		offsetBrightness = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, offset == 0 ? (y + 1) : y, z);

        	aoLightValue = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, offset == 0 ? (y + 1) : y, z);

        	if (offset == 1) {
        		aoTopTopRightOffset = (renderBlocks.aoLightValueScratchXYZNPP + renderBlocks.aoLightValueScratchXYNP + renderBlocks.aoLightValueScratchYZPP + aoLightValue) / 4.0F;
        		aoTopTopLeftOffset = (renderBlocks.aoLightValueScratchYZPP + aoLightValue + renderBlocks.aoLightValueScratchXYZPPP + renderBlocks.aoLightValueScratchXYPP) / 4.0F;
        		aoTopBottomLeftOffset = (aoLightValue + renderBlocks.aoLightValueScratchYZPN + renderBlocks.aoLightValueScratchXYPP + renderBlocks.aoLightValueScratchXYZPPN) / 4.0F;
        		aoTopBottomRightOffset = (renderBlocks.aoLightValueScratchXYNP + renderBlocks.aoLightValueScratchXYZNPN + aoLightValue + renderBlocks.aoLightValueScratchYZPN) / 4.0F;
        		brightnessTopTopRightOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZNPP, renderBlocks.aoBrightnessXYNP, renderBlocks.aoBrightnessYZPP, offsetBrightness);
        		brightnessTopTopLeftOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZPP, renderBlocks.aoBrightnessXYZPPP, renderBlocks.aoBrightnessXYPP, offsetBrightness);
        		brightnessTopBottomLeftOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZPN, renderBlocks.aoBrightnessXYPP, renderBlocks.aoBrightnessXYZPPN, offsetBrightness);
        		brightnessTopBottomRightOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYNP, renderBlocks.aoBrightnessXYZNPN, renderBlocks.aoBrightnessYZPN, offsetBrightness);
        	} else {
        		aoTopTopRight = (renderBlocks.aoLightValueScratchXYZNPP + renderBlocks.aoLightValueScratchXYNP + renderBlocks.aoLightValueScratchYZPP + aoLightValue) / 4.0F;
        		aoTopTopLeft = (renderBlocks.aoLightValueScratchYZPP + aoLightValue + renderBlocks.aoLightValueScratchXYZPPP + renderBlocks.aoLightValueScratchXYPP) / 4.0F;
        		aoTopBottomLeft = (aoLightValue + renderBlocks.aoLightValueScratchYZPN + renderBlocks.aoLightValueScratchXYPP + renderBlocks.aoLightValueScratchXYZPPN) / 4.0F;
        		aoTopBottomRight = (renderBlocks.aoLightValueScratchXYNP + renderBlocks.aoLightValueScratchXYZNPN + aoLightValue + renderBlocks.aoLightValueScratchYZPN) / 4.0F;
        		brightnessTopTopRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZNPP, renderBlocks.aoBrightnessXYNP, renderBlocks.aoBrightnessYZPP, offsetBrightness);
        		brightnessTopTopLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZPP, renderBlocks.aoBrightnessXYZPPP, renderBlocks.aoBrightnessXYPP, offsetBrightness);
        		brightnessTopBottomLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZPN, renderBlocks.aoBrightnessXYPP, renderBlocks.aoBrightnessXYZPPN, offsetBrightness);
        		brightnessTopBottomRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYNP, renderBlocks.aoBrightnessXYZNPN, renderBlocks.aoBrightnessYZPN, offsetBrightness);
        	}
        }

        /*
         * Compute ambient occlusion and brightness for east face
         */
        for (int offset = 1; offset > -1; offset--)
        {
        	if (offset == 0)
        		--z;

        	renderBlocks.aoLightValueScratchXZNN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x - 1, y, z);
        	renderBlocks.aoLightValueScratchYZNN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y - 1, z);
        	renderBlocks.aoLightValueScratchYZPN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y + 1, z);
        	renderBlocks.aoLightValueScratchXZPN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x + 1, y, z);
        	renderBlocks.aoBrightnessXZNN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x - 1, y, z);
        	renderBlocks.aoBrightnessYZNN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y - 1, z);
        	renderBlocks.aoBrightnessYZPN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y + 1, z);
        	renderBlocks.aoBrightnessXZPN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x + 1, y, z);
        	canBlockGrassXYPN = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x + 1, y, z - 1)];
        	canBlockGrassXYNN = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x - 1, y, z - 1)];
        	canBlockGrassYZNP = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x, y + 1, z - 1)];
        	canBlockGrassYZNN = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x, y - 1, z - 1)];

        	if (!canBlockGrassXYNN && !canBlockGrassYZNN) {
        		renderBlocks.aoLightValueScratchXYZNNN = renderBlocks.aoLightValueScratchXZNN;
        		renderBlocks.aoBrightnessXYZNNN = renderBlocks.aoBrightnessXZNN;
        	} else {
        		renderBlocks.aoLightValueScratchXYZNNN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x - 1, y - 1, z);
        		renderBlocks.aoBrightnessXYZNNN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x - 1, y - 1, z);
        	}

        	if (!canBlockGrassXYNN && !canBlockGrassYZNP) {
        		renderBlocks.aoLightValueScratchXYZNPN = renderBlocks.aoLightValueScratchXZNN;
        		renderBlocks.aoBrightnessXYZNPN = renderBlocks.aoBrightnessXZNN;
        	} else {
        		renderBlocks.aoLightValueScratchXYZNPN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x - 1, y + 1, z);
        		renderBlocks.aoBrightnessXYZNPN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x - 1, y + 1, z);
        	}

        	if (!canBlockGrassXYPN && !canBlockGrassYZNN) {
        		renderBlocks.aoLightValueScratchXYZPNN = renderBlocks.aoLightValueScratchXZPN;
        		renderBlocks.aoBrightnessXYZPNN = renderBlocks.aoBrightnessXZPN;
        	} else {
        		renderBlocks.aoLightValueScratchXYZPNN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x + 1, y - 1, z);
        		renderBlocks.aoBrightnessXYZPNN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x + 1, y - 1, z);
        	}

        	if (!canBlockGrassXYPN && !canBlockGrassYZNP) {
        		renderBlocks.aoLightValueScratchXYZPPN = renderBlocks.aoLightValueScratchXZPN;
        		renderBlocks.aoBrightnessXYZPPN = renderBlocks.aoBrightnessXZPN;
        	} else {
        		renderBlocks.aoLightValueScratchXYZPPN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x + 1, y + 1, z);
        		renderBlocks.aoBrightnessXYZPPN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x + 1, y + 1, z);
        	}

        	if (offset == 0)
        		++z;

        	offsetBrightness = mixedBrightness;

        	if (!renderBlocks.blockAccess.isBlockOpaqueCube(x, y, z - 1))
        		offsetBrightness = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, offset == 0 ? (z - 1) : z);

        	aoLightValue = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y, offset == 0 ? (z - 1) : z);

        	if (offset == 1) {
        		aoEastTopLeftOffset = (renderBlocks.aoLightValueScratchXZNN + renderBlocks.aoLightValueScratchXYZNPN + aoLightValue + renderBlocks.aoLightValueScratchYZPN) / 4.0F;
        		aoEastBottomLeftOffset = (aoLightValue + renderBlocks.aoLightValueScratchYZPN + renderBlocks.aoLightValueScratchXZPN + renderBlocks.aoLightValueScratchXYZPPN) / 4.0F;
        		aoEastBottomRightOffset = (renderBlocks.aoLightValueScratchYZNN + aoLightValue + renderBlocks.aoLightValueScratchXYZPNN + renderBlocks.aoLightValueScratchXZPN) / 4.0F;
        		aoEastTopRightOffset = (renderBlocks.aoLightValueScratchXYZNNN + renderBlocks.aoLightValueScratchXZNN + renderBlocks.aoLightValueScratchYZNN + aoLightValue) / 4.0F;
        		brightnessEastTopLeftOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZNN, renderBlocks.aoBrightnessXYZNPN, renderBlocks.aoBrightnessYZPN, offsetBrightness);
        		brightnessEastBottomLeftOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZPN, renderBlocks.aoBrightnessXZPN, renderBlocks.aoBrightnessXYZPPN, offsetBrightness);
        		brightnessEastBottomRightOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZNN, renderBlocks.aoBrightnessXYZPNN, renderBlocks.aoBrightnessXZPN, offsetBrightness);
        		brightnessEastTopRightOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZNNN, renderBlocks.aoBrightnessXZNN, renderBlocks.aoBrightnessYZNN, offsetBrightness);
        	} else {
        		aoEastTopLeft = (renderBlocks.aoLightValueScratchXZNN + renderBlocks.aoLightValueScratchXYZNPN + aoLightValue + renderBlocks.aoLightValueScratchYZPN) / 4.0F;
        		aoEastBottomLeft = (aoLightValue + renderBlocks.aoLightValueScratchYZPN + renderBlocks.aoLightValueScratchXZPN + renderBlocks.aoLightValueScratchXYZPPN) / 4.0F;
        		aoEastBottomRight = (renderBlocks.aoLightValueScratchYZNN + aoLightValue + renderBlocks.aoLightValueScratchXYZPNN + renderBlocks.aoLightValueScratchXZPN) / 4.0F;
        		aoEastTopRight = (renderBlocks.aoLightValueScratchXYZNNN + renderBlocks.aoLightValueScratchXZNN + renderBlocks.aoLightValueScratchYZNN + aoLightValue) / 4.0F;
        		brightnessEastTopLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZNN, renderBlocks.aoBrightnessXYZNPN, renderBlocks.aoBrightnessYZPN, offsetBrightness);
        		brightnessEastBottomLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZPN, renderBlocks.aoBrightnessXZPN, renderBlocks.aoBrightnessXYZPPN, offsetBrightness);
        		brightnessEastBottomRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZNN, renderBlocks.aoBrightnessXYZPNN, renderBlocks.aoBrightnessXZPN, offsetBrightness);
        		brightnessEastTopRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZNNN, renderBlocks.aoBrightnessXZNN, renderBlocks.aoBrightnessYZNN, offsetBrightness);
        	}
        }

        /*
         * Compute ambient occlusion and brightness for west face
         */
        for (int offset = 1; offset > -1; offset--)
        {
        	if (offset == 0)
        		++z;

        	renderBlocks.aoLightValueScratchXZNP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x - 1, y, z);
        	renderBlocks.aoLightValueScratchXZPP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x + 1, y, z);
        	renderBlocks.aoLightValueScratchYZNP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y - 1, z);
        	renderBlocks.aoLightValueScratchYZPP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y + 1, z);
        	renderBlocks.aoBrightnessXZNP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x - 1, y, z);
        	renderBlocks.aoBrightnessXZPP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x + 1, y, z);
        	renderBlocks.aoBrightnessYZNP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y - 1, z);
        	renderBlocks.aoBrightnessYZPP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y + 1, z);
        	canBlockGrassXYPN = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x + 1, y, z + 1)];
        	canBlockGrassXYNN = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x - 1, y, z + 1)];
        	canBlockGrassYZNP = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x, y + 1, z + 1)];
        	canBlockGrassYZNN = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x, y - 1, z + 1)];

        	if (!canBlockGrassXYNN && !canBlockGrassYZNN) {
        		renderBlocks.aoLightValueScratchXYZNNP = renderBlocks.aoLightValueScratchXZNP;
        		renderBlocks.aoBrightnessXYZNNP = renderBlocks.aoBrightnessXZNP;
        	} else {
        		renderBlocks.aoLightValueScratchXYZNNP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x - 1, y - 1, z);
        		renderBlocks.aoBrightnessXYZNNP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x - 1, y - 1, z);
        	}

        	if (!canBlockGrassXYNN && !canBlockGrassYZNP) {
        		renderBlocks.aoLightValueScratchXYZNPP = renderBlocks.aoLightValueScratchXZNP;
        		renderBlocks.aoBrightnessXYZNPP = renderBlocks.aoBrightnessXZNP;
        	} else {
        		renderBlocks.aoLightValueScratchXYZNPP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x - 1, y + 1, z);
        		renderBlocks.aoBrightnessXYZNPP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x - 1, y + 1, z);
        	}

        	if (!canBlockGrassXYPN && !canBlockGrassYZNN) {
        		renderBlocks.aoLightValueScratchXYZPNP = renderBlocks.aoLightValueScratchXZPP;
        		renderBlocks.aoBrightnessXYZPNP = renderBlocks.aoBrightnessXZPP;
        	} else {
        		renderBlocks.aoLightValueScratchXYZPNP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x + 1, y - 1, z);
        		renderBlocks.aoBrightnessXYZPNP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x + 1, y - 1, z);
        	}

        	if (!canBlockGrassXYPN && !canBlockGrassYZNP) {
        		renderBlocks.aoLightValueScratchXYZPPP = renderBlocks.aoLightValueScratchXZPP;
        		renderBlocks.aoBrightnessXYZPPP = renderBlocks.aoBrightnessXZPP;
        	} else {
        		renderBlocks.aoLightValueScratchXYZPPP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x + 1, y + 1, z);
        		renderBlocks.aoBrightnessXYZPPP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x + 1, y + 1, z);
        	}

        	if (offset == 0)
        		--z;

        	offsetBrightness = mixedBrightness;

        	if (!renderBlocks.blockAccess.isBlockOpaqueCube(x, y, z + 1))
        		offsetBrightness = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, offset == 0 ? (z + 1) : z);

        	aoLightValue = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y, offset == 0 ? (z + 1) : z);

        	if (offset == 1) {
        		aoWestTopLeftOffset = (renderBlocks.aoLightValueScratchXZNP + renderBlocks.aoLightValueScratchXYZNPP + aoLightValue + renderBlocks.aoLightValueScratchYZPP) / 4.0F;
        		aoWestTopRightOffset = (aoLightValue + renderBlocks.aoLightValueScratchYZPP + renderBlocks.aoLightValueScratchXZPP + renderBlocks.aoLightValueScratchXYZPPP) / 4.0F;
        		aoWestBottomRightOffset = (renderBlocks.aoLightValueScratchYZNP + aoLightValue + renderBlocks.aoLightValueScratchXYZPNP + renderBlocks.aoLightValueScratchXZPP) / 4.0F;
        		aoWestBottomLeftOffset = (renderBlocks.aoLightValueScratchXYZNNP + renderBlocks.aoLightValueScratchXZNP + renderBlocks.aoLightValueScratchYZNP + aoLightValue) / 4.0F;
        		brightnessWestTopLeftOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZNP, renderBlocks.aoBrightnessXYZNPP, renderBlocks.aoBrightnessYZPP, offsetBrightness);
        		brightnessWestTopRightOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZPP, renderBlocks.aoBrightnessXZPP, renderBlocks.aoBrightnessXYZPPP, offsetBrightness);
        		brightnessWestBottomRightOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZNP, renderBlocks.aoBrightnessXYZPNP, renderBlocks.aoBrightnessXZPP, offsetBrightness);
        		brightnessWestBottomLeftOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZNNP, renderBlocks.aoBrightnessXZNP, renderBlocks.aoBrightnessYZNP, offsetBrightness);
        	} else {
        		aoWestTopLeft = (renderBlocks.aoLightValueScratchXZNP + renderBlocks.aoLightValueScratchXYZNPP + aoLightValue + renderBlocks.aoLightValueScratchYZPP) / 4.0F;
        		aoWestTopRight = (aoLightValue + renderBlocks.aoLightValueScratchYZPP + renderBlocks.aoLightValueScratchXZPP + renderBlocks.aoLightValueScratchXYZPPP) / 4.0F;
        		aoWestBottomRight = (renderBlocks.aoLightValueScratchYZNP + aoLightValue + renderBlocks.aoLightValueScratchXYZPNP + renderBlocks.aoLightValueScratchXZPP) / 4.0F;
        		aoWestBottomLeft = (renderBlocks.aoLightValueScratchXYZNNP + renderBlocks.aoLightValueScratchXZNP + renderBlocks.aoLightValueScratchYZNP + aoLightValue) / 4.0F;
        		brightnessWestTopLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZNP, renderBlocks.aoBrightnessXYZNPP, renderBlocks.aoBrightnessYZPP, offsetBrightness);
        		brightnessWestTopRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZPP, renderBlocks.aoBrightnessXZPP, renderBlocks.aoBrightnessXYZPPP, offsetBrightness);
        		brightnessWestBottomRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessYZNP, renderBlocks.aoBrightnessXYZPNP, renderBlocks.aoBrightnessXZPP, offsetBrightness);
        		brightnessWestBottomLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZNNP, renderBlocks.aoBrightnessXZNP, renderBlocks.aoBrightnessYZNP, offsetBrightness);
        	}
        }

        /*
         * Compute ambient occlusion and brightness for north face
         */
        for (int offset = 1; offset > -1; offset--)
        {
        	if (offset == 0)
        		--x;

        	renderBlocks.aoLightValueScratchXYNN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y - 1, z);
        	renderBlocks.aoLightValueScratchXZNN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y, z - 1);
        	renderBlocks.aoLightValueScratchXZNP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y, z + 1);
        	renderBlocks.aoLightValueScratchXYNP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y + 1, z);
        	renderBlocks.aoBrightnessXYNN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y - 1, z);
        	renderBlocks.aoBrightnessXZNN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z - 1);
        	renderBlocks.aoBrightnessXZNP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z + 1);
        	renderBlocks.aoBrightnessXYNP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y + 1, z);
        	canBlockGrassXYPN = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x - 1, y + 1, z)];
        	canBlockGrassXYNN = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x - 1, y - 1, z)];
        	canBlockGrassYZNP = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x - 1, y, z - 1)];
        	canBlockGrassYZNN = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x - 1, y, z + 1)];

        	if (!canBlockGrassYZNP && !canBlockGrassXYNN) {
        		renderBlocks.aoLightValueScratchXYZNNN = renderBlocks.aoLightValueScratchXZNN;
        		renderBlocks.aoBrightnessXYZNNN = renderBlocks.aoBrightnessXZNN;
        	} else {
        		renderBlocks.aoLightValueScratchXYZNNN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y - 1, z - 1);
        		renderBlocks.aoBrightnessXYZNNN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y - 1, z - 1);
        	}

        	if (!canBlockGrassYZNN && !canBlockGrassXYNN) {
        		renderBlocks.aoLightValueScratchXYZNNP = renderBlocks.aoLightValueScratchXZNP;
        		renderBlocks.aoBrightnessXYZNNP = renderBlocks.aoBrightnessXZNP;
        	} else {
        		renderBlocks.aoLightValueScratchXYZNNP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y - 1, z + 1);
        		renderBlocks.aoBrightnessXYZNNP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y - 1, z + 1);
        	}

        	if (!canBlockGrassYZNP && !canBlockGrassXYPN) {
        		renderBlocks.aoLightValueScratchXYZNPN = renderBlocks.aoLightValueScratchXZNN;
        		renderBlocks.aoBrightnessXYZNPN = renderBlocks.aoBrightnessXZNN;
        	} else {
        		renderBlocks.aoLightValueScratchXYZNPN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y + 1, z - 1);
        		renderBlocks.aoBrightnessXYZNPN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y + 1, z - 1);
        	}

        	if (!canBlockGrassYZNN && !canBlockGrassXYPN) {
        		renderBlocks.aoLightValueScratchXYZNPP = renderBlocks.aoLightValueScratchXZNP;
        		renderBlocks.aoBrightnessXYZNPP = renderBlocks.aoBrightnessXZNP;
        	} else {
        		renderBlocks.aoLightValueScratchXYZNPP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y + 1, z + 1);
        		renderBlocks.aoBrightnessXYZNPP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y + 1, z + 1);
        	}

        	if (offset == 0)
        		++x;

        	offsetBrightness = mixedBrightness;

        	if (!renderBlocks.blockAccess.isBlockOpaqueCube(x - 1, y, z))
        		offsetBrightness = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, offset == 0 ? (x - 1) : x, y, z);

        	aoLightValue = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, offset == 0 ? (x - 1) : x, y, z);

        	if (offset == 1) {
        		aoNorthTopRightOffset = (renderBlocks.aoLightValueScratchXYNN + renderBlocks.aoLightValueScratchXYZNNP + aoLightValue + renderBlocks.aoLightValueScratchXZNP) / 4.0F;
        		aoNorthTopLeftOffset = (aoLightValue + renderBlocks.aoLightValueScratchXZNP + renderBlocks.aoLightValueScratchXYNP + renderBlocks.aoLightValueScratchXYZNPP) / 4.0F;
        		aoNorthBottomLeftOffset = (renderBlocks.aoLightValueScratchXZNN + aoLightValue + renderBlocks.aoLightValueScratchXYZNPN + renderBlocks.aoLightValueScratchXYNP) / 4.0F;
        		aoNorthBottomRightOffset = (renderBlocks.aoLightValueScratchXYZNNN + renderBlocks.aoLightValueScratchXYNN + renderBlocks.aoLightValueScratchXZNN + aoLightValue) / 4.0F;
        		brightnessNorthTopRightOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYNN, renderBlocks.aoBrightnessXYZNNP, renderBlocks.aoBrightnessXZNP, offsetBrightness);
        		brightnessNorthTopLeftOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZNP, renderBlocks.aoBrightnessXYNP, renderBlocks.aoBrightnessXYZNPP, offsetBrightness);
        		brightnessNorthBottomLeftOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZNN, renderBlocks.aoBrightnessXYZNPN, renderBlocks.aoBrightnessXYNP, offsetBrightness);
        		brightnessNorthBottomRightOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZNNN, renderBlocks.aoBrightnessXYNN, renderBlocks.aoBrightnessXZNN, offsetBrightness);
        	} else {
        		aoNorthTopRight = (renderBlocks.aoLightValueScratchXYNN + renderBlocks.aoLightValueScratchXYZNNP + aoLightValue + renderBlocks.aoLightValueScratchXZNP) / 4.0F;
        		aoNorthTopLeft = (aoLightValue + renderBlocks.aoLightValueScratchXZNP + renderBlocks.aoLightValueScratchXYNP + renderBlocks.aoLightValueScratchXYZNPP) / 4.0F;
        		aoNorthBottomLeft = (renderBlocks.aoLightValueScratchXZNN + aoLightValue + renderBlocks.aoLightValueScratchXYZNPN + renderBlocks.aoLightValueScratchXYNP) / 4.0F;
        		aoNorthBottomRight = (renderBlocks.aoLightValueScratchXYZNNN + renderBlocks.aoLightValueScratchXYNN + renderBlocks.aoLightValueScratchXZNN + aoLightValue) / 4.0F;
        		brightnessNorthTopRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYNN, renderBlocks.aoBrightnessXYZNNP, renderBlocks.aoBrightnessXZNP, offsetBrightness);
        		brightnessNorthTopLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZNP, renderBlocks.aoBrightnessXYNP, renderBlocks.aoBrightnessXYZNPP, offsetBrightness);
        		brightnessNorthBottomLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZNN, renderBlocks.aoBrightnessXYZNPN, renderBlocks.aoBrightnessXYNP, offsetBrightness);
        		brightnessNorthBottomRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZNNN, renderBlocks.aoBrightnessXYNN, renderBlocks.aoBrightnessXZNN, offsetBrightness);
        	}
        }

        /*
         * Compute ambient occlusion and brightness for south face
         */
        for (int offset = 1; offset > -1; offset--)
        {
        	if (offset == 0)
        		++x;

        	renderBlocks.aoLightValueScratchXYPN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y - 1, z);
        	renderBlocks.aoLightValueScratchXZPN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y, z - 1);
        	renderBlocks.aoLightValueScratchXZPP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y, z + 1);
        	renderBlocks.aoLightValueScratchXYPP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y + 1, z);
        	renderBlocks.aoBrightnessXYPN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y - 1, z);
        	renderBlocks.aoBrightnessXZPN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z - 1);
        	renderBlocks.aoBrightnessXZPP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z + 1);
        	renderBlocks.aoBrightnessXYPP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y + 1, z);
        	canBlockGrassXYPN = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x + 1, y + 1, z)];
        	canBlockGrassXYNN = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x + 1, y - 1, z)];
        	canBlockGrassYZNP = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x + 1, y, z + 1)];
        	canBlockGrassYZNN = Block.canBlockGrass[renderBlocks.blockAccess.getBlockId(x + 1, y, z - 1)];

        	if (!canBlockGrassXYNN && !canBlockGrassYZNN) {
        		renderBlocks.aoLightValueScratchXYZPNN = renderBlocks.aoLightValueScratchXZPN;
        		renderBlocks.aoBrightnessXYZPNN = renderBlocks.aoBrightnessXZPN;
        	} else {
        		renderBlocks.aoLightValueScratchXYZPNN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y - 1, z - 1);
        		renderBlocks.aoBrightnessXYZPNN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y - 1, z - 1);
        	}

        	if (!canBlockGrassXYNN && !canBlockGrassYZNP) {
        		renderBlocks.aoLightValueScratchXYZPNP = renderBlocks.aoLightValueScratchXZPP;
        		renderBlocks.aoBrightnessXYZPNP = renderBlocks.aoBrightnessXZPP;
        	} else {
        		renderBlocks.aoLightValueScratchXYZPNP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y - 1, z + 1);
        		renderBlocks.aoBrightnessXYZPNP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y - 1, z + 1);
        	}

        	if (!canBlockGrassXYPN && !canBlockGrassYZNN) {
        		renderBlocks.aoLightValueScratchXYZPPN = renderBlocks.aoLightValueScratchXZPN;
        		renderBlocks.aoBrightnessXYZPPN = renderBlocks.aoBrightnessXZPN;
        	} else {
        		renderBlocks.aoLightValueScratchXYZPPN = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y + 1, z - 1);
        		renderBlocks.aoBrightnessXYZPPN = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y + 1, z - 1);
        	}

        	if (!canBlockGrassXYPN && !canBlockGrassYZNP) {
        		renderBlocks.aoLightValueScratchXYZPPP = renderBlocks.aoLightValueScratchXZPP;
        		renderBlocks.aoBrightnessXYZPPP = renderBlocks.aoBrightnessXZPP;
        	} else {
        		renderBlocks.aoLightValueScratchXYZPPP = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, x, y + 1, z + 1);
        		renderBlocks.aoBrightnessXYZPPP = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y + 1, z + 1);
        	}

        	if (offset == 0)
        		--x;

        	offsetBrightness = mixedBrightness;

        	if (!renderBlocks.blockAccess.isBlockOpaqueCube(x + 1, y, z))
        		offsetBrightness = coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, offset == 0 ? (x + 1) : x, y, z);

        	aoLightValue = coverBlock.getAmbientOcclusionLightValue(renderBlocks.blockAccess, offset == 0 ? (x + 1) : x, y, z);

        	if (offset == 1) {
        		aoSouthTopLeftOffset = (renderBlocks.aoLightValueScratchXYPN + renderBlocks.aoLightValueScratchXYZPNP + aoLightValue + renderBlocks.aoLightValueScratchXZPP) / 4.0F;
        		aoSouthBottomLeftOffset = (renderBlocks.aoLightValueScratchXYZPNN + renderBlocks.aoLightValueScratchXYPN + renderBlocks.aoLightValueScratchXZPN + aoLightValue) / 4.0F;
        		aoSouthBottomRightOffset = (renderBlocks.aoLightValueScratchXZPN + aoLightValue + renderBlocks.aoLightValueScratchXYZPPN + renderBlocks.aoLightValueScratchXYPP) / 4.0F;
        		aoSouthTopRightOffset = (aoLightValue + renderBlocks.aoLightValueScratchXZPP + renderBlocks.aoLightValueScratchXYPP + renderBlocks.aoLightValueScratchXYZPPP) / 4.0F;
        		brightnessSouthTopLeftOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYPN, renderBlocks.aoBrightnessXYZPNP, renderBlocks.aoBrightnessXZPP, offsetBrightness);
        		brightnessSouthTopRightOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZPP, renderBlocks.aoBrightnessXYPP, renderBlocks.aoBrightnessXYZPPP, offsetBrightness);
        		brightnessSouthBottomRightOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZPN, renderBlocks.aoBrightnessXYZPPN, renderBlocks.aoBrightnessXYPP, offsetBrightness);
        		brightnessSouthBottomLeftOffset = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZPNN, renderBlocks.aoBrightnessXYPN, renderBlocks.aoBrightnessXZPN, offsetBrightness);
        	} else {
        		aoSouthTopLeft = (renderBlocks.aoLightValueScratchXYPN + renderBlocks.aoLightValueScratchXYZPNP + aoLightValue + renderBlocks.aoLightValueScratchXZPP) / 4.0F;
        		aoSouthBottomLeft = (renderBlocks.aoLightValueScratchXYZPNN + renderBlocks.aoLightValueScratchXYPN + renderBlocks.aoLightValueScratchXZPN + aoLightValue) / 4.0F;
        		aoSouthBottomRight = (renderBlocks.aoLightValueScratchXZPN + aoLightValue + renderBlocks.aoLightValueScratchXYZPPN + renderBlocks.aoLightValueScratchXYPP) / 4.0F;
        		aoSouthTopRight = (aoLightValue + renderBlocks.aoLightValueScratchXZPP + renderBlocks.aoLightValueScratchXYPP + renderBlocks.aoLightValueScratchXYZPPP) / 4.0F;
        		brightnessSouthTopLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYPN, renderBlocks.aoBrightnessXYZPNP, renderBlocks.aoBrightnessXZPP, offsetBrightness);
        		brightnessSouthTopRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZPP, renderBlocks.aoBrightnessXYPP, renderBlocks.aoBrightnessXYZPPP, offsetBrightness);
        		brightnessSouthBottomRight = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXZPN, renderBlocks.aoBrightnessXYZPPN, renderBlocks.aoBrightnessXYPP, offsetBrightness);
        		brightnessSouthBottomLeft = renderBlocks.getAoBrightness(renderBlocks.aoBrightnessXYZPNN, renderBlocks.aoBrightnessXYPN, renderBlocks.aoBrightnessXZPN, offsetBrightness);
        	}
        }
        
        /*
         * Draw faces
         */

        // Draw bottom face
        if (srcBlock.shouldSideBeRendered(renderBlocks.blockAccess, x, y - 1, z, 0) && Slope.shouldRenderBottom(data, coverBlock, renderBlocks.blockAccess, x, y - 1, z))
        {
        	// Set AO for Southwest corner in game
        	ao_no_color[0] = aoBottomTopLeft;
        	renderBlocks.brightnessTopLeft = brightnessBottomTopLeft;

        	// Set AO for Northwest corner in game
        	ao_no_color[3] = aoBottomBottomLeft;
        	renderBlocks.brightnessBottomLeft = brightnessBottomBottomLeft;

        	// Set AO for Northeast corner in game
        	ao_no_color[2] = aoBottomBottomRight;
        	renderBlocks.brightnessBottomRight = brightnessBottomBottomRight;

        	// Set AO for Southeast corner in game
        	ao_no_color[1] = aoBottomTopRight;
        	renderBlocks.brightnessTopRight = brightnessBottomTopRight;

        	if (Slope.isObliqueCorner(data))
        		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 0, 1, false, x, y, z, lightnessBottom);
        	else
        		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 0, 9, false, x, y, z, lightnessBottom);
        }
        
        // Draw negative slope faces for normal slopes and corners formed using regular slopes
        if (Slope.isNegative(data) && !Slope.isObliqueCorner(data) && !Slope.isPyramid(data))
        {
        	// If corner, deconstruct to primary slope
        	int primarySlope = data;
        	if (Slope.isNormalCorner(data)) {
        		if (Slope.isSlopingWest(data))
        			primarySlope = Slope.NEG_WEST;
        		else if (Slope.isSlopingEast(data))
        			primarySlope = Slope.NEG_EAST;
        		else if (Slope.isSlopingSouth(data))
        			primarySlope = Slope.NEG_SOUTH;
        		else
        			primarySlope = Slope.NEG_NORTH;
        	}
        	
        	// Set AO for Southwest corner in game
        	if (Slope.isSlopingSouth(primarySlope)) {
        		ao_no_color[0] = aoBottomTopLeft == 1.0F ? 1.0F : aoSouthTopLeftOffset;
        		renderBlocks.brightnessTopLeft = brightnessSouthTopLeftOffset;
        	} else if (Slope.isSlopingNorth(primarySlope)) {
        		ao_no_color[0] = aoBottomTopLeftOffset;
        		renderBlocks.brightnessTopLeft = brightnessBottomTopLeftOffset;
        	} else if (Slope.isSlopingWest(primarySlope)) {
        		ao_no_color[0] = aoBottomTopLeftOffset;
        		renderBlocks.brightnessTopLeft = brightnessBottomTopLeftOffset;
        	} else { // Slope.isSlopingEast(primarySlope))
        		ao_no_color[0] = aoBottomTopLeft == 1.0F ? 1.0F : aoEastTopRightOffset;
        		renderBlocks.brightnessTopLeft = brightnessEastTopRightOffset;
        	}

        	// Set AO for Northwest corner in game
        	if (Slope.isSlopingSouth(primarySlope)) {
        		ao_no_color[3] = aoBottomBottomLeft == 1.0F ? 1.0F : aoSouthBottomLeftOffset;
        		renderBlocks.brightnessBottomLeft = brightnessSouthBottomLeftOffset;
        	} else if (Slope.isSlopingNorth(primarySlope)) {
        		ao_no_color[3] = aoBottomBottomLeftOffset;
        		renderBlocks.brightnessBottomLeft = brightnessBottomBottomLeftOffset;
        	} else if (Slope.isSlopingWest(primarySlope)) {
        		ao_no_color[3] = aoBottomBottomLeft == 1.0F ? 1.0F : aoWestBottomLeftOffset;
        		renderBlocks.brightnessBottomLeft = brightnessWestBottomLeftOffset;
        	} else { // Slope.isSlopingEast(primarySlope))
        		ao_no_color[3] = aoBottomBottomLeftOffset;
        		renderBlocks.brightnessBottomLeft = brightnessBottomBottomLeftOffset;
        	}

        	// Set AO for Northeast corner in game
        	if (Slope.isSlopingSouth(primarySlope)) {
        		ao_no_color[2] = aoBottomBottomRightOffset;
        		renderBlocks.brightnessBottomRight = brightnessBottomBottomRightOffset;
        	} else if (Slope.isSlopingNorth(primarySlope)) {
        		ao_no_color[2] = aoBottomBottomRight == 1.0F ? 1.0F : aoNorthBottomRightOffset;
        		renderBlocks.brightnessBottomRight = brightnessNorthBottomRightOffset;
        	} else if (Slope.isSlopingWest(primarySlope)) {
        		ao_no_color[2] = aoBottomBottomRight == 1.0F ? 1.0F : aoWestBottomRightOffset;
        		renderBlocks.brightnessBottomRight = brightnessWestBottomRightOffset;
        	} else { // Slope.isSlopingEast(primarySlope))
        		ao_no_color[2] = aoBottomBottomRightOffset;
        		renderBlocks.brightnessBottomRight = brightnessBottomBottomRightOffset;
        	}

        	// Set AO for Southeast corner in game
        	if (Slope.isSlopingSouth(primarySlope)) {
        		ao_no_color[1] = aoBottomTopRightOffset;
        		renderBlocks.brightnessTopRight = brightnessBottomTopRightOffset;
        	} else if (Slope.isSlopingNorth(primarySlope)) {
        		ao_no_color[1] = aoBottomTopRight == 1.0F ? 1.0F : aoNorthTopRightOffset;
        		renderBlocks.brightnessTopRight = brightnessNorthTopRightOffset;
        	} else if (Slope.isSlopingWest(primarySlope)) {
        		ao_no_color[1] = aoBottomTopRightOffset;
        		renderBlocks.brightnessTopRight = brightnessBottomTopRightOffset;
        	} else { // Slope.isSlopingEast(primarySlope))
        		ao_no_color[1] = aoBottomTopRight == 1.0F ? 1.0F : aoEastBottomRightOffset;
        		renderBlocks.brightnessTopRight = brightnessEastBottomRightOffset;
        	}

        	float lightnessValue;
        	if (Slope.isSlopingEast(primarySlope) || Slope.isSlopingWest(primarySlope)) {
        		lightnessValue = lightnessBottomEastWest;
        	} else {
        		lightnessValue = lightnessBottomNorthSouth;
        	}

			if (Slope.isNormalCorner(data)) {
				prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 0, 15, true, x, y, z, lightnessValue);
			} else { // Is a normal slope
				prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 0, 9, true, x, y, z, lightnessValue);
			}
        	
        	// Draw second face if slope is an ordinary corner
        	if (Slope.isNormalCorner(data)) {

        		// Deconstruct to secondary slope by checking slope orientation in reverse order
        		int secondarySlope = data;
        		if (Slope.isSlopingNorth(data))
        			secondarySlope = Slope.NEG_NORTH;
        		else if (Slope.isSlopingSouth(data))
        			secondarySlope = Slope.NEG_SOUTH;
        		else if (Slope.isSlopingEast(data))
        			secondarySlope = Slope.NEG_EAST;
        		else
        			secondarySlope = Slope.NEG_WEST;

            	// Set AO for Southwest corner in game
        		if (Slope.isSlopingSouth(secondarySlope)) {
        			ao_no_color[0] = aoBottomTopLeft == 1.0F ? 1.0F : aoSouthTopLeftOffset;
        			renderBlocks.brightnessTopLeft = brightnessSouthTopLeftOffset;
        		} else if (Slope.isSlopingNorth(secondarySlope)) {
        			ao_no_color[0] = aoBottomTopLeftOffset;
        			renderBlocks.brightnessTopLeft = brightnessBottomTopLeftOffset;
        		} else if (Slope.isSlopingWest(secondarySlope)) {
        			ao_no_color[0] = aoBottomTopLeftOffset;
        			renderBlocks.brightnessTopLeft = brightnessBottomTopLeftOffset;
        		} else { // Slope.isSlopingEast(secondarySlope)
        			ao_no_color[0] = aoBottomTopLeft == 1.0F ? 1.0F : aoEastTopRightOffset;
        			renderBlocks.brightnessTopLeft = brightnessEastTopRightOffset;
        		}

            	// Set AO for Northwest corner in game
        		if (Slope.isSlopingSouth(secondarySlope)) {
        			ao_no_color[3] = aoBottomBottomLeft == 1.0F ? 1.0F : aoSouthBottomLeftOffset;
        			renderBlocks.brightnessBottomLeft = brightnessSouthBottomLeftOffset;
        		} else if (Slope.isSlopingNorth(secondarySlope)) {
        			ao_no_color[3] = aoBottomBottomLeftOffset;
        			renderBlocks.brightnessBottomLeft = brightnessBottomBottomLeftOffset;
        		} else if (Slope.isSlopingWest(secondarySlope)) {
        			ao_no_color[3] = aoBottomBottomLeft == 1.0F ? 1.0F : aoWestBottomLeftOffset;
        			renderBlocks.brightnessBottomLeft = brightnessWestBottomLeftOffset;
        		} else { // Slope.isSlopingEast(secondarySlope)
        			ao_no_color[3] = aoBottomBottomLeftOffset;
        			renderBlocks.brightnessBottomLeft = brightnessBottomBottomLeftOffset;
        		}

            	// Set AO for Northeast corner in game
        		if (Slope.isSlopingSouth(secondarySlope)) {
        			ao_no_color[2] = aoBottomBottomRightOffset;
        			renderBlocks.brightnessBottomRight = brightnessBottomBottomRightOffset;
        		} else if (Slope.isSlopingNorth(secondarySlope)) {
        			ao_no_color[2] = aoBottomBottomRight == 1.0F ? 1.0F : aoNorthBottomRightOffset;
        			renderBlocks.brightnessBottomRight = brightnessNorthBottomRightOffset;
        		} else if (Slope.isSlopingWest(secondarySlope)) {
        			ao_no_color[2] = aoBottomBottomRight == 1.0F ? 1.0F : aoWestBottomRightOffset;
        			renderBlocks.brightnessBottomRight = brightnessWestBottomRightOffset;
        		} else { // Slope.isSlopingEast(secondarySlope)
        			ao_no_color[2] = aoBottomBottomRightOffset;
        			renderBlocks.brightnessBottomRight = brightnessBottomBottomRightOffset;
        		}

            	// Set AO for Southeast corner in game
        		if (Slope.isSlopingSouth(secondarySlope)) {
        			ao_no_color[1] = aoBottomTopRightOffset;
        			renderBlocks.brightnessTopRight = brightnessBottomTopRightOffset;
        		} else if (Slope.isSlopingNorth(secondarySlope)) {
        			ao_no_color[1] = aoBottomTopRight == 1.0F ? 1.0F : aoNorthTopRightOffset;
        			renderBlocks.brightnessTopRight = brightnessNorthTopRightOffset;
        		} else if (Slope.isSlopingWest(secondarySlope)) {
        			ao_no_color[1] = aoBottomTopRightOffset;
        			renderBlocks.brightnessTopRight = brightnessBottomTopRightOffset;
        		} else { // Slope.isSlopingEast(secondarySlope)
        			ao_no_color[1] = aoBottomTopRight == 1.0F ? 1.0F : aoEastBottomRightOffset;
        			renderBlocks.brightnessTopRight = brightnessEastBottomRightOffset;
        		}
        		
        		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 0, 16, true, x, y, z, lightnessBottomNorthSouth); 		
        	}
        }
        
        // Draw negative slope faces for pyramid
        if (Slope.isNegative(data) && Slope.isPyramid(data))
        {        	
        	/*
        	 * Prepare and render east face
        	 */
        	
        	// Set AO for Southwest corner in game
        	ao_no_color[0] = 1.0F;
        	renderBlocks.brightnessTopLeft = brightnessBottomTopLeft;

        	// Set AO for Northwest corner in game
        	ao_no_color[3] = aoBottomBottomLeftOffset;
        	renderBlocks.brightnessBottomLeft = brightnessBottomBottomLeftOffset;

        	// Set AO for Northeast corner in game
        	ao_no_color[2] = aoBottomBottomRightOffset;
        	renderBlocks.brightnessBottomRight = brightnessBottomBottomRightOffset;

        	// Set AO for Southeast corner in game
        	ao_no_color[1] = 1.0F;
        	renderBlocks.brightnessTopRight = brightnessBottomTopRight;

        	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 0, 23, true, x, y, z, lightnessBottomEastWest);        	
        	
        	/*
        	 * Prepare and render west face
        	 */
        	
        	// Set AO for Southwest corner in game
        	ao_no_color[0] = aoBottomTopLeftOffset;
        	renderBlocks.brightnessTopLeft = brightnessBottomTopLeftOffset;

        	// Set AO for Northwest corner in game
        	ao_no_color[3] = 1.0F;
        	renderBlocks.brightnessBottomLeft = brightnessBottomBottomLeft;

        	// Set AO for Northeast corner in game
        	ao_no_color[2] = 1.0F;
        	renderBlocks.brightnessBottomRight = brightnessBottomBottomRight;

        	// Set AO for Southeast corner in game
        	ao_no_color[1] = aoBottomTopRightOffset;
        	renderBlocks.brightnessTopRight = brightnessBottomTopRightOffset;

        	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 0, 24, true, x, y, z, lightnessBottomEastWest);
        	
        	/*
        	 * Prepare and render north face
        	 */
        	
        	// Set AO for Southwest corner in game
        	ao_no_color[0] = aoBottomTopLeftOffset;
        	renderBlocks.brightnessTopLeft = brightnessBottomTopLeftOffset;

        	// Set AO for Northwest corner in game
        	ao_no_color[3] = aoBottomBottomLeftOffset;
        	renderBlocks.brightnessBottomLeft = brightnessBottomBottomLeftOffset;

        	// Set AO for Northeast corner in game
        	ao_no_color[2] = 1.0F;
        	renderBlocks.brightnessBottomRight = brightnessBottomBottomRight;

        	// Set AO for Southeast corner in game
        	ao_no_color[1] = 1.0F;
        	renderBlocks.brightnessTopRight = brightnessBottomTopRight;
        	
        	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 0, 25, true, x, y, z, lightnessBottomNorthSouth);
        	
        	/*
        	 * Prepare and render south face
        	 */
        	
        	// Set AO for Southwest corner in game
        	ao_no_color[0] = 1.0F;
        	renderBlocks.brightnessTopLeft = brightnessBottomTopLeft;

        	// Set AO for Northwest corner in game
        	ao_no_color[3] = 1.0F;
        	renderBlocks.brightnessBottomLeft = brightnessBottomBottomLeft;

        	// Set AO for Northeast corner in game
        	ao_no_color[2] = aoBottomBottomRightOffset;
        	renderBlocks.brightnessBottomRight = brightnessBottomBottomRightOffset;

        	// Set AO for Southeast corner in game
        	ao_no_color[1] = aoBottomTopRightOffset;
        	renderBlocks.brightnessTopRight = brightnessBottomTopRightOffset;
        	
        	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 0, 26, true, x, y, z, lightnessBottomNorthSouth);
        }
        	
        // Draw negative slope face for oblique corners
        if (Slope.isNegative(data) && Slope.isObliqueCorner(data))
        {
        	switch(data) {
	        	case Slope.EXT_NEG_OBL_NE:
	        		
	            	// Set AO for Southwest corner in game
	            	ao_no_color[0] = aoBottomTopLeftOffset;
	            	renderBlocks.brightnessTopLeft = brightnessBottomTopLeftOffset;
	        		
	            	// Set AO for Northwest corner in game
	        		ao_no_color[3] = aoBottomBottomLeft;
	        		renderBlocks.brightnessBottomLeft = brightnessBottomBottomLeft;
	        		
	            	// Set AO for Northeast corner in game
	            	ao_no_color[2] = aoBottomBottomRightOffset;
	            	renderBlocks.brightnessBottomRight = brightnessBottomBottomRightOffset;
	        		
			    	// Set AO for Southeast corner in game
		        	ao_no_color[1] = aoBottomTopRight;
		        	renderBlocks.brightnessTopRight = brightnessBottomTopRight;
	        		
	        		break;
	        	case Slope.EXT_NEG_OBL_NW:
	        		
	            	// Set AO for Southwest corner in game
	        		ao_no_color[0] = aoBottomTopLeft;
	        		renderBlocks.brightnessTopLeft = brightnessBottomTopLeft;
	        		
	            	// Set AO for Northwest corner in game
	            	ao_no_color[3] = aoBottomBottomLeftOffset;
	            	renderBlocks.brightnessBottomLeft = brightnessBottomBottomLeftOffset;
	        		
	            	// Set AO for Northeast corner in game
	        		ao_no_color[2] = aoBottomBottomRight;
	        		renderBlocks.brightnessBottomRight = brightnessBottomBottomRight;
	        		
			    	// Set AO for Southeast corner in game
	            	ao_no_color[1] = aoBottomTopRightOffset;
	            	renderBlocks.brightnessTopRight = brightnessBottomTopRightOffset;
	        		
	        		break;
	        	case Slope.EXT_NEG_OBL_SE:
	        		
	            	// Set AO for Southwest corner in game
	        		ao_no_color[0] = aoBottomTopLeft;
	        		renderBlocks.brightnessTopLeft = brightnessBottomTopLeft;
	        		
	            	// Set AO for Northwest corner in game
	            	ao_no_color[3] = aoBottomBottomLeftOffset;
	            	renderBlocks.brightnessBottomLeft = brightnessBottomBottomLeftOffset;
	        		
	            	// Set AO for Northeast corner in game
	        		ao_no_color[2] = aoBottomBottomRight;
	        		renderBlocks.brightnessBottomRight = brightnessBottomBottomRight;
	        		
			    	// Set AO for Southeast corner in game
	            	ao_no_color[1] = aoBottomTopRightOffset;
	            	renderBlocks.brightnessTopRight = brightnessBottomTopRightOffset;
	        		
	        		break;
	        	case Slope.EXT_NEG_OBL_SW:
	        		
	            	// Set AO for Southwest corner in game
	            	ao_no_color[0] = aoBottomTopLeftOffset;
	            	renderBlocks.brightnessTopLeft = brightnessBottomTopLeftOffset;
	        		
	            	// Set AO for Northwest corner in game
	        		ao_no_color[3] = aoBottomBottomLeft;
	        		renderBlocks.brightnessBottomLeft = brightnessBottomBottomLeft;
	        		
	            	// Set AO for Northeast corner in game
	            	ao_no_color[2] = aoBottomBottomRightOffset;
	            	renderBlocks.brightnessBottomRight = brightnessBottomBottomRightOffset;
	        		
			    	// Set AO for Southeast corner in game
		        	ao_no_color[1] = aoBottomTopRight;
		        	renderBlocks.brightnessTopRight = brightnessBottomTopRight;
	        		
	        		break;
	        	case Slope.INT_NEG_OBL_NE:
	        		
	            	// Set AO for Southwest corner in game
	        		ao_no_color[0] = aoBottomTopLeft == 1.0F ? 1.0F : aoEastTopRightOffset;
	        		renderBlocks.brightnessTopLeft = brightnessEastTopRightOffset;
	        		
	            	// Set AO for Northwest corner in game
	        		ao_no_color[3] = aoBottomBottomLeftOffset;
	        		renderBlocks.brightnessBottomLeft = brightnessBottomBottomLeftOffset;
	        		
	            	// Set AO for Northeast corner in game
	        		ao_no_color[2] = aoBottomBottomRight == 1.0F ? 1.0F : aoNorthBottomRightOffset;
	        		renderBlocks.brightnessBottomRight = brightnessNorthBottomRightOffset;
	        		
			    	// Set AO for Southeast corner in game
		        	//ao_no_color[1] = aoBottomTopRight;
		        	//renderBlocks.brightnessTopRight = brightnessBottomTopRight;
	        		
	        		break;
	        	case Slope.INT_NEG_OBL_NW:
	        		
	            	// Set AO for Southwest corner in game
	        		ao_no_color[0] = aoBottomTopLeftOffset;
	        		renderBlocks.brightnessTopLeft = brightnessBottomTopLeftOffset;
	        		
	            	// Set AO for Northwest corner in game
	        		ao_no_color[3] = aoBottomBottomLeft == 1.0F ? 1.0F : aoWestBottomLeftOffset;
	        		renderBlocks.brightnessBottomLeft = brightnessWestBottomLeftOffset;
	        		
	            	// Set AO for Northeast corner in game
	        		//ao_no_color[2] = aoBottomBottomRight;
	        		//renderBlocks.brightnessBottomRight = brightnessBottomBottomRight;
	        		
			    	// Set AO for Southeast corner in game
		        	ao_no_color[1] = aoBottomTopRight == 1.0F ? 1.0F : aoNorthTopRightOffset;
		        	renderBlocks.brightnessTopRight = brightnessNorthTopRightOffset;
	        		
	        		break;
	        	case Slope.INT_NEG_OBL_SE:
	        		
	            	// Set AO for Southwest corner in game
	        		//ao_no_color[0] = aoBottomTopLeft;
	        		//renderBlocks.brightnessTopLeft = brightnessBottomTopLeft;
	        		
	            	// Set AO for Northwest corner in game
	        		ao_no_color[3] = aoBottomBottomLeft == 1.0F ? 1.0F : aoSouthBottomLeftOffset;
	        		renderBlocks.brightnessBottomLeft = brightnessSouthBottomLeftOffset;
	        		
	            	// Set AO for Northeast corner in game
	        		ao_no_color[2] = aoBottomBottomRightOffset;
	        		renderBlocks.brightnessBottomRight = brightnessBottomBottomRightOffset;
	        		
			    	// Set AO for Southeast corner in game
		        	ao_no_color[1] = aoBottomTopRight == 1.0F ? 1.0F : aoEastBottomRightOffset;
		        	renderBlocks.brightnessTopRight = brightnessEastBottomRightOffset;
	        		
	        		break;
	        	case Slope.INT_NEG_OBL_SW:
	        		
	            	// Set AO for Southwest corner in game
	        		ao_no_color[0] = aoBottomTopLeft == 1.0F ? 1.0F : aoSouthTopLeftOffset;
	        		renderBlocks.brightnessTopLeft = brightnessSouthTopLeftOffset;
	        		
	            	// Set AO for Northwest corner in game
	        		//ao_no_color[3] = aoBottomBottomLeft;
	        		//renderBlocks.brightnessBottomLeft = brightnessBottomBottomLeft;
	        		
	            	// Set AO for Northeast corner in game
	        		ao_no_color[2] = aoBottomBottomRight == 1.0F ? 1.0F : aoWestBottomRightOffset;
	        		renderBlocks.brightnessBottomRight = brightnessWestBottomRightOffset;
	        		
			    	// Set AO for Southeast corner in game
		        	ao_no_color[1] = aoBottomTopRightOffset;
		        	renderBlocks.brightnessTopRight = brightnessBottomTopRightOffset;
	        		
	        		break;
        	}

        	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 0, 2, true, x, y, z, lightnessNegOblique);
        }

        // Draw top face
        if (srcBlock.shouldSideBeRendered(renderBlocks.blockAccess, x, y + 1, z, 1) && Slope.shouldRenderTop(data, coverBlock, renderBlocks.blockAccess, x, y + 1, z))
        {
        	// Set AO for Southeast corner in game
        	ao_no_color[0] = aoTopTopLeft;
        	renderBlocks.brightnessTopLeft = brightnessTopTopLeft;

        	// Set AO for Northeast corner in game
        	ao_no_color[3] = aoTopBottomLeft;
        	renderBlocks.brightnessBottomLeft = brightnessTopBottomLeft;

        	// Set AO for Northwest corner in game
        	ao_no_color[2] = aoTopBottomRight;
        	renderBlocks.brightnessBottomRight = brightnessTopBottomRight;
        	
        	// Set AO for Southwest corner in game
        	ao_no_color[1] = aoTopTopRight;
        	renderBlocks.brightnessTopRight = brightnessTopTopRight;
        	
        	if (Slope.isObliqueCorner(data))
        		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 1, 3, false, x, y, z, lightnessTop);
        	else
        		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 1, 10, false, x, y, z, lightnessTop);
        }
        
        // Draw positive slope faces for normal slopes and corners formed using regular slopes
        if (Slope.isPositive(data) && !Slope.isObliqueCorner(data) && !Slope.isPyramid(data))
        {        	
        	// If corner, deconstruct to primary slope
        	int primarySlope = data;
        	if (Slope.isNormalCorner(data)) {
        		if (Slope.isSlopingWest(data))
        			primarySlope = Slope.POS_WEST;
        		else if (Slope.isSlopingEast(data))
        			primarySlope = Slope.POS_EAST;
        		else if (Slope.isSlopingSouth(data))
        			primarySlope = Slope.POS_SOUTH;
        		else
        			primarySlope = Slope.POS_NORTH;
        	}

        	// Set AO for Southeast corner in game
        	if (Slope.isSlopingSouth(primarySlope)) {
        		ao_no_color[0] = aoTopTopLeftOffset;
        		renderBlocks.brightnessTopLeft = brightnessTopTopLeftOffset;
        	} else if (Slope.isSlopingNorth(primarySlope)) {
        		ao_no_color[0] = aoTopTopLeft == 1.0F ? 1.0F : aoNorthTopLeftOffset;
        		renderBlocks.brightnessTopLeft = brightnessNorthTopLeftOffset;
        	} else if (Slope.isSlopingWest(primarySlope)) {
        		ao_no_color[0] = aoTopTopLeftOffset;
        		renderBlocks.brightnessTopLeft = brightnessTopTopLeftOffset;
        	} else { // Slope.isSlopingEast(primarySlope))
        		ao_no_color[0] = aoTopTopLeft == 1.0F ? 1.0F : aoEastBottomLeftOffset;
        		renderBlocks.brightnessTopLeft = brightnessEastBottomLeftOffset;
        	}

        	// Set AO for Northeast corner in game
        	if (Slope.isSlopingSouth(primarySlope)) {
        		ao_no_color[3] = aoTopBottomLeftOffset;
        		renderBlocks.brightnessBottomLeft = brightnessTopBottomLeftOffset;
        	} else if (Slope.isSlopingNorth(primarySlope)) {
        		ao_no_color[3] = aoTopBottomLeft == 1.0F ? 1.0F : aoNorthBottomLeftOffset;
        		renderBlocks.brightnessBottomLeft = brightnessNorthBottomLeftOffset;
        	} else if (Slope.isSlopingWest(primarySlope)) {
        		ao_no_color[3] = aoTopBottomLeft == 1.0F ? 1.0F : aoWestTopRightOffset;
        		renderBlocks.brightnessBottomLeft = brightnessWestTopRightOffset;
        	} else { // Slope.isSlopingEast(primarySlope))
        		ao_no_color[3] = aoTopBottomLeftOffset;
        		renderBlocks.brightnessBottomLeft = brightnessTopBottomLeftOffset;
        	}

        	// Set AO for Northwest corner in game
        	if (Slope.isSlopingSouth(primarySlope)) {
        		ao_no_color[2] = aoTopBottomRight == 1.0F ? 1.0F : aoSouthBottomRightOffset;
        		renderBlocks.brightnessBottomRight = brightnessSouthBottomRightOffset;
        	} else if (Slope.isSlopingNorth(primarySlope)) {
        		ao_no_color[2] = aoTopBottomRightOffset;
        		renderBlocks.brightnessBottomRight = brightnessTopBottomRightOffset;
        	} else if (Slope.isSlopingWest(primarySlope)) {
        		ao_no_color[2] = aoTopBottomRight == 1.0F ? 1.0F : aoWestTopLeftOffset;
        		renderBlocks.brightnessBottomRight = brightnessWestTopLeftOffset;
        	} else { // Slope.isSlopingEast(primarySlope))
        		ao_no_color[2] = aoTopBottomRightOffset;
        		renderBlocks.brightnessBottomRight = brightnessTopBottomRightOffset;
        	}

        	// Set AO for Southwest corner in game
        	if (Slope.isSlopingSouth(primarySlope)) {
        		ao_no_color[1] = aoTopTopRight == 1.0F ? 1.0F : aoSouthTopRightOffset;
        		renderBlocks.brightnessTopRight = brightnessSouthTopRightOffset;
        	} else if (Slope.isSlopingNorth(primarySlope)) {
        		ao_no_color[1] = aoTopTopRightOffset;
        		renderBlocks.brightnessTopRight = brightnessTopTopRightOffset;
        	} else if (Slope.isSlopingWest(primarySlope)) {
        		ao_no_color[1] = aoTopTopRightOffset;
        		renderBlocks.brightnessTopRight = brightnessTopTopRightOffset;
        	} else { // Slope.isSlopingEast(primarySlope))
        		ao_no_color[1] = aoTopTopRight == 1.0F ? 1.0F : aoEastTopLeftOffset;
        		renderBlocks.brightnessTopRight = brightnessEastTopLeftOffset;
        	}

        	float lightnessValue = (Slope.isSlopingEast(primarySlope) || Slope.isSlopingWest(primarySlope) ? lightnessTopEastWest : lightnessTopNorthSouth);

        	if (Slope.isNormalCorner(data)) {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 1, 17, true, x, y, z, lightnessValue);
        	} else {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 1, 10, true, x, y, z, lightnessValue);
        	}
        	
        	// Draw second face if slope is a corner
        	if (Slope.isNormalCorner(data)) {

        		// Deconstruct to secondary slope
        		int secondarySlope = data;
        		if (Slope.isSlopingNorth(data))
        			secondarySlope = Slope.POS_NORTH;
        		else if (Slope.isSlopingSouth(data))
        			secondarySlope = Slope.POS_SOUTH;
        		else if (Slope.isSlopingEast(data))
        			secondarySlope = Slope.POS_EAST;
        		else
        			secondarySlope = Slope.POS_WEST;
        		
            	// Set AO for Southeast corner in game
            	if (Slope.isSlopingSouth(secondarySlope)) {
            		ao_no_color[0] = aoTopTopLeftOffset;
            		renderBlocks.brightnessTopLeft = brightnessTopTopLeftOffset;
            	} else if (Slope.isSlopingNorth(secondarySlope)) {
            		ao_no_color[0] = aoTopTopLeft == 1.0F ? 1.0F : aoNorthTopLeftOffset;
            		renderBlocks.brightnessTopLeft = brightnessNorthTopLeftOffset;
            	} else if (Slope.isSlopingWest(secondarySlope)) {
            		ao_no_color[0] = aoTopTopLeftOffset;
            		renderBlocks.brightnessTopLeft = brightnessTopTopLeftOffset;
            	} else { // Slope.isSlopingEast(secondarySlope)
            		ao_no_color[0] = aoTopTopLeft == 1.0F ? 1.0F : aoEastBottomLeftOffset;
            		renderBlocks.brightnessTopLeft = brightnessEastBottomLeftOffset;
            	}

            	// Set AO for Northeast corner in game
            	if (Slope.isSlopingSouth(secondarySlope)) {
            		ao_no_color[3] = aoTopBottomLeftOffset;
            		renderBlocks.brightnessBottomLeft = brightnessTopBottomLeftOffset;
            	} else if (Slope.isSlopingNorth(secondarySlope)) {
            		ao_no_color[3] = aoTopBottomLeft == 1.0F ? 1.0F : aoNorthBottomLeftOffset;
            		renderBlocks.brightnessBottomLeft = brightnessNorthBottomLeftOffset;
            	} else if (Slope.isSlopingWest(secondarySlope)) {
            		ao_no_color[3] = aoTopBottomLeft == 1.0F ? 1.0F : aoWestTopRightOffset;
            		renderBlocks.brightnessBottomLeft = brightnessWestTopRightOffset;
            	} else { // Slope.isSlopingEast(secondarySlope)
            		ao_no_color[3] = aoTopBottomLeftOffset;
            		renderBlocks.brightnessBottomLeft = brightnessTopBottomLeftOffset;
            	}

            	// Set AO for Northwest corner in game
            	if (Slope.isSlopingSouth(secondarySlope)) {
            		ao_no_color[2] = aoTopBottomRight == 1.0F ? 1.0F : aoSouthBottomRightOffset;
            		renderBlocks.brightnessBottomRight = brightnessSouthBottomRightOffset;
            	} else if (Slope.isSlopingNorth(secondarySlope)) {
            		ao_no_color[2] = aoTopBottomRightOffset;
            		renderBlocks.brightnessBottomRight = brightnessTopBottomRightOffset;
            	} else if (Slope.isSlopingWest(secondarySlope)) {
            		ao_no_color[2] = aoTopBottomRight == 1.0F ? 1.0F : aoWestTopLeftOffset;
            		renderBlocks.brightnessBottomRight = brightnessWestTopLeftOffset;
            	} else { // Slope.isSlopingEast(secondarySlope)
            		ao_no_color[2] = aoTopBottomRightOffset;
            		renderBlocks.brightnessBottomRight = brightnessTopBottomRightOffset;
            	}

            	// Set AO for Southwest corner in game
            	if (Slope.isSlopingSouth(secondarySlope)) {
            		ao_no_color[1] = aoTopTopRight == 1.0F ? 1.0F : aoSouthTopRightOffset;
            		renderBlocks.brightnessTopRight = brightnessSouthTopRightOffset;
            	} else if (Slope.isSlopingNorth(secondarySlope)) {
            		ao_no_color[1] = aoTopTopRightOffset;
            		renderBlocks.brightnessTopRight = brightnessTopTopRightOffset;
            	} else if (Slope.isSlopingWest(secondarySlope)) {
            		ao_no_color[1] = aoTopTopRightOffset;
            		renderBlocks.brightnessTopRight = brightnessTopTopRightOffset;
            	} else { // Slope.isSlopingEast(secondarySlope)
            		ao_no_color[1] = aoTopTopRight == 1.0F ? 1.0F : aoEastTopLeftOffset;
            		renderBlocks.brightnessTopRight = brightnessEastTopLeftOffset;
            	}
            	
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 1, 18, true, x, y, z, lightnessTopNorthSouth);
        	}
        }
        
        // Draw positive slope faces for pyramid
        if (Slope.isPositive(data) && Slope.isPyramid(data))
        {
        	/*
        	 * Prepare and render east face
        	 */
        	
        	// Set AO for Southeast corner in game
        	ao_no_color[0] = 1.0F;
        	renderBlocks.brightnessTopLeft = brightnessTopTopLeft;

        	// Set AO for Northeast corner in game
        	ao_no_color[3] = aoTopBottomLeftOffset;
        	renderBlocks.brightnessBottomLeft = brightnessTopBottomLeftOffset;

        	// Set AO for Northwest corner in game
        	ao_no_color[2] = aoTopBottomRightOffset;
        	renderBlocks.brightnessBottomRight = brightnessTopBottomRightOffset;
        	
        	// Set AO for Southwest corner in game
        	ao_no_color[1] = 1.0F;
        	renderBlocks.brightnessTopRight = brightnessTopTopRight;

        	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 1, 27, true, x, y, z, lightnessTopEastWest);
        	
        	/*
        	 * Prepare and render west face
        	 */
        	
        	// Set AO for Southeast corner in game
        	ao_no_color[0] = aoTopTopLeftOffset;
        	renderBlocks.brightnessTopLeft = brightnessTopTopLeftOffset;

        	// Set AO for Northeast corner in game
        	ao_no_color[3] = 1.0F;
        	renderBlocks.brightnessBottomLeft = brightnessTopBottomLeft;

        	// Set AO for Northwest corner in game
        	ao_no_color[2] = 1.0F;
        	renderBlocks.brightnessBottomRight = brightnessTopBottomRight;
        	
        	// Set AO for Southwest corner in game
        	ao_no_color[1] = aoTopTopRightOffset;
        	renderBlocks.brightnessTopRight = brightnessTopTopRightOffset;

        	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 1, 28, true, x, y, z, lightnessTopEastWest);
        	
        	/*
        	 * Prepare and render north face
        	 */
        	
        	// Set AO for Southeast corner in game
        	ao_no_color[0] = 1.0F;
        	renderBlocks.brightnessTopLeft = brightnessTopTopLeft;

        	// Set AO for Northeast corner in game
        	ao_no_color[3] = 1.0F;
        	renderBlocks.brightnessBottomLeft = brightnessTopBottomLeft;

        	// Set AO for Northwest corner in game
        	ao_no_color[2] = aoTopBottomRightOffset;
        	renderBlocks.brightnessBottomRight = brightnessTopBottomRightOffset;
        	
        	// Set AO for Southwest corner in game
        	ao_no_color[1] = aoTopTopRightOffset;
        	renderBlocks.brightnessTopRight = brightnessTopTopRightOffset;
        	
        	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 1, 29, true, x, y, z, lightnessTopNorthSouth);
        	
        	/*
        	 * Prepare and render south face
        	 */
        	
        	// Set AO for Southeast corner in game
        	ao_no_color[0] = aoTopTopLeftOffset;
        	renderBlocks.brightnessTopLeft = brightnessTopTopLeftOffset;

        	// Set AO for Northeast corner in game
        	ao_no_color[3] = aoTopBottomLeftOffset;
        	renderBlocks.brightnessBottomLeft = brightnessTopBottomLeftOffset;

        	// Set AO for Northwest corner in game
        	ao_no_color[2] = 1.0F;
        	renderBlocks.brightnessBottomRight = brightnessTopBottomRight;
        	
        	// Set AO for Southwest corner in game
        	ao_no_color[1] = 1.0F;
        	renderBlocks.brightnessTopRight = brightnessTopTopRight;
        	
        	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 1, 30, true, x, y, z, lightnessTopNorthSouth);
        }
        
        // Draw positive slope face for oblique slopes
        if (Slope.isPositive(data) && Slope.isObliqueCorner(data))
        {
        	// Set AO for Southeast corner in game
        	switch(data) {
	        	case Slope.EXT_POS_OBL_NE:
	        		
	            	// Set AO for Southeast corner in game
	    			ao_no_color[0] = aoTopTopLeft;
	    			renderBlocks.brightnessTopLeft = brightnessTopTopLeft;
	    			
	            	// Set AO for Northeast corner in game
	            	ao_no_color[3] = aoTopBottomLeftOffset;
	            	renderBlocks.brightnessBottomLeft = brightnessTopBottomLeftOffset;
	        		
	            	// Set AO for Northwest corner in game
	        		ao_no_color[2] = aoTopBottomRight;
	        		renderBlocks.brightnessBottomRight = brightnessTopBottomRight;
	        		
	            	// Set AO for Southwest corner in game
	            	ao_no_color[1] = aoTopTopRightOffset;
	            	renderBlocks.brightnessTopRight = brightnessTopTopRightOffset;
	        		
	        		break;
	        	case Slope.EXT_POS_OBL_NW:
	        		
	            	// Set AO for Southeast corner in game
	            	ao_no_color[0] = aoTopTopLeftOffset;
	            	renderBlocks.brightnessTopLeft = brightnessTopTopLeftOffset;
	    			
	            	// Set AO for Northeast corner in game
	        		ao_no_color[3] = aoTopBottomLeft;
	        		renderBlocks.brightnessBottomLeft = brightnessTopBottomLeft;
	        		
	            	// Set AO for Northwest corner in game
	            	ao_no_color[2] = aoTopBottomRightOffset;
	            	renderBlocks.brightnessBottomRight = brightnessTopBottomRightOffset;
	        		
	            	// Set AO for Southwest corner in game
	        		ao_no_color[1] = aoTopTopRight;
	        		renderBlocks.brightnessTopRight = brightnessTopTopRight;
	        		
	        		break;
	        	case Slope.EXT_POS_OBL_SE:
      		
	            	// Set AO for Southeast corner in game
	            	ao_no_color[0] = aoTopTopLeftOffset;
	            	renderBlocks.brightnessTopLeft = brightnessTopTopLeftOffset;
	    			
	            	// Set AO for Northeast corner in game
	        		ao_no_color[3] = aoTopBottomLeft;
	        		renderBlocks.brightnessBottomLeft = brightnessTopBottomLeft;
	        		
	            	// Set AO for Northwest corner in game
	            	ao_no_color[2] = aoTopBottomRightOffset;
	            	renderBlocks.brightnessBottomRight = brightnessTopBottomRightOffset;
	        		
	            	// Set AO for Southwest corner in game
	        		ao_no_color[1] = aoTopTopRight;
	        		renderBlocks.brightnessTopRight = brightnessTopTopRight;
	        		
	        		break;
	        	case Slope.EXT_POS_OBL_SW:

	            	// Set AO for Southeast corner in game
	    			ao_no_color[0] = aoTopTopLeft;
	    			renderBlocks.brightnessTopLeft = brightnessTopTopLeft;
	    			
	            	// Set AO for Northeast corner in game
	            	ao_no_color[3] = aoTopBottomLeftOffset;
	            	renderBlocks.brightnessBottomLeft = brightnessTopBottomLeftOffset;
	        		
	            	// Set AO for Northwest corner in game
	        		ao_no_color[2] = aoTopBottomRight;
	        		renderBlocks.brightnessBottomRight = brightnessTopBottomRight;
	        		
	            	// Set AO for Southwest corner in game
	            	ao_no_color[1] = aoTopTopRightOffset;
	            	renderBlocks.brightnessTopRight = brightnessTopTopRightOffset;
	        		
	        		break;
	        	case Slope.INT_POS_OBL_NE:

	            	// Set AO for Southeast corner in game
	    			//ao_no_color[0] = aoTopTopLeft;
	    			//renderBlocks.brightnessTopLeft = brightnessTopTopLeft;
	    			
	            	// Set AO for Northeast corner in game
	        		ao_no_color[3] = aoTopBottomRight == 1.0F ? 1.0F : aoNorthBottomLeftOffset;
	        		renderBlocks.brightnessBottomLeft = brightnessNorthBottomLeftOffset;
	        		
	            	// Set AO for Northwest corner in game
	        		ao_no_color[2] = aoTopBottomRightOffset;
	        		renderBlocks.brightnessBottomRight = brightnessTopBottomRightOffset;
	        		
	            	// Set AO for Southwest corner in game
	        		ao_no_color[1] = aoTopTopRight == 1.0F ? 1.0F : aoEastTopLeftOffset;
	        		renderBlocks.brightnessTopRight = brightnessEastTopLeftOffset;
	        		
	        		break;
	        	case Slope.INT_POS_OBL_NW:
	        		
	            	// Set AO for Southeast corner in game
	    			ao_no_color[0] = aoTopTopLeft == 1.0F ? 1.0F : aoNorthTopLeftOffset;
	    			renderBlocks.brightnessTopLeft = brightnessNorthTopLeftOffset;
	    			
	            	// Set AO for Northeast corner in game
	        		//ao_no_color[3] = aoTopBottomLeft;
	        		//renderBlocks.brightnessBottomLeft = brightnessTopBottomLeft;
	        		
	            	// Set AO for Northwest corner in game
	        		ao_no_color[2] = aoTopBottomRight == 1.0F ? 1.0F : aoWestTopLeftOffset;
	        		renderBlocks.brightnessBottomRight = brightnessWestTopLeftOffset;
	        		
	            	// Set AO for Southwest corner in game
	        		ao_no_color[1] = aoTopTopRightOffset;
	        		renderBlocks.brightnessTopRight = brightnessTopTopRightOffset;
	        		
	        		break;
	        	case Slope.INT_POS_OBL_SE:

	            	// Set AO for Southeast corner in game
	    			ao_no_color[0] = aoTopTopLeft == 1.0F ? 1.0F : aoEastBottomLeftOffset;
	    			renderBlocks.brightnessTopLeft = brightnessEastBottomLeftOffset;
	    			
	            	// Set AO for Northeast corner in game
	        		ao_no_color[3] = aoTopBottomLeftOffset;
	        		renderBlocks.brightnessBottomLeft = brightnessTopBottomLeftOffset;
	        		
	            	// Set AO for Northwest corner in game
	        		ao_no_color[2] = aoTopBottomRight == 1.0F ? 1.0F : aoSouthBottomRightOffset;
	        		renderBlocks.brightnessBottomRight = brightnessSouthBottomRightOffset;
	        		
	            	// Set AO for Southwest corner in game
	        		//ao_no_color[1] = aoTopTopRight;
	        		//renderBlocks.brightnessTopRight = brightnessTopTopRight;
	        		
	        		break;
	        	case Slope.INT_POS_OBL_SW:
	        		
	            	// Set AO for Southeast corner in game
	    			ao_no_color[0] = aoTopTopLeftOffset;
	    			renderBlocks.brightnessTopLeft = brightnessTopTopLeftOffset;
	    			
	            	// Set AO for Northeast corner in game
	        		ao_no_color[3] = aoTopBottomRight == 1.0F ? 1.0F : aoWestTopRightOffset;
	        		renderBlocks.brightnessBottomLeft = brightnessWestTopRightOffset;
	        		
	            	// Set AO for Northwest corner in game
	        		//ao_no_color[2] = aoTopBottomRight;
	        		//renderBlocks.brightnessBottomRight = brightnessTopBottomRight;
	        		
	            	// Set AO for Southwest corner in game
	        		ao_no_color[1] = aoTopTopRight == 1.0F ? 1.0F : aoSouthTopRightOffset;
	        		renderBlocks.brightnessTopRight = brightnessSouthTopRightOffset;
	        		
	        		break;
        	}

        	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 1, 4, true, x, y, z, lightnessPosOblique);
        }

        // Draw east face
        if (srcBlock.shouldSideBeRendered(renderBlocks.blockAccess, x, y, z - 1, 2) && Slope.shouldRenderEast(data, coverBlock, renderBlocks.blockAccess, x, y, z - 1))
        {
        	// Top west corner in game
        	ao_no_color[0] = aoEastTopLeft;
        	renderBlocks.brightnessTopLeft = brightnessEastTopLeft;

        	// Top east corner in game
        	ao_no_color[3] = aoEastBottomLeft;
        	renderBlocks.brightnessBottomLeft = brightnessEastBottomLeft;

        	// Bottom east corner in game
        	ao_no_color[2] = aoEastBottomRight;
        	renderBlocks.brightnessBottomRight = brightnessEastBottomRight;

        	// Bottom west corner in game
        	ao_no_color[1] = aoEastTopRight;
        	renderBlocks.brightnessTopRight = brightnessEastTopRight;
        	
            if (Slope.isNormalCorner(data)) {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 2, 19, false, x, y, z, lightnessEast);
            } else if (Slope.isObliqueCorner(data)) {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 2, 5, false, x, y, z, lightnessEast);
            } else {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 2, 11, false, x, y, z, lightnessEast);
            }
        }

        // Draw west face
        if (srcBlock.shouldSideBeRendered(renderBlocks.blockAccess, x, y, z + 1, 3) && Slope.shouldRenderWest(data, coverBlock, renderBlocks.blockAccess, x, y, z + 1))
        {
        	// Top west corner in game
            ao_no_color[0] = aoWestTopLeft;
            renderBlocks.brightnessTopLeft = brightnessWestTopLeft;

            // Bottom west corner in game
            ao_no_color[3] = aoWestBottomLeft;
            renderBlocks.brightnessBottomLeft = brightnessWestBottomLeft;

            // Bottom east corner in game
            ao_no_color[2] = aoWestBottomRight;
            renderBlocks.brightnessBottomRight = brightnessWestBottomRight;

            // Top east corner in game
            ao_no_color[1] = aoWestTopRight;
            renderBlocks.brightnessTopRight = brightnessWestTopRight;

            if (Slope.isNormalCorner(data)) {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 3, 20, false, x, y, z, lightnessWest);
            } else if (Slope.isObliqueCorner(data)) {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 3, 6, false, x, y, z, lightnessWest);
            } else {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 3, 12, false, x, y, z, lightnessWest);
            }
        }

        // Draw north facing side slope
        if (Slope.isSideSlopingNorth(data))
        {
        	// Top south corner in game
        	if (data == Slope.SIDE_NE) {
            	ao_no_color[0] = aoEastTopLeftOffset;
            	renderBlocks.brightnessTopLeft = brightnessEastTopLeftOffset;
        	} else { // (slope == Slope.SIDE_NW)
        		ao_no_color[0] = aoNorthTopLeftOffset;
        		renderBlocks.brightnessTopLeft = brightnessNorthTopLeftOffset;
        	}

        	// Top north corner in game
        	if (data == Slope.SIDE_NE) {
        		ao_no_color[3] = aoNorthBottomLeftOffset;
        		renderBlocks.brightnessBottomLeft = brightnessNorthBottomLeftOffset;
        	} else { // (slope == Slope.SIDE_NW)
                ao_no_color[3] = aoWestTopLeftOffset;
                renderBlocks.brightnessBottomLeft = brightnessWestTopLeftOffset;
        	}

        	// Bottom north corner in game
        	if (data == Slope.SIDE_NE) {
        		ao_no_color[2] = aoNorthBottomRightOffset;
        		renderBlocks.brightnessBottomRight = brightnessNorthBottomRightOffset;
        	} else { // (slope == Slope.SIDE_NW)
                ao_no_color[2] = aoWestBottomLeftOffset;
                renderBlocks.brightnessBottomRight = brightnessWestBottomLeftOffset;
        	}

        	// Bottom south corner in game
        	if (data == Slope.SIDE_NE) {
            	ao_no_color[1] = aoEastTopRightOffset;
            	renderBlocks.brightnessTopRight = brightnessEastTopRightOffset;
        	} else { // (slope == Slope.SIDE_NW)
        		ao_no_color[1] = aoNorthTopRightOffset;
        		renderBlocks.brightnessTopRight = brightnessNorthTopRightOffset;
        	}
        	
        	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 4, 13, false, x, y, z, lightnessSide);
        }
        
        // Draw north face
        if (srcBlock.shouldSideBeRendered(renderBlocks.blockAccess, x - 1, y, z, 4) && Slope.shouldRenderNorth(data, coverBlock, renderBlocks.blockAccess, x - 1, y, z))
        {
        	// Top south corner in game
        	ao_no_color[0] = aoNorthTopLeft;
        	renderBlocks.brightnessTopLeft = brightnessNorthTopLeft;

        	// Top north corner in game
        	ao_no_color[3] = aoNorthBottomLeft;
        	renderBlocks.brightnessBottomLeft = brightnessNorthBottomLeft;

        	// Bottom north corner in game
        	ao_no_color[2] = aoNorthBottomRight;
        	renderBlocks.brightnessBottomRight = brightnessNorthBottomRight;

        	// Bottom south corner in game
        	ao_no_color[1] = aoNorthTopRight;
        	renderBlocks.brightnessTopRight = brightnessNorthTopRight;

            if (Slope.isNormalCorner(data)) {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 4, 21, false, x, y, z, lightnessNorth);
            } else if (Slope.isObliqueCorner(data)) {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 4, 7, false, x, y, z, lightnessNorth);
            } else {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 4, 13, false, x, y, z, lightnessNorth);
            }
        }
        
        // Draw south facing side slope
        if (Slope.isSideSlopingSouth(data))
        {
        	// Bottom south corner in game
        	if (data == Slope.SIDE_SW) {
        		ao_no_color[0] = aoSouthTopLeftOffset;
        		renderBlocks.brightnessTopLeft = brightnessSouthTopLeftOffset;
        	} else { // (slope == Slope.SIDE_SE)
            	ao_no_color[0] = aoEastBottomRightOffset;
            	renderBlocks.brightnessTopLeft = brightnessEastBottomRightOffset;
        	}

        	// Bottom north corner in game
            if (data == Slope.SIDE_SW) {
                ao_no_color[3] = aoWestBottomRightOffset;
                renderBlocks.brightnessBottomLeft = brightnessWestBottomRightOffset;
        	} else { // (slope == Slope.SIDE_SE)
            	ao_no_color[3] = aoSouthBottomLeftOffset;
            	renderBlocks.brightnessBottomLeft = brightnessSouthBottomLeftOffset;
        	}

        	// Top north corner in game
        	if (data == Slope.SIDE_SW) {
                ao_no_color[2] = aoWestTopRightOffset;
                renderBlocks.brightnessBottomRight = brightnessWestTopRightOffset;
        	} else { // (slope == Slope.SIDE_SE)
        		ao_no_color[2] = aoSouthBottomRightOffset;
        		renderBlocks.brightnessBottomRight = brightnessSouthBottomRightOffset;
        	}

        	// Top south corner in game
        	if (data == Slope.SIDE_SW) {
        		ao_no_color[1] = aoSouthTopRightOffset;
        		renderBlocks.brightnessTopRight = brightnessSouthTopRightOffset;
        	} else { // (slope == Slope.SIDE_SE)
            	ao_no_color[1] = aoEastBottomLeftOffset;
            	renderBlocks.brightnessTopRight = brightnessEastBottomLeftOffset;
        	}

        	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 5, 14, false, x, y, z, lightnessSide);
        }
        
        // Draw south face
        if (srcBlock.shouldSideBeRendered(renderBlocks.blockAccess, x + 1, y, z, 5) && Slope.shouldRenderSouth(data, coverBlock, renderBlocks.blockAccess, x + 1, y, z))
        {
        	// Bottom south corner in game
        	ao_no_color[0] = aoSouthTopLeft;
        	renderBlocks.brightnessTopLeft = brightnessSouthTopLeft;

        	// Bottom north corner in game
            ao_no_color[3] = aoSouthBottomLeft;
            renderBlocks.brightnessBottomLeft = brightnessSouthBottomLeft;

        	// Top north corner in game
        	ao_no_color[2] = aoSouthBottomRight;
        	renderBlocks.brightnessBottomRight = brightnessSouthBottomRight;

        	// Top south corner in game
        	ao_no_color[1] = aoSouthTopRight;
        	renderBlocks.brightnessTopRight = brightnessSouthTopRight;

            if (Slope.isNormalCorner(data)) {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 5, 22, false, x, y, z, lightnessSouth);
            } else if (Slope.isObliqueCorner(data)) {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 5, 8, false, x, y, z, lightnessSouth);
            } else {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 5, 14, false, x, y, z, lightnessSouth);
            }
        }

        renderBlocks.enableAO = false;
        return true;
    }

	@Override
    /**
     * Renders a slope block at the given coordinates, with a given color ratio.  Args: block, x, y, z, r, g, b
     */
	public boolean renderStandardSlopeWithColorMultiplier(TECarpentersBlock TE, RenderBlocks renderBlocks, Block coverBlock, Block srcBlock, int x, int y, int z, float red, float green, float blue)
    {
    	int data = BlockProperties.getData(TE);
    	
        renderBlocks.enableAO = false;
        Tessellator tessellator = Tessellator.instance;
        
        float[] rgb_YP = { 1.0F, 1.0F, 1.0F };
        float[] rgb_YN = { 0.5F, 0.5F, 0.5F };
        float[] rgb_X = { 0.6F, 0.6F, 0.6F };
        float[] rgb_Z = { 0.8F, 0.8F, 0.8F };
        float[] rgb_obl_YP = { 0.85F, 0.85F, 0.85F };
        float[] rgb_obl_YN = { 0.6F, 0.6F, 0.6F };
        float[] rgb_YN_X = { 0.55F, 0.55F, 0.55F };
        float[] rgb_YP_X = { 0.8F, 0.8F, 0.8F };
        float[] rgb_YN_Z = { 0.65F, 0.65F, 0.65F };
        float[] rgb_YP_Z = { 0.9F, 0.9F, 0.9F };
        float[] rgb_side = { 0.7F, 0.7F, 0.7F };

        // Draw bottom face
        if (srcBlock.shouldSideBeRendered(renderBlocks.blockAccess, x, y - 1, z, 0) && Slope.shouldRenderBottom(data, coverBlock, renderBlocks.blockAccess, x, y - 1, z))
        {
        	tessellator.setBrightness(coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y - 1, z));
        	rgb_no_color = rgb_YN;

        	if (Slope.isObliqueCorner(data))
        		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 0, 1, false, x, y, z, 0.0F);
        	else
        		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 0, 9, false, x, y, z, 0.0F);
        }
        
        // Draw negative slope faces for normal slopes and corners formed using regular slopes
        if (Slope.isNegative(data) && !Slope.isObliqueCorner(data) && !Slope.isPyramid(data))
        {
        	tessellator.setBrightness(coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z));

        	if (Slope.isSlopingEast(data) || Slope.isSlopingWest(data)) {
        		rgb_no_color = rgb_YN_Z;
        	} else {
        		rgb_no_color = rgb_YN_X;
        	}

			if (Slope.isNormalCorner(data)) {
				prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 0, 15, true, x, y, z, 0.0F);
			} else { // Is a normal slope
				prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 0, 9, true, x, y, z, 0.0F);
			}

        	// Draw second face if slope is an ordinary corner
        	if (Slope.isNormalCorner(data)) {
        		rgb_no_color = rgb_YN_X;
        		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 0, 16, true, x, y, z, 0.0F);
        	}
        }
        
        // Draw negative slope faces for pyramid
        if (Slope.isNegative(data) && Slope.isPyramid(data))
        {
        	tessellator.setBrightness(coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z));

    		rgb_no_color = rgb_YN_Z;
    		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 0, 23, true, x, y, z, 0.0F);
    		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 0, 24, true, x, y, z, 0.0F);

    		rgb_no_color = rgb_YN_X;
    		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 0, 25, true, x, y, z, 0.0F);
    		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 0, 26, true, x, y, z, 0.0F);
        }
        
        // Draw negative slope face for oblique corners
        if (Slope.isNegative(data) && Slope.isObliqueCorner(data))
        {
        	tessellator.setBrightness(coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z));

    		rgb_no_color = rgb_obl_YN;
    		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 0, 2, true, x, y, z, 0.0F);
        }

        // Draw top face
        if (srcBlock.shouldSideBeRendered(renderBlocks.blockAccess, x, y + 1, z, 1) && Slope.shouldRenderTop(data, coverBlock, renderBlocks.blockAccess, x, y + 1, z))
        {
        	tessellator.setBrightness(coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y + 1, z));
        	
    		rgb_no_color = rgb_YP;
        	
        	if (Slope.isObliqueCorner(data))
        		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 1, 3, false, x, y, z, 0.0F);
        	else
        		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 1, 10, false, x, y, z, 0.0F);
        }
        
        // Draw positive slope faces for normal slopes and corners formed using regular slopes
        if (Slope.isPositive(data) && !Slope.isObliqueCorner(data) && !Slope.isPyramid(data))
        {
        	tessellator.setBrightness(coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z));

    		if (Slope.isSlopingEast(data) || Slope.isSlopingWest(data)) {
        		rgb_no_color = rgb_YP_Z;
    		} else {
        		rgb_no_color = rgb_YP_X;
    		}

        	if (Slope.isNormalCorner(data)) {
        		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 1, 17, true, x, y, z, 0.0F);
        	} else {
        		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 1, 10, true, x, y, z, 0.0F);
        	}
        	
        	// Draw second face if slope is a corner
        	if (Slope.isNormalCorner(data)) {
        		rgb_no_color = rgb_YP_X;
            	
        		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 1, 18, true, x, y, z, 0.0F);
        	}
        }
        
        // Draw positive slope faces for pyramid
        if (Slope.isPositive(data) && Slope.isPyramid(data))
        {
        	tessellator.setBrightness(coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z));

    		rgb_no_color = rgb_YP_Z;
    		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 1, 27, true, x, y, z, 0.0F);
    		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 1, 28, true, x, y, z, 0.0F);

    		rgb_no_color = rgb_YP_X;
    		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 1, 29, true, x, y, z, 0.0F);
    		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 1, 30, true, x, y, z, 0.0F);
        }
        
        // Draw positive slope face for oblique slopes
        if (Slope.isPositive(data) && Slope.isObliqueCorner(data))
        {
        	tessellator.setBrightness(coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z));
        	
    		rgb_no_color = rgb_obl_YP;
    		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 1, 4, true, x, y, z, 0.0F);
        }

        // Draw east face
        if (srcBlock.shouldSideBeRendered(renderBlocks.blockAccess, x, y, z - 1, 2) && Slope.shouldRenderEast(data, coverBlock, renderBlocks.blockAccess, x, y, z - 1))
        {
            tessellator.setBrightness(coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z - 1));
    		rgb_no_color = rgb_Z;

            if (Slope.isNormalCorner(data)) {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 2, 19, false, x, y, z, 0.0F);
            } else if (Slope.isObliqueCorner(data)) {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 2, 5, false, x, y, z, 0.0F);
            } else {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 2, 11, false, x, y, z, 0.0F);
            }
        }

        // Draw west face
        if (srcBlock.shouldSideBeRendered(renderBlocks.blockAccess, x, y, z + 1, 3) && (Slope.shouldRenderWest(data, coverBlock, renderBlocks.blockAccess, x, y, z + 1)))
        {
            tessellator.setBrightness(coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z + 1));
    		rgb_no_color = rgb_Z;

            if (Slope.isNormalCorner(data)) {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 3, 20, false, x, y, z, 0.0F);
            } else if (Slope.isObliqueCorner(data)) {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 3, 6, false, x, y, z, 0.0F);
            } else {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 3, 12, false, x, y, z, 0.0F);
            }
        }
        
        // Draw north facing side slope
        if (Slope.isSideSlopingNorth(data))
        {
        	tessellator.setBrightness(coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z));
    		rgb_no_color = rgb_side;

    		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 4, 13, false, x, y, z, 0.0F);
        }

        // Draw north face
        if (srcBlock.shouldSideBeRendered(renderBlocks.blockAccess, x - 1, y, z, 4) && (Slope.shouldRenderNorth(data, coverBlock, renderBlocks.blockAccess, x - 1, y, z)))
        {
        	tessellator.setBrightness(coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x - 1, y, z));
    		rgb_no_color = rgb_X;

            if (Slope.isNormalCorner(data)) {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 4, 21, false, x, y, z, 0.0F);
            } else if (Slope.isObliqueCorner(data)) {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 4, 7, false, x, y, z, 0.0F);
            } else {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 4, 13, false, x, y, z, 0.0F);
            }
        }
        
        // Draw south facing side slope
        if (Slope.isSideSlopingSouth(data))
        {
        	tessellator.setBrightness(coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x, y, z));
    		rgb_no_color = rgb_side;

    		prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 5, 14, false, x, y, z, 0.0F);
        }

        // Draw south face
        if (srcBlock.shouldSideBeRendered(renderBlocks.blockAccess, x + 1, y, z, 5) && (Slope.shouldRenderSouth(data, coverBlock, renderBlocks.blockAccess, x + 1, y, z)))
        {
        	tessellator.setBrightness(coverBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, x + 1, y, z));
    		rgb_no_color = rgb_X;

            if (Slope.isNormalCorner(data)) {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 5, 22, false, x, y, z, 0.0F);
            } else if (Slope.isObliqueCorner(data)) {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 5, 8, false, x, y, z, 0.0F);
            } else {
            	prepareSlopeRender(TE, renderBlocks, coverBlock, srcBlock, 5, 14, false, x, y, z, 0.0F);
            }
        }

        return true;
    }

}