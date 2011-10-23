package de.xghostkillerx.unlimitedlava;

import java.util.logging.Logger;
import org.blockface.bukkitstats.CallHome;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.*;

/**
 * UnlimitedLava for CraftBukkit/Bukkit
 * Handles some general stuff!
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

public class UnlimitedLava extends JavaPlugin {
	
	public static final Logger log = Logger.getLogger("Minecraft");
	private final UnlimitedLavaBlockListener blockListener = new UnlimitedLavaBlockListener(this);
	private final UnlimitedLavaPlayerListener playerListener = new UnlimitedLavaPlayerListener(this);
	FileConfiguration config;

	// Shutdown
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " " + pdfFile.getVersion()	+ " has been disabled!");
	}

	// Start
	public void onEnable() {
		// Events
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_SPREAD, blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_FROMTO, blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_BUCKET_FILL, playerListener, Event.Priority.Normal, this);

		// Config
		config = this.getConfig();
		config.options().copyDefaults(true);
		loadConfig();

		// Message
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " " + pdfFile.getVersion()	+ " is enabled!");
		
		// Stats
		CallHome.load(this);
	}

	// Reload the config file, via command /unlimitedlava reload or /ulava reload and at the start!
	public void loadConfig() {
		config.options().header("For help please refer to this topic: http://bit.ly/n1Wex2 or http://bit.ly/pCj7v3");
		config.addDefault("sources.three", true);
		config.addDefault("sources.two", true);
		config.addDefault("sources.other", false);
		config.addDefault("sources.big", false);
		config.addDefault("sources.fall", true);
		config.addDefault("configuration.permissions", true);
		config.addDefault("configuration.messages", true);
		saveConfig();
	}
	
	//Refer to UnlimitedLavaCommands
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		UnlimitedLavaCommands cmd = new UnlimitedLavaCommands(this);
		return cmd.UnlimitedLavaCommand(sender, command, commandLabel, args);
	}
}