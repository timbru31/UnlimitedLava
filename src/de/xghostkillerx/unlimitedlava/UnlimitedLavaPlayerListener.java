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
		if (plugin.permissions == true) {
			if (event.getBlockClicked().getTypeId() == 9) {
				return;
			}
			// If the player hasn't got the permissions, cancel the event and give a empty bucket!
			if (!player.hasPermission("unlimitedlava.use")) {
				if (plugin.two == true) {
					if (UnlimitedLavaSpreadCheck.checkSpreadValidityTwo(clicked)) {
						if (plugin.messages == true) {
							player.sendMessage(ChatColor.DARK_RED + "You don't have the permission to use the UnlimitedLava!");
						}
						event.setCancelled(true);
						ItemStack bucket = new ItemStack(325, 1);
						player.setItemInHand(bucket);
						player.updateInventory();

					}
				}
				if (plugin.three == true) {
					if (UnlimitedLavaSpreadCheck.checkSpreadValidityThree(clicked)) {
						if (plugin.messages == true) {
							player.sendMessage(ChatColor.DARK_RED + "You don't have the permission to use the UnlimitedLava!");
						}
						event.setCancelled(true);
						ItemStack bucket = new ItemStack(325, 1);
						player.setItemInHand(bucket);
						player.updateInventory();
					}
				}
				if (plugin.other == true) {
					if (UnlimitedLavaSpreadCheck.checkSpreadValidityOther(clicked)) {
						if (plugin.messages == true) {
							player.sendMessage(ChatColor.DARK_RED + "You don't have the permission to use the UnlimitedLava!");
						}
						event.setCancelled(true);
						ItemStack bucket = new ItemStack(325, 1);
						player.setItemInHand(bucket);
						player.updateInventory();
					}
				}
				if (plugin.big == true) {
					if (UnlimitedLavaSpreadCheck.checkSpreadValidityBig(clicked)) {
						if (plugin.messages == true) {
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

