package com.greenjon902.g_dem__smp.tpa;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.PluginComponent;
import com.greenjon902.g_dem__smp.chat.ChatAPI;
import com.greenjon902.g_dem__smp.tpa.commands.*;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Tpa implements PluginComponent {
    private final HashMap<Player, Player> tpaRequests = new HashMap<>();
    private final HashMap<Player, Player> tpaHereRequests = new HashMap<>();

    @Override
    public void setup() {
        //noinspection ConstantConditions
        G_Dem__SMP.getInstance().getCommand("tpa").setExecutor(new CommandTpa());
        //noinspection ConstantConditions
        G_Dem__SMP.getInstance().getCommand("tpaAccept").setExecutor(new CommandTpaAccept());
        //noinspection ConstantConditions
        G_Dem__SMP.getInstance().getCommand("tpaDeny").setExecutor(new CommandTpaDeny());
        //noinspection ConstantConditions
        G_Dem__SMP.getInstance().getCommand("tpaHere").setExecutor(new CommandTpaHere());
        //noinspection ConstantConditions
        G_Dem__SMP.getInstance().getCommand("tpaHereAll").setExecutor(new CommandTpaHereAll());
        //noinspection ConstantConditions
        G_Dem__SMP.getInstance().getCommand("tpaMute").setExecutor(new CommandTpaMute());
    }

    @Override
    public void enable() {

    }

    @Override
    public void end() {

    }

    public void sendTpaRequest(Player commandSender, Player recipient) {
        tpaRequests.put(commandSender, recipient);

        ChatAPI.sendMessage("tpa", new HashMap<String, String>() {{
            put("toUserName", recipient.getName());
            put("fromUserName", commandSender.getName());
        }}, "Tpa", recipient);
    }

    public void sendTpaHereRequest(Player commandSender, Player recipient) {
        tpaHereRequests.put(commandSender, recipient);

        ChatAPI.sendMessage("tpa.here", new HashMap<String, String>() {{
            put("toUserName", recipient.getName());
            put("fromUserName", commandSender.getName());
        }}, "Tpa", recipient);
    }
}
