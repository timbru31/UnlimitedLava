package de.xghostkillerx.unlimitedlava;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.inventory.ItemStack;

public class UnlimitedLavaInventoryListener implements Listener {
	public static UnlimitedLava plugin;
	public UnlimitedLavaInventoryListener(UnlimitedLava instance) {
		plugin = instance;
	}

	@EventHandler
	public void onFurnanceBurn (final FurnaceBurnEvent event) {
		if (event.getFuel().getType() == Material.LAVA_BUCKET) {
			final Block furnace = event.getBlock();
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
					// Set the fuel item to the config one
					String configItem = plugin.getConfig().getString("furnace.item");
					Material item = Material.valueOf(configItem.toUpperCase());
					Furnace furn = (Furnace) furnace.getState();
					furn.getInventory().setItem(1,new ItemStack(item, 1));
				}
			});
		}
	}
}
