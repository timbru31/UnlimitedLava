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

	@SuppressWarnings("deprecation")
	public void onPlayerBucketFill(PlayerBucketFillEvent event) {
		Block clicked = event.getBlockClicked();
		Player player = event.getPlayer();
		// Only if lava is clicked ;)
		if (plugin.config.getBoolean("configuration.permissions", true)) {
			if (event.getBlockClicked().getTypeId() == 9) {
				return;
			}
			// If the player hasn't got the permissions, cancel the event and give a empty bucket!
			if (!player.hasPermission("unlimitedlava.use")) {
				if (plugin.config.getBoolean("sources.two", true)) {
					if (UnlimitedLavaCheck.checkSpreadValidityTwo(clicked)) {
						if (plugin.config.getBoolean("configuration.messages", true)) {
							player.sendMessage(ChatColor.DARK_RED + "You don't have the permission to use the UnlimitedLava!");
						}
						event.setCancelled(true);
						ItemStack bucket = new ItemStack(325, 1);
						player.setItemInHand(bucket);
						player.updateInventory();

					}
				}
				if (plugin.config.getBoolean("sources.three", true)) {
					if (UnlimitedLavaCheck.checkSpreadValidityThree(clicked)) {
						if (plugin.config.getBoolean("configuration.messages", true)) {
							player.sendMessage(ChatColor.DARK_RED + "You don't have the permission to use the UnlimitedLava!");
						}
						event.setCancelled(true);
						ItemStack bucket = new ItemStack(325, 1);
						player.setItemInHand(bucket);
						player.updateInventory();
					}
				}
				if (plugin.config.getBoolean("sources.other", true)) {
					if (UnlimitedLavaCheck.checkSpreadValidityOther(clicked)) {
						if (plugin.config.getBoolean("configuration.messages", true)) {
							player.sendMessage(ChatColor.DARK_RED + "You don't have the permission to use the UnlimitedLava!");
						}
						event.setCancelled(true);
						ItemStack bucket = new ItemStack(325, 1);
						player.setItemInHand(bucket);
						player.updateInventory();
					}
				}
				if (plugin.config.getBoolean("sources.big", true)) {
					if (UnlimitedLavaCheck.checkSpreadValidityBig(clicked)) {
						if (plugin.config.getBoolean("configuration.messages", true)) {
							player.sendMessage(ChatColor.DARK_RED + "You don't have the permission to use the UnlimitedLava!");
						}
						event.setCancelled(true);
						ItemStack bucket = new ItemStack(325, 1);
						player.setItemInHand(bucket);
						player.updateInventory();
					}
				}
			}
		}
	}
}