package de.dustplanet.unlimitedlava;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * UnlimitedLavaCommands
 * Handles all commands!
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

public class UnlimitedLavaCommands implements CommandExecutor {

	UnlimitedLava plugin;
	public UnlimitedLavaCommands(UnlimitedLava instance) {
		plugin = instance;
	}
	private String[] values = {"three", "two", "other", "big", "lava_fall", "water_fall", "plus", "T"};
	private String message, value;
	private int i;

	// Commands... First check if config value permissions is true
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		// reload
		if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
			if (sender.hasPermission("unlimitedlava.reload") || !plugin.permissions) {
				UnlimitedLavaReload(sender);
				return true;
			}
			else {
				message = plugin.localization.getString("permission_denied");
				plugin.message(sender, null, message, null);
				return true;
			}
		}
		// status
		else if (args.length > 0 && args[0].equalsIgnoreCase("status")) {
			if (sender.hasPermission("unlimitedlava.status") || !plugin.permissions) {
				UnlimitedLavaStatus(sender);
				return true;
			}
			else {
				message = plugin.localization.getString("permission_denied");
				plugin.message(sender, null, message, null);
				return true;
			}
		}
		// help
		else if (args.length > 0 && args[0].equalsIgnoreCase("help")) {
			if (sender.hasPermission("unlimitedlava.help") || !plugin.permissions) {
				UnlimitedLavaHelp(sender);
				return true;
			}
			else {
				message = plugin.localization.getString("permission_denied");
				plugin.message(sender, null, message, null);
				return true;
			}
		}
		// Enable
		else if (args.length > 0 && args[0].equalsIgnoreCase("enable")) {
			// Enable all
			if (args.length > 1 && args[1].equalsIgnoreCase("all")) {
				if (sender.hasPermission("unlimitedlava.enable.all") || !plugin.permissions) {
					UnlimitedLavaEnableAll(sender);
					return true;
				} 
				else {
					message = plugin.localization.getString("permission_denied");
					plugin.message(sender, null, message, null);
					return true;
				}
			}
			// Enable a source
			else if (args.length > 1 && Arrays.asList(values).contains(args[1])) {
				value = args[1];
				if (sender.hasPermission("unlimitedlava.enable." + args[1]) || !plugin.permissions) {
					UnlimitedLavaEnableSource(sender, value);
					return true;
				}
				else {
					message = plugin.localization.getString("permission_denied");
					plugin.message(sender, null, message, null);
					return true;
				}
			}
			// Enable furnace
			else if (args.length > 1 && args[1].equalsIgnoreCase("furnace")) {
				if (sender.hasPermission("unlimitedlava.enable.furnace") || !plugin.permissions) {
					UnlimitedLavaEnableFurnace(sender);
					return true;
				} 
				else {
					message = plugin.localization.getString("permission_denied");
					plugin.message(sender, null, message, null);
					return true;
				}
			}
			// Enable permissions
			else if (args.length > 1 && args[1].equalsIgnoreCase("permissions")) {
				if (sender.hasPermission("unlimitedlava.enable.permissions") || !plugin.permissions) {
					UnlimitedLavaEnablePermissions(sender);
					return true;
				}
				else {
					message = plugin.localization.getString("permission_denied");
					plugin.message(sender, null, message, null);
					return true;
				}
			}
			// Enable messages
			else if (args.length > 1 && args[1].equalsIgnoreCase("messages")) {
				if (sender.hasPermission("unlimitedlava.enable.messages") || !plugin.permissions) {
					UnlimitedLavaEnableMessages(sender);
					return true;
				}
				else {
					message = plugin.localization.getString("permission_denied");
					plugin.message(sender, null, message, null);
					return true;
				}
			}
		}
		// Disable
		else if (args.length > 0 && args[0].equalsIgnoreCase("disable")) {
			// Disable all
			if (args.length > 1 && args[1].equalsIgnoreCase("all")) {
				if (sender.hasPermission("unlimitedlava.disable.all") || !plugin.permissions) {
					UnlimitedLavaDisableAll(sender);
					return true;
				}
				else {
					message = plugin.localization.getString("permission_denied");
					plugin.message(sender, null, message, null);
					return true;
				}
			}
			// Disable a source
			else if (args.length > 1 && Arrays.asList(values).contains(args[1])) {
				value = args[1];
				if (sender.hasPermission("unlimitedlava.disable." + args[1]) || !plugin.permissions) {
					UnlimitedLavaDisableSource(sender, value);
					return true;
				}
				else {
					message = plugin.localization.getString("permission_denied");
					plugin.message(sender, null, message, null);
					return true;
				}
			}
			// Disable permissions
			else if (args.length > 1 && args[1].equalsIgnoreCase("permissions")) {
				if (sender.hasPermission("unlimitedlava.disable.permissions") || !plugin.permissions) {
					UnlimitedLavaDisablePermissions(sender);
					return true;
				}
				else {
					message = plugin.localization.getString("permission_denied");
					plugin.message(sender, null, message, null);
					return true;
				}
			}
			// Disable messages
			else if (args.length > 1 && args[1].equalsIgnoreCase("messages")) {
				if (sender.hasPermission("unlimitedlava.disable.messages") || !plugin.permissions) {
					UnlimitedLavaDisableMessages(sender);
					return true;
				}
				else {
					message = plugin.localization.getString("permission_denied");
					plugin.message(sender, null, message, null);
					return true;
				}
			}
			// Disable furnace
			else if (args.length > 1 && args[1].equalsIgnoreCase("furnace")) {
				if (sender.hasPermission("unlimitedlava.disable.furnace") || !plugin.permissions) {
					UnlimitedLavaDisableFurnace(sender);
					return true;
				} 
				else {
					message = plugin.localization.getString("permission_denied");
					plugin.message(sender, null, message, null);
					return true;
				}
			}
		}
		return false;
	}

	private void UnlimitedLavaStatus(CommandSender sender) {
		String header = ChatColor.YELLOW + "Status of UnlimitedLava";
		String string = "";
		// Big
		if (plugin.big) string += "Big: " +  ChatColor.DARK_GREEN + plugin.big + ChatColor.WHITE + ", ";
		else string += "Big: " +  ChatColor.DARK_RED + plugin.big + ChatColor.WHITE + ", ";
		// Three
		if (plugin.three) string += "three: " +  ChatColor.DARK_GREEN + plugin.three + ChatColor.WHITE + ", ";
		else string += "three: " +  ChatColor.DARK_RED + plugin.three + ChatColor.WHITE + ", ";
		// Two
		if (plugin.two) string += "two: " +  ChatColor.DARK_GREEN + plugin.two + ChatColor.WHITE + ", ";
		else string += "two:" +  ChatColor.DARK_RED + plugin.two + ChatColor.WHITE + ", ";
		string += "\n";
		// Other
		if (plugin.other) string += "other: " +  ChatColor.DARK_GREEN + plugin.other + ChatColor.WHITE + ", ";
		else string += "other: " +  ChatColor.DARK_RED + plugin.other + ChatColor.WHITE + ", ";
		// Plus
		if (plugin.plus) string += "plus: " +  ChatColor.DARK_GREEN + plugin.plus + ChatColor.WHITE + ", ";
		else string += "plus: " +  ChatColor.DARK_RED + plugin.plus + ChatColor.WHITE + ", ";
		// T
		if (plugin.T) string += "T: " +  ChatColor.DARK_GREEN + plugin.T + ChatColor.WHITE + ", ";
		else string += "T: " +  ChatColor.DARK_RED + plugin.T + ChatColor.WHITE + ", ";
		string += "\n";
		// LavaFall
		if (plugin.lavaFall) string += "lavaFall: " +  ChatColor.DARK_GREEN + plugin.lavaFall + ChatColor.WHITE + ", ";
		else string += "lavaFall: " +  ChatColor.DARK_RED + plugin.lavaFall + ChatColor.WHITE + ", ";
		// WaterFall
		if (plugin.waterFall) string += "waterFall: " +  ChatColor.DARK_GREEN + plugin.waterFall + ChatColor.WHITE + ", ";
		else string += "waterFall: " +  ChatColor.DARK_RED + plugin.waterFall + ChatColor.WHITE + ", ";
		// MinHeight
		string += "minHeight: " +  ChatColor.YELLOW + plugin.height + ChatColor.WHITE + ", ";
		string += "\n";
		// furnace
		if (plugin.furnace) string += "furnace: " +  ChatColor.DARK_GREEN + plugin.furnace + ChatColor.WHITE + ", ";
		else string += "furnace: " +  ChatColor.DARK_RED + plugin.furnace + ChatColor.WHITE + ", ";
		// Permissions
		if (plugin.permissions) string += "permissions: " +  ChatColor.DARK_GREEN + plugin.permissions + ChatColor.WHITE + ", ";
		else string += "permissions: " +  ChatColor.DARK_RED + plugin.permissions + ChatColor.WHITE + ", ";
		// Messages
		if (plugin.messages) string += "messages: " +  ChatColor.DARK_GREEN + plugin.messages + ChatColor.WHITE + ", ";
		else string += "messages: " +  ChatColor.DARK_RED + plugin.messages + ChatColor.WHITE + ", ";
		String worlds = ChatColor.YELLOW + "Active worlds: " + ChatColor.DARK_GREEN + plugin.enabledWords.toString().replace("[", "").replace("]", "");
		sender.sendMessage(header);
		sender.sendMessage(string);
		sender.sendMessage(worlds);

	}

	// Reloads the config with /unlimitedlava reload or /ulava reload
	private void UnlimitedLavaReload(CommandSender sender) {
		plugin.loadConfigsAgain();		
		plugin.loadValues();
		message = plugin.localization.getString("reload");
		plugin.message(sender, null, message, null);
	}

	// See the help with /unlimitedlava help or /ulava help
	private void UnlimitedLavaHelp(CommandSender sender) {
		for (i = 1; i <= 8; i++) {
			message = plugin.localization.getString("help_" + Integer.toString(i));
			plugin.message(sender, null, message, null);
		}
	}

	// Enable a source
	private void UnlimitedLavaEnableSource(CommandSender sender, String value) {
		plugin.config.set("sources." + value, true);
		plugin.saveConfig();
		plugin.loadValues();
		message = plugin.localization.getString("enable_source");
		plugin.message(sender, null, message, value);
	}

	// Enables all sources with /unlimitedlava enable all or /ulava enable all
	private void UnlimitedLavaEnableAll(CommandSender sender) {
		plugin.config.set("sources.three", true);
		plugin.config.set("sources.two", true);
		plugin.config.set("sources.other", true);
		plugin.config.set("sources.big", true);
		plugin.config.set("sources.plus", true);
		plugin.config.set("sources.T", true);
		plugin.config.set("sources.water_fall", true);
		plugin.config.set("sources.lava_fall", true);
		plugin.saveConfig();
		plugin.three = true;
		plugin.two = true;
		plugin.other = true;
		plugin.plus = true;
		plugin.big = true;
		plugin.T = true;
		plugin.waterFall = true;
		plugin.lavaFall = true;
		message = plugin.localization.getString("enable_all");
		plugin.message(sender, null, message, null);
	}

	// Enables permissions with /unlimitedlava enable permissions or /ulava enable permissions
	private void UnlimitedLavaEnablePermissions(CommandSender sender) {
		plugin.config.set("configuration.permissions", true);
		plugin.saveConfig();
		plugin.permissions = true;
		for (i = 1; i <= 2; i++) {
			message = plugin.localization.getString("enable_permissions_" + Integer.toString(i));
			plugin.message(sender, null, message, null);
		}
	}

	// Enables messages with /unlimitedlava enable messages or /ulava enable messages
	private void UnlimitedLavaEnableMessages(CommandSender sender) {
		plugin.config.set("configuration.messages", true);
		plugin.saveConfig();
		plugin.messages = true;
		message = plugin.localization.getString("enable_messages");
		plugin.message(sender, null, message, null);
	}

	// Enables furnace with /unlimitedlava enable furnace or /ulava enable furnace
	private void UnlimitedLavaEnableFurnace(CommandSender sender) {
		plugin.config.set("configuration.furnace", true);
		plugin.saveConfig();
		plugin.furnace = true;
		message = plugin.localization.getString("enable_furnace");
		plugin.message(sender, null, message, null);
	}

	// Disable a source
	private void UnlimitedLavaDisableSource(CommandSender sender, String value) {
		plugin.config.set("sources." + value, false);
		plugin.saveConfig();
		plugin.loadValues();
		message = plugin.localization.getString("disable_source");
		plugin.message(sender, null, message, value);
	}

	// Disables all sources with /unlimitedlava disable all or /ulava disable all
	private void UnlimitedLavaDisableAll(CommandSender sender) {
		plugin.config.set("sources.three", false);
		plugin.config.set("sources.two", false);
		plugin.config.set("sources.other", false);
		plugin.config.set("sources.big", false);
		plugin.config.set("sources.plus", false);
		plugin.config.set("sources.T", false);
		plugin.config.set("sources.water_fall", false);
		plugin.config.set("sources.lava_fall", false);
		plugin.saveConfig();
		plugin.three = false;
		plugin.two = false;
		plugin.other = false;
		plugin.plus = false;
		plugin.big = false;
		plugin.T = false;
		plugin.waterFall = false;
		plugin.lavaFall = false;
		message = plugin.localization.getString("disable_all");
		plugin.message(sender, null, message, null);
	}

	// Disables permissions with /unlimitedlava disable permissions or /ulava disable permissions
	private void UnlimitedLavaDisablePermissions(CommandSender sender) {
		plugin.config.set("configuration.permissions", false);
		plugin.saveConfig();
		plugin.permissions = false;
		for (i = 1; i <= 2; i++) {
			message = plugin.localization.getString("disable_permissions_" + Integer.toString(i));
			plugin.message(sender, null, message, null);
		}
	}

	// Disables messages with /unlimitedlava disable messages or /ulava disable messages
	private void UnlimitedLavaDisableMessages(CommandSender sender) {
		plugin.config.set("configuration.messages", false);
		plugin.saveConfig();
		plugin.messages = false;
		message = plugin.localization.getString("disable_messages");
		plugin.message(sender, null, message, null);
	}

	// Disables furnace with /unlimitedlava disable furnace or /ulava disable furnace
	private void UnlimitedLavaDisableFurnace(CommandSender sender) {
		plugin.config.set("configuration.furnace", false);
		plugin.saveConfig();
		plugin.furnace = false;
		message = plugin.localization.getString("disable_furnace");
		plugin.message(sender, null, message, null);
	}
}
