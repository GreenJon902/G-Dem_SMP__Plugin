package com.greenjon902.g_dem__smp;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class G_Dem__SMP extends JavaPlugin {
    private static final PluginComponent[] components =
            {
    };


    @Override
    public void onEnable() {
        Logger logger = getLogger();

        logger.info("Starting the G-Dem SMP plugin...");

        int component_index;
        PluginComponent component;

        for (component_index=0; component_index < components.length; component_index++) {
            component = components[component_index];
            logger.info("Setting up " + component.getClass().toString());
            component.setup(this);
        }

        for (component_index=0; component_index < components.length; component_index++) {
            component = components[component_index];
            logger.info("Enabling " + component.getClass().toString());
            component.enable(this);
        }

        logger.info("Started the G-Dem SMP plugin...");

    }

    @Override
    public void onDisable() {
        Logger logger = getLogger();

        logger.info("Ending the G-Dem SMP plugin...");

        int component_index;
        PluginComponent component;
        for (component_index=0; component_index < components.length; component_index++) {
            component = components[component_index];
            component.end(this);
        }

        logger.info("Ended the G-Dem SMP plugin...");
    }
}
