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
    public static final Logger log = Logger.getLogger("Minecraft");
    private final UnlimitedLavaBlockListener blockListener = new UnlimitedLavaBlockListener(this);
	

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
		if (cmd.getName().equalsIgnoreCase("unlimitedlava")) {
			if (args.length > 0 && args[0].equals("reload")) {
				if (sender.hasPermission("unlimitedlava.reload")) {
				reloadConfig();
				sender.sendMessage("§2UnlimitedLava config reloaded");
				return true;
			    }
                else {
                    sender.sendMessage("§4You don't have the permission to do this!");
                    return true;
                }
			}
			if (args.length > 0 && args[0].equals("help")) {
				if (sender.hasPermission("unlimitedlava.help")) {
				sender.sendMessage("To see the help type §4/unlimitedlava help");
				sender.sendMessage("To reload the config use §4/unlimitedlava reload");
				sender.sendMessage("To enable a source use §4/unlimitedlava enable §e<sources>");
				sender.sendMessage("To disable a source use §4/unlimitedlava disable §e<sources>");
				sender.sendMessage("§eSources §fcan be: all, three, two, other");
				return true;
				}
                else {
                    sender.sendMessage("§4You don't have the permission to do this!");
                    return true;
                }
			}
			if (args.length > 0 && args[0].equals("enable")) {
				if (args.length > 1 && args[1].equals("all")) {
					if (sender.hasPermission("unlimitedlava.enable.all")) {
					config.setProperty("three", true);
					config.setProperty("two", true);
					config.setProperty("other", true);
					config.save();
					reloadConfig();
					sender.sendMessage("§cAll §2UnlimitedLava sources §2enabled");
					return true;
					}
	                else {
	                    sender.sendMessage("§4You don't have the permission to do this!");
	                    return true;
	                }
				}
				if (args.length > 1 && args[1].equals("three")) {
					if (sender.hasPermission("unlimitedlava.enable.three")) {
					config.setProperty("three", true);
					config.save();
					reloadConfig();
					sender.sendMessage("§2UnlimitedLava source §cthree (3x3) §2enabled");
					return true;
					}
	                else {
	                    sender.sendMessage("§4You don't have the permission to do this!");
	                    return true;
	                }
				}
				if (args.length > 1 && args[1].equals("two")) {
					if (sender.hasPermission("unlimitedlava.enable.two")) {
					config.setProperty("two", true);
					config.save();
					reloadConfig();
					sender.sendMessage("§2UnlimitedLava source §ctwo (2x2) §2enabled");
					return true;
					}
	                else {
	                    sender.sendMessage("§4You don't have the permission to do this!");
	                    return true;
	                }
				}
				if (args.length > 1 && args[1].equals("other")) {
					if (sender.hasPermission("unlimitedlava.enable.other")) {
					config.setProperty("other", true);
					config.save();
					reloadConfig();
					sender.sendMessage("§2UnlimitedLava source §cother §2enabled");
					return true;
					}
	                else {
	                    sender.sendMessage("§4You don't have the permission to do this!");
	                    return true;
	                }
				}
			}
			if (args.length > 0 && args[0].equals("disable")) {
				if (args.length > 1 && args[1].equals("all")) {
					if (sender.hasPermission("unlimitedlava.disable.all")) {
					config.setProperty("three", false);
					config.setProperty("two", false);
					config.setProperty("other", false);
					config.save();
					reloadConfig();
					sender.sendMessage("§cAll §2UnlimitedLava sources §2disabled");
					return true;
					}
	                else {
	                    sender.sendMessage("§4You don't have the permission to do this!");
	                    return true;
	                }
				}
				if (args.length > 1 && args[1].equals("three")) {
					if (sender.hasPermission("unlimitedlava.disable.three")) {
					config.setProperty("three", false);
					config.save();
					reloadConfig();
					sender.sendMessage("§2UnlimitedLava source §cthree (3x3) §2disabled");
					return true;
					}
	                else {
	                    sender.sendMessage("§4You don't have the permission to do this!");
	                    return true;
	                }
				}
				if (args.length > 1 && args[1].equals("two")) {
					if (sender.hasPermission("unlimitedlava.disable.two")) {
					config.setProperty("two", false);
					config.save();
					reloadConfig();
					sender.sendMessage("§2UnlimitedLava source §ctwo (2x2) §2disabled");
					return true;
					}
	                else {
	                    sender.sendMessage("§4You don't have the permission to do this!");
	                    return true;
	                }
				}
				if (args.length > 1 && args[1].equals("other")) {
					if (sender.hasPermission("unlimitedlava.disable.other")) {
					config.setProperty("other", false);
					config.save();
					reloadConfig();
					sender.sendMessage("§2UnlimitedLava source §cother §2disabled");
					return true;
					}
	                else {
	                    sender.sendMessage("§4You don't have the permission to do this!");
	                    return true;
	                }
				}
			}
		}
		return false;
	}

    public void reloadConfig() {
    	config.load();
        config.setHeader("# If you haven't understood the config (especially the point 'other'),", "# please refer to this topic: http://bit.ly/n1Wex2");
        three = config.getBoolean("three", true);
        two = config.getBoolean("two", true);
        other = config.getBoolean("other", false);
        config.save();
    }
}