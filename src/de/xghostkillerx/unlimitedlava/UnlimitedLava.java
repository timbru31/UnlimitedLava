package de.xghostkillerx.unlimitedlava;

import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
* UnlimitedLava for CraftBukkit
*
* @author xGhOsTkiLLeRx
*/

public class UnlimitedLava extends JavaPlugin
{
public static final Logger log = Logger.getLogger("Minecraft");
private final UnlimitedLavaBlockListener blockListener = new UnlimitedLavaBlockListener(this);

//Shutdown
public void onDisable() {
PluginDescriptionFile pdfFile = this.getDescription();
        log.info(pdfFile.getName()+" " +pdfFile.getVersion()+" has been disabled!");
}
//Start
public void onEnable() {
PluginDescriptionFile pdfFile = this.getDescription();
PluginManager pm = getServer().getPluginManager();
log.info(pdfFile.getName()+" " +pdfFile.getVersion()+" is enabled!");
pm.registerEvent(Event.Type.BLOCK_FROMTO,blockListener,Event.Priority.Normal,this);
}

}