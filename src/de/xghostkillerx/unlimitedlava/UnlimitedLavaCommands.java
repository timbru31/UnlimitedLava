package de.xghostkillerx.unlimitedlava;

import java.util.Arrays;
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
 * 
 */

public class UnlimitedLavaCommands implements CommandExecutor {

	UnlimitedLava plugin;
	public UnlimitedLavaCommands(UnlimitedLava instance) {
		plugin = instance;
	}
	private String[] values = {"three", "two", "other", "big", "lava_fall", "water_fall"};
	private String message, value;
	private int i;

	// Commands... First check if config value permissions is true
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		//reload
		if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
			if (plugin.config.getBoolean("configuration.permissions") == true) {
				if (sender.hasPermission("unlimitedlava.reload")) {
					UnlimitedLavaReload(sender);
					return true;
				}
				else {
					message = plugin.localization.getString("permission_denied");
					plugin.message(sender, null, message, null);
					return true;
				}
			}
			if (plugin.config.getBoolean("configuration.permissions") == false) {
				UnlimitedLavaReload(sender);
				return true;
			}
		}
		// help
		if (args.length > 0 && args[0].equalsIgnoreCase("help")) {
			if (plugin.config.getBoolean("configuration.permissions") == true) {
				if (sender.hasPermission("unlimitedlava.help")) {
					UnlimitedLavaHelp(sender);
					return true;
				}
				else {
					message = plugin.localization.getString("permission_denied");
					plugin.message(sender, null, message, null);
					return true;
				}
			}
			if (plugin.config.getBoolean("configuration.permissions") == false) {
				UnlimitedLavaHelp(sender);
				return true;
			}
		}
		// Enable
		if (args.length > 0 && args[0].equalsIgnoreCase("enable")) {
			// Enable all
			if (args.length > 1 && args[1].equalsIgnoreCase("all")) {
				if (plugin.config.getBoolean("configuration.permissions") == true) {
					if (sender.hasPermission("unlimitedlava.enable.all")) {
						UnlimitedLavaEnableAll(sender);
						return true;
					} 
					else {
						message = plugin.localization.getString("permission_denied");
						plugin.message(sender, null, message, null);
						return true;
					}
				}
				if (plugin.config.getBoolean("configuration.permissions") == false) {
					UnlimitedLavaEnableAll(sender);
					return true;
				}
			}
			// Enable a source
			if (args.length > 1 && Arrays.asList(values).contains(args[1])) {
				value = args[1];
				if (plugin.config.getBoolean("configuration.permissions") == true) {
					if (sender.hasPermission("unlimitedlava.ensable." + args[1])) {
						UnlimitedLavaEnableSource(sender, value);
						return true;
					}
					else {
						message = plugin.localization.getString("permission_denied");
						plugin.message(sender, null, message, null);
						return true;
					}
				}
				if (plugin.config.getBoolean("configuration.permissions") == false) {
					UnlimitedLavaEnableSource(sender, value);
					return true;
				}
			}
			// Enable furnace
			if (args.length > 1 && args[1].equalsIgnoreCase("furnace")) {
				if (plugin.config.getBoolean("configuration.permissions") == true) {
					if (sender.hasPermission("unlimitedlava.enable.furnace")) {
						UnlimitedLavaEnableFurnace(sender);
						return true;
					} 
					else {
						message = plugin.localization.getString("permission_denied");
						plugin.message(sender, null, message, null);
						return true;
					}
				}
				if (plugin.config.getBoolean("configuration.permissions") == false) {
					UnlimitedLavaEnableFurnace(sender);
					return true;
				}
			}
			// Enable permissions
			if (args.length > 1 && args[1].equalsIgnoreCase("permissions")) {
				if (plugin.config.getBoolean("configuration.permissions") == true) {
					if (sender.hasPermission("unlimitedlava.enable.permissions")) {
						UnlimitedLavaEnablePermissions(sender);
						return true;
					}
					else {
						message = plugin.localization.getString("permission_denied");
						plugin.message(sender, null, message, null);
						return true;
					}
				}
				if (plugin.config.getBoolean("configuration.permissions") == false) {
					UnlimitedLavaEnablePermissions(sender);
					return true;
				}
			}
			// Enable messages
			if (args.length > 1 && args[1].equalsIgnoreCase("messages")) {
				if (plugin.config.getBoolean("configuration.permissions") == true) {
					if (sender.hasPermission("unlimitedlava.enable.messages")) {
						UnlimitedLavaEnableMessages(sender);
						return true;
					}
					else {
						message = plugin.localization.getString("permission_denied");
						plugin.message(sender, null, message, null);
						return true;
					}
				}
				if (plugin.config.getBoolean("configuration.permissions") == false) {
					UnlimitedLavaEnableMessages(sender);
					return true;
				}
			}
		}
		// Disable
		if (args.length > 0 && args[0].equalsIgnoreCase("disable")) {
			// Disable all
			if (args.length > 1 && args[1].equalsIgnoreCase("all")) {
				if (plugin.config.getBoolean("configuration.permissions") == true) {
					if (sender.hasPermission("unlimitedlava.disable.all")) {
						UnlimitedLavaDisableAll(sender);
						return true;
					}
					else {
						message = plugin.localization.getString("permission_denied");
						plugin.message(sender, null, message, null);
						return true;
					}
				}
				if (plugin.config.getBoolean("configuration.permissions") == false) {
					UnlimitedLavaDisableAll(sender);
					return true;
				}
			}
			// Disable a source
			if (args.length > 1 && Arrays.asList(values).contains(args[1])) {
				value = args[1];
				if (plugin.config.getBoolean("configuration.permissions") == true) {
					if (sender.hasPermission("unlimitedlava.disable." + args[1])) {
						UnlimitedLavaDisableSource(sender, value);
						return true;
					}
					else {
						message = plugin.localization.getString("permission_denied");
						plugin.message(sender, null, message, null);
						return true;
					}
				}
				if (plugin.config.getBoolean("configuration.permissions") == false) {
					UnlimitedLavaDisableSource(sender, value);
					return true;
				}
			}
			// Disable permissions
			if (args.length > 1 && args[1].equalsIgnoreCase("permissions")) {
				if (plugin.config.getBoolean("configuration.permissions") == true) {
					if (sender.hasPermission("unlimitedlava.disable.permissions")) {
						UnlimitedLavaDisablePermissions(sender);
						return true;
					}
					else {
						message = plugin.localization.getString("permission_denied");
						plugin.message(sender, null, message, null);
						return true;
					}
				}
				if (plugin.config.getBoolean("configuration.permissions") == false) {
					UnlimitedLavaDisablePermissions(sender);
					return true;
				}
			}
			// Disable messages
			if (args.length > 1 && args[1].equalsIgnoreCase("messages")) {
				if (plugin.config.getBoolean("configuration.permissions") == true) {
					if (sender.hasPermission("unlimitedlava.disable.messages")) {
						UnlimitedLavaDisableMessages(sender);
						return true;
					}
					else {
						message = plugin.localization.getString("permission_denied");
						plugin.message(sender, null, message, null);
						return true;
					}
				}
				if (plugin.config.getBoolean("configuration.permissions") == false) {
					UnlimitedLavaDisableMessages(sender);
					return true;
				}
			}
			// Disable furnace
			if (args.length > 1 && args[1].equalsIgnoreCase("furnace")) {
				if (plugin.config.getBoolean("configuration.permissions") == true) {
					if (sender.hasPermission("unlimitedlava.disable.furnace")) {
						UnlimitedLavaDisableFurnace(sender);
						return true;
					} 
					else {
						message = plugin.localization.getString("permission_denied");
						plugin.message(sender, null, message, null);
						return true;
					}
				}
				if (plugin.config.getBoolean("configuration.permissions") == false) {
					UnlimitedLavaDisableFurnace(sender);
					return true;
				}
			}
		}
		return false;
	}

	// Reloads the config with /unlimitedlava reload or /ulava reload
	private void UnlimitedLavaReload(CommandSender sender) {
		plugin.loadConfigsAgain();		
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
		message = plugin.localization.getString("enable_source");
		plugin.message(sender, null, message, value);
	}

	// Enables all sources with /unlimitedlava enable all or /ulava enable all
	private void UnlimitedLavaEnableAll(CommandSender sender) {
		plugin.config.set("sources.three", true);
		plugin.config.set("sources.two", true);
		plugin.config.set("sources.other", true);
		plugin.config.set("sources.big", true);
		plugin.saveConfig();
		message = plugin.localization.getString("enable_all");
		plugin.message(sender, null, message, null);
	}

	// Enables permissions with /unlimitedlava enable permissions or /ulava enable permissions
	private void UnlimitedLavaEnablePermissions(CommandSender sender) {
		plugin.config.set("configuration.permissions", true);
		plugin.saveConfig();
		for (i = 1; i <= 2; i++) {
			message = plugin.localization.getString("enable_permissions_" + Integer.toString(i));
			plugin.message(sender, null, message, null);
		}
	}

	// Enables messages with /unlimitedlava enable messages or /ulava enable messages
	private void UnlimitedLavaEnableMessages(CommandSender sender) {
		plugin.config.set("configuration.messages", true);
		plugin.saveConfig();
		message = plugin.localization.getString("enable_messages");
		plugin.message(sender, null, message, null);
	}
	
	// Enables furnace with /unlimitedlava enable furnace or /ulava enable furnace
	private void UnlimitedLavaEnableFurnace(CommandSender sender) {
		plugin.config.set("configuration.furnace", true);
		plugin.saveConfig();
		message = plugin.localization.getString("enable_furnace");
		plugin.message(sender, null, message, null);
	}
	
	// Disable a source
	private void UnlimitedLavaDisableSource(CommandSender sender, String value) {
		plugin.config.set("sources." + value, false);
		plugin.saveConfig();
		message = plugin.localization.getString("disable_source");
		plugin.message(sender, null, message, value);
	}

	// Disables all sources with /unlimitedlava disable all or /ulava disable all
	private void UnlimitedLavaDisableAll(CommandSender sender) {
		plugin.config.set("sources.three", false);
		plugin.config.set("sources.two", false);
		plugin.config.set("sources.other", false);
		plugin.config.set("sources.big", false);
		plugin.saveConfig();
		message = plugin.localization.getString("disable_all");
		plugin.message(sender, null, message, null);
	}

	// Disables permissions with /unlimitedlava disable permissions or /ulava disable permissions
	private void UnlimitedLavaDisablePermissions(CommandSender sender) {
		plugin.config.set("configuration.permissions", false);
		plugin.saveConfig();
		for (i = 1; i <= 2; i++) {
			message = plugin.localization.getString("disable_permissions_" + Integer.toString(i));
			plugin.message(sender, null, message, null);
		}
	}

	// Disables messages with /unlimitedlava disable messages or /ulava disable messages
	private void UnlimitedLavaDisableMessages(CommandSender sender) {
		plugin.config.set("configuration.messages", false);
		plugin.saveConfig();
		message = plugin.localization.getString("disable_messages");
		plugin.message(sender, null, message, null);
	}
	
	// Disables furnace with /unlimitedlava disable furnace or /ulava disable furnace
	private void UnlimitedLavaDisableFurnace(CommandSender sender) {
		plugin.config.set("configuration.furnace", false);
		plugin.saveConfig();
		message = plugin.localization.getString("disable_furnace");
		plugin.message(sender, null, message, null);
	}
}
