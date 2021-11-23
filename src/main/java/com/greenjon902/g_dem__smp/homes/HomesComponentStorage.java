package com.greenjon902.g_dem__smp.homes;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class HomesComponentStorage {
    private File configFile;
    @SuppressWarnings("FieldCanBeLocal")
    private YamlConfiguration config;
    @SuppressWarnings("FieldCanBeLocal")
    private File homesFolder;
    private HashMap<UUID, HashMap<String, YamlConfiguration>> homes;

    private void checkFiles() {
        configFile = new File(G_Dem__SMP.getInstance().getDataFolder(), "homes/config.yml");
        if (!configFile.exists()) {
            //noinspection ResultOfMethodCallIgnored
            configFile.getParentFile().mkdirs();
            G_Dem__SMP.getInstance().saveResource("homes/config.yml", false);
        }

        homesFolder = new File(G_Dem__SMP.getInstance().getDataFolder(), "homes/playerHomes");
        if (!homesFolder.exists()) {
            //noinspection ResultOfMethodCallIgnored
            homesFolder.mkdirs();
        }
    }

    public void load() {
        checkFiles();

        config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }



    public void save() {
            checkFiles();
        }

    public void setPlayerHome(UUID uniqueId, String name, Location location, CommandSender commandSender) {
        if (!homes.containsKey(uniqueId)) {
            homes.put(uniqueId, new HashMap<>());
        }
        HashMap<String, YamlConfiguration> playerHomes = homes.get(uniqueId);
        if (playerHomes.containsKey(name)) {
            commandSender.sendMessage("That home is already set!");
        } else {
            System.out.println(name);
            playerHomes.put(name, Home.fromLocation(location).toYamlConfiguration());
            //noinspection ConstantConditions
            commandSender.sendMessage("Set home called " + name + " at " + location.getBlock().getX() + " " +
                    location.getBlock().getY() + " " + location.getBlock().getZ() + " in " +
                    location.getWorld().getName());
        }
    }
}

class Home {
    public double x;
    public double y;
    public double z;
    public double pitch;
    public double yaw;

    public String world;

    public static Home fromLocation(Location location) {
        Home home = new Home();
        home.x = location.getX();
        home.y = location.getY();
        home.z = location.getZ();
        home.pitch = location.getPitch();
        home.yaw = location.getYaw();
        //noinspection ConstantConditions
        home.world = location.getWorld().getName();
        return home;
    }

    public YamlConfiguration toYamlConfiguration() {
        return new YamlConfiguration() {{
            set("x", x);
            set("y", y);
            set("z", z);
            set("pitch", pitch);
            set("yaw", yaw);

            set("world", world);
        }};
    }
}
