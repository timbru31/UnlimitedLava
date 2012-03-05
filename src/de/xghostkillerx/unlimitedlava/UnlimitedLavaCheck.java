package de.xghostkillerx.unlimitedlava;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

/**
 * UnlimitedLavaSpreadCheck
 * Handles the infinite sources spread checks for the Block- and PlayerListener
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

public class UnlimitedLavaCheck {

	static UnlimitedLava plugin;
	public UnlimitedLavaCheck(UnlimitedLava instance) {
		plugin = instance;
	}
	private static int n;

	static boolean checkSpreadValidityTwo(Block block) {
		n = 0;
		/*
		 * For the 2x2 source!
		 *
		 * Defines the number of valid "source" lava flows surrounding this
		 * block Sets the value of valid lava flows, surrounding this block If a
		 * valid flow of lava exists, add to the count
		 *
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
		if (block.getRelative(BlockFace.NORTH_NORTH_WEST).getType() == Material.STATIONARY_LAVA) return false;
		if (block.getRelative(BlockFace.NORTH_NORTH_EAST).getType() == Material.STATIONARY_LAVA) return false;
		if (block.getRelative(BlockFace.SOUTH_SOUTH_WEST).getType() == Material.STATIONARY_LAVA) return false;
		if (block.getRelative(BlockFace.SOUTH_SOUTH_EAST).getType() == Material.STATIONARY_LAVA) return false;
		if (block.getRelative(BlockFace.DOWN).getType() == Material.AIR) return false;
		
		// If more than 1 flow exists, yay, it's okay!
		return (n >=1) ? true : false;
	}

	static boolean checkSpreadValidityThree(Block block) {
		n = 0;
		/*
		 * For the 3x3 source!
		 *
		 * Defines the number of valid "source" lava flows surrounding this
		 * block Sets the value of valid lava flows, surrounding this block If a
		 * valid flow of lava exists, add to the count
		 *
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
		if (block.getRelative(BlockFace.NORTH_NORTH_WEST).getType() == Material.STATIONARY_LAVA) return false;
		if (block.getRelative(BlockFace.NORTH_NORTH_EAST).getType() == Material.STATIONARY_LAVA) return false;
		if (block.getRelative(BlockFace.SOUTH_SOUTH_WEST).getType() == Material.STATIONARY_LAVA) return false;
		if (block.getRelative(BlockFace.SOUTH_SOUTH_EAST).getType() == Material.STATIONARY_LAVA) return false;
		if (block.getRelative(BlockFace.DOWN).getType() == Material.AIR) return false;
		
		// If more than 8 flow exists, yay, it's okay!
		return (n >=8) ? true : false;
	}

	static boolean checkSpreadValidityBig(Block block) {
		n = 0;
		/*
		 * For the big source!
		 *
		 * Defines the number of valid "source" lava flows surrounding this
		 * block Sets the value of valid lava flows, surrounding this block If a
		 * valid flow of lava exists, add to the count
		 *
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
		if (block.getRelative(BlockFace.DOWN).getType() == Material.AIR) return false;
		
		// If more than 9 flow exists, yay, it's okay!
		return (n >=9) ? true : false;
	}

	static boolean checkSpreadValidityOther(Block block) {
		n = 0;
		/*
		 * For other sources!
		 *
		 * Defines the number of valid "source" lava flows surrounding this
		 * block Sets the value of valid lava flows, surrounding this block If a
		 * valid flow of lava exists, add to the count
		 *
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
		if (block.getRelative(BlockFace.DOWN).getType() == Material.AIR) return false;
		
		// If more than 2 flow exists, yay, it's okay!
		return (n >=2) ? true : false;
	}
}