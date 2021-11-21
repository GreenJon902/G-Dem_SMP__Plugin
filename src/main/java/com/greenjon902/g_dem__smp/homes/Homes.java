package com.greenjon902.g_dem__smp.homes;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.PluginComponent;
import com.greenjon902.g_dem__smp.homes.commands.*;
import org.bukkit.Bukkit;

import java.util.logging.Logger;

public class Homes implements PluginComponent {
    public static final HomesComponentStorage storage = new HomesComponentStorage();

    @Override
    public void setup(G_Dem__SMP mainClass) {
        Logger logger = Bukkit.getLogger();

        logger.info("Loading storage...");
        storage.load();
        logger.info("Loaded storage");

        logger.info("Registering commands...");
        //noinspection ConstantConditions
        mainClass.getCommand("setHome").setExecutor(new CommandSetHome());
        //noinspection ConstantConditions
        mainClass.getCommand("home").setExecutor(new CommandHome());
        //noinspection ConstantConditions
        mainClass.getCommand("delHome").setExecutor(new CommandDelHome());
        //noinspection ConstantConditions
        mainClass.getCommand("listHomes").setExecutor(new CommandListHomes());
        //noinspection ConstantConditions
        mainClass.getCommand("listAllHomes").setExecutor(new CommandListAllHomes());
        logger.info("Registered commands");
    }

    @Override
    public void enable(G_Dem__SMP mainClass) {

    }

    @Override
    public void end(G_Dem__SMP mainClass) {
        storage.save();
    }
}
