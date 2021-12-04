package com.greenjon902.g_dem__smp.chat;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.PluginComponent;

public class Chat implements PluginComponent {

    @Override
    public void setup() {
        G_Dem__SMP.getInstance().getServer().getPluginManager().registerEvents(new ChatHandler(), G_Dem__SMP.getInstance());
    }

    @Override
    public void enable() {

    }

    @Override
    public void end() {

    }
}
