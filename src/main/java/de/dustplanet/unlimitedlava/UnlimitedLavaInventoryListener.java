package de.dustplanet.unlimitedlava;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.inventory.ItemStack;

public class UnlimitedLavaInventoryListener implements Listener {
    private UnlimitedLava plugin;

    public UnlimitedLavaInventoryListener(UnlimitedLava instance) {
        plugin = instance;
    }

    @EventHandler
    public void onFurnaceBurn(FurnaceBurnEvent event) {
        if (plugin.isFurnace()) {
            if (event.getFuel().getType() == Material.LAVA_BUCKET) {
                final Block furnace = event.getBlock();
                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, setFurnaceItem(furnace));
            }
        }
    }

    private Runnable setFurnaceItem(final Block furnace) {
        return () -> {
            String configItem = plugin.getConfig().getString("furnace.item");
            Material item = Material.matchMaterial(configItem);
            if (item == null) {
                item = Material.AIR;
            }
            Furnace furn = (Furnace) furnace.getState();
            furn.getInventory().setItem(1, new ItemStack(item, 1));
        };
    }
}
