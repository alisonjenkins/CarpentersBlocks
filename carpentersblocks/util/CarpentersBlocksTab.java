package carpentersblocks.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import carpentersblocks.CarpentersBlocks;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CarpentersBlocksTab extends CreativeTabs
{

	public CarpentersBlocksTab(String label)
	{
		super(label);
	}

    @Override
	@SideOnly(Side.CLIENT)
    public Item getTabIconItem()
    {
        return CarpentersBlocks.itemCarpentersHammer;
    }

	@Override
	public String getTranslatedTabLabel()
	{
		return LanguageRegistry.instance().getStringLocalization("itemGroup.carpentersBlocks.name");
	}

}
