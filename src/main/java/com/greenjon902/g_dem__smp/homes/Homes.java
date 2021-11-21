package com.greenjon902.g_dem__smp.homes;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.PluginComponent;
import com.greenjon902.g_dem__smp.homes.commands.CommandDelHome;
import com.greenjon902.g_dem__smp.homes.commands.CommandHome;
import com.greenjon902.g_dem__smp.homes.commands.CommandSetHome;

public class Homes implements PluginComponent {
    @Override
    public void setup(G_Dem__SMP mainClass) {
        //noinspection ConstantConditions
        mainClass.getCommand("setHome").setExecutor(new CommandSetHome());
        //noinspection ConstantConditions
        mainClass.getCommand("home").setExecutor(new CommandHome());
        //noinspection ConstantConditions
        mainClass.getCommand("delHome").setExecutor(new CommandDelHome());
    }

    @Override
    public void enable(G_Dem__SMP mainClass) {

    }

    @Override
    public void end(G_Dem__SMP mainClass) {

    }
}
