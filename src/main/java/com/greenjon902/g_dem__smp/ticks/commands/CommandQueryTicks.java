package com.greenjon902.g_dem__smp.ticks.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.chat.ChatAPI;
import com.greenjon902.g_dem__smp.ticks.Ticks;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CommandQueryTicks implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof ConsoleCommandSender) {
                ChatAPI.sendMessage("ticks.commands.queryTicks.errors.consoleHasNoTickAmount", "Ticks", sender);
                return true;
            }

            OfflinePlayer player = (OfflinePlayer) sender;

            ChatAPI.sendMessage("ticks.commands.queryTicks", new HashMap<String, String>() {{
                put("tickAmount", String.valueOf(((Ticks) G_Dem__SMP.getComponent("Ticks")).get(player)));
            }}, "Ticks", sender);

        } else if (args.length == 1) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);

            ChatAPI.sendMessage("ticks.commands.queryTicks.other", new HashMap<String, String>() {{
                put("tickAmount", String.valueOf(((Ticks) G_Dem__SMP.getComponent("Ticks")).get(player)));
                put("userName", player.getName());
            }}, "Ticks", sender);

        } else {
            ChatAPI.sendMessage("ticks.commands.queryTicks.errors.tooManyArguments", "Ticks", sender);
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            ArrayList<String> players = new ArrayList<>();
            for (UUID uniqueId : ((Ticks) G_Dem__SMP.getComponent("Ticks")).getAllPlayersWithTickRecords()) {
                players.add(Bukkit.getOfflinePlayer(uniqueId).getName());
            }
            System.out.println(players);
            return players;
        }
        return new ArrayList<>();
    }
}
