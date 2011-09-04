package de.xghostkillerx.unlimitedlava;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

/**
 * UnlimitedLavaPlayerListener
 * Handles the players activities!
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

public class UnlimitedLavaPlayerListener extends PlayerListener {

	public static UnlimitedLava plugin;

	public UnlimitedLavaPlayerListener(UnlimitedLava instance) {
		plugin = instance;
	}
	
	@SuppressWarnings("deprecation")
	public void onPlayerBucketFill(PlayerBucketFillEvent event) {
		Block clicked = event.getBlockClicked();
		Player player = event.getPlayer();
		// Only if lava is clicked ;)
		if (plugin.permissions == true) {
			if (event.getBlockClicked().getTypeId() == 9) {
				return;
			}
			// If the player hasn't got the permissions, cancel the event and give a empty bucket!
			if (!player.hasPermission("unlimitedlava.use")) {
				if (plugin.two == true) {
					if (checkSpreadValidityTwo(clicked)) {
						if (plugin.messages == true) {
							player.sendMessage(ChatColor.DARK_RED + "You don't have the permission to use the UnlimitedLava!");
						}
						event.setCancelled(true);
						ItemStack bucket = new ItemStack(325, 1);
						player.setItemInHand(bucket);
						player.updateInventory();

					}
				}
				if (plugin.three == true) {
					if (checkSpreadValidityThree(clicked)) {
						if (plugin.messages == true) {
							player.sendMessage(ChatColor.DARK_RED + "You don't have the permission to use the UnlimitedLava!");
						}
						event.setCancelled(true);
						ItemStack bucket = new ItemStack(325, 1);
						player.setItemInHand(bucket);
						player.updateInventory();
					}
				}
				if (plugin.other == true) {
					if (checkSpreadValidityOther(clicked)) {
						if (plugin.messages == true) {
							player.sendMessage(ChatColor.DARK_RED + "You don't have the permission to use the UnlimitedLava!");
						}
						event.setCancelled(true);
						ItemStack bucket = new ItemStack(325, 1);
						player.setItemInHand(bucket);
						player.updateInventory();
					}
				}
				if (plugin.big == true) {
					if (checkSpreadValidityBig(clicked)) {
						if (plugin.messages == true) {
							player.sendMessage(ChatColor.DARK_RED + "You don't have the permission to use the UnlimitedLava!");
						}
						event.setCancelled(true);
						ItemStack bucket = new ItemStack(325, 1);
						player.setItemInHand(bucket);
						player.updateInventory();
					}
				}
			}
		}
	}

	private boolean checkSpreadValidityTwo(Block block) {
		int n = 0;
		/*
		 * For the 2x2 source!
		 * 
		 * Defines the number of valid "source" lava flows surrounding this
		 * block Sets the value of valid lava flows, surrounding this block If a
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
		 * block Sets the value of valid lava flows, surrounding this block If a
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
		if (block.getRelative(BlockFace.SOUTH_EAST).getType() == Material.STATIONARY_LAVA) {
			n++;
		}
		if (block.getRelative(BlockFace.SOUTH_WEST).getType() == Material.STATIONARY_LAVA) {
			n++;
		}
		if (block.getRelative(BlockFace.NORTH_EAST).getType() == Material.STATIONARY_LAVA) {
			n++;
		}
		if (block.getRelative(BlockFace.NORTH_WEST).getType() == Material.STATIONARY_LAVA) {
			n++;
		}
		if (block.getRelative(BlockFace.NORTH_NORTH_WEST).getType() == Material.STATIONARY_LAVA)
			return false;
		if (block.getRelative(BlockFace.NORTH_NORTH_EAST).getType() == Material.STATIONARY_LAVA)
			return false;
		if (block.getRelative(BlockFace.SOUTH_SOUTH_WEST).getType() == Material.STATIONARY_LAVA)
			return false;	
		if (block.getRelative(BlockFace.SOUTH_SOUTH_EAST).getType() == Material.STATIONARY_LAVA)
			return false;
		if (block.getRelative(BlockFace.DOWN).getType() == Material.AIR)
			return false;		
		// If more than 8 flow exists, yay, it's okay!
	    
		if (n >= 8) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkSpreadValidityBig(Block block) {
		int n = 0;
		/*
		 * For the big source!
		 * 
		 * Defines the number of valid "source" lava flows surrounding this
		 * block Sets the value of valid lava flows, surrounding this block If a
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
		if (block.getRelative(BlockFace.SOUTH_EAST).getType() == Material.STATIONARY_LAVA) {
			n++;
		}
		if (block.getRelative(BlockFace.SOUTH_WEST).getType() == Material.STATIONARY_LAVA) {
			n++;
		}
		if (block.getRelative(BlockFace.NORTH_EAST).getType() == Material.STATIONARY_LAVA) {
			n++;
		}
		if (block.getRelative(BlockFace.NORTH_WEST).getType() == Material.STATIONARY_LAVA) {
			n++;
		}
		if (block.getRelative(BlockFace.SOUTH_SOUTH_EAST).getType() == Material.STATIONARY_LAVA) {
			n++;
		}
		if (block.getRelative(BlockFace.SOUTH_SOUTH_WEST).getType() == Material.STATIONARY_LAVA) {
			n++;
		}
		if (block.getRelative(BlockFace.NORTH_NORTH_EAST).getType() == Material.STATIONARY_LAVA) {
			n++;
		}
		if (block.getRelative(BlockFace.NORTH_NORTH_WEST).getType() == Material.STATIONARY_LAVA) {
			n++;
		}
		// If more than 9 flow exists, yay, it's okay!
	    
		if (n >= 9) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkSpreadValidityOther(Block block) {
		int n = 0;
		/*
		 * For other sources!
		 * 
		 * Defines the number of valid "source" lava flows surrounding this
		 * block Sets the value of valid lava flows, surrounding this block If a
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

