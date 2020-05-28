package de.dustplanet.unlimitedlava;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Levelled;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings(value = { "IMC_IMMATURE_CLASS_NO_TOSTRING" })
public class UnlimitedLavaCheck {
    private UnlimitedLava plugin;

    public UnlimitedLavaCheck(UnlimitedLava instance) {
        plugin = instance;
    }

    public boolean checkLavaSpreadValidity(Block block) {
        /*
         * NEW Consolidated check code. Returns true if block is fillable, false if block is not fillable. Our previous methods read several
         * blocks multiple times and the test logic is duplicated in two places. This processing time could be better spent elsewhere.
         * Previous code was duplicated between UnlimitedLavaBlockListener and UnlimitedLavaPlayerListener. Consolidation of these tests
         * allows changes to be made in one place. checkLavaSpreadValidity is intended to reduce redundancy and improve efficiency over our
         * previous code.
         */

        // Don't fill above air and don't initialize variables if we aren't
        // going to use them.
        if (block.getRelative(BlockFace.DOWN).getType() == Material.AIR) {
            return false;
        }

        // blockRelitive, blockMaterial, blockData, and blockIsSolid arrays
        // collect info of each block in a two block radius for later use in the
        // evaluation section.
        Material[] blockMaterial = new Material[24];
        int[] blockData = new int[24];
        boolean[] blockIsSolid = new boolean[24];
        Block[] blockRelative = { block.getRelative(BlockFace.NORTH), block.getRelative(BlockFace.NORTH_EAST),
                block.getRelative(BlockFace.EAST), block.getRelative(BlockFace.SOUTH_EAST), block.getRelative(BlockFace.SOUTH),
                block.getRelative(BlockFace.SOUTH_WEST), block.getRelative(BlockFace.WEST), block.getRelative(BlockFace.NORTH_WEST),
                block.getRelative(BlockFace.NORTH, 2), block.getRelative(BlockFace.NORTH_NORTH_EAST),
                block.getRelative(BlockFace.NORTH_EAST, 2), block.getRelative(BlockFace.EAST_NORTH_EAST),
                block.getRelative(BlockFace.EAST, 2), block.getRelative(BlockFace.EAST_SOUTH_EAST),
                block.getRelative(BlockFace.SOUTH_EAST, 2), block.getRelative(BlockFace.SOUTH_SOUTH_EAST),
                block.getRelative(BlockFace.SOUTH, 2), block.getRelative(BlockFace.SOUTH_SOUTH_WEST),
                block.getRelative(BlockFace.SOUTH_WEST, 2), block.getRelative(BlockFace.WEST_SOUTH_WEST),
                block.getRelative(BlockFace.WEST, 2), block.getRelative(BlockFace.WEST_NORTH_WEST),
                block.getRelative(BlockFace.NORTH_WEST, 2), block.getRelative(BlockFace.NORTH_NORTH_WEST) };
        int faces = 0, borders = 0, corners = 0, cBlocks = 0, lBlocks = 0, rBlocks = 0;
        boolean lake = false, fill = false;
        // cBlock, lBlock, and rBlock contain lists of blocks to be tested for
        // Corners, Lakes, and Rings respectively.
        int[][] cBlock = { { 0, 1, 2 }, { 2, 3, 4 }, { 4, 5, 6 }, { 6, 7, 0 } };
        int[][] lBlock = { { 8, 9, 10, 11, 12 }, { 12, 13, 14, 15, 16 }, { 16, 17, 18, 19, 20 }, { 20, 21, 22, 23, 8 } };
        int[][] rBlock = { { 6, 7, 23, 8, 9, 1, 2 }, { 0, 8, 9, 10, 11, 12, 2 }, { 0, 1, 11, 12, 13, 3, 4 }, { 2, 12, 13, 14, 15, 16, 4 },
                { 2, 3, 15, 16, 17, 5, 6 }, { 4, 16, 17, 18, 19, 20, 6 }, { 4, 5, 19, 20, 21, 7, 0 }, { 6, 20, 21, 22, 23, 8, 0 } };

        for (int i = 0; i < blockMaterial.length; i++) {
            blockMaterial[i] = blockRelative[i].getType();
            if (!(blockRelative[i].isLiquid() || blockRelative[i].isEmpty())) {
                blockIsSolid[i] = true;
            } else {
                blockIsSolid[i] = false;
            }
            if (blockMaterial[i] == Material.WATER || blockMaterial[i] == Material.LAVA) {
                blockData[i] = ((Levelled) blockRelative[i].getBlockData()).getLevel();
            } else {
                blockData[i] = -1;
            }
        }

        /*
         * Evaluations Faces and Borders: Faces are full blocks of lava one block N, S, E, or W of the target. Borders are any solid block
         * (not liquid or air) one block N, S, E, or W of the target. A diagonal solid is not considered a border because it would not
         * prevent flow from the target block.
         */
        for (int i = 0; i <= 3; i++) {
            int v = i * 2;
            if (blockMaterial[v] == Material.LAVA && blockData[v] == 0) {
                faces++;
            } else if (blockIsSolid[v]) {
                borders++;
            }
            /*
             * Corners and Lake Corners are three contiguous full lava blocks (face, diagonal, face) and are required to further test for a
             * lake. A Lake is valid when at least 4 blocks contiguous to the tested corner contain lava of any amount.
             */
            cBlocks = 0;
            for (int c = 0; c <= 2; c++) {
                if (blockMaterial[cBlock[i][c]] == Material.LAVA && blockData[cBlock[i][c]] == 0) {
                    cBlocks++;
                }
            }
            if (cBlocks == 3) {
                corners++;
                lBlocks = 0;
                for (int l = 0; l <= 4; l++) {
                    if (blockMaterial[lBlock[i][l]] == Material.LAVA) {
                        lBlocks++;
                    }
                    // Lake blocks do not need to be full beyond the previously tested corner.
                }
                if (lBlocks >= 4) {
                    lake = true;
                }
            }
        }
        if (plugin.isDebug()) {
            plugin.getLogger().info("Borders: " + borders + ", corners: " + corners + ", faces: " + faces + ", lake: " + lake
                    + ", cBlocks: " + cBlocks + ", lBlocks: " + lBlocks);
        }
        // Final Checks
        // Big, fill any block in the middle of a lake. Minimum requirement: 4
        // full faces, one full corner, and at least 4 lava blocks of any amount
        // contiguous with the full corner.
        if (faces == 4 && (lBlocks == 0 || lBlocks == 2 || lBlocks == 3 || lBlocks == 4 || lBlocks == 5) && plugin.isBig()) {
            // borders and corners are not used here because it they would be redundant and could invalidate a valid
            // fill.
            if (plugin.isDebug()) {
                plugin.getLogger().info("Detected a \"big\" pool - filling");
            }
            fill = true;
        }
        // Three, a 3x3 pool. Minimum Requirement: 4 full corners (includes faces)
        else if (borders == 0 && corners == 4 && faces == 4 && !lake && lBlocks == 0 && plugin.isThree()) {
            if (plugin.isDebug()) {
                plugin.getLogger().info("Detected a \"3x3\" pool - filling");
            }
            fill = true;
        }
        // Two. a 2x2 pool. Conditional requirements depending on status of Big
        // allows larger lava pools to be filled of other options are disabled.
        else if (borders == 2 && corners == 1 && faces == 2 && plugin.isTwo()) {
            if (plugin.isDebug()) {
                plugin.getLogger().info("Detected a \"2x2\" pool - filling");
            }
            fill = true;
        }
        // Plus, fills the center block of a + shape. Can be free standing.
        // Diagonal borders are no longer tested. Corners will invalidate valid
        // fills. Do not use either of these variables for Plus.
        else if (borders == 0 && corners == 0 && faces == 4 && plugin.isPlus()) {
            if (plugin.isDebug()) {
                plugin.getLogger().info("Detected a \"plus\" pool - filling");
            }
            fill = true;
        }
        // Other, a block will be filled from any 2 faces of full lava but only
        // if it is bordered by 2 solid blocks.
        else if (borders == 2 && corners == 0 && faces == 2 && plugin.isOther()) {
            if (plugin.isDebug()) {
                plugin.getLogger().info("Detected an \"other\" pool - filling");
            }
            fill = true;
        }
        // T Shape, the "T" block from Tetris. Requires 3 faces, 1 border.
        // Conditional 0 corners if Big is enabled to prevent shore filling.
        else if (borders == 1 && corners == 0 && faces == 3 && plugin.isTShape()) {
            if (plugin.isDebug()) {
                plugin.getLogger().info("Detected a \"T\" pool - filling");
            }
            fill = true;
        }
        // Ring, 7 full lava blocks around a solid. Block to be filled must have
        // 2 bordering solids.
        // The ring test it is evaluated only if necessary because it is a huge
        // test and not reusable.
        if (plugin.isRing()) {
            for (int i = 0; i <= 7; i++) {
                rBlocks = 0;
                for (int r = 0; r <= 6; r++) {
                    if (blockMaterial[rBlock[i][r]] == Material.LAVA && blockData[rBlock[i][r]] == 0) {
                        rBlocks++;
                    }
                }
                if (plugin.isDebug()) {
                    plugin.getLogger().info("rBlocks: " + rBlocks);
                }
                if (faces == 2 && borders == 2 && rBlocks == 7 && corners == 0) {
                    if (plugin.isDebug()) {
                        plugin.getLogger().info("Detected a \"ring\" pool - filling");
                    }
                    fill = true;
                    break;
                }
            }
        }
        return fill;
    }
}
