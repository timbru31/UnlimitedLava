package de.xghostkillerx.unlimitedlava;

import java.util.logging.Logger;

import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

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

	public Configuration config;
	public Boolean three;
	public Boolean two;
	public Boolean other;
	public Boolean big;
	public Boolean permissions;
	public Boolean messages;
	public static final Logger log = Logger.getLogger("Minecraft");
	private final UnlimitedLavaBlockListener blockListener = new UnlimitedLavaBlockListener(
			this);
	private final UnlimitedLavaPlayerListener playerListener = new UnlimitedLavaPlayerListener(
			this);

	// Shutdown
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " " + pdfFile.getVersion() + " has been disabled!");
	}

	// Start
	public void onEnable() {
		// Events
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_FROMTO, blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_BUCKET_FILL, playerListener, Event.Priority.Normal, this);

		// Config
		config = getConfiguration();
		reloadConfig();
		config.save();

		// Message
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " " + pdfFile.getVersion()	+ " is enabled!");
	}

	// Reload the config file, via command /unlimitedlava reload or /ulava reload and at the start!
	public void reloadConfig() {
		config.load();
		config.setHeader("# If you haven't understood the config (especially the point 'other' and 'big'),", "# please refer to this topic: http://bit.ly/n1Wex2 or http://bit.ly/pCj7v3", "# If you set messages to true, a message will be displayed, if a player without the", "# permission tries to use a unlimited block");
		three = config.getBoolean("three", true);
		two = config.getBoolean("two", true);
		other = config.getBoolean("other", false);
		big = config.getBoolean("big", false);
		permissions = config.getBoolean("permissions", true);
		messages = config.getBoolean("messages", true);
		config.save();
	}
	
	//Refer to UnlimitedLavaCommands
	public boolean onCommand(CommandSender sender, Command command,
			String commandLabel, String[] args) {
		UnlimitedLavaCommands cmd = new UnlimitedLavaCommands(this);
		return cmd.UnlimitedLavaCommand(sender, command, commandLabel, args);
	}
}