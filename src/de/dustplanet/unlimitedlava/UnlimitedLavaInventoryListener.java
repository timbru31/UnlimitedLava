package de.dustplanet.unlimitedlava;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.inventory.ItemStack;

/**
 * UnlimitedLavaInventoryListener
 * Handles the furnace burn stuff
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
 * 
 */

public class UnlimitedLavaInventoryListener implements Listener {
	public static UnlimitedLava plugin;
	public UnlimitedLavaInventoryListener(UnlimitedLava instance) {
		plugin = instance;
	}
	private Material item = null;

	@EventHandler
	public void onFurnaceBurn (FurnaceBurnEvent event) {
		if (plugin.config.getBoolean("configuration.furnace") == true) {
			if (event.getFuel().getType() == Material.LAVA_BUCKET) {
				final Block furnace = event.getBlock();
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						// Set the fuel item to the config one
						String configItem = plugin.getConfig().getString("furnace.item");
						try {
							item = Material.valueOf(configItem.toUpperCase());
						}
						catch (Exception e) {
							item = Material.AIR;
						}
						Furnace furn = (Furnace) furnace.getState();
						furn.getInventory().setItem(1,new ItemStack(item, 1));
					}
				});
			}
		}
	}
}
