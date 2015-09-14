package zdoctor.morecrops.zcore.events;

import java.util.Random;

import net.minecraft.event.ClickEvent;
import net.minecraft.init.Items;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.AllowDespawn;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import zdoctor.morecrops.MoreCrops;
import zdoctor.morecrops.achievements.classes.AchievementSeeds;
import zdoctor.morecrops.zcore.common.EasyCrop;
import zdoctor.morecrops.zcore.config.Config;
import zdoctor.morecrops.zcore.gameregistry.ZItems;

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
		@SubscribeEvent
		public void checkAchievement(ItemPickupEvent e) {
			System.out.println(e.player.getDisplayNameString() + " picked up " + e.pickedUp.getEntityItem().getItem().getUnlocalizedName());
			if(e.pickedUp.getEntityItem().getItem() == Items.skull && e.pickedUp.getEntityItem().getItemDamage() == 1)
				e.player.addStat(AchievementSeeds.GetWitherSkull, 1);
			if(e.pickedUp.getEntityItem().getItem() == Items.nether_star)
				e.player.addStat(AchievementSeeds.GetNetherStar, 1);
			if(e.pickedUp.getEntityItem().getItem() == ZItems.Strawberry)
				e.player.addStat(AchievementSeeds.GetStrawberry, 1);
		}
		@SubscribeEvent
		public void checkAchievement(ItemCraftedEvent e) {
			if(e.crafting.getItem() == ZItems.DiamondSeed)
				e.player.addStat(AchievementSeeds.CraftDiamondSeed, 1);
			if(e.crafting.getItem() == ZItems.EmeraldSeed)
				e.player.addStat(AchievementSeeds.CraftEmeraldSeed, 1);
			if(e.crafting.getItem() == ZItems.GoldSeed)
				e.player.addStat(AchievementSeeds.CraftGoldSeed, 1);
			if(e.crafting.getItem() == ZItems.IronSeed)
				e.player.addStat(AchievementSeeds.CraftIronSeed, 1);
		}
	}
	
	public static class Forge {
		@SubscribeEvent
		public void checkBoneMealUse(BonemealEvent be) {
			if(be.block.getBlock() instanceof EasyCrop) {
				EasyCrop plant = (EasyCrop) be.block.getBlock();
				if(!plant.canUseBonemeal(be.world, null, be.pos, be.block)) {
					if(be.isCancelable() && !be.isCanceled()) {
						be.setCanceled(true);
					}
				}
			}
		}
	}
}
