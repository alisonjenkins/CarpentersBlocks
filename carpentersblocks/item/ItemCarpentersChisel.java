package carpentersblocks.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import carpentersblocks.CarpentersBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCarpentersChisel extends Item
{

	public ItemCarpentersChisel(int itemID)
	{
		super(itemID);
		this.setUnlocalizedName("itemCarpentersChisel");
		
		if (CarpentersBlocks.itemCarpentersToolsDamageable) {
        	this.setMaxDamage(300);
        	this.canRepair = true;
		}
		
		this.setCreativeTab(CarpentersBlocks.tabCarpentersBlocks);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon("carpentersblocks:chisel");
	}
	
}
