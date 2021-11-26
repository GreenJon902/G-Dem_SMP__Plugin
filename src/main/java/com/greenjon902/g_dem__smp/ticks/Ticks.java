package com.greenjon902.g_dem__smp.ticks;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.PluginComponent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;


public class Ticks implements PluginComponent {
    File ticksFile;

    @Override
    public void setup(G_Dem__SMP mainClass) {
        ticksFile = new File(G_Dem__SMP.getInstance().getDataFolder(), "/ticks/records/");
        if (!ticksFile.exists()) {
            //noinspection ResultOfMethodCallIgnored
            ticksFile.mkdirs();
        }
    }

    @Override
    public void enable(G_Dem__SMP mainClass) {
    }

    @Override
    public void end(G_Dem__SMP mainClass) {

    }

    public static void takeRecord() {
        System.out.println("Make record function ran but it ain got nothin in it");
    }
}
