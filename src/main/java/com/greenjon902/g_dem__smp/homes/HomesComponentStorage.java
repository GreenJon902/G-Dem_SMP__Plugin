package com.greenjon902.g_dem__smp.homes;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class HomesComponentStorage {
    private File configFile;
    @SuppressWarnings("FieldCanBeLocal")
    private YamlConfiguration config;
    @SuppressWarnings("FieldCanBeLocal")
    private File homesFolder;
    private HashMap<UUID, HashMap<String, Home>> homes;

    private void checkFiles() {
        configFile = new File(G_Dem__SMP.getInstance().getDataFolder(), "/homes/config.yml");
        if (!configFile.exists()) {
            //noinspection ResultOfMethodCallIgnored
            configFile.getParentFile().mkdirs();
            G_Dem__SMP.getInstance().saveResource("homes/config.yml", false);
        }

        homesFolder = new File(G_Dem__SMP.getInstance().getDataFolder(), "/homes/playerHomes");
        if (!homesFolder.exists()) {
            //noinspection ResultOfMethodCallIgnored
            homesFolder.mkdirs();
        }
    }

    public Location getHomeLocation(String name, UUID uniqueId) {
        if (homes.containsKey(uniqueId) && homes.get(uniqueId).containsKey(name)) {
            return homes.get(uniqueId).get(name).toLocation();
        }
        return null;
    }

    public void load() {
        checkFiles();

        config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        homes = new HashMap<>();
        File playerHomesFolder = new File(G_Dem__SMP.getInstance().getDataFolder(), "/homes/playerHomes/");

        UUID playerUid;
        //noinspection ConstantConditions
        for (File file : playerHomesFolder.listFiles()) {

            if (!file.isFile() & !file.getName().equals(".DS_Store")) {
                playerUid = UUID.fromString(file.getName().replace(".yml", ""));
                homes.put(playerUid, new HashMap<>());

                //noinspection ConstantConditions
                for (File file2 : new File(playerHomesFolder, playerUid.toString()).listFiles()) {
                    System.out.println(file + file.getName() + file.getName() + file2);
                    if (file2.isFile() & !file2.getName().equals(".DS_Store")) {
                        homes.get(playerUid).put(file2.getName().replace(".yml", ""),
                                Home.fromYamlConfiguration(YamlConfiguration.loadConfiguration(file2)));
                        System.out.println(file + file.getName() + file.getName() + file2);
                    }
                }
            }
        }
        System.out.println(homes);
    }



    public void save() {
        checkFiles();


        Home playerHome;
        File playerFolder;
        String name;
        Home home;
        File playerHomesFolder = new File(G_Dem__SMP.getInstance().getDataFolder(), "/homes/playerHomes/");
        for (UUID playerUid : homes.keySet()) {

            playerFolder = new File(playerHomesFolder, playerUid.toString() + "/");
            if (!playerFolder.exists()) {
                //noinspection ResultOfMethodCallIgnored
                playerFolder.mkdirs();
            }

            for (Map.Entry<String, Home> homeEntry : homes.get(playerUid).entrySet()) {
                name = homeEntry.getKey();
                home = homeEntry.getValue();

                try {
                    home.toYamlConfiguration().save(new File(playerFolder, name + ".yml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setPlayerHome(UUID uniqueId, String name, Location location, CommandSender commandSender) {
        if (!homes.containsKey(uniqueId)) {
            homes.put(uniqueId, new HashMap<>());
        }
        HashMap<String, Home> playerHomes = homes.get(uniqueId);
        if (playerHomes.containsKey(name)) {
            commandSender.sendMessage("That home is already set!");
        } else {
            System.out.println(name);
            playerHomes.put(name, Home.fromLocation(location));
            //noinspection ConstantConditions
            commandSender.sendMessage("Set home called " + name + " at " + location.getBlock().getX() + " " +
                    location.getBlock().getY() + " " + location.getBlock().getZ() + " in " +
                    location.getWorld().getName());
        }
    }

    public Set<String> getPlayerHomes(UUID uniqueId) {
        return homes.get(uniqueId).keySet();
    }

    public HashMap<UUID, Set<String>> getAllPlayerHomes() {
        HashMap<UUID, Set<String>> allHomes = new HashMap<>();

        for (UUID uniqueId : homes.keySet()) {
            allHomes.put(uniqueId, getPlayerHomes(uniqueId));
        }

        return allHomes;
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

    public Location toLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z, (float) yaw, (float) pitch);
    }

    public static Home fromYamlConfiguration(YamlConfiguration yamlConfiguration) {
        Home home = new Home();
        home.x = yamlConfiguration.getDouble("x");
        home.y = yamlConfiguration.getDouble("y");
        home.z = yamlConfiguration.getDouble("z");
        home.pitch = yamlConfiguration.getDouble("pitch");
        home.yaw = yamlConfiguration.getDouble("yaw");
        home.world = yamlConfiguration.getString("world");
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
