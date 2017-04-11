package de.dustplanet.unlimitedlava;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class UnlimitedLavaBlockListener implements Listener {
    private UnlimitedLava plugin;
    private UnlimitedLavaCheck check;

    public UnlimitedLavaBlockListener(UnlimitedLava instance, UnlimitedLavaCheck instanceCheck) {
        plugin = instance;
        check = instanceCheck;
    }

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        // Refer to http://minecraft.gamepedia.com/Data_values#Water_and_Lava
        Block sourceBlock = event.getBlock();
        Block targetBlock = event.getToBlock();

        if (!plugin.getEnabledWorlds().contains(sourceBlock.getWorld().getUID())) {
            return;
        }

        if (sourceBlock.getY() <= plugin.getHeight()) {
            return;
        }

        if (plugin.isLavaFall() && sourceBlock.getY() > 0 && event.getFace() == BlockFace.DOWN) {
            if (event.getToBlock().getRelative(BlockFace.UP).getType() == Material.LAVA
                    || event.getToBlock().getRelative(BlockFace.UP).getType() == Material.STATIONARY_LAVA) {
                if (event.getToBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
                    event.getToBlock().setTypeIdAndData(Material.LAVA.getId(), (byte) 0x0, true);
                    event.setCancelled(true);
                }
            }
        }

        if (plugin.isWaterFall() && sourceBlock.getY() > 0 && event.getFace() == BlockFace.DOWN) {
            if (event.getToBlock().getRelative(BlockFace.UP).getType() == Material.WATER
                    || event.getToBlock().getRelative(BlockFace.UP).getType() == Material.STATIONARY_WATER) {
                if (event.getToBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
                    event.getToBlock().setTypeIdAndData(Material.WATER.getId(), (byte) 0x0, true);
                    event.setCancelled(true);
                }
            }
        }

        if (sourceBlock.getData() != 0x0) {
            return;
        }

        if (sourceBlock.getType() == Material.LAVA || sourceBlock.getType() == Material.STATIONARY_LAVA) {
            if (targetBlock.getType() == Material.LAVA || targetBlock.getType() == Material.STATIONARY_LAVA) {
                // Full block (0x0) and not falling (0x8)
                if (targetBlock.getData() != 0x0 && targetBlock.getData() != 0x8) {
                    if (check.checkLavaSpreadValidity(targetBlock)) {
                        targetBlock.setTypeIdAndData(Material.LAVA.getId(), (byte) 0x0, true);
                    }
                }
            }
        }
    }
}
