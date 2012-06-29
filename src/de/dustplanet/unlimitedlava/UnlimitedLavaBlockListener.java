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
 * @thanks to Xastabus for the cool improvements of the checks!
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
		
		if (!plugin.enabledWords.contains(sourceBlock.getWorld().getName())) return;

		// Only if the height is greater or the same
		if (sourceBlock.getY() <= plugin.height) return;

		// Lava fall
		if (plugin.lavaFall) {
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
		if (plugin.waterFall) {
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
		if (sourceBlock.getData() != 0x0) return;

		if (sourceBlock.getType() == Material.LAVA || sourceBlock.getType() == Material.STATIONARY_LAVA) {
			// Check if we can use the surrounded check
			if (targetBlock.getType() == Material.LAVA || targetBlock.getType() == Material.STATIONARY_LAVA) {
				// Full block (0x0) and not falling (0x8)
				if (targetBlock.getData() != 0x0 && targetBlock.getData() != 0x8) {
					// Check which cases are valid
					int faces = UnlimitedLavaCheck.checkSpreadValidityFaces(targetBlock);
					int corners = UnlimitedLavaCheck.checkSpreadValidityCorners(targetBlock);
					boolean lake = UnlimitedLavaCheck.checkIsInLake(targetBlock);
					boolean border = UnlimitedLavaCheck.checkIsOnBorder(targetBlock);
					// Big
					if (plugin.big && faces == 4 &&  corners == 4 && lake && !border)
						event.getToBlock().setTypeIdAndData(Material.LAVA.getId(), (byte) 0x0, true);
					// Three
					else if (plugin.three && faces == 4 &&  corners == 4 && !lake && !border)
						event.getToBlock().setTypeIdAndData(Material.LAVA.getId(), (byte) 0x0, true);
					// Two
					else if (plugin.two && faces == 2 &&  corners == 1 && !lake && border)
						event.getToBlock().setTypeIdAndData(Material.LAVA.getId(), (byte) 0x0, true);
					// Plus
					else if (plugin.plus && faces == 4 &&  corners == 0 && !lake && border)
						event.getToBlock().setTypeIdAndData(Material.LAVA.getId(), (byte) 0x0, true);
					// Other
					else if (plugin.other && faces == 2 &&  corners == 0 && !lake && border)
						event.getToBlock().setTypeIdAndData(Material.LAVA.getId(), (byte) 0x0, true);
					// T Shape
					else if (plugin.T && faces == 3 &&  corners == 0 && !lake && border)
						event.getToBlock().setTypeIdAndData(Material.LAVA.getId(), (byte) 0x0, true);
				}
			}
		}
	}
}