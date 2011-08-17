package de.xghostkillerx.unlimitedlava;

import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;


/**
 * UnlimitedLava for CraftBukkit
 * 
 * Refer to:
 * http://forums.bukkit.org/threads/19441/
 *
 * @author xGhOsTkiLLeRx
 * @thanks to loganwm for the help!!
 * 
 */

public class UnlimitedLava extends JavaPlugin {
	
	public Configuration config;
    public Boolean three;
    public Boolean two;
    public Boolean other;
    public Boolean permissions;
    public static final Logger log = Logger.getLogger("Minecraft");
	private final UnlimitedLavaBlockListener blockListener = new UnlimitedLavaBlockListener(
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
        
        // Config
        config = getConfiguration();
        reloadConfig();
        config.save();
        
        // Message
        PluginDescriptionFile pdfFile = this.getDescription();
        log.info(pdfFile.getName() + " " + pdfFile.getVersion() + " is enabled!");
    }

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		String[] subargs = new String[args.length - 1];
		for (int x = 1; x < args.length; x++)
			subargs[x - 1] = args[x];
		if (cmd.getName().equalsIgnoreCase("unlimitedlava")) {
			if (args.length > 0 && args[0].equals("reload")) {
				if (permissions == true) {
					if (sender.hasPermission("unlimitedlava.reload")) {
						UnlimitedLavaReload(sender, subargs);
						return true;
					}
					else {
						sender.sendMessage("§4You don't have the permission to do this!");
						return true;
					}
				}
				if (permissions == false) {
					UnlimitedLavaReload(sender, subargs);
					return true;
				}
			}
			if (args.length > 0 && args[0].equals("help")) {
				if (permissions == true) {
					if (sender.hasPermission("unlimitedlava.help")) {
						UnlimitedLavaHelp(sender, subargs);
						return true;
					}
					else {
	                    sender.sendMessage("§4You don't have the permission to do this!");
	                    return true;
					}
				}
				if (permissions == false) {
					UnlimitedLavaHelp(sender, subargs);
					return true;
				}
			}
			if (args.length > 0 && args[0].equals("enable")) {
				if (args.length > 1 && args[1].equals("all")) {
					if (permissions == true) {
						if (sender.hasPermission("unlimitedlava.enable.all")) {
							UnlimitedLavaEnableAll(sender, subargs);
							return true;
						} else {
							sender.sendMessage("§4You don't have the permission to do this!");
							return true;
						}
					}
					if (permissions == false) {
						UnlimitedLavaEnableAll(sender, subargs);
						return true;
					}
				}
				if (args.length > 1 && args[1].equals("three")) {
					if (permissions == true) {
						if (sender.hasPermission("unlimitedlava.enable.three")) {
							UnlimitedLavaEnableThree(sender, subargs);
							return true;
						} else {
							sender.sendMessage("§4You don't have the permission to do this!");
							return true;
						}
					}
					if (permissions == false) {
						UnlimitedLavaEnableThree(sender, subargs);
						return true;
					}
				}
				if (args.length > 1 && args[1].equals("two")) {
					if (permissions == true) {
						if (sender.hasPermission("unlimitedlava.enable.two")) {
							UnlimitedLavaEnableTwo(sender, subargs);
							return true;
						} else {
							sender.sendMessage("§4You don't have the permission to do this!");
							return true;
						}
					}
					if (permissions == false) {
						UnlimitedLavaEnableTwo(sender, subargs);
						return true;
					}
				}
				if (args.length > 1 && args[1].equals("other")) {
					if (permissions == true) {
						if (sender.hasPermission("unlimitedlava.enable.other")) {
							UnlimitedLavaEnableOther(sender, subargs);
							return true;
						} else {
							sender.sendMessage("§4You don't have the permission to do this!");
							return true;
						}
					}
					if (permissions == false) {
						UnlimitedLavaEnableOther(sender, subargs);
						return true;
					}
				}
				if (args.length > 1 && args[1].equals("permissions")) {
					if (permissions == true) {
						if (sender.hasPermission("unlimitedlava.enable.permissions")) {
							UnlimitedLavaEnablePermissions(sender, subargs);
							return true;
						} else {
							sender.sendMessage("§4You don't have the permission to do this!");
							return true;
						}
					}
					if (permissions == false) {
						UnlimitedLavaEnablePermissions(sender, subargs);
						return true;
					}
				}
			}
			if (args.length > 0 && args[0].equals("disable")) {
				if (args.length > 1 && args[1].equals("all")) {
					if (permissions == true) {
						if (sender.hasPermission("unlimitedlava.disable.all")) {
							UnlimitedLavaDisableAll(sender, subargs);
							return true;
						} else {
							sender.sendMessage("§4You don't have the permission to do this!");
							return true;
						}
					}
					if (permissions == false) {
						UnlimitedLavaDisableAll(sender, subargs);
						return true;
					}
				}
				if (args.length > 1 && args[1].equals("three")) {
					if (permissions == true) {
						if (sender.hasPermission("unlimitedlava.disable.three")) {
							UnlimitedLavaDisableThree(sender, subargs);
							return true;
						} else {
							sender.sendMessage("§4You don't have the permission to do this!");
							return true;
						}
					}
					if (permissions == false) {
						UnlimitedLavaDisableThree(sender, subargs);
						return true;
					}
				}
				if (args.length > 1 && args[1].equals("two")) {
					if (permissions == true) {
						if (sender.hasPermission("unlimitedlava.disable.two")) {
							UnlimitedLavaDisableTwo(sender, subargs);
							return true;
						} else {
							sender.sendMessage("§4You don't have the permission to do this!");
							return true;
						}
					}
					if (permissions == false) {
						UnlimitedLavaDisableTwo(sender, subargs);
						return true;
					}
				}
				if (args.length > 1 && args[1].equals("other")) {
					if (permissions == true) {
						if (sender.hasPermission("unlimitedlava.disable.other")) {
							UnlimitedLavaDisableOther(sender, subargs);
							return true;
						} else {
							sender.sendMessage("§4You don't have the permission to do this!");
							return true;
						}
					}
					if (permissions == false) {
						UnlimitedLavaDisableOther(sender, subargs);
						return true;
					}
				}
				if (args.length > 1 && args[1].equals("permissions")) {
					if (permissions == true) {
						if (sender.hasPermission("unlimitedlava.disable.permissions")) {
							UnlimitedLavaDisablePermissions(sender, subargs);
							return true;
						} else {
							sender.sendMessage("§4You don't have the permission to do this!");
							return true;
						}
					}
					if (permissions == false) {
						UnlimitedLavaDisablePermissions(sender, subargs);
						return true;
					}
				}
			}
		}
		return false;
	}

	// Reload the config file, via command /unlimitedlava reload and at the start!
    public void reloadConfig() {
    	config.load();
        config.setHeader("# If you haven't understood the config (especially the point 'other'),", "# please refer to this topic: http://bit.ly/n1Wex2");
        three = config.getBoolean("three", true);
        two = config.getBoolean("two", true);
        other = config.getBoolean("other", false);
        permissions = config.getBoolean("permissions", true);
        config.save();
    }
    // Reload the config with /unlimitedlava reload
    private boolean UnlimitedLavaReload(CommandSender sender, String[] args) {
		reloadConfig();
		sender.sendMessage("§2UnlimitedLava config reloaded!");
		return true;
    }
    // See the help with /unlimitedlava help
    private boolean UnlimitedLavaHelp(CommandSender sender, String[] args) {
		sender.sendMessage("To see the help type §4/unlimitedlava help");
		sender.sendMessage("To reload the config use §4/unlimitedlava reload");
		sender.sendMessage("To enable a source or permissions use §4/unlimitedlava enable §e<value>");
		sender.sendMessage("To disable a source or permissions use §4/unlimitedlava disable §e<value>");
		sender.sendMessage("§eValues §fcan be: all, three, two, other or permissions");
		return true;
	}
    // Enable all sources with /unlimitedlava enable all
    private boolean UnlimitedLavaEnableAll(CommandSender sender, String[] args) {
		config.setProperty("three", true);
		config.setProperty("two", true);
		config.setProperty("other", true);
		config.save();
		reloadConfig();
		sender.sendMessage("§cAll §2UnlimitedLava sources §2enabled!");
		return true;
    }
    // Enable 3x3 with /unlimitedlava enable three
    private boolean UnlimitedLavaEnableThree(CommandSender sender, String[] args) {
		config.setProperty("three", true);
		config.save();
		reloadConfig();
		sender.sendMessage("§2UnlimitedLava source §cthree (3x3) §2enabled!");
		return true;
    }
    // Enable 2x2 with /unlimitedlava enable two
    private boolean UnlimitedLavaEnableTwo(CommandSender sender, String[] args) {
		config.setProperty("two", true);
		config.save();
		reloadConfig();
		sender.sendMessage("§2UnlimitedLava source §ctwo (2x2) §2enabled!");
		return true;
    }
    // Enable other sources with /unlimitedlava enable other
    private boolean UnlimitedLavaEnableOther(CommandSender sender, String[] args) {
		config.setProperty("other", true);
		config.save();
		reloadConfig();
		sender.sendMessage("§2UnlimitedLava source §cother §2enabled!");
		return true;
    }
    // Disable all sources with /unlimitedlava disable all
    private boolean UnlimitedLavaDisableAll(CommandSender sender, String[] args) {
		config.setProperty("three", false);
		config.setProperty("two", false);
		config.setProperty("other", false);
		config.save();
		reloadConfig();
		sender.sendMessage("§cAll §2UnlimitedLava sources §2disabled!");
		return true;
    }
    // Disable 3x3 source with /unlimitedlava disable three
    private boolean UnlimitedLavaDisableThree(CommandSender sender, String[] args) {
		config.setProperty("three", false);
		config.save();
		reloadConfig();
		sender.sendMessage("§2UnlimitedLava source §cthree (3x3) §2disabled!");
		return true;
    }
    // Disable 2x2 source with /unlimitedlava disable two
    private boolean UnlimitedLavaDisableTwo(CommandSender sender, String[] args) {
		config.setProperty("two", false);
		config.save();
		reloadConfig();
		sender.sendMessage("§2UnlimitedLava source §ctwo (2x2) §2disabled!");
		return true;
    }
    // Disable other sources with /unlimitedlava disable other
    private boolean UnlimitedLavaDisableOther(CommandSender sender, String[] args) {
		config.setProperty("other", false);
		config.save();
		reloadConfig();
		sender.sendMessage("§2UnlimitedLava source §cother §2disabled!");
		return true;
    }
    // Enable permissions with /unlimitedlava enable permissions
    private boolean UnlimitedLavaEnablePermissions(CommandSender sender, String[] args) {
		config.setProperty("permissions", true);
		config.save();
		reloadConfig();
		sender.sendMessage("§2UnlimitedLava §cpermissions §2enabled! Only OPs or players with the permission can use the /unlimitedlava commands!");
		return true;
    }
    // Disable permissions with /unlimitedlava disable permissions
    private boolean UnlimitedLavaDisablePermissions(CommandSender sender, String[] args) {
		config.setProperty("permissions", false);
		config.save();
		reloadConfig();
		sender.sendMessage("§2UnlimitedLava §cpermissions §2disabled! All players can use the /unlimitedlava commands!");
		return true;
    }
}