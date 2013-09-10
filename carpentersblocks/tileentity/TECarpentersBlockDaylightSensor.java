package carpentersblocks.tileentity;

import carpentersblocks.CarpentersBlocks;
import carpentersblocks.block.BlockCarpentersDaylightSensor;

public class TECarpentersBlockDaylightSensor extends TECarpentersBlock {
	byte counter = 0;

	@Override
	/**
	 * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
	 * ticks and creates a new spawn inside its implementation.
	 */
	public void updateEntity() {
		if (!this.worldObj.isRemote) {
			counter++;

			if (counter >= 20) {
				((BlockCarpentersDaylightSensor) this.getBlockType()).updateLightLevel(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
				counter = 0;
			}
		}
	}
}
