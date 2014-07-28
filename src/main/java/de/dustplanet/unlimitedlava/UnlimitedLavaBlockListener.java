package de.dustplanet.unlimitedlava;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

/**
 * UnlimitedLavaBlockListener
 * Handles the infinite sources!
 *
 * Refer to the dev.bukkit.org page:
 * http://dev.bukkit.org/bukkit-plugins/unlimited-lava/
 *
 * @author xGhOsTkiLLeRx
 * thanks to loganwm for the help!!
 * thanks to Edward Hand for the idea and original InfiniteLava plugin!
 * thanks to ferrybig for the awesome fall code!
 * thanks to Xastabus for the cool improvements of the checks!
 *
 */

public class UnlimitedLavaBlockListener implements Listener {
    private UnlimitedLava plugin;
    private UnlimitedLavaCheck check;

    public UnlimitedLavaBlockListener(UnlimitedLava instance, UnlimitedLavaCheck instanceCheck) {
        plugin = instance;
        check = instanceCheck;
    }

    // Unlimited sources
    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        // Refer to http://www.minecraftwiki.net/wiki/Data_values#Water_and_Lava
        Block sourceBlock = event.getBlock();
        Block targetBlock = event.getToBlock();

        // Is the world on the list?
        if (!plugin.enabledWorlds.contains(sourceBlock.getWorld().getName())) {
            return;
        }

        // Only if the height is greater or the same
        if (sourceBlock.getY() <= plugin.height) {
            return;
        }

        // Lava fall
        if (plugin.lavaFall) {
            // Security check with 0
            if (sourceBlock.getY() > 0) {
                if (event.getFace() == BlockFace.DOWN) {
                    if (event.getToBlock().getRelative(BlockFace.UP).getType() == Material.LAVA
                            || event.getToBlock().getRelative(BlockFace.UP).getType() == Material.STATIONARY_LAVA) {
                        if (event.getToBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
                            event.getToBlock().setTypeIdAndData(Material.LAVA.getId(), (byte) 0x0, true);
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }

        // Water fall
        if (plugin.waterFall) {
            // Security check with 0
            if (sourceBlock.getY() > 0) {
                if (event.getFace() == BlockFace.DOWN) {
                    if (event.getToBlock().getRelative(BlockFace.UP).getType() == Material.WATER
                            || event.getToBlock().getRelative(BlockFace.UP).getType() == Material.STATIONARY_WATER) {
                        if (event.getToBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
                            event.getToBlock().setTypeIdAndData(Material.WATER.getId(), (byte) 0x0, true);
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }

        // Check if we got a full block of lava
        if (sourceBlock.getData() != 0x0) {
            return;
        }

        if (sourceBlock.getType() == Material.LAVA || sourceBlock.getType() == Material.STATIONARY_LAVA) {
            // Check if we can use the surrounded check
            if (targetBlock.getType() == Material.LAVA || targetBlock.getType() == Material.STATIONARY_LAVA) {
                // Full block (0x0) and not falling (0x8)
                if (targetBlock.getData() != 0x0 && targetBlock.getData() != 0x8) {
                    // Call the "SpreadCheck" if valid refill the block
                    if (check.checkLavaSpreadValidity(targetBlock))
                        targetBlock.setTypeIdAndData(Material.LAVA.getId(), (byte) 0x0, true);
                }
            }
        }
    }
}
