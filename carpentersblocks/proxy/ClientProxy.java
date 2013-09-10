package carpentersblocks.proxy;

import net.minecraftforge.common.MinecraftForge;
import carpentersblocks.CarpentersBlocks;
import carpentersblocks.renderer.BlockHandlerCarpentersBarrier;
import carpentersblocks.renderer.BlockHandlerCarpentersBed;
import carpentersblocks.renderer.BlockHandlerCarpentersBlock;
import carpentersblocks.renderer.BlockHandlerCarpentersButton;
import carpentersblocks.renderer.BlockHandlerCarpentersDaylightSensor;
import carpentersblocks.renderer.BlockHandlerCarpentersDoor;
import carpentersblocks.renderer.BlockHandlerCarpentersGate;
import carpentersblocks.renderer.BlockHandlerCarpentersHatch;
import carpentersblocks.renderer.BlockHandlerCarpentersLever;
import carpentersblocks.renderer.BlockHandlerCarpentersPressurePlate;
import carpentersblocks.renderer.BlockHandlerCarpentersSlope;
import carpentersblocks.renderer.BlockHandlerCarpentersStairs;
import carpentersblocks.renderer.tileentity.TERendererCarpentersBlock;
import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.handler.IconHandler;
import carpentersblocks.util.handler.LanguageHandler;
import carpentersblocks.util.handler.OptifineHandler;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
	
	@Override
    public void registerRenderInformation(FMLPreInitializationEvent event)
    {
    	MinecraftForge.EVENT_BUS.register(new IconHandler());
    	LanguageHandler.init(event);
		
		if (CarpentersBlocks.enableBarrier) {
			CarpentersBlocks.carpentersBarrierRenderID = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(CarpentersBlocks.carpentersBarrierRenderID, new BlockHandlerCarpentersBarrier());
		}
		
		if (CarpentersBlocks.enableButton) {
			CarpentersBlocks.carpentersButtonRenderID = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(CarpentersBlocks.carpentersButtonRenderID, new BlockHandlerCarpentersButton());
		}
		
		if (CarpentersBlocks.enableDaylightSensor) {
			CarpentersBlocks.carpentersDaylightSensorRenderID = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(CarpentersBlocks.carpentersDaylightSensorRenderID, new BlockHandlerCarpentersDaylightSensor());
		}
		
		if (CarpentersBlocks.enableGate) {
			CarpentersBlocks.carpentersGateRenderID = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(CarpentersBlocks.carpentersGateRenderID, new BlockHandlerCarpentersGate());
		}

		if (CarpentersBlocks.enableLever) {
			CarpentersBlocks.carpentersLeverRenderID = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(CarpentersBlocks.carpentersLeverRenderID, new BlockHandlerCarpentersLever());
		}

		if (CarpentersBlocks.enablePressurePlate) {
			CarpentersBlocks.carpentersPressurePlateRenderID = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(CarpentersBlocks.carpentersPressurePlateRenderID, new BlockHandlerCarpentersPressurePlate());
		}
		
		if (CarpentersBlocks.enableSlope) {
			CarpentersBlocks.carpentersSlopeRenderID = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(CarpentersBlocks.carpentersSlopeRenderID, new BlockHandlerCarpentersSlope());
		}
		
		if (CarpentersBlocks.enableStairs) {
			CarpentersBlocks.carpentersStairsRenderID = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(CarpentersBlocks.carpentersStairsRenderID, new BlockHandlerCarpentersStairs());
		}
		
		if (CarpentersBlocks.enableHatch) {
			CarpentersBlocks.carpentersHatchRenderID = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(CarpentersBlocks.carpentersHatchRenderID, new BlockHandlerCarpentersHatch());
		}
		
		if (CarpentersBlocks.enableDoor) {
			CarpentersBlocks.carpentersDoorRenderID = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(CarpentersBlocks.carpentersDoorRenderID, new BlockHandlerCarpentersDoor());
		}
		
		if (CarpentersBlocks.enableBed) {
			CarpentersBlocks.carpentersBedRenderID = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(CarpentersBlocks.carpentersBedRenderID, new BlockHandlerCarpentersBed());
		}
		
		CarpentersBlocks.carpentersBlockRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(CarpentersBlocks.carpentersBlockRenderID, new BlockHandlerCarpentersBlock());

		if (CarpentersBlocks.enableOptifineIntegration && FMLClientHandler.instance().hasOptifine())
			CarpentersBlocks.enableOptifineIntegration = OptifineHandler.init();
		
        ClientRegistry.bindTileEntitySpecialRenderer(TECarpentersBlock.class, new TERendererCarpentersBlock()); 
    }
		
}
