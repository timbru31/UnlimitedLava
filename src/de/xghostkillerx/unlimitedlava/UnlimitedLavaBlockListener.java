package de.xghostkillerx.unlimitedlava;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockListener;

/**
 * UnlimitedLavaBlockListener
 * 
 * Refer to: http://forums.bukkit.org/threads/19441/
 * 
 * @author xGhOsTkiLLeRx
 * @thanks to loganwm for the help!!
 * 
 */

public class UnlimitedLavaBlockListener extends BlockListener {

	public static UnlimitedLava plugin;
	public UnlimitedLavaBlockListener(UnlimitedLava instance) {
		plugin = instance;
	}
	
	public void onBlockFromTo(BlockFromToEvent event) {
		Player player = event.getPlayer();
		if (player.hasPermission("unlimitedlava.use")) {
		Block sourceBlock = event.getBlock();
		Block targetBlock = event.getToBlock();
		/*
		 * Refer to http://www.minecraftwiki.net/wiki/Data_values#Water_and_Lava
		 * Check if we got a full block of lava
		 * 
		 */
		if (plugin.two == true) {
			if (event.getBlock().getData() != 0x0) {
				return;
			}
			if ((sourceBlock.getType() == Material.LAVA || sourceBlock.getType() == Material.STATIONARY_LAVA)) {
				// Check if we can use the surrounded check
				if (targetBlock.getType() == Material.LAVA || targetBlock.getType() == Material.STATIONARY_LAVA) {
					// Full block (0x0) and not falling (0x8)
					if (targetBlock.getData() != 0x0 && targetBlock.getData() != 0x8) {
						//Spread if possible
						if (checkSpreadValidityTwo(targetBlock)) {
							// Only full blocks
							event.getToBlock().setType(Material.LAVA);
						}
					}
				}
				/*
				 * If the block flows into air, check if the air can get a full lava
				 * block
				 * 
				 */
				else if (targetBlock.getType() == Material.AIR) {
					// Spread if possible
					if (checkSpreadValidityTwo(event.getToBlock())) {
						// Yay, we got a full lava block!
						event.getToBlock().setType(Material.LAVA);
						event.getToBlock().setData((byte) 0x0);
					}
				}
			}
		}
		if (plugin.three == true) {
			if (event.getBlock().getData() != 0x0) {
				return;
			}
			if ((sourceBlock.getType() == Material.LAVA || sourceBlock.getType() == Material.STATIONARY_LAVA)) {
				// Check if we can use the surrounded check
				if (targetBlock.getType() == Material.LAVA || targetBlock.getType() == Material.STATIONARY_LAVA) {
					// Full block (0x0) and not falling (0x8)
					if (targetBlock.getData() != 0x0 && targetBlock.getData() != 0x8) {
						//Spread if possible
						if (checkSpreadValidityThree(targetBlock)) {
							// Only full blocks
							event.getToBlock().setType(Material.LAVA);
						}
					}
				}
				/*
				 * If the block flows into air, check if the air can get a full lava
				 * block
				 * 
				 */
				else if (targetBlock.getType() == Material.AIR) {
					// Spread if possible
					if (checkSpreadValidityThree(event.getToBlock())) {
						// Yay, we got a full lava block!
						event.getToBlock().setType(Material.LAVA);
						event.getToBlock().setData((byte) 0x0);
					}
				}
			}
		}
		if (plugin.other == true) {
			if (event.getBlock().getData() != 0x0) {
				return;
			}
			if ((sourceBlock.getType() == Material.LAVA || sourceBlock.getType() == Material.STATIONARY_LAVA)) {
				// Check if we can use the surrounded check
				if (targetBlock.getType() == Material.LAVA || targetBlock.getType() == Material.STATIONARY_LAVA) {
					// Full block (0x0) and not falling (0x8)
					if (targetBlock.getData() != 0x0 && targetBlock.getData() != 0x8) {
						//Spread if possible
						if (checkSpreadValidityOther(targetBlock)) {
							// Only full blocks
							event.getToBlock().setType(Material.LAVA);
						}
					}
				}
				/*
				 * If the block flows into air, check if the air can get a full lava
				 * block
				 * 
				 */
				else if (targetBlock.getType() == Material.AIR) {
					// Spread if possible
					if (checkSpreadValidityOther(event.getToBlock())) {
						// Yay, we got a full lava block!
						event.getToBlock().setType(Material.LAVA);
						event.getToBlock().setData((byte) 0x0);
					}
				}
			}
		}
	}
		else {
			player.sendMessage("pwnded");
		}
}

private boolean checkSpreadValidityTwo(Block block) {
int n = 0;
		/*
		 * For the 2x2 source!
		 * 
		 * Defines the number of valid "source" lava flows surrounding this
		 * block Sets the value of valid lava flows, sorrounding this block If a
		 * valid flow of lava exists, add to the count
		 */
	    if ((block.getRelative(BlockFace.WEST).getType() == Material.LAVA || block.getRelative(BlockFace.WEST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.WEST).getData() == 0x0) && ((block.getRelative(BlockFace.NORTH).getType() == Material.LAVA || block.getRelative(BlockFace.NORTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.NORTH).getData() == 0x0) && ((block.getRelative(BlockFace.NORTH_WEST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.NORTH_WEST).getData() == 0x0))) {
	    	n++;
		}
	    if ((block.getRelative(BlockFace.WEST).getType() == Material.LAVA || block.getRelative(BlockFace.WEST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.WEST).getData() == 0x0) && ((block.getRelative(BlockFace.SOUTH).getType() == Material.LAVA || block.getRelative(BlockFace.SOUTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.SOUTH).getData() == 0x0) && ((block.getRelative(BlockFace.SOUTH_WEST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.SOUTH_WEST).getData() == 0x0))) {
	    	n++;
	    }
	    if ((block.getRelative(BlockFace.EAST).getType() == Material.LAVA || block.getRelative(BlockFace.EAST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.EAST).getData() == 0x0) && ((block.getRelative(BlockFace.SOUTH).getType() == Material.LAVA || block.getRelative(BlockFace.SOUTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.SOUTH).getData() == 0x0) && ((block.getRelative(BlockFace.SOUTH_EAST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.SOUTH_EAST).getData() == 0x0))) {
	    	n++;
	    }
	    if ((block.getRelative(BlockFace.EAST).getType() == Material.LAVA || block.getRelative(BlockFace.EAST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.EAST).getData() == 0x0) && ((block.getRelative(BlockFace.NORTH).getType() == Material.LAVA || block.getRelative(BlockFace.NORTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.NORTH).getData() == 0x0) && ((block.getRelative(BlockFace.NORTH_EAST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.NORTH_EAST).getData() == 0x0))) {
	    	n++;
	    }
		if (block.getRelative(BlockFace.DOWN).getType() == Material.AIR)
			return false;
	    if ((block.getRelative(BlockFace.EAST).getType() == Material.LAVA || block.getRelative(BlockFace.EAST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.EAST).getData() == 0x0) && ((block.getRelative(BlockFace.NORTH).getType() == Material.LAVA || block.getRelative(BlockFace.NORTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.NORTH).getData() == 0x0) && ((block.getRelative(BlockFace.WEST).getType() == Material.LAVA || block.getRelative(BlockFace.WEST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.WEST).getData() == 0x0) && ((block.getRelative(BlockFace.SOUTH).getType() == Material.LAVA || block.getRelative(BlockFace.SOUTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.SOUTH).getData() == 0x0)))
	    	return false;
	    if ((block.getRelative(BlockFace.WEST).getType() == Material.LAVA || block.getRelative(BlockFace.WEST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.WEST).getData() == 0x0) && ((block.getRelative(BlockFace.NORTH).getType() == Material.LAVA || block.getRelative(BlockFace.NORTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.NORTH).getData() == 0x0) && ((block.getRelative(BlockFace.SOUTH).getType() == Material.LAVA || block.getRelative(BlockFace.SOUTH).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.SOUTH).getData() == 0x0)))
	    	return false;
	    if ((block.getRelative(BlockFace.WEST).getType() == Material.LAVA || block.getRelative(BlockFace.WEST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.WEST).getData() == 0x0) && ((block.getRelative(BlockFace.NORTH).getType() == Material.LAVA || block.getRelative(BlockFace.NORTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.NORTH).getData() == 0x0) && ((block.getRelative(BlockFace.EAST).getType() == Material.LAVA || block.getRelative(BlockFace.EAST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.EAST).getData() == 0x0)))
	    	return false;
	    if ((block.getRelative(BlockFace.WEST).getType() == Material.LAVA || block.getRelative(BlockFace.WEST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.WEST).getData() == 0x0) && ((block.getRelative(BlockFace.SOUTH).getType() == Material.LAVA || block.getRelative(BlockFace.SOUTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.SOUTH).getData() == 0x0) && ((block.getRelative(BlockFace.EAST).getType() == Material.LAVA || block.getRelative(BlockFace.EAST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.EAST).getData() == 0x0)))
	    	return false;
	    if ((block.getRelative(BlockFace.SOUTH).getType() == Material.LAVA || block.getRelative(BlockFace.SOUTH).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.SOUTH).getData() == 0x0) && ((block.getRelative(BlockFace.NORTH).getType() == Material.LAVA || block.getRelative(BlockFace.NORTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.NORTH).getData() == 0x0) && ((block.getRelative(BlockFace.EAST).getType() == Material.LAVA || block.getRelative(BlockFace.EAST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.EAST).getData() == 0x0)))
	    	return false;
		if (block.getRelative(BlockFace.NORTH_NORTH_WEST).getType() == Material.STATIONARY_LAVA)
			return false;
		if (block.getRelative(BlockFace.NORTH_NORTH_EAST).getType() == Material.STATIONARY_LAVA)
			return false;
		if (block.getRelative(BlockFace.SOUTH_SOUTH_WEST).getType() == Material.STATIONARY_LAVA)
			return false;	
		if (block.getRelative(BlockFace.SOUTH_SOUTH_EAST).getType() == Material.STATIONARY_LAVA)
			return false;
	   	// If more than 1 flow exists, yay, it's okay!
	    
		if (n >= 1) {
			return true;
		} else {
			return false;
		}
	}

private boolean checkSpreadValidityThree(Block block) {
int n = 0;
		/*
		 * For the 3x3 source!
		 * 
		 * Defines the number of valid "source" lava flows surrounding this
		 * block Sets the value of valid lava flows, sorrounding this block If a
		 * valid flow of lava exists, add to the count
		 */
		if ((block.getRelative(BlockFace.EAST).getType() == Material.LAVA || block.getRelative(BlockFace.EAST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.EAST).getData() == 0x0) {
			n++;
			}
		if ((block.getRelative(BlockFace.WEST).getType() == Material.LAVA || block.getRelative(BlockFace.WEST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.WEST).getData() == 0x0) {
			n++;
		}
		if ((block.getRelative(BlockFace.NORTH).getType() == Material.LAVA || block.getRelative(BlockFace.NORTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.NORTH).getData() == 0x0) {
			n++;
		}
		if ((block.getRelative(BlockFace.SOUTH).getType() == Material.LAVA || block.getRelative(BlockFace.SOUTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.SOUTH).getData() == 0x0) {
			n++;
		}
		if (block.getRelative(BlockFace.DOWN).getType() == Material.AIR)
			return false;		
		// If more than 4 flow exists, yay, it's okay!
	    
		if (n >= 4) {
			return true;
		} else {
			return false;
		}
	}

private boolean checkSpreadValidityOther(Block block) {
int n = 0;
		/*
		 * For Other sources!
		 * 
		 * Defines the number of valid "source" lava flows surrounding this
		 * block Sets the value of valid lava flows, sorrounding this block If a
		 * valid flow of lava exists, add to the count
		 */
		if ((block.getRelative(BlockFace.EAST).getType() == Material.LAVA || block.getRelative(BlockFace.EAST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.EAST).getData() == 0x0) {
			n++;
		}
		if ((block.getRelative(BlockFace.WEST).getType() == Material.LAVA || block.getRelative(BlockFace.WEST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.WEST).getData() == 0x0) {
			n++;
		}
		if ((block.getRelative(BlockFace.NORTH).getType() == Material.LAVA || block.getRelative(BlockFace.NORTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.NORTH).getData() == 0x0) {
			n++;
		}
		if ((block.getRelative(BlockFace.SOUTH).getType() == Material.LAVA || block.getRelative(BlockFace.SOUTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.SOUTH).getData() == 0x0) {
			n++;
		}
		if (block.getRelative(BlockFace.DOWN).getType() == Material.AIR)
			return false;
	    if ((block.getRelative(BlockFace.WEST).getType() == Material.LAVA || block.getRelative(BlockFace.WEST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.WEST).getData() == 0x0) && ((block.getRelative(BlockFace.NORTH).getType() == Material.LAVA || block.getRelative(BlockFace.NORTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.NORTH).getData() == 0x0) && ((block.getRelative(BlockFace.NORTH_WEST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.NORTH_WEST).getData() == 0x0)))
	    	return false;
	    if ((block.getRelative(BlockFace.WEST).getType() == Material.LAVA || block.getRelative(BlockFace.WEST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.WEST).getData() == 0x0) && ((block.getRelative(BlockFace.SOUTH).getType() == Material.LAVA || block.getRelative(BlockFace.SOUTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.SOUTH).getData() == 0x0) && ((block.getRelative(BlockFace.SOUTH_WEST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.SOUTH_WEST).getData() == 0x0)))
	    	return false;
	    if ((block.getRelative(BlockFace.EAST).getType() == Material.LAVA || block.getRelative(BlockFace.EAST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.EAST).getData() == 0x0) && ((block.getRelative(BlockFace.SOUTH).getType() == Material.LAVA || block.getRelative(BlockFace.SOUTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.SOUTH).getData() == 0x0) && ((block.getRelative(BlockFace.SOUTH_EAST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.SOUTH_EAST).getData() == 0x0)))
	    	return false;
	    if ((block.getRelative(BlockFace.EAST).getType() == Material.LAVA || block.getRelative(BlockFace.EAST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.EAST).getData() == 0x0) && ((block.getRelative(BlockFace.NORTH).getType() == Material.LAVA || block.getRelative(BlockFace.NORTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.NORTH).getData() == 0x0) && ((block.getRelative(BlockFace.NORTH_EAST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.NORTH_EAST).getData() == 0x0)))
	    	return false;
		if ((block.getRelative(BlockFace.EAST).getType() == Material.LAVA || block.getRelative(BlockFace.EAST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.EAST).getData() == 0x0) && ((block.getRelative(BlockFace.NORTH).getType() == Material.LAVA || block.getRelative(BlockFace.NORTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.NORTH).getData() == 0x0) && ((block.getRelative(BlockFace.WEST).getType() == Material.LAVA || block.getRelative(BlockFace.WEST).getType() == Material.STATIONARY_LAVA) && (block.getRelative(BlockFace.WEST).getData() == 0x0) && ((block.getRelative(BlockFace.SOUTH).getType() == Material.LAVA || block.getRelative(BlockFace.SOUTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.SOUTH).getData() == 0x0)))
			return false;
		// If more than 2 flow exists, yay, it's okay!
	    
		if (n >= 2) {
			return true;
		} else {
			return false;
		}
	}
}
