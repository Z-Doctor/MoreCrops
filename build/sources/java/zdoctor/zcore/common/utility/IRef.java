package zdoctor.zcore.common.utility;

import net.minecraft.item.Item;

public interface IRef {
	/** Remeber to add your modid. modid:UnlocalizedName */
	public String getName();

	public Item getItem();

	public int getMeta();
}
