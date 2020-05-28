package de.dustplanet.unlimitedlava;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings(value = { "FCCD_FIND_CLASS_CIRCULAR_DEPENDENCY", "CD_CIRCULAR_DEPENDENCY", "IMC_IMMATURE_CLASS_NO_TOSTRING" })
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
            if (event.getBlockClicked().getType() == Material.WATER) {
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
