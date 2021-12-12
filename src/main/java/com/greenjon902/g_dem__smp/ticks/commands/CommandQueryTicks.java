package com.greenjon902.g_dem__smp.ticks.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.chat.ChatAPI;
import com.greenjon902.g_dem__smp.ticks.Ticks;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.HashMap;

public class CommandQueryTicks implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof ConsoleCommandSender) {
                ChatAPI.sendMessage("ticks.commands.queryTicks.errors.consoleHasNoTickAmount", "Ticks", sender);
                return true;
            }

            OfflinePlayer player = (OfflinePlayer) sender;

            ChatAPI.sendMessage("ticks.commands.queryTicks", new HashMap<String, String>() {{
                put("tickRecord", String.valueOf(((Ticks) G_Dem__SMP.getComponent("Ticks")).get(player)));
            }}, "Ticks", sender);

        } else if (args.length == 1) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);

            ChatAPI.sendMessage("ticks.commands.queryTicks.other", new HashMap<String, String>() {{
                put("userName", String.valueOf(((Ticks) G_Dem__SMP.getComponent("Ticks")).get(player)));
                put("tickRecord", sender.getName());
            }}, "Ticks", sender);

        } else {
            ChatAPI.sendMessage("ticks.commands.queryTicks.errors.tooManyArguments", "Ticks", sender);
        }

        return true;
    }
}
