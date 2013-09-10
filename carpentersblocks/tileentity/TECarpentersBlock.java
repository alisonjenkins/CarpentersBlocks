package carpentersblocks.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import carpentersblocks.CarpentersBlocks;
import carpentersblocks.block.BlockCarpentersDaylightSensor;

public class TECarpentersBlock extends TileEntity
{

	public short[] cover = new short[7];
	public byte[] pattern = new byte[7];
	public byte[] color = new byte[7];
	public byte[] overlay = new byte[7];
	
	/**
	 * Holds information like direction, block type, etc.
	 */
	public short data = 0;

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		if (!nbt.hasKey("color")) {
			CompatibilityHelper.convertData(this, nbt);
		} else {
			for (int count = 0; count < 7; ++count)
				cover[count] = nbt.getShort("cover_" + count);

			pattern = nbt.getByteArray("pattern");
			color = nbt.getByteArray("color");
			overlay = nbt.getByteArray("overlay");
			data = nbt.getShort("data");
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

		for (int count = 0; count < 7; ++count)
			nbt.setShort("cover_" + count, cover[count]);
				
		nbt.setByteArray("pattern", pattern);
		nbt.setByteArray("color", color);
		nbt.setByteArray("overlay", overlay);
		nbt.setShort("data", data);
	}

    @Override
    /**
     * Overridden in a sign to provide the text.
     */
	public Packet getDescriptionPacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
    }

    @Override
    /**
     * Called when you receive a TileEntityData packet for the location this
     * TileEntity is currently in. On the client, the NetworkManager will always
     * be the remote server. On the server, it will be whomever is responsible for
     * sending the packet.
     *
     * @param net The NetworkManager the packet originated from
     * @param pkt The data packet
     */
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
    	readFromNBT(pkt.customParam1);

		if (this.worldObj.isRemote)
		{
			Minecraft.getMinecraft().renderGlobal.markBlockForRenderUpdate(this.xCoord, this.yCoord, this.zCoord);
			this.worldObj.updateAllLightTypes(this.xCoord, this.yCoord, this.zCoord);
		}
    }
    
    @Override
    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
	public void updateEntity()
    {
    	if (!this.worldObj.isRemote)
	    	if (this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord) == CarpentersBlocks.blockCarpentersDaylightSensorID)	
		    	if (this.worldObj.getTotalWorldTime() % 20L == 0L)
		    		((BlockCarpentersDaylightSensor)this.getBlockType()).updateLightLevel(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }
    
}