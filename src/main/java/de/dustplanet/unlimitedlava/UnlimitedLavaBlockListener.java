package de.dustplanet.unlimitedlava;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Levelled;
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
            if (targetBlock.getRelative(BlockFace.UP).getType() == Material.LAVA) {
                if (targetBlock.getRelative(BlockFace.DOWN).getType() != Material.AIR) {
                    Levelled targetLevel = (Levelled) targetBlock.getBlockData();
                    targetLevel.setLevel(0);
                    event.setCancelled(true);
                }
            }
        }

        if (plugin.isWaterFall() && sourceBlock.getY() > 0 && event.getFace() == BlockFace.DOWN) {
            if (targetBlock.getRelative(BlockFace.UP).getType() == Material.WATER) {
                if (targetBlock.getRelative(BlockFace.DOWN).getType() != Material.AIR) {
                    Levelled targetLevel = (Levelled) targetBlock.getBlockData();
                    targetLevel.setLevel(0);
                    event.setCancelled(true);
                }
            }
        }

        if (!(sourceBlock.getBlockData() instanceof Levelled) || ((Levelled) sourceBlock.getBlockData()).getLevel() != 0) {
            return;
        }

        if (sourceBlock.getType() == Material.LAVA && targetBlock.getType() == Material.LAVA) {
            Levelled targetLevel = (Levelled) targetBlock.getBlockData();
            // Full block (0x0) and not falling (0x8)
            if (targetLevel.getLevel() != 0 && targetLevel.getLevel() != 8) {
                if (check.checkLavaSpreadValidity(targetBlock)) {
                    targetLevel.setLevel(0);
                }
            }
        }

    }
}
