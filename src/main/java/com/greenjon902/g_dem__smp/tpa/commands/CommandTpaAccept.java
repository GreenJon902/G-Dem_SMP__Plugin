package com.greenjon902.g_dem__smp.tpa.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.chat.ChatAPI;
import com.greenjon902.g_dem__smp.tpa.NoTpaRequestException;
import com.greenjon902.g_dem__smp.tpa.Tpa;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandTpaAccept implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            ChatAPI.sendMessage("tpa.commands.tpaAccept.errors.consoleCannotAcceptTpaRequests", "Tpa", sender);
        } else if (!(args.length < 2)) {
            ChatAPI.sendMessage("tpa.commands.tpaAccept.errors.acceptsOneArgument", "Tpa", sender);

        } else if (args.length == 1) {
            Player playerSender = (Player) sender;
            Player supposedTpaRequestSender = Bukkit.getPlayerExact(args[0]);

            if (supposedTpaRequestSender == null) {
                ChatAPI.sendMessage("tpa.commands.tpaAccept.errors.playerIsNotOnline", new HashMap<String, String>() {{
                    put("sender", args[0]);
                    put("recipient", playerSender.getName());
                }}, "Tpa", sender);
                return true;
            }

            try {
                ((Tpa) G_Dem__SMP.getComponent("Tpa")).tpaAccept(playerSender, supposedTpaRequestSender);
                ChatAPI.sendMessage("tpa.commands.tpaAccept", new HashMap<String, String>() {{
                    put("sender", supposedTpaRequestSender.getName());
                    put("recipient", playerSender.getName());
                }}, "tpa", playerSender);
            } catch (NoTpaRequestException e) {
                ChatAPI.sendMessage("tpa.commands.tpaAccept.errors.noTpaRequest.withName", new HashMap<String, String>() {{
                    put("sender", supposedTpaRequestSender.getName());
                    put("recipient", playerSender.getName());
                }}, "Tpa", playerSender);
            }


        } else {
            Player playerSender = (Player) sender;
            Player supposedTpaRequestSender = ((Tpa) G_Dem__SMP.getComponent("Tpa")).getLastPlayerWhoSentATpaRequestWhereRecipientIs(playerSender);

            if (supposedTpaRequestSender == null) {
                ChatAPI.sendMessage("tpa.commands.tpaAccept.errors.noTpaRequest", new HashMap<String, String>() {{
                    put("sender", playerSender.getName());
                }}, "Tpa", playerSender);
                return true;
            }

            try {
                ((Tpa) G_Dem__SMP.getComponent("Tpa")).tpaAccept(playerSender, supposedTpaRequestSender);
                ChatAPI.sendMessage("tpa.commands.tpaAccept", new HashMap<String, String>() {{
                    put("sender", supposedTpaRequestSender.getName());
                    put("recipient", playerSender.getName());
                }}, "Tpa", playerSender);
            } catch (NoTpaRequestException e) { // Should be impossible but just to please java / intelliJ
                e.printStackTrace();
            }
        }
        return true;
    }
}
