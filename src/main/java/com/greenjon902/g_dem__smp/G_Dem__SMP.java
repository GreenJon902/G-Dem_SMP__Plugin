package com.greenjon902.g_dem__smp;

import com.greenjon902.g_dem__smp.sit.Sit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Logger;

public final class G_Dem__SMP extends JavaPlugin {
    // Please end any components with a comma because that makes merging a lot easier
    private static final HashMap<String, PluginComponent> components = new HashMap<String, PluginComponent>() {{
                    put("Sit", new Sit());
    }};

    public static PluginComponent getComponent(String name) {
        return components.get(name);
    }

    private static G_Dem__SMP instance;

    public static G_Dem__SMP getInstance() {
        return G_Dem__SMP.instance;
    }

    @Override
    public void onEnable() {
        Logger logger = getLogger();

        logger.info("Starting the G-Dem SMP plugin...");
        G_Dem__SMP.instance = this;

        PluginComponent component;

        for (String component_name : components.keySet()) {
            component = components.get(component_name);
            logger.info("Setting up " + component.getClass().toString());
            component.setup();
        }

        for (String component_name : components.keySet()) {
            component = components.get(component_name);
            logger.info("Enabling " + component.getClass().toString());
            component.enable();
        }

        logger.info("Started the G-Dem SMP plugin...");

    }

    @Override
    public void onDisable() {
        Logger logger = getLogger();

        logger.info("Ending the G-Dem SMP plugin...");

        PluginComponent component;
        for (String component_name : components.keySet()) {
            component = components.get(component_name);
            logger.info("Disabling " + component.getClass().toString());
            component.end();
        }

        logger.info("Ended the G-Dem SMP plugin...");
    }
}
