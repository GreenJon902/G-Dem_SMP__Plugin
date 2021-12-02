package com.greenjon902.g_dem__smp.homes;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.PluginComponent;
import com.greenjon902.g_dem__smp.homes.commands.*;
import org.bukkit.Bukkit;

import java.util.logging.Logger;

public class Homes implements PluginComponent {
    public static final HomesComponentStorage storage = new HomesComponentStorage();

    @Override
    public void setup() {
        Logger logger = Bukkit.getLogger();

        logger.info("Loading storage...");
        storage.load();
        logger.info("Loaded storage");

        logger.info("Registering commands...");
        //noinspection ConstantConditions
        G_Dem__SMP.getInstance().getCommand("setHome").setExecutor(new CommandSetHome());
        //noinspection ConstantConditions
        G_Dem__SMP.getInstance().getCommand("home").setExecutor(new CommandHome());
        //noinspection ConstantConditions
        G_Dem__SMP.getInstance().getCommand("delHome").setExecutor(new CommandDelHome());
        //noinspection ConstantConditions
        G_Dem__SMP.getInstance().getCommand("listHomes").setExecutor(new CommandListHomes());
        //noinspection ConstantConditions
        G_Dem__SMP.getInstance().getCommand("listAllHomes").setExecutor(new CommandListAllHomes());
        logger.info("Registered commands");
    }

    @Override
    public void enable() {

    }

    @Override
    public void end() {
        Logger logger = Bukkit.getLogger();

        logger.info("Saving storage...");
        storage.save();
        logger.info("Saved storage");
    }
}
