package com.greenjon902.g_dem__smp.pluginName;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.PluginComponent;
import com.greenjon902.g_dem__smp.pluginName.commands.CommandCommandName;
import org.bukkit.Bukkit;

import java.util.logging.Logger;

public class PluginName implements PluginComponent {
    @Override
    public void setup(G_Dem__SMP mainClass) {
        Logger logger = Bukkit.getLogger();

        logger.info("Registering commands...");
        //noinspection ConstantConditions
        mainClass.getCommand("setHome").setExecutor(new CommandCommandName());
    }

    @Override
    public void enable(G_Dem__SMP mainClass) {

    }

    @Override
    public void end(G_Dem__SMP mainClass) {

    }
}
