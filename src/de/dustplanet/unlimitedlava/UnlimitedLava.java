package de.dustplanet.unlimitedlava;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.World;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
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
 * @thanks to ferrybig for the awesome fall code!
 * @thanks to Xastabus for the cool improvements of the checks!
 * 
 */

public class UnlimitedLava extends JavaPlugin {
	public static final Logger log = Logger.getLogger("Minecraft");
	private final UnlimitedLavaBlockListener blockListener = new UnlimitedLavaBlockListener(this);
	private final UnlimitedLavaPlayerListener playerListener = new UnlimitedLavaPlayerListener(this);
	private final UnlimitedLavaInventoryListener inventoryListener = new UnlimitedLavaInventoryListener(this);
	public boolean three, two, other, big, plus, T, lavaFall, waterFall, messages = true, permissions = true, furnace;
	public int height = 60;
	public List<String> enabledWords = new ArrayList<String>();
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
		loadValues();
		
		// Localization
		localizationFile = new File(getDataFolder(), "localization.yml");
		if(!localizationFile.exists()){
			localizationFile.getParentFile().mkdirs();
			copy(getResource("localization.yml"), localizationFile);
		}
		// Try to load
		localization = YamlConfiguration.loadConfiguration(localizationFile);
		loadLocalization();

		
		//Refer to UnlimitedLavaCommands
		executor = new UnlimitedLavaCommands(this);
		getCommand("unlimitedlava").setExecutor(executor);

		// Message
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " " + pdfFile.getVersion()	+ " is enabled!");
		
		// Stats
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {}
	}

	// Reloads the config file, via command /unlimitedlava reload or /ulava reload and at the start!
	public void loadConfig() {
		config.options().header("For help please refer to this topic: http://bit.ly/n1Wex2 or http://bit.ly/pCj7v3");
		config.addDefault("configuration.permissions", true);
		config.addDefault("configuration.messages", true);
		config.addDefault("configuration.furnace", true);
		config.addDefault("configuration.height", 60);
		config.addDefault("sources.three", true);
		config.addDefault("sources.two", true);
		config.addDefault("sources.other", false);
		config.addDefault("sources.big", false);
		config.addDefault("sources.plus", true);
		config.addDefault("sources.T", true);
		config.addDefault("sources.lava_fall", true);
		config.addDefault("sources.water_fall", false);
		config.addDefault("furnace.item", "BUCKET");
		List<World> temp = getServer().getWorlds();
		List<String> tempList = new ArrayList<String>();
		for (World w : temp) {
			tempList.add(w.getName());
		}
		config.addDefault("enabled_worlds", tempList);
		config.options().copyDefaults(true);
		saveConfig();
	}
	
	// Load the values into memory
	public void loadValues() {
		three = config.getBoolean("sources.three");
		two = config.getBoolean("sources.two");
		other = config.getBoolean("sources.other");
		big = config.getBoolean("sources.big");
		plus = config.getBoolean("sources.plus");
		T = config.getBoolean("sources.T");
		lavaFall = config.getBoolean("sources.lava_fall");
		waterFall = config.getBoolean("sources.water_fall");
		permissions = config.getBoolean("configuration.permissions");
		messages = config.getBoolean("configuration.messages");
		furnace = config.getBoolean("configuration.furnace");
		height = config.getInt("configuration.height");
		enabledWords = config.getStringList("enabled_worlds");
	}
	
	// Loads the localization
	public void loadLocalization() {
		localization.options().header("The underscores are used for the different lines!");
		localization.addDefault("permission_denied", "&4You don''t have the permission to do this!");
		localization.addDefault("reload", "&2UnlimitedLava &4%version &2reloaded!");
		localization.addDefault("help_1", "&2Welcome to the UnlimitedLava version &4%version &2help");
		localization.addDefault("help_2", "To see the help type &4/unlimitedlava help &for &4/ulava help");
		localization.addDefault("help_3", "To reload use &4/unlimitedlava reload &for &4/ulava reload");
		localization.addDefault("help_4", "To enable something use &4/unlimitedlava enable &e<value>");
		localization.addDefault("help_5", "or &4/ulava enable &e<value>");
		localization.addDefault("help_6", "To disable something use &4/unlimitedlava disable &e<value>");
		localization.addDefault("help_7", "or &4/ulava disable &e<value>");
		localization.addDefault("help_8", "&eValues: &fpermissions, messages, furnace, all, three, two, other, big, lava_fall, water_fall, plus, T");
		localization.addDefault("help_9", "&eThe status of UnlimitedLava can be seen with &4/ulava status");
		localization.addDefault("enable_source", "&2UnlimitedLava source &4%source &2enabled!");
		localization.addDefault("enable_all", "&4All &2UnlimitedLava sources enabled!");
		localization.addDefault("enable_messages", "&2UnlimitedLava messages enabled!");
		localization.addDefault("enable_permissions_1", "&2UnlimitedLava permissions enabled! Only OPs");
		localization.addDefault("enable_permissions_2", "&2or players with the permission can use the plugin!");
		localization.addDefault("enable_furnace", "&2UnlimitedLava &4furnace &2enabled!");
		localization.addDefault("disable_source", "&2UnlimitedLava source &4%source &2disabled!");
		localization.addDefault("disable_all", "&4All &2UnlimitedLava sources disabled!");
		localization.addDefault("disable_messages", "&2UnlimitedLava messages disabled!");
		localization.addDefault("disable_permissions_1", "&2UnlimitedLava permissions disabled! Only OPs");
		localization.addDefault("disable_permissions_2", "&4All players can use the plugin!");
		localization.addDefault("disable_furnace", "&2UnlimitedLava &4furnace &2disabled!");
		localization.options().copyDefaults(true);
		saveLocalization();
	}

	// Saves the localization
	public void saveLocalization() {
		try {
			localization.save(localizationFile);
		} catch (IOException e) {
			log.warning("UnlimitedLava failed to save the localization! Please report this! (I/O)");
		}
	}
	
	// Reloads the config via command /unlimitedlava reload or /ulava reload
	public void loadConfigsAgain() {
		try {
			config.load(configFile);
			saveConfig();
			localization.load(localizationFile);
			saveLocalization();
		} catch (FileNotFoundException e) {
			log.warning("UnlimitedLava failed to save the localization! Please report this! (FileNotFound)");
		} catch (IOException e) {
			log.warning("UnlimitedLava failed to save the localization! Please report this! (I/O)");
		} catch (InvalidConfigurationException e) {
			log.warning("UnlimitedLava failed to save the localization! Please report this! (InvalidConfiguration)");
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Message the sender or player
	public void message(CommandSender sender, Player player, String message, String value) {
		PluginDescriptionFile pdfFile = this.getDescription();
		message = message
				.replaceAll("&([0-9a-fk-or])", "\u00A7$1")
				.replaceAll("%version", pdfFile.getVersion())
				.replaceAll("%source", value)
				.replaceAll("%value", value);
		if (player != null) {
			player.sendMessage(message);
		}
		else if (sender != null) {
			sender.sendMessage(message);
		}
	}
}