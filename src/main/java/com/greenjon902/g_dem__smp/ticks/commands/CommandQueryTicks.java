package com.greenjon902.g_dem__smp.ticks.commands;

import com.greenjon902.g_dem__smp.chat.ChatAPI;
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

            ChatAPI.sendMessage("ticks.commands.queryTicks.other", new HashMap<String, String>() {{
                put("tickRecords", "123"); // TODO: Get actual tick amounts
            }}, "Ticks", sender);

        } else {
            ChatAPI.sendMessage("ticks.commands.queryTicks.errors.tooManyArguments", "Ticks", sender);
        }

        return true;
    }
}
