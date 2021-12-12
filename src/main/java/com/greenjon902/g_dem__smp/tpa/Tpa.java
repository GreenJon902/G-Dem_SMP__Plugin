package com.greenjon902.g_dem__smp.tpa;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.PluginComponent;
import com.greenjon902.g_dem__smp.chat.ChatAPI;
import com.greenjon902.g_dem__smp.tpa.commands.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Tpa implements PluginComponent {
    public ArrayList<TpaRequest> tpaRequests = new ArrayList<>();

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

    public void sendTpaRequest(Player from, Player to) {
        TpaRequest tpaRequest = new TpaRequest(from, to);
        tpaRequests.add(tpaRequest);

        ChatAPI.sendMessage("tpa", new HashMap<String, String>() {{
            put("fromUserName", from.getName());
        }}, "Tpa", to);
    }
}

class TpaRequest {
    public Player from;
    public Player to;

    public TpaRequest(Player from, Player to) {
        this.from = from;
        this.to = to;
    }
}