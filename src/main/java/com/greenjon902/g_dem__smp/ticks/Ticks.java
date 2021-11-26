package com.greenjon902.g_dem__smp.ticks;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.PluginComponent;

import java.io.File;


public class Ticks implements PluginComponent {
    File configFile;

    @Override
    public void setup(G_Dem__SMP mainClass) {
        configFile = new File(G_Dem__SMP.getInstance().getDataFolder(), "/ticks/config.yml");
        if (!configFile.exists()) {
            //noinspection ResultOfMethodCallIgnored
            configFile.getParentFile().mkdirs();
            G_Dem__SMP.getInstance().saveResource("ticks/config.yml", false);
        }
    }

    @Override
    public void enable(G_Dem__SMP mainClass) {

    }

    @Override
    public void end(G_Dem__SMP mainClass) {

    }
}
