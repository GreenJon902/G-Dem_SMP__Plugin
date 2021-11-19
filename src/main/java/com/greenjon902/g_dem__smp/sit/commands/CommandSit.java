package com.greenjon902.g_dem__smp.sit.commands;

import com.greenjon902.g_dem__smp.sit.SitAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class CommandSit implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Logger logger = Bukkit.getLogger();

        if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender console = (ConsoleCommandSender) sender;

            console.sendMessage("Silly you, console can't sit!");
            return false;


        } else if (sender instanceof Player) {
            Player player = (Player) sender;

            SitAPI.getAPI().toggle(player);
        }

        return true;
    }
}
