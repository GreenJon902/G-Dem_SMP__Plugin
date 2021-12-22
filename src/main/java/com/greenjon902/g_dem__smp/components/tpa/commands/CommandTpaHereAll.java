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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandTpaHereAll implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            ChatAPI.sendMessage("tpa.commands.tpaHereAll.errors.consoleCannotSendTpaHereAllRequests", "Tpa", sender);

        } else {
            Player playerSender = (Player) sender;

            ArrayList<Player> players = new ArrayList<>();

            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                if (player.getUniqueId().equals(playerSender.getUniqueId())) {
                    players.add(player);
                    ((Tpa) G_Dem__SMP.getComponent("Tpa")).sendTpaRequest((Player) sender, player);
                }
            }

            ChatAPI.sendMessage("tpa.commands.tpaHereAll", new HashMap<String, String>() {{
                put("sender", playerSender.getName());
                put("recipients", players.toString());
            }}, "Tpa", playerSender);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return TabCompleterHelper.noSolutions;
    }
}
