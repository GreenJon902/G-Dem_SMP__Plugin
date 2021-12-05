package com.greenjon902.g_dem__smp.ticks.commands;

import com.greenjon902.g_dem__smp.chat.ChatAPI;
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
        int amount;
        OfflinePlayer player;

        if (args.length == 0) {
            ChatAPI.sendMessage("ticks.commands.queryTicks.ticks", new HashMap<String, String>() {{
                put("tickRecord", "1"); // TODO: Get actual tick amount
            }}, "Ticks", sender);

        } else if (args.length == 1) {
            if (sender instanceof ConsoleCommandSender) {
                ChatAPI.sendMessage("ticks.commands.queryTicks.errors.consoleHasNoTickAmount", "Ticks", sender);
                return true;
            }

            amount = Integer.parseInt(args[0]);
            player = (OfflinePlayer) sender;

            ChatAPI.sendMessage("ticks.commands.queryTicks.ticks.withAmount", new HashMap<String, String>() {{
                put("tickRecords", "123"); // TODO: Get actual tick amounts
            }}, "Ticks", sender);

        } else if (args.length == 2) {
            player = Bukkit.getOfflinePlayer(args[0]);
            amount = Integer.parseInt(args[1]);

            ChatAPI.sendMessage("ticks.commands.queryTicks.ticks.withAmount.other", new HashMap<String, String>() {{
                put("userName", player.getName());
                put("tickRecords", "123"); // TODO: Get actual tick amounts
            }}, "Ticks", sender);
        } else {
            ChatAPI.sendMessage("ticks.commands.queryTicks.errors.takesUpToTwoArguments", "Ticks", sender);
        }

        return true;
    }
}
