package zdoctor.zcore.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import zdoctor.zcore.common.reference.References;
import zdoctor.zcore.common.utility.IRef;

public class RenderGame {
	private static RenderItem renderItem;
	private static List<IRef> renderList = new ArrayList<IRef>();

	public RenderGame(FMLInitializationEvent event) {
		if (event.getSide() == Side.CLIENT) {
			renderItem = Minecraft.getMinecraft().getRenderItem();
			render();
		}
	}
	
	public static void render(Item itemIn, int meta, String file) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemIn, meta, new ModelResourceLocation(file, "inventory"));
	}
	
	private static void render() {
		Iterator<IRef> toRender = renderList.iterator();

		while (toRender.hasNext()) {
			IRef temp = toRender.next();
			renderItem.getItemModelMesher().register(temp.getItem(), temp.getMeta(), new ModelResourceLocation(temp.getName(), "inventory"));
		}
	}

	public static synchronized void register(IRef item) {
		if (item != null) {
			renderList.add(item);
		}
	}
}
