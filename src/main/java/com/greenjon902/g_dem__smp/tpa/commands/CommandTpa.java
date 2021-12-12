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

public class CommandTpa implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            ChatAPI.sendMessage("tpa.commands.tpa.errors.consoleCannotSendTpaRequests", "Tpa", sender);
        } else if (args.length != 1) {
            ChatAPI.sendMessage("tpa.commands.tpa.error.acceptsOneArgument", "Tpa", sender);
        } else {
            Player from = (Player) sender;
            Player to = Bukkit.getPlayerExact(args[0]);
            System.out.println(to);
            if (to == null) {
                System.out.println(1);
                ChatAPI.sendMessage("tpa.commands.tpa.error.playerIsNotOnline", new HashMap<String, String>() {{
                    put("toUserName", args[0]);
                    put("fromUserName", from.getName());
                }}, "Tpa", sender);
                return true;
            }

            ChatAPI.sendMessage("tpa.commands.tpa", new HashMap<String, String>() {{
                put("toUserName", to.getName());
                put("fromUserName", from.getName());
            }}, "tpa", from);
            ((Tpa) G_Dem__SMP.getComponent("Tpa")).sendTpaRequest(from, to);
        }
        return true;
    }
}
