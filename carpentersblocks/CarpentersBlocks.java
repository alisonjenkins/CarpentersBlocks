package carpentersblocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import net.minecraftforge.oredict.ShapedOreRecipe;
import carpentersblocks.block.BlockCarpentersBarrier;
import carpentersblocks.block.BlockCarpentersBed;
import carpentersblocks.block.BlockCarpentersBlock;
import carpentersblocks.block.BlockCarpentersButton;
import carpentersblocks.block.BlockCarpentersDaylightSensor;
import carpentersblocks.block.BlockCarpentersDoor;
import carpentersblocks.block.BlockCarpentersGate;
import carpentersblocks.block.BlockCarpentersHatch;
import carpentersblocks.block.BlockCarpentersLever;
import carpentersblocks.block.BlockCarpentersPressurePlate;
import carpentersblocks.block.BlockCarpentersSlope;
import carpentersblocks.block.BlockCarpentersStairs;
import carpentersblocks.item.ItemCarpentersBed;
import carpentersblocks.item.ItemCarpentersChisel;
import carpentersblocks.item.ItemCarpentersDoor;
import carpentersblocks.item.ItemCarpentersHammer;
import carpentersblocks.proxy.CommonProxy;
import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.CarpentersBlocksTab;
import carpentersblocks.util.ModLogger;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(
        modid = "CarpentersBlocks",
        name = "Carpenter's Blocks",
        version = "v1.84",
        dependencies = "required-after:Forge@[7.7.2.682,)"
	)
@NetworkMod(
        clientSideRequired = true,
        serverSideRequired = false
	)
public class CarpentersBlocks
{
	
    @Instance("CarpentersBlocks")
    public static CarpentersBlocks instance;
    
    @SidedProxy(clientSide = "carpentersblocks.proxy.ClientProxy", serverSide = "carpentersblocks.proxy.CommonProxy")
    public static CommonProxy proxy;
    
    public static CreativeTabs tabCarpentersBlocks = new CarpentersBlocksTab("carpentersBlocks");

    private String	CATEGORY_CONTROL = "control",
    				CATEGORY_SLOPE = "slope",
    				CATEGORY_FEATURES = "features",
    				CATEGORY_RECIPEQUANTITIES = "recipe quantities",
    				CATEGORY_TOOLS = "tools",
    				CATEGORY_RENDERING = "rendering";
        
    public static Item	itemCarpentersHammer,
						itemCarpentersChisel,
						itemCarpentersDoor,
						itemCarpentersBed;
    
    public static Block	blockCarpentersSlope,
						blockCarpentersStairs,
					    blockCarpentersBarrier,
						blockCarpentersGate,
						blockCarpentersBlock,
						blockCarpentersButton,
						blockCarpentersLever,
						blockCarpentersPressurePlate,
						blockCarpentersDaylightSensor,
						blockCarpentersHatch,
						blockCarpentersDoor,
						blockCarpentersBed;
    
    public static int 	carpentersSlopeRenderID,
						carpentersStairsRenderID,
    					carpentersBarrierRenderID,
    					carpentersGateRenderID,
    					carpentersBlockRenderID,
    					carpentersButtonRenderID,
    					carpentersLeverRenderID,
    					carpentersPressurePlateRenderID,
    					carpentersDaylightSensorRenderID,
    					carpentersHatchRenderID,
    					carpentersDoorRenderID,
    					carpentersBedRenderID;

    public static int 	blockCarpentersSlopeID,
						blockCarpentersStairsID,
					    blockCarpentersBarrierID,
    					blockCarpentersGateID,
    					blockCarpentersBlockID,
    					blockCarpentersButtonID,
    					blockCarpentersLeverID,
    					blockCarpentersPressurePlateID,
    					blockCarpentersDaylightSensorID,
    					blockCarpentersHatchID,
    					blockCarpentersDoorID,
    					blockCarpentersBedID;

    public static int	itemCarpentersHammerID,
    					itemCarpentersChiselID,
    					itemCarpentersDoorID,
    					itemCarpentersBedID;
    
    /*
     * Category: CONTROL
     */
    public static boolean	enableSlope = true,
							enableStairs = true,
    						enableBarrier = true,
    	    				enableGate = true,
    						enableBlock = true,
    						enableButton = true,
    	    				enableLever = true,
    	    	    		enablePressurePlate = true,
    						enableDaylightSensor = true,
    						enableHatch = true,
    						enableDoor = true,
    						enableBed = true;
    
    /*
     * Category: FEATURES
     */
    public static boolean	enableCovers = true,
    						enableOverlays = true,
    						enableSideCovers = true,
    						enableDyeColors = true,
    						enableFancyFluids = true;
    
    /*
     * Category: SLOPE
     */
    public static int hitboxPrecision = 2;
    
    /*
     * Category: RECIPE QUANTITIES
     */
    private int	recipeQuantitySlope = 4,
    			recipeQuantityStairs = 4,
    			recipeQuantityBarrier = 2,
    			recipeQuantityGate = 1,
    			recipeQuantityBlock = 5,
    			recipeQuantityButton = 1,
    			recipeQuantityLever = 1,
    			recipeQuantityPressurePlate = 1,
    			recipeQuantityDaylightSensor = 1,
    			recipeQuantityHatch = 1,
    			recipeQuantityDoor = 1,
    			recipeQuantityBed = 1;

    /*
     * Category: TOOLS
     */
    public static boolean	itemCarpentersToolsDamageable = true,
    	    				enableChisel = true;
    
    /*
     * Category: RENDERING
     */
    public static boolean	enableZFightingFix = false,
    						enableOptifineIntegration = true,
    						enablePlantSupport = true;
    
    private int baseBlockID = 2401,
    			baseItemID = 5401;

    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {    	
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        blockCarpentersSlopeID = config.getBlock("Slope", baseBlockID++).getInt(baseBlockID);
        blockCarpentersStairsID = config.getBlock("Stairs", baseBlockID++).getInt(baseBlockID);
        blockCarpentersBarrierID = config.getBlock("Barrier", baseBlockID++).getInt(baseBlockID);
        blockCarpentersGateID = config.getBlock("Gate", baseBlockID++).getInt(baseBlockID);
        blockCarpentersBlockID = config.getBlock("Block", baseBlockID++).getInt(baseBlockID);
        blockCarpentersButtonID = config.getBlock("Button", baseBlockID++).getInt(baseBlockID);
        blockCarpentersLeverID = config.getBlock("Lever", baseBlockID++).getInt(baseBlockID);
        blockCarpentersPressurePlateID = config.getBlock("PressurePlate", baseBlockID++).getInt(baseBlockID);
        blockCarpentersDaylightSensorID = config.getBlock("DaylightSensor", baseBlockID++).getInt(baseBlockID);
        blockCarpentersHatchID = config.getBlock("Hatch", baseBlockID++).getInt(baseBlockID);
        blockCarpentersDoorID = config.getBlock("Door", baseBlockID++).getInt(baseBlockID);
        blockCarpentersBedID = config.getBlock("Bed", baseBlockID++).getInt(baseBlockID);

        enableSlope = config.get(CATEGORY_CONTROL, "Enable Slope", enableSlope).getBoolean(enableSlope);
        enableStairs = config.get(CATEGORY_CONTROL, "Enable Stairs", enableStairs).getBoolean(enableStairs);
        enableBarrier = config.get(CATEGORY_CONTROL, "Enable Barrier", enableBarrier).getBoolean(enableBarrier);
        enableGate = config.get(CATEGORY_CONTROL, "Enable Gate", enableGate).getBoolean(enableGate);
        enableBlock = config.get(CATEGORY_CONTROL, "Enable Block/Slab", enableBlock).getBoolean(enableBlock);
        enableButton = config.get(CATEGORY_CONTROL, "Enable Button", enableButton).getBoolean(enableButton);
        enableLever = config.get(CATEGORY_CONTROL, "Enable Lever", enableLever).getBoolean(enableLever);
        enablePressurePlate = config.get(CATEGORY_CONTROL, "Enable Pressure Plate", enablePressurePlate).getBoolean(enablePressurePlate);
        enableDaylightSensor = config.get(CATEGORY_CONTROL, "Enable Daylight Sensor", enableDaylightSensor).getBoolean(enableDaylightSensor);
        enableHatch = config.get(CATEGORY_CONTROL, "Enable Hatch", enableHatch).getBoolean(enableHatch);
        enableDoor = config.get(CATEGORY_CONTROL, "Enable Door", enableDoor).getBoolean(enableDoor);
        enableBed = config.get(CATEGORY_CONTROL, "Enable Bed", enableBed).getBoolean(enableBed);
        
        enableCovers = config.get(CATEGORY_FEATURES, "Enable Covers", enableCovers).getBoolean(enableCovers);
        enableOverlays = config.get(CATEGORY_FEATURES, "Enable Overlays", enableOverlays).getBoolean(enableOverlays);
        enableSideCovers = config.get(CATEGORY_FEATURES, "Enable Side Covers", enableSideCovers).getBoolean(enableSideCovers);
        enableDyeColors = config.get(CATEGORY_FEATURES, "Enable Dye Colors", enableDyeColors).getBoolean(enableDyeColors);
        enableFancyFluids = config.get(CATEGORY_FEATURES, "Enable Fancy Fluids", enableFancyFluids).getBoolean(enableFancyFluids);
        
        recipeQuantitySlope = config.get(CATEGORY_RECIPEQUANTITIES, "Slope", recipeQuantitySlope).getInt(recipeQuantitySlope);
        recipeQuantityStairs = config.get(CATEGORY_RECIPEQUANTITIES, "Stairs", recipeQuantityStairs).getInt(recipeQuantityStairs);
        recipeQuantityBarrier = config.get(CATEGORY_RECIPEQUANTITIES, "Barrier", recipeQuantityBarrier).getInt(recipeQuantityBarrier);
        recipeQuantityGate = config.get(CATEGORY_RECIPEQUANTITIES, "Gate", recipeQuantityGate).getInt(recipeQuantityGate);
        recipeQuantityBlock = config.get(CATEGORY_RECIPEQUANTITIES, "Block", recipeQuantityBlock).getInt(recipeQuantityBlock);
        recipeQuantityButton = config.get(CATEGORY_RECIPEQUANTITIES, "Button", recipeQuantityButton).getInt(recipeQuantityButton);
        recipeQuantityLever = config.get(CATEGORY_RECIPEQUANTITIES, "Lever", recipeQuantityLever).getInt(recipeQuantityLever);
        recipeQuantityPressurePlate = config.get(CATEGORY_RECIPEQUANTITIES, "Pressure Plate", recipeQuantityPressurePlate).getInt(recipeQuantityPressurePlate);
        recipeQuantityDaylightSensor = config.get(CATEGORY_RECIPEQUANTITIES, "Daylight Sensor", recipeQuantityDaylightSensor).getInt(recipeQuantityDaylightSensor);
        recipeQuantityHatch = config.get(CATEGORY_RECIPEQUANTITIES, "Hatch", recipeQuantityHatch).getInt(recipeQuantityHatch);
        recipeQuantityDoor = config.get(CATEGORY_RECIPEQUANTITIES, "Door", recipeQuantityDoor).getInt(recipeQuantityDoor);
        recipeQuantityBed = config.get(CATEGORY_RECIPEQUANTITIES, "Bed", recipeQuantityBed).getInt(recipeQuantityBed);
        
        itemCarpentersHammerID = config.getItem("Hammer", baseItemID++).getInt(baseItemID);
        itemCarpentersChiselID = config.getItem("Chisel", baseItemID++).getInt(baseItemID);
        itemCarpentersDoorID = config.getItem("Door", baseItemID++).getInt(baseItemID);
        itemCarpentersBedID = config.getItem("Bed", baseItemID++).getInt(baseItemID);
                
        itemCarpentersToolsDamageable = config.get(CATEGORY_TOOLS, "Tools Damageable", itemCarpentersToolsDamageable).getBoolean(itemCarpentersToolsDamageable);
        enableChisel = config.get(CATEGORY_TOOLS, "Enable Chisel", enableChisel).getBoolean(enableChisel);
        
        Property enableZFightingFixProp = config.get(CATEGORY_RENDERING, "enableZFightingFix", enableZFightingFix);
        enableZFightingFixProp.comment = "Setting this to true will resolve z-fighting with chiseled patterns\nthat may occur with Optifine or other client-side performance mods.\nWill cause all Carpenter's Blocks to be invisible behind ice or water.";
        enableZFightingFix = enableZFightingFixProp.getBoolean(enableZFightingFix);
        
        Property enableOptifineIntegrationProp = config.get(CATEGORY_RENDERING, "enableOptifineIntegration", enableOptifineIntegration);
        enableOptifineIntegrationProp.comment = "Provides integration with Optifine's block coloring methods.\nNeeded to support Custom Colors.";
        enableOptifineIntegration = enableOptifineIntegrationProp.getBoolean(enableOptifineIntegration);
        
        Property hitboxPrecisionProp = config.get(CATEGORY_SLOPE, "hitboxPrecision", hitboxPrecision);
        hitboxPrecisionProp.comment = "This controls the smoothness of the slope faces (excluding oblique interior corners).\nSmoothness of 2 is similar to stairs.\nA value of 50 is recommended for fluidity, but higher values under certain configurations will create collision bugs.";
        hitboxPrecision = hitboxPrecisionProp.getInt(hitboxPrecision);
        
        config.save();

        ModLogger.init();
        proxy.registerHandlers(event);
    	proxy.registerRenderInformation(event);
    }

    @Init
    public void init(FMLInitializationEvent event)
    {
    	if (enableBarrier) {
    		blockCarpentersBarrier = (new BlockCarpentersBarrier(blockCarpentersBarrierID));
    		GameRegistry.registerBlock(blockCarpentersBarrier, "blockCarpentersBarrier");
    		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockCarpentersBarrier, recipeQuantityBarrier), "X X", "XXX", 'X', "stickWood"));
    	}
    	
    	if (enableBlock) {
    		blockCarpentersBlock = (new BlockCarpentersBlock(blockCarpentersBlockID));
    		GameRegistry.registerBlock(blockCarpentersBlock, "blockCarpentersBlock");
    		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockCarpentersBlock, recipeQuantityBlock), "XXX", "XYX", "XXX", 'X', "stickWood", 'Y', "plankWood"));
    	}
    	
    	if (enableButton) {
    		blockCarpentersButton = (new BlockCarpentersButton(blockCarpentersButtonID));
    		GameRegistry.registerBlock(blockCarpentersButton, "blockCarpentersButton");
    		GameRegistry.addRecipe(new ItemStack(blockCarpentersButton, recipeQuantityButton), new Object[] {"X", 'X', blockCarpentersBlock});
    	}
    	
    	if (enableDaylightSensor) {
    		blockCarpentersDaylightSensor = (new BlockCarpentersDaylightSensor(blockCarpentersDaylightSensorID));
    		GameRegistry.registerBlock(blockCarpentersDaylightSensor, "blockCarpentersDaylightSensor");
    		GameRegistry.addRecipe(new ItemStack(blockCarpentersDaylightSensor, recipeQuantityDaylightSensor), new Object[] {"XXX", "YYY", "ZZZ", 'X', Block.glass, 'Y', Item.netherQuartz, 'Z', blockCarpentersBlock});
    	}
    	
    	if (enableGate) {
    		blockCarpentersGate = (new BlockCarpentersGate(blockCarpentersGateID));
    		GameRegistry.registerBlock(blockCarpentersGate, "blockCarpentersGate");
    		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockCarpentersGate, recipeQuantityGate), "XYX", "XYX", 'X', "stickWood", 'Y', blockCarpentersBlock));
    	}

    	if (enableLever) {
    		blockCarpentersLever = (new BlockCarpentersLever(blockCarpentersLeverID));
    		GameRegistry.registerBlock(blockCarpentersLever, "blockCarpentersLever");
    		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockCarpentersLever, recipeQuantityLever), "X", "Y", 'X', "stickWood", 'Y', blockCarpentersBlock));
    	}

    	if (enablePressurePlate) {
    		blockCarpentersPressurePlate = (new BlockCarpentersPressurePlate(blockCarpentersPressurePlateID));
    		GameRegistry.registerBlock(blockCarpentersPressurePlate, "blockCarpentersPressurePlate");
    		GameRegistry.addRecipe(new ItemStack(blockCarpentersPressurePlate, recipeQuantityPressurePlate), new Object[] {"XX", 'X', blockCarpentersBlock});
    	}
    	
    	if (enableSlope) {
    		blockCarpentersSlope = (new BlockCarpentersSlope(blockCarpentersSlopeID));
    		GameRegistry.registerBlock(blockCarpentersSlope, "blockCarpentersSlope");
    		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockCarpentersSlope, recipeQuantitySlope), "  X", " XY", "XYY", 'X', "stickWood", 'Y', "plankWood"));
    	}

    	if (enableStairs) {
    		blockCarpentersStairs = (new BlockCarpentersStairs(blockCarpentersStairsID));
    		GameRegistry.registerBlock(blockCarpentersStairs, "blockCarpentersStairs");
    		GameRegistry.addRecipe(new ItemStack(blockCarpentersStairs, recipeQuantityStairs), new Object[] {"  X", " XX", "XXX", 'X', blockCarpentersBlock});
    	}
    	
    	if (enableHatch) {
    		blockCarpentersHatch = (new BlockCarpentersHatch(blockCarpentersHatchID));
    		GameRegistry.registerBlock(blockCarpentersHatch, "blockCarpentersHatch");
    		GameRegistry.addRecipe(new ItemStack(blockCarpentersHatch, recipeQuantityHatch), new Object[] {"XXX", "XXX", 'X', blockCarpentersBlock});
    	}
    	
    	if (enableDoor) {
        	itemCarpentersDoor = new ItemCarpentersDoor(itemCarpentersDoorID - 256);
    		blockCarpentersDoor = (new BlockCarpentersDoor(blockCarpentersDoorID));
    		GameRegistry.registerItem(itemCarpentersDoor, "itemCarpentersDoor");
    		GameRegistry.registerBlock(blockCarpentersDoor, "blockCarpentersDoor");
        	GameRegistry.addRecipe(new ItemStack(itemCarpentersDoor, recipeQuantityDoor), new Object[] {"XX", "XX", "XX", 'X', blockCarpentersBlock});
    	}
    	
    	if (enableBed) {
        	itemCarpentersBed = new ItemCarpentersBed(itemCarpentersBedID - 256);
    		blockCarpentersBed = (new BlockCarpentersBed(blockCarpentersBedID));
    		GameRegistry.registerItem(itemCarpentersBed, "itemCarpentersBed");
    		GameRegistry.registerBlock(blockCarpentersBed, "blockCarpentersBed");
        	GameRegistry.addRecipe(new ItemStack(itemCarpentersBed, recipeQuantityBed), new Object[] {"XXX", "YYY", 'X', Block.cloth, 'Y', blockCarpentersBlock});
    	}
    	
    	if (enableChisel) {
        	itemCarpentersChisel = new ItemCarpentersChisel(itemCarpentersChiselID - 256);
    		GameRegistry.registerItem(itemCarpentersChisel, "itemCarpentersChisel");
        	GameRegistry.addRecipe(new ItemStack(itemCarpentersChisel, 1), new Object[] { "X", "Y", 'X', Item.ingotIron, 'Y', blockCarpentersBlock });
    	}

    	// String ID reflects old class name for compatibility with versions prior to v1.6
    	GameRegistry.registerTileEntity(TECarpentersBlock.class, "TileEntityCarpentersSlope");

    	itemCarpentersHammer = new ItemCarpentersHammer(itemCarpentersHammerID - 256);
		GameRegistry.registerItem(itemCarpentersHammer, "itemCarpentersHammer");
    	GameRegistry.addRecipe(new ItemStack(itemCarpentersHammer, 1), new Object[] { "XX ", " YX", " Y ", 'X', Item.ingotIron, 'Y', blockCarpentersBlock });
    }
    
}
