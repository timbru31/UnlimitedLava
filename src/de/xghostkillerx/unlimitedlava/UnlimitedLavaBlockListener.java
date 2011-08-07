package de.xghostkillerx.unlimitedlava;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockListener;

/**
 * UnlimitedLavaBlockListener
 * 
 * Refer to:
 * http://forums.bukkit.org/threads/19441/
 *
 * @author xGhOsTkiLLeRx
 * @thanks to loganwm for the help!!
 * 
 * All the comments will be first in English and second in German!
 * (Because I am German, but I want that you understand my source, too!)
 */

public class UnlimitedLavaBlockListener extends BlockListener {

    public static UnlimitedLava plugin;

    public UnlimitedLavaBlockListener(UnlimitedLava instance) {
        plugin = instance;
    }

    public void onBlockFromTo(BlockFromToEvent event) {
        Block sourceBlock = event.getBlock();
        Block targetBlock = event.getToBlock();


        /**
         * Refer to http://www.minecraftwiki.net/wiki/Data_values#Water_and_Lava
         * Check if we got a full block of lava
         * 
         * Verweis: http://www.minecraftwiki.net/wiki/Data_values#Water_and_Lava
         * Check für vollen Lava Block (0x00)
         */
        if (event.getBlock().getData() != 0x0) {
            return;
        }


        if ((sourceBlock.getType() == Material.LAVA || sourceBlock.getType() == Material.STATIONARY_LAVA)) {
            /**
             * Check if we can use the surrounded check
             * 
             * Checke ob der Surround-Check möglich ist
             */
            if (targetBlock.getType() == Material.LAVA || targetBlock.getType() == Material.STATIONARY_LAVA) {
                /**
                 * Full block (0x0) and not falling (0x08)
                 * 
                 * Voller Block und nicht nach unten fallend (0x08)
                 */
                if (targetBlock.getData() != 0x0 && targetBlock.getData() != 0x08) {
                    /**
                     * Spread if possible
                     * 
                     * Ausdehnung, wenn möglich
                     */
                    if (checkSpreadValidity(targetBlock)) {
                        /**
                         * Only full blocks
                         * 
                         * Nur volle Blöcke (0x0)
                         */
                        event.getToBlock().setType(Material.LAVA);
                    }
                }
            } 
            /**
             * If the block flows into air, check if the air can get a full lava block
             * 
             * Wenn der Block in Luft fließt, prüfe ob die Luft zu einem vollen Lava Block werden kann
             */
            else if (targetBlock.getType() == Material.AIR) {
                /**
                 * Spread if possible
                 * 
                 * Dehne aus, wenn möglich
                 */
                if (checkSpreadValidity(event.getToBlock())) {
                    /**
                     * È voilá¡, we get a full lava block!
                     * 
                     * È voilá¡, wir haben einen Lava Block (0x0)
                     */
                    event.getToBlock().setType(Material.LAVA);
                    event.getToBlock().setData((byte) 0x0);
                }
            }
        }
    }

    private boolean checkSpreadValidity(Block block) {
        int n = 0;
        /**
         * defines the number of valid "source" lava flows surrounding this block
         * Sets the value of valid lava flows, sorrounding this block
         * 
         * Setzt die Anzahl der Lavaflüße um diesen einen Block fest
         */
        /**
         * If a real flow of lava exists, add to the count
         * 
         * Wenn ein Lavafluss besteht, zum Zähler hinzufügen
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

        // if (block.getFace(BlockFace.DOWN).getType() == Material.AIR)
        //return false;

        /**
         * If more than 2 flows exists, yay, it's okay!
         * 
         * Wenn es mehr als 2 Lavaflüsse gibt, stimmt es und ist in Ordnung :D
         */
        if (n >= 2) {
            return true;
        } else {
            return false;
        }

    }
}