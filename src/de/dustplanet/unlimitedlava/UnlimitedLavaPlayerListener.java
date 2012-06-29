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

	public static UnlimitedLava plugin;
	public UnlimitedLavaPlayerListener(UnlimitedLava instance) {
		plugin = instance;
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
				// Check which cases are valid
				int faces = UnlimitedLavaCheck.checkSpreadValidityFaces(clicked);
				int corners = UnlimitedLavaCheck.checkSpreadValidityCorners(clicked);
				boolean lake = UnlimitedLavaCheck.checkIsInLake(clicked);
				boolean border = UnlimitedLavaCheck.checkIsOnBorder(clicked);
				// Big
				if (plugin.big && faces == 4 &&  corners == 4 && lake && !border)
					giveBucketBack(player, event);
				// Three
				else if (plugin.three && faces == 4 &&  corners == 4 && !lake && !border)
					giveBucketBack(player, event);
				// Two
				else if (plugin.two && faces == 2 &&  corners == 1 && !lake && border)
					giveBucketBack(player, event);
				// Plus
				else if (plugin.plus && faces == 4 &&  corners == 0 && !lake && border)
					giveBucketBack(player, event);
				// Other
				else if (plugin.other && faces == 2 &&  corners == 0 && !lake && border)
					giveBucketBack(player, event);
				// T Shape
				else if (plugin.T && faces == 3 &&  corners == 0 && !lake && border)
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