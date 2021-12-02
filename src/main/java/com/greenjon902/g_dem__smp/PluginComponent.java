package com.greenjon902.g_dem__smp;

public abstract class PluginComponent {
    public void setup() {
        PluginComponent.instance = this;
    };

    public abstract void enable();

    public abstract void end();



    private static PluginComponent instance;

    public static PluginComponent getInstance() {
        return PluginComponent.instance;
    }
}
