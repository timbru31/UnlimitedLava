package de.xghostkillerx.unlimitedlava;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;

/**
 * UnlimitedLavaCommands
 * Handles all commands!
 * 
 * Refer to:
 * http://bit.ly/n1Wex2
 *
 * @author xGhOsTkiLLeRx
 * @thanks to loganwm for the help!!
 * @thanks to Edward Hand for the idea and original InfiniteLava plugin!
 * 
 */

public class UnlimitedLavaCommands {
	
	UnlimitedLava plugin;
	public UnlimitedLavaCommands(UnlimitedLava instance) {
	plugin = instance;
	}
	
	//Commands... First check if config value permissions is true
	public boolean UnlimitedLavaCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if ((command.getName().equalsIgnoreCase("unlimitedlava")) || (command.getName().equalsIgnoreCase("ulava"))) {
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
				if (args.length > 1 && args[1].equals("big")) {
					if (plugin.permissions == true) {
						if (sender.hasPermission("unlimitedlava.enable.big")) {
							UnlimitedLavaEnableBig(sender, args);
							return true;
						} else {
							sender.sendMessage(ChatColor.DARK_RED + "You don't have the permission to do this!");
							return true;
						}
					}
					if (plugin.permissions == false) {
						UnlimitedLavaEnableBig(sender, args);
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
				if (args.length > 1 && args[1].equals("big")) {
					if (plugin.permissions == true) {
						if (sender.hasPermission("unlimitedlava.disable.big")) {
							UnlimitedLavaDisableBig(sender, args);
							return true;
						} else {
							sender.sendMessage(ChatColor.DARK_RED + "You don't have the permission to do this!");
							return true;
						}
					}
					if (plugin.permissions == false) {
						UnlimitedLavaDisableBig(sender, args);
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

	// Reload the config with /unlimitedlava reload or /ulava help
	private boolean UnlimitedLavaReload(CommandSender sender, String[] args) {
		PluginDescriptionFile pdfFile = plugin.getDescription();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_GREEN + "UnlimitedLava version " + ChatColor.DARK_RED + pdfFile.getVersion() + ChatColor.DARK_GREEN + " reloaded!");
		return true;
	}
	// See the help with /unlimitedlava help or /ulava help
	private boolean UnlimitedLavaHelp(CommandSender sender, String[] args) {
		PluginDescriptionFile pdfFile = plugin.getDescription();
		sender.sendMessage(ChatColor.DARK_GREEN	+ "Welcome to the UnlimitedLava version " + ChatColor.DARK_RED + pdfFile.getVersion() + ChatColor.DARK_GREEN + " help!");
		sender.sendMessage("To see the help type " + ChatColor.DARK_RED	+ "/unlimitedlava help " + ChatColor.WHITE + "or " + ChatColor.DARK_RED	+ "/ulava help");
		sender.sendMessage("To reload use " + ChatColor.DARK_RED + "/unlimitedlava reload " + ChatColor.WHITE + "or " + ChatColor.DARK_RED + "/ulava reload");
		sender.sendMessage("To enable something use " + ChatColor.DARK_RED + "/unlimitedlava enable " + ChatColor.YELLOW + "<value>");
		sender.sendMessage("or " + ChatColor.DARK_RED + "/ulava enable " + ChatColor.YELLOW + "<value>");
		sender.sendMessage("To disable something use " + ChatColor.DARK_RED	+ "/unlimitedlava disable " + ChatColor.YELLOW + "<value>");
		sender.sendMessage("or " + ChatColor.DARK_RED + "/ulava disable " + ChatColor.YELLOW + "<value>");
		sender.sendMessage(ChatColor.YELLOW + "Values " + ChatColor.WHITE + "can be: all, three, two, other, big or permissions");
		return true;
	}
	// Enable all sources with /unlimitedlava enable all or /ulava enable all
	private boolean UnlimitedLavaEnableAll(CommandSender sender, String[] args) {
		plugin.config.setProperty("three", true);
		plugin.config.setProperty("two", true);
		plugin.config.setProperty("other", true);
		plugin.config.setProperty("big", true);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_RED + "All " + ChatColor.DARK_GREEN + "UnlimitedLava sources enabled!");
		return true;
	}
	// Enable 3x3 with /unlimitedlava enable three or /ulava enable three
	private boolean UnlimitedLavaEnableThree(CommandSender sender, String[] args) {
		plugin.config.setProperty("three", true);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_GREEN + "UnlimitedLava source " + ChatColor.DARK_RED + "three (3x3) " + ChatColor.DARK_GREEN + "enabled!");
		return true;
	}
	// Enable 2x2 with /unlimitedlava enable two or /ulava enable two
	private boolean UnlimitedLavaEnableTwo(CommandSender sender, String[] args) {
		plugin.config.setProperty("two", true);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_GREEN + "UnlimitedLava source " + ChatColor.DARK_RED + "two (2x2) " + ChatColor.DARK_GREEN + "enabled!");
		return true;
	}
	// Enable other sources with /unlimitedlava enable other or /ulava enable other
	private boolean UnlimitedLavaEnableOther(CommandSender sender, String[] args) {
		plugin.config.setProperty("other", true);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_GREEN + "UnlimitedLava source " + ChatColor.DARK_RED + "other " + ChatColor.DARK_GREEN + "enabled!");
		return true;
	}
	// Enable big sources with /unlimitedlava enable big or /ulava enable big
	private boolean UnlimitedLavaEnableBig(CommandSender sender, String[] args) {
		plugin.config.setProperty("big", true);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_GREEN + "UnlimitedLava source " + ChatColor.DARK_RED + "big " + ChatColor.DARK_GREEN + "enabled!");
		return true;
	}
	// Enable permissions with /unlimitedlava enable permissions or /ulava enable permissions
	private boolean UnlimitedLavaEnablePermissions(CommandSender sender, String[] args) {
		plugin.config.setProperty("permissions", true);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_GREEN + "UnlimitedLava " + ChatColor.DARK_RED	+ "permissions " + ChatColor.DARK_GREEN	+ "enabled! Only OPs or players with the permission can use the /unlimitedlava commands!");
		return true;
	}
	// Disable all sources with /unlimitedlava disable all or /ulava disable all
	private boolean UnlimitedLavaDisableAll(CommandSender sender, String[] args) {
		plugin.config.setProperty("three", false);
		plugin.config.setProperty("two", false);
		plugin.config.setProperty("other", false);
		plugin.config.setProperty("big", false);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_RED + "All " + ChatColor.DARK_GREEN + "UnlimitedLava sources disabled!");
		return true;
	}
	// Disable 3x3 source with /unlimitedlava disable three or /ulava disable two
	private boolean UnlimitedLavaDisableThree(CommandSender sender,	String[] args) {
		plugin.config.setProperty("three", false);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_GREEN + "UnlimitedLava source " + ChatColor.DARK_RED + "three (3x3) " + ChatColor.DARK_GREEN + "disabled!");
		return true;
	}
	// Disable 2x2 source with /unlimitedlava disable two or /ulava disable two
	private boolean UnlimitedLavaDisableTwo(CommandSender sender, String[] args) {
		plugin.config.setProperty("two", false);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_GREEN + "UnlimitedLava source " + ChatColor.DARK_RED + "two (2x2) " + ChatColor.DARK_GREEN + "disabled!");
		return true;
	}
	// Disable other sources with /unlimitedlava disable other or /ulava disable other
	private boolean UnlimitedLavaDisableOther(CommandSender sender,	String[] args) {
		plugin.config.setProperty("other", false);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_GREEN + "UnlimitedLava source " + ChatColor.DARK_RED + "other " + ChatColor.DARK_GREEN + "disabled!");
		return true;
	}
	// Disable big sources with /unlimitedlava disable big or /ulava disable big
	private boolean UnlimitedLavaDisableBig(CommandSender sender, String[] args) {
		plugin.config.setProperty("big", false);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_GREEN + "UnlimitedLava source " + ChatColor.DARK_RED + "big " + ChatColor.DARK_GREEN + "disabled!");
		return true;
	}
	// Disable permissions with /unlimitedlava disable permissions or /ulava disable permissions
	private boolean UnlimitedLavaDisablePermissions(CommandSender sender, String[] args) {
		plugin.config.setProperty("permissions", false);
		plugin.config.save();
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.DARK_GREEN + "UnlimitedLava " + ChatColor.DARK_RED + "permissions " + ChatColor.DARK_GREEN	+ "disabled! All players can use the /unlimitedlava commands!");
		return true;
	}
}
