package com.greenjon902.g_dem__smp.chat;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.PluginComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

    public void sendMessage(String messageId, String componentId, CommandSender receiver) {
        // TODO: Load actual message and stuff
        receiver.sendMessage("[" + componentId + "]  " + messageId);
    }

    public void announceMessage(String messageId, String componentId) {
        // TODO: Load actual message and stuff
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            player.sendMessage("[" + componentId + "]  " + messageId);
        }
    }
}
