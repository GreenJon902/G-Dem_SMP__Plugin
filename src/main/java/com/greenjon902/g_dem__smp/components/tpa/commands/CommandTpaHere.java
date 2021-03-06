package com.greenjon902.g_dem__smp.components.tpa.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.components.chat.ChatAPI;
import com.greenjon902.g_dem__smp.components.tpa.Tpa;
import com.greenjon902.tabCompleterHelper.TabCompleterHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class CommandTpaHere implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            ChatAPI.sendMessage("tpa.commands.tpaHere.errors.consoleCannotSendTpaRequests", "Tpa", sender);
        } else if (args.length != 1) {
            ChatAPI.sendMessage("tpa.commands.tpaHere.errors.acceptsOneArgument", "Tpa", sender);

        } else {
            Player playerSender = (Player) sender;
            Player recipient = Bukkit.getPlayerExact(args[0]);

            if (recipient == null) {
                ChatAPI.sendMessage("tpa.commands.tpaHere.errors.playerIsNotOnline", new HashMap<String, String>() {{
                    put("recipient", args[0]);
                    put("sender", playerSender.getName());
                }}, "Tpa", sender);
                return true;
            }

            ChatAPI.sendMessage("tpa.commands.tpaHere", new HashMap<String, String>() {{
                put("recipient", recipient.getName());
                put("sender", playerSender.getName());
            }}, "Tpa", sender);
            ((Tpa) G_Dem__SMP.getComponent("Tpa")).sendTpaHereRequest(playerSender, recipient);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return TabCompleterHelper.filterWithFunction(
                    Bukkit.getOnlinePlayers().toArray(),
                    (player) -> ((Player) player).getName(),
                    args[0]);
        }
        return TabCompleterHelper.noSolutions;
    }
}
