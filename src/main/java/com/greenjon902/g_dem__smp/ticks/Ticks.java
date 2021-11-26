package com.greenjon902.g_dem__smp.ticks;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.PluginComponent;
import org.bukkit.entity.Player;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Ticks implements PluginComponent {
    private static File ticksFolder;

    @Override
    public void setup(G_Dem__SMP mainClass) {
        ticksFolder = new File(G_Dem__SMP.getInstance().getDataFolder(), "/ticks/records");
        if (!ticksFolder.exists()) {
            //noinspection ResultOfMethodCallIgnored
            ticksFolder.mkdirs();
        }
    }

    @Override
    public void enable(G_Dem__SMP mainClass) {
        mainClass.getServer().getPluginManager().registerEvents(new PlayerSessionEvents(), mainClass);
    }

    @Override
    public void end(G_Dem__SMP mainClass) {

    }

    public static void takeRecord(Player player) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(new File(ticksFolder, String.valueOf(player.getUniqueId()) + ".txt"), true));
            output.write(",");
            output.write(String.valueOf(player.getTicksLived()));
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
