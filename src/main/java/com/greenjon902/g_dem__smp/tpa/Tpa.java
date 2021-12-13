package com.greenjon902.g_dem__smp.tpa;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.PluginComponent;
import com.greenjon902.g_dem__smp.chat.ChatAPI;
import com.greenjon902.g_dem__smp.tpa.commands.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Tpa implements PluginComponent {
    private final HashMap<Player, ArrayList<Player>> tpaRequests = new HashMap<>();
    private final HashMap<Player, ArrayList<Player>> tpaHereRequests = new HashMap<>();
    private final HashMap<Player, Player> lastTpaRequestToPlayer = new HashMap<>(); // can be both tpa and tpaHere

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
        if (!tpaRequests.containsKey(commandSender)) {
            tpaRequests.put(commandSender, new ArrayList<>());
        }

        if (!tpaRequests.get(commandSender).contains(recipient)) {
            tpaRequests.get(commandSender).add(recipient);
            lastTpaRequestToPlayer.put(recipient, commandSender);

            ChatAPI.sendMessage("tpa", new HashMap<String, String>() {{
                put("toUserName", recipient.getName());
                put("fromUserName", commandSender.getName());
            }}, "Tpa", recipient);
        }
        System.out.println(tpaRequests);
    }

    public void sendTpaHereRequest(Player commandSender, Player recipient) {
        if (!tpaHereRequests.containsKey(commandSender)) {
            tpaHereRequests.put(commandSender, new ArrayList<>());
        }

        if (!tpaHereRequests.get(commandSender).contains(recipient)) {
            tpaHereRequests.get(commandSender).add(recipient);
            lastTpaRequestToPlayer.put(recipient, commandSender);

            ChatAPI.sendMessage("tpa.here", new HashMap<String, String>() {{
                put("toUserName", recipient.getName());
                put("fromUserName", commandSender.getName());
            }}, "Tpa", recipient);
        }
        System.out.println(tpaHereRequests);
    }

    public void tpaAccept(Player commandSender, Player supposedTpaRequestSender) throws NoTpaRequestException {
        if (tpaRequests.containsKey(supposedTpaRequestSender)) {
            if (tpaRequests.get(supposedTpaRequestSender).contains(commandSender)) {
                return;
            }
        }

        if (tpaHereRequests.containsKey(supposedTpaRequestSender)) {
            if (tpaHereRequests.get(supposedTpaRequestSender).contains(commandSender)) {
                return;
            }
        }

        throw new NoTpaRequestException();
    }

    public Player getLastPlayerWhoSentATpaRequestWhereRecipientIs(Player recipient) {
        if (lastTpaRequestToPlayer.containsKey(recipient)) {
            return lastTpaRequestToPlayer.get(recipient);
        }
        return null;
    }
}
