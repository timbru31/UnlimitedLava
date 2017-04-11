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
 * Refer to the dev.bukkit.org page:
 * https://dev.bukkit.org/projects/unlimited-lava
 *
 * @author xGhOsTkiLLeRx
 * thanks to loganwm for the help!
 * thanks to Edward Hand for the idea and original InfiniteLava plugin!
 * thanks to ferrybig for the awesome fall code!
 * thanks to Xastabus for the cool improvements of the checks!
 *
 */

public class UnlimitedLavaCommands implements CommandExecutor {
    private UnlimitedLava plugin;
    private String[] values = { "three", "two", "other", "big", "lava_fall", "water_fall", "plus", "T", "ring" };

    public UnlimitedLavaCommands(UnlimitedLava instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        String message, value;
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("unlimitedlava.reload") || !plugin.isPermissions()) {
                reload(sender);
            } else {
                message = plugin.getLocalization().getString("permission_denied");
                plugin.message(sender, null, message, null);
            }
            return true;
        } else if (args.length > 0 && args[0].equalsIgnoreCase("status")) {
            if (sender.hasPermission("unlimitedlava.status") || !plugin.isPermissions()) {
                displayStatus(sender);
            } else {
                message = plugin.getLocalization().getString("permission_denied");
                plugin.message(sender, null, message, null);
            }
            return true;
        } else if (args.length > 0 && args[0].equalsIgnoreCase("help")) {
            if (sender.hasPermission("unlimitedlava.help") || !plugin.isPermissions()) {
                displayHelp(sender);
            } else {
                message = plugin.getLocalization().getString("permission_denied");
                plugin.message(sender, null, message, null);
            }
            return true;
        } else if (args.length > 0 && args[0].equalsIgnoreCase("enable")) {
            if (args.length > 1 && args[1].equalsIgnoreCase("all")) {
                if (sender.hasPermission("unlimitedlava.enable.all") || !plugin.isPermissions()) {
                    enableAll(sender);
                } else {
                    message = plugin.getLocalization().getString("permission_denied");
                    plugin.message(sender, null, message, null);
                }
                return true;
            } else if (args.length > 1 && Arrays.asList(values).contains(args[1])) {
                value = args[1];
                if (sender.hasPermission("unlimitedlava.enable." + args[1]) || !plugin.isPermissions()) {
                    enableSource(sender, value);
                } else {
                    message = plugin.getLocalization().getString("permission_denied");
                    plugin.message(sender, null, message, null);
                }
                return true;
            } else if (args.length > 1 && args[1].equalsIgnoreCase("furnace")) {
                if (sender.hasPermission("unlimitedlava.enable.furnace") || !plugin.isPermissions()) {
                    enableFurnace(sender);
                } else {
                    message = plugin.getLocalization().getString("permission_denied");
                    plugin.message(sender, null, message, null);
                }
                return true;
            } else if (args.length > 1 && args[1].equalsIgnoreCase("permissions")) {
                if (sender.hasPermission("unlimitedlava.enable.permissions") || !plugin.isPermissions()) {
                    enablePermissions(sender);
                } else {
                    message = plugin.getLocalization().getString("permission_denied");
                    plugin.message(sender, null, message, null);
                }
                return true;
            } else if (args.length > 1 && args[1].equalsIgnoreCase("messages")) {
                if (sender.hasPermission("unlimitedlava.enable.messages") || !plugin.isPermissions()) {
                    enableMessages(sender);
                } else {
                    message = plugin.getLocalization().getString("permission_denied");
                    plugin.message(sender, null, message, null);
                }
                return true;
            }
        } else if (args.length > 0 && args[0].equalsIgnoreCase("disable")) {
            if (args.length > 1 && args[1].equalsIgnoreCase("all")) {
                if (sender.hasPermission("unlimitedlava.disable.all") || !plugin.isPermissions()) {
                    disableAll(sender);
                } else {
                    message = plugin.getLocalization().getString("permission_denied");
                    plugin.message(sender, null, message, null);
                }
                return true;
            } else if (args.length > 1 && Arrays.asList(values).contains(args[1])) {
                value = args[1];
                if (sender.hasPermission("unlimitedlava.disable." + args[1]) || !plugin.isPermissions()) {
                    disableSource(sender, value);
                } else {
                    message = plugin.getLocalization().getString("permission_denied");
                    plugin.message(sender, null, message, null);
                }
                return true;
            } else if (args.length > 1 && args[1].equalsIgnoreCase("permissions")) {
                if (sender.hasPermission("unlimitedlava.disable.permissions") || !plugin.isPermissions()) {
                    disablePermissions(sender);
                } else {
                    message = plugin.getLocalization().getString("permission_denied");
                    plugin.message(sender, null, message, null);
                }
                return true;
            } else if (args.length > 1 && args[1].equalsIgnoreCase("messages")) {
                if (sender.hasPermission("unlimitedlava.disable.messages") || !plugin.isPermissions()) {
                    disableMessages(sender);
                } else {
                    message = plugin.getLocalization().getString("permission_denied");
                    plugin.message(sender, null, message, null);
                }
                return true;
            } else if (args.length > 1 && args[1].equalsIgnoreCase("furnace")) {
                if (sender.hasPermission("unlimitedlava.disable.furnace") || !plugin.isPermissions()) {
                    disableFurnace(sender);

                } else {
                    message = plugin.getLocalization().getString("permission_denied");
                    plugin.message(sender, null, message, null);
                }
                return true;
            }
        }
        return false;
    }

    private void displayStatus(CommandSender sender) {
        String header = ChatColor.YELLOW + "Status of UnlimitedLava";
        String string = "";
        if (plugin.isBig()) {
            string += "Big: " + ChatColor.DARK_GREEN + plugin.isBig() + ChatColor.WHITE + ", ";
        } else {
            string += "Big: " + ChatColor.DARK_RED + plugin.isBig() + ChatColor.WHITE + ", ";
        }
        if (plugin.isThree()) {
            string += "three: " + ChatColor.DARK_GREEN + plugin.isThree() + ChatColor.WHITE + ", ";
        } else {
            string += "three: " + ChatColor.DARK_RED + plugin.isThree() + ChatColor.WHITE + ", ";
        }
        if (plugin.isTwo()) {
            string += "two: " + ChatColor.DARK_GREEN + plugin.isTwo() + ChatColor.WHITE + ", ";
        } else {
            string += "two:" + ChatColor.DARK_RED + plugin.isTwo() + ChatColor.WHITE + ", ";
        }
        string += "\n";
        if (plugin.isOther()) {
            string += "other: " + ChatColor.DARK_GREEN + plugin.isOther() + ChatColor.WHITE + ", ";
        } else {
            string += "other: " + ChatColor.DARK_RED + plugin.isOther() + ChatColor.WHITE + ", ";
        }
        if (plugin.isPlus()) {
            string += "plus: " + ChatColor.DARK_GREEN + plugin.isPlus() + ChatColor.WHITE + ", ";
        } else {
            string += "plus: " + ChatColor.DARK_RED + plugin.isPlus() + ChatColor.WHITE + ", ";
        }
        if (plugin.isTShape()) {
            string += "T: " + ChatColor.DARK_GREEN + plugin.isTShape() + ChatColor.WHITE + ", ";
        } else {
            string += "T: " + ChatColor.DARK_RED + plugin.isTShape() + ChatColor.WHITE + ", ";
        }
        if (plugin.isRing()) {
            string += "ring: " + ChatColor.DARK_GREEN + plugin.isRing() + ChatColor.WHITE + ", ";
        } else {
            string += "ring: " + ChatColor.DARK_RED + plugin.isRing() + ChatColor.WHITE + ", ";
        }
        string += "\n";
        if (plugin.isLavaFall()) {
            string += "lavaFall: " + ChatColor.DARK_GREEN + plugin.isLavaFall() + ChatColor.WHITE + ", ";
        } else {
            string += "lavaFall: " + ChatColor.DARK_RED + plugin.isLavaFall() + ChatColor.WHITE + ", ";
        }
        if (plugin.isWaterFall()) {
            string += "waterFall: " + ChatColor.DARK_GREEN + plugin.isWaterFall() + ChatColor.WHITE + ", ";
        } else {
            string += "waterFall: " + ChatColor.DARK_RED + plugin.isWaterFall() + ChatColor.WHITE + ", ";
        }
        string += "minHeight: " + ChatColor.YELLOW + plugin.getHeight() + ChatColor.WHITE + ", ";
        string += "\n";
        if (plugin.isFurnace()) {
            string += "furnace: " + ChatColor.DARK_GREEN + plugin.isFurnace() + ChatColor.WHITE + ", ";
        } else {
            string += "furnace: " + ChatColor.DARK_RED + plugin.isFurnace() + ChatColor.WHITE + ", ";
        }
        if (plugin.isPermissions()) {
            string += "permissions: " + ChatColor.DARK_GREEN + plugin.isPermissions() + ChatColor.WHITE + ", ";
        } else {
            string += "permissions: " + ChatColor.DARK_RED + plugin.isPermissions() + ChatColor.WHITE + ", ";
        }
        if (plugin.isMessages()) {
            string += "messages: " + ChatColor.DARK_GREEN + plugin.isMessages() + ChatColor.WHITE + ", ";
        } else {
            string += "messages: " + ChatColor.DARK_RED + plugin.isMessages() + ChatColor.WHITE + ", ";
        }
        String worlds = ChatColor.YELLOW + "Active worlds:";
        sender.sendMessage(header);
        sender.sendMessage(string);
        sender.sendMessage(worlds);
        plugin.getEnabledWorlds().forEach(
                uid -> sender.sendMessage(ChatColor.DARK_GREEN + "\t" + plugin.getServer().getWorld(uid).getName()));
    }

    private void reload(CommandSender sender) {
        plugin.loadConfigsAgain();
        plugin.loadValues();
        String message = plugin.getLocalization().getString("reload");
        plugin.message(sender, null, message, null);
    }

    private void displayHelp(CommandSender sender) {
        String message = plugin.getLocalization().getString("help");
        plugin.message(sender, null, message, null);
    }

    private void enableSource(CommandSender sender, String value) {
        plugin.getConfig().set("sources." + value, true);
        plugin.saveConfig();
        plugin.loadValues();
        String message = plugin.getLocalization().getString("enable_source");
        plugin.message(sender, null, message, value);
    }

    private void enableAll(CommandSender sender) {
        plugin.getConfig().set("sources.three", true);
        plugin.getConfig().set("sources.two", true);
        plugin.getConfig().set("sources.other", true);
        plugin.getConfig().set("sources.big", true);
        plugin.getConfig().set("sources.plus", true);
        plugin.getConfig().set("sources.T", true);
        plugin.getConfig().set("sources.ring", true);
        plugin.getConfig().set("sources.water_fall", true);
        plugin.getConfig().set("sources.lava_fall", true);
        plugin.saveConfig();
        plugin.setThree(true);
        plugin.setTwo(true);
        plugin.setOther(true);
        plugin.setPlus(true);
        plugin.setBig(true);
        plugin.setTShape(true);
        plugin.setRing(true);
        plugin.setWaterFall(true);
        plugin.setLavaFall(true);
        String message = plugin.getLocalization().getString("enable_all");
        plugin.message(sender, null, message, null);
    }

    private void enablePermissions(CommandSender sender) {
        plugin.getConfig().set("configuration.permissions", true);
        plugin.saveConfig();
        plugin.setPermissions(true);
        String message = plugin.getLocalization().getString("enable_permissions");
        plugin.message(sender, null, message, null);
    }

    private void enableMessages(CommandSender sender) {
        plugin.getConfig().set("configuration.messages", true);
        plugin.saveConfig();
        plugin.setMessages(true);
        String message = plugin.getLocalization().getString("enable_messages");
        plugin.message(sender, null, message, null);
    }

    private void enableFurnace(CommandSender sender) {
        plugin.getConfig().set("configuration.furnace", true);
        plugin.saveConfig();
        plugin.setFurnace(true);
        String message = plugin.getLocalization().getString("enable_furnace");
        plugin.message(sender, null, message, null);
    }

    private void disableSource(CommandSender sender, String value) {
        plugin.getConfig().set("sources." + value, false);
        plugin.saveConfig();
        plugin.loadValues();
        String message = plugin.getLocalization().getString("disable_source");
        plugin.message(sender, null, message, value);
    }

    private void disableAll(CommandSender sender) {
        plugin.getConfig().set("sources.three", false);
        plugin.getConfig().set("sources.two", false);
        plugin.getConfig().set("sources.other", false);
        plugin.getConfig().set("sources.big", false);
        plugin.getConfig().set("sources.plus", false);
        plugin.getConfig().set("sources.T", false);
        plugin.getConfig().set("sources.ring", false);
        plugin.getConfig().set("sources.water_fall", false);
        plugin.getConfig().set("sources.lava_fall", false);
        plugin.saveConfig();
        plugin.setThree(false);
        plugin.setTwo(false);
        plugin.setOther(false);
        plugin.setPlus(false);
        plugin.setBig(false);
        plugin.setTShape(false);
        plugin.setRing(false);
        plugin.setWaterFall(false);
        plugin.setLavaFall(false);
        String message = plugin.getLocalization().getString("disable_all");
        plugin.message(sender, null, message, null);
    }

    private void disablePermissions(CommandSender sender) {
        plugin.getConfig().set("configuration.permissions", false);
        plugin.saveConfig();
        plugin.setPermissions(false);
        String message = plugin.getLocalization().getString("disable_permissions");
        plugin.message(sender, null, message, null);
    }

    private void disableMessages(CommandSender sender) {
        plugin.getConfig().set("configuration.messages", false);
        plugin.saveConfig();
        plugin.setMessages(false);
        String message = plugin.getLocalization().getString("disable_messages");
        plugin.message(sender, null, message, null);
    }

    private void disableFurnace(CommandSender sender) {
        plugin.getConfig().set("configuration.furnace", false);
        plugin.saveConfig();
        plugin.setFurnace(false);
        String message = plugin.getLocalization().getString("disable_furnace");
        plugin.message(sender, null, message, null);
    }
}
