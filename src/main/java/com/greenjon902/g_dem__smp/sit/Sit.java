package com.greenjon902.g_dem__smp.sit;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.PluginComponent;
import com.greenjon902.g_dem__smp.sit.commands.CommandSit;
import com.greenjon902.g_dem__smp.sit.commands.CommandStandAll;

public class Sit implements PluginComponent {
    @Override
    public void setup(G_Dem__SMP mainClass) {
        System.out.println("Test124");
        //noinspection ConstantConditions
        mainClass.getCommand("sit").setExecutor(new CommandSit());
        //noinspection ConstantConditions
        mainClass.getCommand("standAll").setExecutor(new CommandStandAll());
    }

    @Override
    public void enable(G_Dem__SMP mainClass) {

    }

    @Override
    public void end(G_Dem__SMP mainClass) {
        SitAPI.getAPI().standAll();
    }
}
