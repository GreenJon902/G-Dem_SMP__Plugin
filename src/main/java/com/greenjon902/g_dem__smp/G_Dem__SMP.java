package com.greenjon902.g_dem__smp;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class G_Dem__SMP extends JavaPlugin {
    @Override
    public void onEnable() {
        Logger logger = getLogger();
        logger.info("Starting the G-Dem SMP plugin...");

        logger.info("Started the G-Dem SMP plugin...");

    }

    @Override
    public void onDisable() {
        Logger logger = getLogger();
        logger.info("Ending the G-Dem SMP plugin...");

        logger.info("Ended the G-Dem SMP plugin...");
    }
}
