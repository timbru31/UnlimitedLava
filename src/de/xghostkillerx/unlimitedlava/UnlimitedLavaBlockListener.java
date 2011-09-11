package de.xghostkillerx.unlimitedlava;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockListener;

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
 * 
 */

public class UnlimitedLavaBlockListener extends BlockListener {

	public static UnlimitedLava plugin;
	
	public UnlimitedLavaBlockListener(UnlimitedLava instance) {
		plugin = instance;
	}

	public void onBlockFromTo(BlockFromToEvent event) {
		Block sourceBlock = event.getBlock();
		Block targetBlock = event.getToBlock();
		/*
		 * Refer to http://www.minecraftwiki.net/wiki/Data_values#Water_and_Lava
		 * Check if we got a full block of lava
		 */
		if (sourceBlock.getData() != 0x0) {
			return;
		}
		if (sourceBlock.getType() == Material.LAVA || sourceBlock.getType() == Material.STATIONARY_LAVA) {
			// Check if we can use the surrounded check
			if (targetBlock.getType() == Material.LAVA || targetBlock.getType() == Material.STATIONARY_LAVA) {
				// Full block (0x0) and not falling (0x8)
				if (targetBlock.getData() != 0x0 && targetBlock.getData() != 0x8) {
					// Spread if possible for TWO
					if (plugin.two == true) {
						if (UnlimitedLavaSpreadCheck.checkSpreadValidityTwo(targetBlock)) {
							// Only full blocks
							event.getToBlock().setType(Material.LAVA);
						}
					}
					// Spread if possible for THREE
					if (plugin.three == true) {
						if (UnlimitedLavaSpreadCheck.checkSpreadValidityThree(targetBlock)) {
							// Only full blocks
							event.getToBlock().setType(Material.LAVA);
						}
					}
					// Spread if possible for OTHER
					if (plugin.other == true) {
						if (UnlimitedLavaSpreadCheck.checkSpreadValidityOther(targetBlock)) {
							// Only full blocks
							event.getToBlock().setType(Material.LAVA);
						}
					}
					// Spread if possible for BIG
					if (plugin.big == true) {
						if (UnlimitedLavaSpreadCheck.checkSpreadValidityBig(targetBlock)) {
							// Only full blocks
							event.getToBlock().setType(Material.LAVA);
						}
					}
				}
				/*
				 * If the block flows into air, check if the air can get a full
				 * lava block
				 */
				else if (targetBlock.getType() == Material.AIR) {
					if (plugin.two == true) {
						// Spread if possible for TWO
						if (UnlimitedLavaSpreadCheck.checkSpreadValidityTwo(event.getToBlock())) {
							// Yay, we got a full lava block!
							event.getToBlock().setType(Material.LAVA);
							event.getToBlock().setData((byte) 0x0);
						}
					}
					if (plugin.three == true) {
						// Spread if possible for THREE
						if (UnlimitedLavaSpreadCheck.checkSpreadValidityThree(event.getToBlock())) {
							// Yay, we got a full lava block!
							event.getToBlock().setType(Material.LAVA);
							event.getToBlock().setData((byte) 0x0);
						}
					}
					if (plugin.other == true) {
						// Spread if possible for OTHER
						if (UnlimitedLavaSpreadCheck.checkSpreadValidityOther(event.getToBlock())) {
							// Yay, we got a full lava block!
							event.getToBlock().setType(Material.LAVA);
							event.getToBlock().setData((byte) 0x0);
						}
					}
					if (plugin.big == true) {
						// Spread if possible for BIG
						if (UnlimitedLavaSpreadCheck.checkSpreadValidityBig(event.getToBlock())) {
							// Yay, we got a full lava block!
							event.getToBlock().setType(Material.LAVA);
							event.getToBlock().setData((byte) 0x0);
						}
					}
				}
			}
		}
	}
}
