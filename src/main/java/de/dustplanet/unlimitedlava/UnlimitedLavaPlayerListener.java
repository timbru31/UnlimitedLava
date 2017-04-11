package de.dustplanet.unlimitedlava;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;

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
        if (plugin.isPermissions()) {
            if (event.getBlockClicked().getType() == Material.WATER
                    || event.getBlockClicked().getType() == Material.STATIONARY_WATER) {
                return;
            }
            if (!player.hasPermission("unlimitedlava.use") && check.checkLavaSpreadValidity(clicked)) {
                event.setCancelled(true);
                if (plugin.isMessages()) {
                    String message = plugin.getLocalization().getString("permission_denied");
                    plugin.message(null, player, message, null);
                }
            }
        }
    }
}
