package de.xghostkillerx.unlimitedlava;

import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * UnlimitedLava for CraftBukkit
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

public class UnlimitedLava extends JavaPlugin {

    public static final Logger log = Logger.getLogger("Minecraft");
    private final UnlimitedLavaBlockListener blockListener = new UnlimitedLavaBlockListener(this);

    /*
     * Shutdown
     * 
     * Ende
     */
    public void onDisable() {
        PluginDescriptionFile pdfFile = this.getDescription();
        log.info(pdfFile.getName() + " " + pdfFile.getVersion() + " has been disabled!");
    }

    // Start
    public void onEnable() {
        PluginDescriptionFile pdfFile = this.getDescription();
        PluginManager pm = getServer().getPluginManager();
        log.info(pdfFile.getName() + " " + pdfFile.getVersion() + " is enabled!");
        pm.registerEvent(Event.Type.BLOCK_FROMTO, blockListener, Event.Priority.Normal, this);
    }
}