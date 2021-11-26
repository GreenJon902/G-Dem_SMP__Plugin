package com.greenjon902.g_dem__smp.ticks;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.PluginComponent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;


public class Ticks implements PluginComponent {
    File configFile;
    YamlConfiguration config;
    File ticksFile;

    @Override
    public void setup(G_Dem__SMP mainClass) {
        configFile = new File(G_Dem__SMP.getInstance().getDataFolder(), "/ticks/config.yml");
        if (!configFile.exists()) {
            //noinspection ResultOfMethodCallIgnored
            configFile.getParentFile().mkdirs();
            G_Dem__SMP.getInstance().saveResource("ticks/config.yml", false);
        }
        config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }


        ticksFile = new File(G_Dem__SMP.getInstance().getDataFolder(), "/ticks/ticks.csv");
        if (!ticksFile.exists()) {
            //noinspection ResultOfMethodCallIgnored
            ticksFile.mkdirs();
        }
    }

    @Override
    public void enable(G_Dem__SMP mainClass) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(mainClass, Ticks::takeRecord, 0,
                20L *60*config.getInt("record-interval"));
    }

    @Override
    public void end(G_Dem__SMP mainClass) {

    }

    public static void takeRecord() {
        System.out.println("Make record function ran but it ain got nothin in it");
    }
}
