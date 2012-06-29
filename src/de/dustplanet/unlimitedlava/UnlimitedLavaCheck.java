package de.dustplanet.unlimitedlava;

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
 * @thanks to ferrybig for the awesome fall code!
 * @thanks to Xastabus for the cool improvements of the checks!
 * 
 */

public class UnlimitedLavaCheck {

	static UnlimitedLava plugin;
	public UnlimitedLavaCheck(UnlimitedLava instance) {
		plugin = instance;
	}

	static int checkSpreadValidityFaces(Block block) {
		/* Find the number of blocks adjacent to the target that are filled with lava.
		 * This value will be used to determine whether to fill trenches of the "other" (2) and "plus" (4) configuration 
		 */
		int i = 0;
		// Do not fill above air.
		if (block.getRelative(BlockFace.DOWN).getType() == Material.AIR) return i;

		// Find the number of adjacent blocks are full blocks of Lava.  Return that value.
		if ((block.getRelative(BlockFace.EAST).getType() == Material.LAVA || block.getRelative(BlockFace.EAST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.EAST).getData() == 0x0) {
			i++;
		}
		if ((block.getRelative(BlockFace.WEST).getType() == Material.LAVA || block.getRelative(BlockFace.WEST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.WEST).getData() == 0x0) {
			i++;
		}
		if ((block.getRelative(BlockFace.NORTH).getType() == Material.LAVA || block.getRelative(BlockFace.NORTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.NORTH).getData() == 0x0) {
			i++;
		}
		if ((block.getRelative(BlockFace.SOUTH).getType() == Material.LAVA || block.getRelative(BlockFace.SOUTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.SOUTH).getData() == 0x0) {
			i++;
		}

		return i;
	}

	static int checkSpreadValidityCorners(Block block) {
		/* 
		 * Find the number of 3 block corners surrounding the target block that are filled with lava.
		 * This value will be used to determine whether to fill 2x2 (requires 1 corner) or 3x3 (requires 4 corners) pools.
		 */
		int i = 0, n = 0, ne = 0, s = 0, nw = 0, sw = 0, w = 0, e = 0, se = 0;
		// Do not fill above air.
		if (block.getRelative(BlockFace.DOWN).getType() == Material.AIR) return i;


		// Check 1 block radius for full lava blocks.
		if ((block.getRelative(BlockFace.EAST).getType() == Material.LAVA || block.getRelative(BlockFace.EAST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.EAST).getData() == 0x0) {
			e++;
		}
		if ((block.getRelative(BlockFace.WEST).getType() == Material.LAVA || block.getRelative(BlockFace.WEST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.WEST).getData() == 0x0) {
			w++;
		}
		if ((block.getRelative(BlockFace.NORTH).getType() == Material.LAVA || block.getRelative(BlockFace.NORTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.NORTH).getData() == 0x0) {
			n++;
		}
		if ((block.getRelative(BlockFace.SOUTH).getType() == Material.LAVA || block.getRelative(BlockFace.SOUTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.SOUTH).getData() == 0x0) {
			s++;
		}
		if ((block.getRelative(BlockFace.NORTH_EAST).getType() == Material.LAVA || block.getRelative(BlockFace.NORTH_EAST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.NORTH_EAST).getData() == 0x0) {
			ne++;
		}
		if ((block.getRelative(BlockFace.SOUTH_WEST).getType() == Material.LAVA || block.getRelative(BlockFace.SOUTH_WEST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.SOUTH_WEST).getData() == 0x0) {
			sw++;
		}
		if ((block.getRelative(BlockFace.NORTH_WEST).getType() == Material.LAVA || block.getRelative(BlockFace.NORTH_WEST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.NORTH_WEST).getData() == 0x0) {
			nw++;
		}
		if ((block.getRelative(BlockFace.SOUTH_EAST).getType() == Material.LAVA || block.getRelative(BlockFace.SOUTH_EAST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.SOUTH_EAST).getData() == 0x0) {
			se++;
		}

		// Calculate 3 block corners of lava.
		if (n + ne + e == 3) i++;
		if (e + se + s == 3) i++;
		if (s + sw + w == 3) i++;
		if (w + nw + n == 3) i++;

		return i;
	}
	
	static boolean checkIsInLake(Block block) {
		int i = 0;
		// Lake test.  If any 2 blocks in a 2 block radius are filled with lava then the target block is part of a lake.
		if ((block.getRelative(BlockFace.NORTH_NORTH_EAST).getType() == Material.LAVA || block.getRelative(BlockFace.NORTH_NORTH_EAST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.NORTH_NORTH_EAST).getData() == 0x0) {
			i++;
		}
		if ((block.getRelative(BlockFace.SOUTH_SOUTH_WEST).getType() == Material.LAVA || block.getRelative(BlockFace.SOUTH_SOUTH_WEST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.SOUTH_SOUTH_WEST).getData() == 0x0) {
			i++;
		}
		if ((block.getRelative(BlockFace.NORTH_NORTH_WEST).getType() == Material.LAVA || block.getRelative(BlockFace.NORTH_NORTH_WEST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.NORTH_NORTH_WEST).getData() == 0x0) {
			i++;
		}
		if ((block.getRelative(BlockFace.SOUTH_SOUTH_EAST).getType() == Material.LAVA || block.getRelative(BlockFace.SOUTH_SOUTH_EAST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.SOUTH_SOUTH_EAST).getData() == 0x0) {
			i++;
		}
		if ((block.getRelative(BlockFace.EAST_NORTH_EAST).getType() == Material.LAVA || block.getRelative(BlockFace.EAST_NORTH_EAST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.EAST_NORTH_EAST).getData() == 0x0) {
			i++;
		}
		if ((block.getRelative(BlockFace.WEST_SOUTH_WEST).getType() == Material.LAVA || block.getRelative(BlockFace.WEST_SOUTH_WEST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.WEST_SOUTH_WEST).getData() == 0x0) {
			i++;
		}
		if ((block.getRelative(BlockFace.WEST_NORTH_WEST).getType() == Material.LAVA || block.getRelative(BlockFace.WEST_NORTH_WEST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.WEST_NORTH_WEST).getData() == 0x0) {
			i++;
		}
		if ((block.getRelative(BlockFace.EAST_SOUTH_EAST).getType() == Material.LAVA || block.getRelative(BlockFace.EAST_SOUTH_EAST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.EAST_SOUTH_EAST).getData() == 0x0) {
			i++;
		}

		return (i >=2 ) ? true : false;
	}

	static boolean checkIsOnBorder(Block block) {
		int i = 0;
		/* Border test for "big" option.
		 * Find the number of blocks in a 1 block radius that are not filled with Lava.
		 * If 2 or more blocks in the 1 block radius are not filled with Lava the target block is on the border and
		 * should not be filled if "big" is enabled.
		 */
		// Do not fill above air.
		if (block.getRelative(BlockFace.DOWN).getType() == Material.AIR) return true;

		if (((block.getRelative(BlockFace.EAST).getType() == Material.LAVA || block.getRelative(BlockFace.EAST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.EAST).getData() == 0x0) != true){
			i++;
		}
		if (((block.getRelative(BlockFace.WEST).getType() == Material.LAVA || block.getRelative(BlockFace.WEST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.WEST).getData() == 0x0) != true){
			i++;
		}
		if (((block.getRelative(BlockFace.NORTH).getType() == Material.LAVA || block.getRelative(BlockFace.NORTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.NORTH).getData() == 0x0) != true){
			i++;
		}
		if (((block.getRelative(BlockFace.SOUTH).getType() == Material.LAVA || block.getRelative(BlockFace.SOUTH).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.SOUTH).getData() == 0x0) != true){
			i++;
		}
		if (((block.getRelative(BlockFace.NORTH_EAST).getType() == Material.LAVA || block.getRelative(BlockFace.NORTH_EAST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.NORTH_EAST).getData() == 0x0) != true){
			i++;
		}
		if (((block.getRelative(BlockFace.SOUTH_EAST).getType() == Material.LAVA || block.getRelative(BlockFace.SOUTH_EAST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.SOUTH_EAST).getData() == 0x0) != true){
			i++;
		}
		if (((block.getRelative(BlockFace.SOUTH_WEST).getType() == Material.LAVA || block.getRelative(BlockFace.SOUTH_WEST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.SOUTH_WEST).getData() == 0x0) != true){
			i++;
		}
		if (((block.getRelative(BlockFace.NORTH_WEST).getType() == Material.LAVA || block.getRelative(BlockFace.NORTH_WEST).getType() == Material.STATIONARY_LAVA) && block.getRelative(BlockFace.NORTH_WEST).getData() == 0x0) != true){
			i++;
		}

		return (i >=2 ) ? true : false;
	}
}