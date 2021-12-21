package com.greenjon902.g_dem__smp.tpa;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.PluginComponent;
import com.greenjon902.g_dem__smp.chat.ChatAPI;
import com.greenjon902.g_dem__smp.tpa.commands.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    }

    @Override
    public void enable() {

    }

    @Override
    public void end() {

    }

    public void sendTpaRequest(Player sender, Player recipient) {
        if (!tpaRequests.containsKey(sender)) {
            tpaRequests.put(sender, new ArrayList<>());
        }

        if (!tpaRequests.get(sender).contains(recipient)) {
            System.out.println("1" + recipient);
            tpaRequests.get(sender).add(recipient);
            lastTpaRequestToPlayer.put(recipient, sender);

            ChatAPI.sendMessage("tpa", new HashMap<String, String>() {{
                put("recipient", recipient.getName());
                put("sender", sender.getName());
            }}, "Tpa", recipient);
        }
        System.out.println(tpaRequests);
    }

    public void sendTpaHereRequest(Player sender, Player recipient) {
        if (!tpaHereRequests.containsKey(sender)) {
            tpaHereRequests.put(sender, new ArrayList<>());
        }

        if (!tpaHereRequests.get(sender).contains(recipient)) {
            tpaHereRequests.get(sender).add(recipient);
            lastTpaRequestToPlayer.put(recipient, sender);

            ChatAPI.sendMessage("tpa.here", new HashMap<String, String>() {{
                put("recipient", recipient.getName());
                put("sender", sender.getName());
            }}, "Tpa", recipient);
        }
        System.out.println(tpaHereRequests);
    }

    public void tpaAccept(Player sender, Player supposedTpaRequestSender) throws NoTpaRequestException {
        System.out.println(tpaHereRequests);
        if (tpaRequests.containsKey(supposedTpaRequestSender)) { // original sender -> original recipient
            if (tpaRequests.get(supposedTpaRequestSender).contains(sender)) {

                supposedTpaRequestSender.teleport(sender.getLocation());
                tpaRequests.get(supposedTpaRequestSender).remove(sender);
                lastTpaRequestToPlayer.remove(sender);
                System.out.println(tpaHereRequests);
                ChatAPI.sendMessage("tpa.accept", new HashMap<String, String>() {{
                    put("sender", supposedTpaRequestSender.getName());
                    put("recipient", sender.getName());
                }}, "Tpa", supposedTpaRequestSender);
                return;
            }
        }

        if (tpaHereRequests.containsKey(supposedTpaRequestSender)) {// original recipient -> original sender
            if (tpaHereRequests.get(supposedTpaRequestSender).contains(sender)) {

                sender.teleport(supposedTpaRequestSender.getLocation());
                tpaHereRequests.get(supposedTpaRequestSender).remove(sender);
                lastTpaRequestToPlayer.remove(sender);
                System.out.println(tpaHereRequests);
                ChatAPI.sendMessage("tpa.accept.here", new HashMap<String, String>() {{
                    put("sender", supposedTpaRequestSender.getName());
                    put("recipient", sender.getName());
                }}, "Tpa", supposedTpaRequestSender);
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

    public void tpaDeny(Player sender, Player supposedTpaRequestSender) throws NoTpaRequestException {
        System.out.println(tpaHereRequests);
        if (tpaRequests.containsKey(supposedTpaRequestSender)) {
            if (tpaRequests.get(supposedTpaRequestSender).contains(sender)) {

                tpaRequests.get(supposedTpaRequestSender).remove(sender);
                lastTpaRequestToPlayer.remove(sender);
                ChatAPI.sendMessage("tpa.deny", new HashMap<String, String>() {{
                    put("sender", supposedTpaRequestSender.getName());
                    put("recipient", sender.getName());
                }}, "Tpa", supposedTpaRequestSender);
                return;
            }
        }

        if (tpaHereRequests.containsKey(supposedTpaRequestSender)) {
            if (tpaHereRequests.get(supposedTpaRequestSender).contains(sender)) {

                tpaHereRequests.get(supposedTpaRequestSender).remove(sender);
                lastTpaRequestToPlayer.remove(sender);
                ChatAPI.sendMessage("tpa.deny.here", new HashMap<String, String>() {{
                    put("sender", supposedTpaRequestSender.getName());
                    put("recipient", sender.getName());
                }}, "Tpa", supposedTpaRequestSender);
                return;
            }
        }
        System.out.println(tpaHereRequests);

        throw new NoTpaRequestException();
    }

    public List<Player> getPlayersWhoSentATpaRequestWhereRecipientIs(Player recipient) {
        ArrayList<Player> players = new ArrayList<>();
        for (Player sender : tpaRequests.keySet()) {
            if (tpaRequests.containsKey(sender)) {
                 if (tpaRequests.get(sender).contains(recipient)) {
                     players.add(sender);
                 }
            }
        }

        for (Player sender : tpaHereRequests.keySet()) {
            if (tpaHereRequests.containsKey(sender)) {
                if (tpaHereRequests.get(sender).contains(recipient)) {
                    players.add(sender);
                }
            }
        }
        return players;
    }
}
