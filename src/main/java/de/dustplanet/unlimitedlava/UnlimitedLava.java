package de.dustplanet.unlimitedlava;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.stream.Collectors;

import org.bstats.bukkit.Metrics;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Getter;
import lombok.Setter;
import net.gravitydevelopment.updater.Updater;

@SuppressFBWarnings("CRLF_INJECTION_LOGS")
public class UnlimitedLava extends JavaPlugin {
    private static final int PLUGIN_ID = 31702;
    private static final int BSTATS_PLUGIN_ID = 558;
    private File configFile, localizationFile;
    @Getter
    @Setter
    private boolean three, two, other, big, plus, tShape, ring, lavaFall, waterFall, messages = true, permissions = true, furnace, debug;
    private FileConfiguration config;
    @Getter
    private FileConfiguration localization;
    @Getter
    private int height = 60;
    @Getter
    private List<UUID> enabledWorlds = new ArrayList<>();

    @Override
    public void onDisable() {
        enabledWorlds.clear();
    }

    @Override
    public void onEnable() {
        registerEvents();

        if (!loadConfigFile()) {
            return;
        }
        loadLocalizationFile();

        registerCommand();

        startMetrics();

        startAutoUpdater();
    }

    @SuppressFBWarnings("CRLF_INJECTION_LOGS")
    private void startAutoUpdater() {
        if (config.getBoolean("configuration.autoUpdater", true)) {
            if (getDescription().getVersion().contains("SNAPSHOT")) {
                getLogger().info("AutoUpdater is disabled because you are running a dev build!");
            } else {
                try {
                    // Updater https://bukkit.org/threads96681/
                    Updater updater = new Updater(this, PLUGIN_ID, getFile(), Updater.UpdateType.DEFAULT, true);
                    getLogger().info("AutoUpdater is enabled.");
                    getLogger().info("Result from AutoUpdater is: " + updater.getResult().name());
                } catch (Exception e) {
                    getLogger().info("Error while auto updating: " + e.getMessage());
                }
            }
        } else {
            getLogger().info("AutoUpdater is disabled due to config setting.");
        }
    }

    private boolean loadConfigFile() {
        configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists() && !getDataFolder().exists() && !getDataFolder().mkdirs()) {
            getLogger().severe("The config folder could NOT be created, make sure it's writable!");
            getLogger().severe("Disabling now!");
            setEnabled(false);
            return false;
        }

        if (!configFile.exists()) {
            copy("config.yml", configFile);
        }

        config = getConfig();
        loadConfig();
        loadValues();
        return true;
    }

    private void loadLocalizationFile() {
        localizationFile = new File(getDataFolder(), "localization.yml");
        if (!localizationFile.exists()) {
            copy("localization.yml", localizationFile);
        }

        localization = ScalarYamlConfiguration.loadConfiguration(localizationFile);
        loadLocalization();
    }

    @SuppressWarnings("unused")
    @SuppressFBWarnings("SEC_SIDE_EFFECT_CONSTRUCTOR")
    private void startMetrics() {
        new Metrics(this, BSTATS_PLUGIN_ID);
    }

    private void registerCommand() {
        PluginCommand command = getCommand("unlimitedlava");
        if (command != null) {
            UnlimitedLavaCommands executor = new UnlimitedLavaCommands(this);
            command.setExecutor(executor);
        }
    }

    private void registerEvents() {
        UnlimitedLavaCheck unlimitedLavaCheck = new UnlimitedLavaCheck(this);
        UnlimitedLavaBlockListener blockListener = new UnlimitedLavaBlockListener(this, unlimitedLavaCheck);
        UnlimitedLavaPlayerListener playerListener = new UnlimitedLavaPlayerListener(this, unlimitedLavaCheck);
        UnlimitedLavaInventoryListener inventoryListener = new UnlimitedLavaInventoryListener(this);
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(blockListener, this);
        pm.registerEvents(playerListener, this);
        pm.registerEvents(inventoryListener, this);
    }

    void loadConfig() {
        config.options().header("For help please refer to Bukkit page: https://dev.bukkit.org/projects/unlimited-lava");
        config.addDefault("configuration.permissions", Boolean.TRUE);
        config.addDefault("configuration.messages", Boolean.TRUE);
        config.addDefault("configuration.furnace", Boolean.TRUE);
        config.addDefault("configuration.height", 60);
        config.addDefault("configuration.autoUpdater", Boolean.TRUE);
        config.addDefault("sources.three", Boolean.TRUE);
        config.addDefault("sources.two", Boolean.TRUE);
        config.addDefault("sources.other", Boolean.FALSE);
        config.addDefault("sources.big", Boolean.FALSE);
        config.addDefault("sources.plus", Boolean.TRUE);
        config.addDefault("sources.T", Boolean.TRUE);
        config.addDefault("sources.ring", Boolean.TRUE);
        config.addDefault("sources.lava_fall", Boolean.TRUE);
        config.addDefault("sources.water_fall", Boolean.FALSE);
        config.addDefault("furnace.item", "BUCKET");
        List<String> worlds = getServer().getWorlds().stream().map(w -> w.getUID().toString()).collect(Collectors.toList());
        config.addDefault("enabled_worlds", worlds);
        config.addDefault("debug", Boolean.FALSE);
        config.options().copyDefaults(true);
        saveConfig();
    }

    void loadValues() {
        three = config.getBoolean("sources.three");
        two = config.getBoolean("sources.two");
        other = config.getBoolean("sources.other");
        big = config.getBoolean("sources.big");
        plus = config.getBoolean("sources.plus");
        tShape = config.getBoolean("sources.T");
        ring = config.getBoolean("sources.ring");
        lavaFall = config.getBoolean("sources.lava_fall");
        waterFall = config.getBoolean("sources.water_fall");
        permissions = config.getBoolean("configuration.permissions");
        messages = config.getBoolean("configuration.messages");
        furnace = config.getBoolean("configuration.furnace");
        height = config.getInt("configuration.height");
        enabledWorlds = config.getStringList("enabled_worlds").stream().map(worldStringOrUUID -> {
            @Nullable
            World world = null;
            try {
                UUID uuid = UUID.fromString(worldStringOrUUID);
                world = getServer().getWorld(uuid);
            } catch (@SuppressWarnings("unused") IllegalArgumentException e) {
                world = getServer().getWorld(worldStringOrUUID);
            }
            if (world != null) {
                return world.getUID();
            }
            getLogger().warning("World string '" + worldStringOrUUID + "' is not a valid world and will be ignored");
            return null;

        }).filter(uuid -> uuid != null).collect(Collectors.toList());

        debug = config.getBoolean("debug");
    }

    void loadLocalization() {
        localization.addDefault("permission_denied", "&4You do not have the permission to do this!");
        localization.addDefault("reload", "&2UnlimitedLava &4%version &2reloaded!");
        localization.addDefault("help",
                "&2Welcome to the UnlimitedLava version &4%version &2help\n"
                        + "To see the help type &4/unlimitedlava help &for &4/ulava help\n"
                        + "To reload use &4/unlimitedlava reload &for &4/ulava reload\n"
                        + "To enable something use &4/unlimitedlava enable &e<value>\n" + "or &4/ulava enable &e<value>\n"
                        + "To disable something use &4/unlimitedlava disable &e<value>\n" + "or &4/ulava disable &e<value>\n"
                        + "&eValues: &fpermissions, messages, furnace, all, three, two, other, big, lava_fall, water_fall, plus, T\n"
                        + "&eThe status of UnlimitedLava can be seen with &4/ulava status");
        localization.addDefault("enable_source", "&2UnlimitedLava source &4%source &2enabled!");
        localization.addDefault("enable_all", "&4All &2UnlimitedLava sources enabled!");
        localization.addDefault("enable_messages", "&2UnlimitedLava messages enabled!");
        localization.addDefault("enable_permissions",
                "&2UnlimitedLava permissions enabled!\n" + "&2Only OPs or players with the permission can use the plugin!");
        localization.addDefault("enable_furnace", "&2UnlimitedLava &4furnace &2enabled!");
        localization.addDefault("disable_source", "&2UnlimitedLava source &4%source &2disabled!");
        localization.addDefault("disable_all", "&4All &2UnlimitedLava sources disabled!");
        localization.addDefault("disable_messages", "&2UnlimitedLava messages disabled!");
        localization.addDefault("disable_permissions", "&2UnlimitedLava permissions disabled!\n" + "&4All players can use the plugin!");
        localization.addDefault("disable_furnace", "&2UnlimitedLava &4furnace &2disabled!");
        localization.options().copyDefaults(true);
        saveLocalization();
    }

    public void saveLocalization() {
        try {
            localization.save(localizationFile);
        } catch (IOException e) {
            getLogger().log(Level.WARNING, "Failed to save the localization! Please report this!", e.getCause());
        }
    }

    @SuppressFBWarnings(value = { "IMC_IMMATURE_CLASS_PRINTSTACKTRACE", "INFORMATION_EXPOSURE_THROUGH_AN_ERROR_MESSAGE" })
    public void loadConfigsAgain() {
        try {
            config.load(configFile);
            saveConfig();
            localization.load(localizationFile);
            saveLocalization();
        } catch (IOException | InvalidConfigurationException e) {
            getLogger().warning("Failed to save the localization! Please report this!");
            e.printStackTrace();
        }
    }

    @SuppressFBWarnings("NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE")
    private void copy(String yml, File file) {
        try (OutputStream out = Files.newOutputStream(file.toPath()); InputStream in = getResource(yml)) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } catch (IOException e) {
            getLogger().log(Level.WARNING, "Failed to copy the default config!", e.getCause());
        }
    }

    public void message(CommandSender sender, Player player, String message, String value) {
        if (message == null) {
            return;
        }

        String newValue = value;
        if (newValue == null) {
            newValue = "";
        }
        PluginDescriptionFile pdfFile = this.getDescription();
        String[] msg = ChatColor
                .translateAlternateColorCodes('\u0026',
                        message.replace("%version", pdfFile.getVersion()).replace("%source", newValue).replace("%value", newValue))
                .split("\n");
        if (player != null) {
            player.sendMessage(msg);
        } else if (sender != null) {
            sender.sendMessage(msg);
        } else {
            getLogger().info("Player and sender are null! Please report this");
        }
    }
}
