package zcore.events;

import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.AllowDespawn;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import zcore.common.EasyCrop;
import zdoctor.morecrops.MoreCrops;

public class CustomEvents {
	/**
	 * Place all your FML Events here. Remember to annotate with @SubscribeEvent
	 * @author Z_Doctor
	 */
	public static class FML {
		@SubscribeEvent
		public void playerLoggedIn(PlayerLoggedInEvent le) {
			ChatComponentText helloMsg = new ChatComponentText("<MoreCrops-" + MoreCrops.modid +"> Thanks for using MoreCrops! Check for updates ");
			helloMsg.getChatStyle().setColor(EnumChatFormatting.DARK_GREEN);
			ChatComponentText updateLink = new ChatComponentText("here");
			updateLink.getChatStyle().setColor(EnumChatFormatting.DARK_RED);
			updateLink.getChatStyle().setUnderlined(true);
			updateLink.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, 
					"http://adf.ly/1Nn2gv"));
			le.player.addChatComponentMessage(helloMsg.appendSibling(updateLink));
		}
	}
	
	public static class Forge {
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
		@SubscribeEvent
		public void stopSpawn(WorldEvent.PotentialSpawns ps) {
			if(ps.isCancelable() && !ps.isCanceled()) {
				ps.setCanceled(true);
			}
		}
		@SubscribeEvent
		public void despawnAll(AllowDespawn d) {
			d.setResult(Result.ALLOW);
		}
	}
}
