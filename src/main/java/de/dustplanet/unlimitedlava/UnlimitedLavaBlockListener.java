package de.dustplanet.unlimitedlava;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Levelled;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class UnlimitedLavaBlockListener implements Listener {
    UnlimitedLava plugin;
    UnlimitedLavaCheck check;

    public UnlimitedLavaBlockListener(UnlimitedLava instance, UnlimitedLavaCheck instanceCheck) {
        plugin = instance;
        check = instanceCheck;
    }

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        // Refer to https://minecraft.gamepedia.com/Block_states#Lava
        Block sourceBlock = event.getBlock();

        if (!plugin.getEnabledWorlds().contains(sourceBlock.getWorld().getUID())) {
            return;
        }

        if (sourceBlock.getY() <= plugin.getHeight()) {
            return;
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                Block targetBlock = event.getToBlock();

                if (plugin.isLavaFall() && sourceBlock.getY() > 0 && event.getFace() == BlockFace.DOWN) {
                    if (targetBlock.getRelative(BlockFace.UP).getType() == Material.LAVA) {
                        if (targetBlock.getRelative(BlockFace.DOWN).getType() != Material.AIR) {
                            Levelled targetLevel = (Levelled) targetBlock.getBlockData();
                            targetLevel.setLevel(0);
                            targetBlock.setBlockData(targetLevel);
                            event.setCancelled(true);
                        }
                    }
                }

                if (plugin.isWaterFall() && sourceBlock.getY() > 0 && event.getFace() == BlockFace.DOWN) {
                    if (targetBlock.getRelative(BlockFace.UP).getType() == Material.WATER) {
                        if (targetBlock.getRelative(BlockFace.DOWN).getType() != Material.AIR) {
                            Levelled targetLevel = (Levelled) targetBlock.getBlockData();
                            targetLevel.setLevel(0);
                            targetBlock.setBlockData(targetLevel);
                            event.setCancelled(true);
                        }
                    }
                }

                if (!(sourceBlock.getBlockData() instanceof Levelled) || ((Levelled) sourceBlock.getBlockData()).getLevel() != 0) {
                    return;
                }

                if (sourceBlock.getType() == Material.LAVA) {
                    if (targetBlock.getType() == Material.LAVA) {
                        Levelled targetLevel = (Levelled) targetBlock.getBlockData();
                        // Full block (0x0) and not falling (>= 0x8)
                        if (targetLevel.getLevel() != 0 && targetLevel.getLevel() < 8) {
                            if (check.checkLavaSpreadValidity(targetBlock)) {
                                targetLevel.setLevel(0);
                                targetBlock.setBlockData(targetLevel);
                            }
                        }
                    }
                }
            }
        }.runTaskLater(plugin, 1);
    }
}
