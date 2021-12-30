package com.greenjon902.g_dem__smp.sit;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.PluginComponent;
import com.greenjon902.g_dem__smp.sit.commands.CommandSit;
import com.greenjon902.g_dem__smp.sit.commands.CommandStandAll;

public class Sit implements PluginComponent {
    public static SitAPI API = new SitAPI();


    @Override
    public void setup() {
        //noinspection ConstantConditions
        G_Dem__SMP.getInstance().getCommand("sit").setExecutor(new CommandSit());
        //noinspection ConstantConditions
        G_Dem__SMP.getInstance().getCommand("standAll").setExecutor(new CommandStandAll());
    }

    @Override
    public void enable() {

    }

    @Override
    public void end() {
        Sit.API.standAll();
    }
}
