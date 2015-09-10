package zdoctor.morecrops.events;

import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zdoctor.zcore.common.EasyCrop;

public class ForgeEvents {
	@SubscribeEvent
	public void checkBoneMealUse(BonemealEvent be) {
		if(be.block.getBlock() instanceof EasyCrop){
			EasyCrop plant = (EasyCrop) be.block.getBlock();
			if(!plant.canUseBonemeal())
				if(be.isCancelable() && !be.isCanceled()) {
					be.setCanceled(!plant.canUseBonemeal());
				}
		}
	}
}
