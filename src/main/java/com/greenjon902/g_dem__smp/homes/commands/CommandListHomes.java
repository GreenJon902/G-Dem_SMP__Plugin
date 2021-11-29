package com.greenjon902.g_dem__smp.homes.commands;

import com.greenjon902.g_dem__smp.homes.Homes;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandListHomes implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        UUID uniqueId;
        if (args.length == 0) {
            if (sender instanceof ConsoleCommandSender) {
                ConsoleCommandSender console = (ConsoleCommandSender) sender;
                console.sendMessage("Console has no homes!");
                return true;
            } else {
                Player player = (Player) sender;
                uniqueId = player.getUniqueId();
            }
        } else {
            uniqueId = Bukkit.getOfflinePlayer(args[0]).getUniqueId();
        }
        sender.sendMessage(Homes.storage.getPlayerHomes(uniqueId).toString());

        return true;
    }
}
