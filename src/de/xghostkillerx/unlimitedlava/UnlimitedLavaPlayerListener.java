package de.xghostkillerx.unlimitedlava;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

/**
 * UnlimitedLavaPlayerListener
 * Handles the players activities!
 *
 * Refer to the forum thread:
 * http://bit.ly/n1Wex2
 * Refer to the dev.bukkit.org page:
 * http://bit.ly/pCj7v3
 *
 * @author xGhOsTkiLLeRx
 * @thanks to loganwm for the help!!
 * @thanks to Edward Hand for the idea and original InfiniteLava plugin!
 *
 */

public class UnlimitedLavaPlayerListener extends PlayerListener {

	public static UnlimitedLava plugin;
	public UnlimitedLavaPlayerListener(UnlimitedLava instance) {
		plugin = instance;
	}

	public void onPlayerBucketFill(PlayerBucketFillEvent event) {
		Block clicked = event.getBlockClicked();
		Player player = event.getPlayer();
		// Only if lava is clicked ;)
		if (plugin.config.getBoolean("configuration.permissions") == true) {
			if (event.getBlockClicked().getTypeId() == 9) {
				return;
			}
			// If the player hasn't got the permissions, cancel the event and give an empty bucket back!
			if (!player.hasPermission("unlimitedlava.use")) {
				// 2x2 source
				if (plugin.config.getBoolean("sources.two") == true) {
					if (UnlimitedLavaCheck.checkSpreadValidityTwo(clicked)) {
						giveBucketBack(player, event);
						message(player);
					}
				}
				// 3x3 source
				if (plugin.config.getBoolean("sources.three") == true) {
					if (UnlimitedLavaCheck.checkSpreadValidityThree(clicked)) {
						giveBucketBack(player, event);
						message(player);
					}
				}
				// Other source
				if (plugin.config.getBoolean("sources.other") == true) {
					if (UnlimitedLavaCheck.checkSpreadValidityOther(clicked)) {
						giveBucketBack(player, event);
						message(player);
					}
				}
				// Big source
				if (plugin.config.getBoolean("sources.big") == true) {
					if (UnlimitedLavaCheck.checkSpreadValidityBig(clicked)) {
						giveBucketBack(player, event);
						message(player);
					}
				}
			}
		}
	}
	
	// Gives a bucket back
	@SuppressWarnings("deprecation")
	public void giveBucketBack(Player player, PlayerBucketFillEvent event) {
		event.setCancelled(true);
		ItemStack bucket = new ItemStack(325, 1);
		player.setItemInHand(bucket);
		player.updateInventory();
	}
	
	// Sends a message to the player
	public void message(Player player) {
		if (plugin.config.getBoolean("configuration.messages") == true) {
			player.sendMessage(ChatColor.DARK_RED + "You don't have the permission to use the UnlimitedLava!");
		}
	}
}