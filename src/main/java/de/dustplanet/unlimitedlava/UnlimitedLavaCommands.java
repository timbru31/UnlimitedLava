package de.dustplanet.unlimitedlava;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings(value = { "FCCD_FIND_CLASS_CIRCULAR_DEPENDENCY", "CD_CIRCULAR_DEPENDENCY", "CLI_CONSTANT_LIST_INDEX",
        "IMC_IMMATURE_CLASS_NO_TOSTRING" })
public class UnlimitedLavaCommands implements CommandExecutor {
    private UnlimitedLava plugin;
    private String[] values = { "three", "two", "other", "big", "lava_fall", "water_fall", "plus", "T", "ring" };

    public UnlimitedLavaCommands(UnlimitedLava instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        String message, value;
        if (args.length > 0 && "reload".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("unlimitedlava.reload") || !plugin.isPermissions()) {
                reload(sender);
            } else {
                message = plugin.getLocalization().getString("permission_denied");
                plugin.message(sender, null, message, null);
            }
            return true;
        } else if (args.length > 0 && "status".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("unlimitedlava.status") || !plugin.isPermissions()) {
                displayStatus(sender);
            } else {
                message = plugin.getLocalization().getString("permission_denied");
                plugin.message(sender, null, message, null);
            }
            return true;
        } else if (args.length > 0 && "help".equalsIgnoreCase(args[0])) {
            if (sender.hasPermission("unlimitedlava.help") || !plugin.isPermissions()) {
                displayHelp(sender);
            } else {
                message = plugin.getLocalization().getString("permission_denied");
                plugin.message(sender, null, message, null);
            }
            return true;
        } else if (args.length > 0 && "enable".equalsIgnoreCase(args[0])) {
            if (args.length > 1 && "all".equalsIgnoreCase(args[1])) {
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
            } else if (args.length > 1 && "furnace".equalsIgnoreCase(args[1])) {
                if (sender.hasPermission("unlimitedlava.enable.furnace") || !plugin.isPermissions()) {
                    enableFurnace(sender);
                } else {
                    message = plugin.getLocalization().getString("permission_denied");
                    plugin.message(sender, null, message, null);
                }
                return true;
            } else if (args.length > 1 && "permissions".equalsIgnoreCase(args[1])) {
                if (sender.hasPermission("unlimitedlava.enable.permissions") || !plugin.isPermissions()) {
                    enablePermissions(sender);
                } else {
                    message = plugin.getLocalization().getString("permission_denied");
                    plugin.message(sender, null, message, null);
                }
                return true;
            } else if (args.length > 1 && "messages".equalsIgnoreCase(args[1])) {
                if (sender.hasPermission("unlimitedlava.enable.messages") || !plugin.isPermissions()) {
                    enableMessages(sender);
                } else {
                    message = plugin.getLocalization().getString("permission_denied");
                    plugin.message(sender, null, message, null);
                }
                return true;
            }
        } else if (args.length > 0 && "disable".equalsIgnoreCase(args[0])) {
            if (args.length > 1 && "all".equalsIgnoreCase(args[1])) {
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
            } else if (args.length > 1 && "permissions".equalsIgnoreCase(args[1])) {
                if (sender.hasPermission("unlimitedlava.disable.permissions") || !plugin.isPermissions()) {
                    disablePermissions(sender);
                } else {
                    message = plugin.getLocalization().getString("permission_denied");
                    plugin.message(sender, null, message, null);
                }
                return true;
            } else if (args.length > 1 && "messages".equalsIgnoreCase(args[1])) {
                if (sender.hasPermission("unlimitedlava.disable.messages") || !plugin.isPermissions()) {
                    disableMessages(sender);
                } else {
                    message = plugin.getLocalization().getString("permission_denied");
                    plugin.message(sender, null, message, null);
                }
                return true;
            } else if (args.length > 1 && "furnace".equalsIgnoreCase(args[1])) {
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
        StringBuilder stringBuilder = new StringBuilder();
        appendPart(stringBuilder, "big", plugin.isBig());
        appendPart(stringBuilder, "three", plugin.isThree());
        appendPart(stringBuilder, "two", plugin.isTwo());
        stringBuilder.append('\n');
        appendPart(stringBuilder, "other", plugin.isOther());
        appendPart(stringBuilder, "plus", plugin.isPlus());
        appendPart(stringBuilder, "T", plugin.isTShape());
        appendPart(stringBuilder, "ring", plugin.isRing());
        stringBuilder.append('\n');
        appendPart(stringBuilder, "lavaFall", plugin.isLavaFall());
        appendPart(stringBuilder, "waterFall", plugin.isWaterFall());
        // @formatter:off
        stringBuilder
            .append("minHeight: ")
            .append(ChatColor.YELLOW)
            .append(plugin.getHeight())
            .append(ChatColor.WHITE)
            .append(", \n");
        // @formatter:on
        appendPart(stringBuilder, "furnace", plugin.isFurnace());
        appendPart(stringBuilder, "permission", plugin.isPermissions());
        appendPart(stringBuilder, "messages", plugin.isMessages());
        String worlds = ChatColor.YELLOW + "Active worlds:";
        sender.sendMessage(header);
        sender.sendMessage(stringBuilder.toString());
        sender.sendMessage(worlds);
        plugin.getEnabledWorlds().forEach(uuid -> {
            World world = plugin.getServer().getWorld(uuid);
            String worldName = world != null ? world.getName() : uuid.toString();
            sender.sendMessage(ChatColor.DARK_GREEN + "- " + worldName);
        });
    }

    @SuppressWarnings("static-method")
    private void appendPart(StringBuilder stringBuilder, String part, boolean partFlag) {
        // @@formatter:off
        stringBuilder
            .append(part)
            .append(": ")
            .append(partFlag ? ChatColor.DARK_GREEN : ChatColor.RED)
            .append(partFlag)
            .append(ChatColor.WHITE)
            .append(", ");
        // @formatter:on
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
        plugin.getConfig().set("sources." + value, Boolean.TRUE);
        plugin.saveConfig();
        plugin.loadValues();
        String message = plugin.getLocalization().getString("enable_source");
        plugin.message(sender, null, message, value);
    }

    private void enableAll(CommandSender sender) {
        plugin.getConfig().set("sources.three", Boolean.TRUE);
        plugin.getConfig().set("sources.two", Boolean.TRUE);
        plugin.getConfig().set("sources.other", Boolean.TRUE);
        plugin.getConfig().set("sources.big", Boolean.TRUE);
        plugin.getConfig().set("sources.plus", Boolean.TRUE);
        plugin.getConfig().set("sources.T", Boolean.TRUE);
        plugin.getConfig().set("sources.ring", Boolean.TRUE);
        plugin.getConfig().set("sources.water_fall", Boolean.TRUE);
        plugin.getConfig().set("sources.lava_fall", Boolean.TRUE);
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
        plugin.getConfig().set("configuration.permissions", Boolean.TRUE);
        plugin.saveConfig();
        plugin.setPermissions(true);
        String message = plugin.getLocalization().getString("enable_permissions");
        plugin.message(sender, null, message, null);
    }

    private void enableMessages(CommandSender sender) {
        plugin.getConfig().set("configuration.messages", Boolean.TRUE);
        plugin.saveConfig();
        plugin.setMessages(true);
        String message = plugin.getLocalization().getString("enable_messages");
        plugin.message(sender, null, message, null);
    }

    private void enableFurnace(CommandSender sender) {
        plugin.getConfig().set("configuration.furnace", Boolean.TRUE);
        plugin.saveConfig();
        plugin.setFurnace(true);
        String message = plugin.getLocalization().getString("enable_furnace");
        plugin.message(sender, null, message, null);
    }

    private void disableSource(CommandSender sender, String value) {
        plugin.getConfig().set("sources." + value, Boolean.FALSE);
        plugin.saveConfig();
        plugin.loadValues();
        String message = plugin.getLocalization().getString("disable_source");
        plugin.message(sender, null, message, value);
    }

    private void disableAll(CommandSender sender) {
        plugin.getConfig().set("sources.three", Boolean.FALSE);
        plugin.getConfig().set("sources.two", Boolean.FALSE);
        plugin.getConfig().set("sources.other", Boolean.FALSE);
        plugin.getConfig().set("sources.big", Boolean.FALSE);
        plugin.getConfig().set("sources.plus", Boolean.FALSE);
        plugin.getConfig().set("sources.T", Boolean.FALSE);
        plugin.getConfig().set("sources.ring", Boolean.FALSE);
        plugin.getConfig().set("sources.water_fall", Boolean.FALSE);
        plugin.getConfig().set("sources.lava_fall", Boolean.FALSE);
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
        plugin.getConfig().set("configuration.permissions", Boolean.FALSE);
        plugin.saveConfig();
        plugin.setPermissions(false);
        String message = plugin.getLocalization().getString("disable_permissions");
        plugin.message(sender, null, message, null);
    }

    private void disableMessages(CommandSender sender) {
        plugin.getConfig().set("configuration.messages", Boolean.FALSE);
        plugin.saveConfig();
        plugin.setMessages(false);
        String message = plugin.getLocalization().getString("disable_messages");
        plugin.message(sender, null, message, null);
    }

    private void disableFurnace(CommandSender sender) {
        plugin.getConfig().set("configuration.furnace", Boolean.FALSE);
        plugin.saveConfig();
        plugin.setFurnace(false);
        String message = plugin.getLocalization().getString("disable_furnace");
        plugin.message(sender, null, message, null);
    }
}
