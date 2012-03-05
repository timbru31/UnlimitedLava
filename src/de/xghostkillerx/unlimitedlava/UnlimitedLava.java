package de.xghostkillerx.unlimitedlava;

import java.io.*;
import java.util.logging.Logger;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.*;
import org.bukkit.entity.Player;

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
	private final UnlimitedLavaInventoryListener inventoryListener = new UnlimitedLavaInventoryListener(this);
	public FileConfiguration config;
	public FileConfiguration localization;
	public File configFile;
	public File localizationFile;
	private UnlimitedLavaCommands executor;

	// Shutdown
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " " + pdfFile.getVersion()	+ " has been disabled!");
	}

	// Start
	public void onEnable() {
		// Events
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(blockListener, this);
		pm.registerEvents(playerListener, this);
		pm.registerEvents(inventoryListener, this);

		// Config
		configFile = new File(getDataFolder(), "config.yml");
		if (!configFile.exists()) {
	        configFile.getParentFile().mkdirs();
	        copy(getResource("config.yml"), configFile);
	    }
		config = this.getConfig();
		loadConfig();
		
		// Localization
		localizationFile = new File(getDataFolder(), "localization.yml");
		if(!localizationFile.exists()){
			localizationFile.getParentFile().mkdirs();
			copy(getResource("localization.yml"), localizationFile);
		}
		// Try to load
		try {
			localization = YamlConfiguration.loadConfiguration(localizationFile);
			loadLocalization();
		}
		// if it failed, tell it
		catch (Exception e) {
			log.warning("CookMe failed to load the localization!");
		}
		
		//Refer to UnlimitedLavaCommands
		executor = new UnlimitedLavaCommands(this);
		getCommand("unlimitedlava").setExecutor(executor);

		// Message
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " " + pdfFile.getVersion()	+ " is enabled!");
		
		// Stats
		try {
			Metrics metrics = new Metrics();
			metrics.beginMeasuringPlugin(this);
		}
		catch (IOException e) {}
	}

	// Reloads the config file, via command /unlimitedlava reload or /ulava reload and at the start!
	public void loadConfig() {
		config.options().header("For help please refer to this topic: http://bit.ly/n1Wex2 or http://bit.ly/pCj7v3");
		config.addDefault("configuration.permissions", true);
		config.addDefault("configuration.messages", true);
		config.addDefault("sources.three", true);
		config.addDefault("sources.two", true);
		config.addDefault("sources.other", false);
		config.addDefault("sources.big", false);
		config.addDefault("fall.lava", true);
		config.options().copyDefaults(true);
		saveConfig();
	}
	
	// Loads the localization
	public void loadLocalization() {
		localization.options().header("The underscores are used for the different lines!");
		localization.addDefault("permission_denied", "&4You don''t have the permission to do this!");
		localization.options().copyDefaults(true);
		saveLocalization();
	}

	// Saves the localization
	public void saveLocalization() {
		try {
			localization.save(localizationFile);
		}
		catch (IOException e) {
			log.warning("CookMe failed to save the localization! Please report this!");
		}
	}
	
	// Reloads the config via command /unlimitedlava reload or /ulava reload
	public void loadConfigAgain() {
		try {
			config.load(configFile);
			saveConfig();
			localization.load(localizationFile);
			saveLocalization();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// If no config is found, copy the default one!
	private void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len=in.read(buf)) > 0) {
				out.write(buf,0,len);
			}
			out.close();
			in.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Message the sender or player
	public void message(CommandSender sender, Player player, String message, String value) {
		PluginDescriptionFile pdfFile = this.getDescription();
		message = message
				.replaceAll("&([0-9a-fk])", "\u00A7$1")
				.replaceAll("%version", pdfFile.getVersion())
				.replaceAll("%effect", value)
				.replaceAll("%value", value);
		if (player != null) {
			player.sendMessage(message);
		}
		else if (sender != null) {
			sender.sendMessage(message);
		}
	}
}