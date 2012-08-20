package de.dustplanet.unlimitedlava;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;
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
 * @thanks to ferrybig for the awesome fall code!
 * @thanks to Xastabus for the cool improvements of the checks!
 *
 */

public class UnlimitedLavaPlayerListener implements Listener {
	private UnlimitedLava plugin;
	private UnlimitedLavaCheck check;
	public UnlimitedLavaPlayerListener(UnlimitedLava instance, UnlimitedLavaCheck instanceCheck) {
		plugin = instance;
		check = instanceCheck;
	}

	@EventHandler
	public void onPlayerBucketFill(PlayerBucketFillEvent event) {
		Block clicked = event.getBlockClicked();
		Player player = event.getPlayer();
		// Only if lava is clicked ;)
		if (plugin.permissions) {
			if (event.getBlockClicked().getTypeId() == 9) {
				return;
			}
			// If the player hasn't got the permissions, cancel the event and give an empty bucket back!
			if (!player.hasPermission("unlimitedlava.use")) {
				if (check.checkLavaSpreadValidity(clicked))
					giveBucketBack(player, event);
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
		// Message if wanted
		if (plugin.messages) {
			String message = plugin.localization.getString("permission_denied");
			plugin.message(null, player, message, null);
		}
	}
}