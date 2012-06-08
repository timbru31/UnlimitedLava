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

public class UnlimitedLavaBlockListener implements Listener {

	public static UnlimitedLava plugin;
	public UnlimitedLavaBlockListener(UnlimitedLava instance) {
		plugin = instance;
	}

	// Unlimited sources
	@EventHandler
	public void onBlockFromTo(BlockFromToEvent event) {
		// Refer to http://www.minecraftwiki.net/wiki/Data_values#Water_and_Lava
		Block sourceBlock = event.getBlock();
		Block targetBlock = event.getToBlock();
		
		// Only if the height is greater or the same
		if (sourceBlock.getY() < plugin.config.getInt("configuration.height")) return;

		// Lava fall
		if (plugin.config.getBoolean("sources.lava_fall") == true) {
			// Security check with 0
			if (sourceBlock.getY() > 0) {
				if(event.getFace() == BlockFace.DOWN) {
					if (event.getToBlock().getRelative(BlockFace.UP).getType() == Material.LAVA
							|| event.getToBlock().getRelative(BlockFace.UP).getType() == Material.STATIONARY_LAVA) {
						if( event.getToBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
							event.getToBlock().setTypeIdAndData(Material.LAVA.getId(), (byte) 0x0, true);
							event.setCancelled(true);
						}
					}
				}
			}
		}
		
		// Water fall
		if (plugin.config.getBoolean("sources.water_fall") == true) {
			// Security check with 0
			if (sourceBlock.getY() > 0) {
				if(event.getFace() == BlockFace.DOWN) {
					if (event.getToBlock().getRelative(BlockFace.UP).getType() == Material.WATER
							|| event.getToBlock().getRelative(BlockFace.UP).getType() == Material.STATIONARY_WATER) {
						if( event.getToBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
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
					// Spread if possible for TWO
					if (plugin.config.getBoolean("sources.two") == true) {
						if (UnlimitedLavaCheck.checkSpreadValidityTwo(targetBlock)) {
							// Only full blocks
							event.getToBlock().setTypeIdAndData(Material.LAVA.getId(), (byte) 0x0, true);
						}
					}
					// Spread if possible for THREE
					if (plugin.config.getBoolean("sources.three") == true) {
						if (UnlimitedLavaCheck.checkSpreadValidityThree(targetBlock)) {
							// Only full blocks
							event.getToBlock().setTypeIdAndData(Material.LAVA.getId(), (byte) 0x0, true);
						}
					}
					// Spread if possible for OTHER
					if (plugin.config.getBoolean("sources.other") == true) {
						if (UnlimitedLavaCheck.checkSpreadValidityOther(targetBlock)) {
							// Only full blocks
							event.getToBlock().setTypeIdAndData(Material.LAVA.getId(), (byte) 0x0, true);
						}
					}
					// Spread if possible for BIG
					if (plugin.config.getBoolean("sources.big") == true) {
						if (UnlimitedLavaCheck.checkSpreadValidityBig(targetBlock)) {
							// Only full blocks
							event.getToBlock().setTypeIdAndData(Material.LAVA.getId(), (byte) 0x0, true);
						}
					}
				}
			}
		}
	}
}