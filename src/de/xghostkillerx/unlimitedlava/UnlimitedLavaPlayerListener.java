package de.xghostkillerx.unlimitedlava;

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
		Player player = event.getPlayer();
		if (plugin.permissions == true) {
			if (event.getBlockClicked().getTypeId() == 9) {
				return;
			}
			if (!player.hasPermission("unlimitedlava.use")) {
				if ((plugin.three == true) || (plugin.two == true) || (plugin.other == true) || (plugin.big == true)) {
					event.setCancelled(true);
					//player.setItemInHand(new ItemStack(325));
					//ItemStack bucket = player.getItemInHand();
					//player.setItemInHand(bucket);
					//player.getItemInHand().setAmount(1);
					event.getItemStack();
					ItemStack bucket = new ItemStack(325, 1);
					event.setItemStack (bucket);
					player.sendMessage("Debug, test message!");
				}
			}
		}
	}
}

