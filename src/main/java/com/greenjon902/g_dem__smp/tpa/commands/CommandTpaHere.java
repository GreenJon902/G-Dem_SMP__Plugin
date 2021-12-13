package com.greenjon902.g_dem__smp.tpa.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.chat.ChatAPI;
import com.greenjon902.g_dem__smp.tpa.Tpa;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandTpaHere implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            ChatAPI.sendMessage("tpa.commands.tpaHere.errors.consoleCannotSendTpaRequests", "Tpa", sender);
        } else if (args.length != 1) {
            ChatAPI.sendMessage("tpa.commands.tpaHere.errors.acceptsOneArgument", "Tpa", sender);

        } else {
            Player commandSender = (Player) sender;
            Player recipient = Bukkit.getPlayerExact(args[0]);

            if (recipient == null) {
                ChatAPI.sendMessage("tpa.commands.tpaHere.errors.playerIsNotOnline", new HashMap<String, String>() {{
                    put("toUserName", args[0]);
                    put("fromUserName", commandSender.getName());
                }}, "Tpa", sender);
                return true;
            }

            ChatAPI.sendMessage("tpa.commands.tpaHere", new HashMap<String, String>() {{
                put("toUserName", recipient.getName());
                put("fromUserName", commandSender.getName());
            }}, "tpa", commandSender);
            ((Tpa) G_Dem__SMP.getComponent("Tpa")).sendTpaHereRequest(commandSender, recipient);
        }
        return true;
    }
}
