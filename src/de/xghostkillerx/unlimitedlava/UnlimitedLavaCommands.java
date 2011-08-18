package de.xghostkillerx.unlimitedlava;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;

/**
 * UnlimitedLavaCommands
 * 
 * Refer to: http://forums.bukkit.org/threads/19441/
 * 
 * @author xGhOsTkiLLeRx
 * @thanks to loganwm for the help!!
 * 
 */

public class UnlimitedLavaCommands {
	UnlimitedLava plugin;
	public UnlimitedLavaCommands(UnlimitedLava instance) {
	plugin = instance;
	}
public boolean UnlimitedLavaCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
	if (command.getName().equalsIgnoreCase("unlimitedlava")) {
		if (args.length > 0 && args[0].equals("reload")) {
			if (plugin.permissions == true) {
				if (sender.hasPermission("unlimitedlava.reload")) {
					UnlimitedLavaReload(sender, args);
					return true;
				}
				else {
					sender.sendMessage(ChatColor.DARK_RED + "You don't have the permission to do this!");
					return true;
				}
			}
			if (plugin.permissions == false) {
				UnlimitedLavaReload(sender, args);
				return true;
			}
		}
		if (args.length > 0 && args[0].equals("help")) {
			if (plugin.permissions == true) {
				if (sender.hasPermission("unlimitedlava.help")) {
					UnlimitedLavaHelp(sender, args);
					return true;
				}
				else {
                    sender.sendMessage(ChatColor.DARK_RED + "You don't have the permission to do this!");
                    return true;
				}
			}
			if (plugin.permissions == false) {
				UnlimitedLavaHelp(sender, args);
				return true;
			}
		}
		if (args.length > 0 && args[0].equals("enable")) {
			if (args.length > 1 && args[1].equals("all")) {
				if (plugin.permissions == true) {
					if (sender.hasPermission("unlimitedlava.enable.all")) {
						UnlimitedLavaEnableAll(sender, args);
						return true;
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "You don't have the permission to do this!");
						return true;
					}
				}
				if (plugin.permissions == false) {
					UnlimitedLavaEnableAll(sender, args);
					return true;
				}
			}
			if (args.length > 1 && args[1].equals("three")) {
				if (plugin.permissions == true) {
					if (sender.hasPermission("unlimitedlava.enable.three")) {
						UnlimitedLavaEnableThree(sender, args);
						return true;
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "You don't have the permission to do this!");
						return true;
					}
				}
				if (plugin.permissions == false) {
					UnlimitedLavaEnableThree(sender, args);
					return true;
				}
			}
			if (args.length > 1 && args[1].equals("two")) {
				if (plugin.permissions == true) {
					if (sender.hasPermission("unlimitedlava.enable.two")) {
						UnlimitedLavaEnableTwo(sender, args);
						return true;
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "You don't have the permission to do this!");
						return true;
					}
				}
				if (plugin.permissions == false) {
					UnlimitedLavaEnableTwo(sender, args);
					return true;
				}
			}
			if (args.length > 1 && args[1].equals("other")) {
				if (plugin.permissions == true) {
					if (sender.hasPermission("unlimitedlava.enable.other")) {
						UnlimitedLavaEnableOther(sender, args);
						return true;
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "You don't have the permission to do this!");
						return true;
					}
				}
				if (plugin.permissions == false) {
					UnlimitedLavaEnableOther(sender, args);
					return true;
				}
			}
			if (args.length > 1 && args[1].equals("permissions")) {
				if (plugin.permissions == true) {
					if (sender.hasPermission("unlimitedlava.enable.permissions")) {
						UnlimitedLavaEnablePermissions(sender, args);
						return true;
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "You don't have the permission to do this!");
						return true;
					}
				}
				if (plugin.permissions == false) {
					UnlimitedLavaEnablePermissions(sender, args);
					return true;
				}
			}
		}
		if (args.length > 0 && args[0].equals("disable")) {
			if (args.length > 1 && args[1].equals("all")) {
				if (plugin.permissions == true) {
					if (sender.hasPermission("unlimitedlava.disable.all")) {
						UnlimitedLavaDisableAll(sender, args);
						return true;
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "You don't have the permission to do this!");
						return true;
					}
				}
				if (plugin.permissions == false) {
					UnlimitedLavaDisableAll(sender, args);
					return true;
				}
			}
			if (args.length > 1 && args[1].equals("three")) {
				if (plugin.permissions == true) {
					if (sender.hasPermission("unlimitedlava.disable.three")) {
						UnlimitedLavaDisableThree(sender, args);
						return true;
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "You don't have the permission to do this!");
						return true;
					}
				}
				if (plugin.permissions == false) {
					UnlimitedLavaDisableThree(sender, args);
					return true;
				}
			}
			if (args.length > 1 && args[1].equals("two")) {
				if (plugin.permissions == true) {
					if (sender.hasPermission("unlimitedlava.disable.two")) {
						UnlimitedLavaDisableTwo(sender, args);
						return true;
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "You don't have the permission to do this!");
						return true;
					}
				}
				if (plugin.permissions == false) {
					UnlimitedLavaDisableTwo(sender, args);
					return true;
				}
			}
			if (args.length > 1 && args[1].equals("other")) {
				if (plugin.permissions == true) {
					if (sender.hasPermission("unlimitedlava.disable.other")) {
						UnlimitedLavaDisableOther(sender, args);
						return true;
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "You don't have the permission to do this!");
						return true;
					}
				}
				if (plugin.permissions == false) {
					UnlimitedLavaDisableOther(sender, args);
					return true;
				}
			}
			if (args.length > 1 && args[1].equals("permissions")) {
				if (plugin.permissions == true) {
					if (sender.hasPermission("unlimitedlava.disable.permissions")) {
						UnlimitedLavaDisablePermissions(sender, args);
						return true;
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "You don't have the permission to do this!");
						return true;
					}
				}
				if (plugin.permissions == false) {
					UnlimitedLavaDisablePermissions(sender, args);
					return true;
				}
			}
		}
	}
	
	return false;
}

	// Reload the config with /unlimitedlava reload
	private boolean UnlimitedLavaReload(CommandSender sender, String[] args) {
		PluginDescriptionFile pdfFile = plugin.getDescription();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_GREEN + "UnlimitedLava version " + ChatColor.DARK_RED + pdfFile.getVersion() + ChatColor.DARK_GREEN + " reloaded!");
		return true;
	}
	// See the help with /unlimitedlava help
	private boolean UnlimitedLavaHelp(CommandSender sender, String[] args) {
		PluginDescriptionFile pdfFile = plugin.getDescription();
		sender.sendMessage(ChatColor.DARK_GREEN	+ "Welcome to the UnlimitedLava version " + ChatColor.DARK_RED + pdfFile.getVersion() + ChatColor.DARK_GREEN + " help!");
		sender.sendMessage("To see the help type " + ChatColor.DARK_RED	+ "/unlimitedlava help");
		sender.sendMessage("To reload the config use " + ChatColor.DARK_RED	+ "/unlimitedlava reload");
		sender.sendMessage("To enable something use " + ChatColor.DARK_RED + "/unlimitedlava enable " + ChatColor.YELLOW + "<value>");
		sender.sendMessage("To disable something use " + ChatColor.DARK_RED	+ "/unlimitedlava disable " + ChatColor.YELLOW + "<value>");
		sender.sendMessage(ChatColor.YELLOW + "Values " + ChatColor.WHITE + "can be: all, three, two, other or permissions");
		return true;
	}
	// Enable all sources with /unlimitedlava enable all
	private boolean UnlimitedLavaEnableAll(CommandSender sender, String[] args) {
		plugin.config.setProperty("three", true);
		plugin.config.setProperty("two", true);
		plugin.config.setProperty("other", true);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_RED + "All " + ChatColor.DARK_GREEN + "UnlimitedLava sources enabled!");
		return true;
	}
	// Enable 3x3 with /unlimitedlava enable three
	private boolean UnlimitedLavaEnableThree(CommandSender sender, String[] args) {
		plugin.config.setProperty("three", true);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_GREEN + "UnlimitedLava source " + ChatColor.DARK_RED + "three (3x3) " + ChatColor.DARK_GREEN + "enabled!");
		return true;
	}
	// Enable 2x2 with /unlimitedlava enable two
	private boolean UnlimitedLavaEnableTwo(CommandSender sender, String[] args) {
		plugin.config.setProperty("two", true);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_GREEN + "UnlimitedLava source " + ChatColor.DARK_RED + "two (2x2) " + ChatColor.DARK_GREEN + "enabled!");
		return true;
	}
	// Enable other sources with /unlimitedlava enable other
	private boolean UnlimitedLavaEnableOther(CommandSender sender, String[] args) {
		plugin.config.setProperty("other", true);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_GREEN + "UnlimitedLava source " + ChatColor.DARK_RED + "other " + ChatColor.DARK_GREEN + "enabled!");
		return true;
	}
	// Disable all sources with /unlimitedlava disable all
	private boolean UnlimitedLavaDisableAll(CommandSender sender, String[] args) {
		plugin.config.setProperty("three", false);
		plugin.config.setProperty("two", false);
		plugin.config.setProperty("other", false);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_RED + "All " + ChatColor.DARK_GREEN + "UnlimitedLava sources disabled!");
		return true;
	}
	// Disable 3x3 source with /unlimitedlava disable three
	private boolean UnlimitedLavaDisableThree(CommandSender sender,	String[] args) {
		plugin.config.setProperty("three", false);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_GREEN + "UnlimitedLava source " + ChatColor.DARK_RED + "three (3x3) " + ChatColor.DARK_GREEN + "disabled!");
		return true;
	}
	// Disable 2x2 source with /unlimitedlava disable two
	private boolean UnlimitedLavaDisableTwo(CommandSender sender, String[] args) {
		plugin.config.setProperty("two", false);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_GREEN + "UnlimitedLava source " + ChatColor.DARK_RED + "two (2x2) " + ChatColor.DARK_GREEN + "disabled!");
		return true;
	}
	// Disable other sources with /unlimitedlava disable other
	private boolean UnlimitedLavaDisableOther(CommandSender sender,	String[] args) {
		plugin.config.setProperty("other", false);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_GREEN + "UnlimitedLava source " + ChatColor.DARK_RED + "other " + ChatColor.DARK_GREEN + "disabled!");
		return true;
	}
	// Enable permissions with /unlimitedlava enable permissions
	private boolean UnlimitedLavaEnablePermissions(CommandSender sender, String[] args) {
		plugin.config.setProperty("permissions", true);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_GREEN + "UnlimitedLava " + ChatColor.DARK_RED	+ "permissions " + ChatColor.DARK_GREEN	+ "enabled! Only OPs or players with the permission can use the /unlimitedlava commands!");
		return true;
	}
	// Disable permissions with /unlimitedlava disable permissions
	private boolean UnlimitedLavaDisablePermissions(CommandSender sender, String[] args) {
		plugin.config.setProperty("permissions", false);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_GREEN + "UnlimitedLava " + ChatColor.DARK_RED + "permissions " + ChatColor.DARK_GREEN	+ "disabled! All players can use the /unlimitedlava commands!");
		return true;
	}
}
