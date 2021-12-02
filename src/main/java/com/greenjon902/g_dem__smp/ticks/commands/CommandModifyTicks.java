package com.greenjon902.g_dem__smp.ticks.commands;

import com.greenjon902.g_dem__smp.ticks.Ticks;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandModifyTicks implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 3) {
            sender.sendMessage("/modifyTicks requires 3 arguments");

        } else {
            switch (args[1]) {
                case "set":
                    Ticks.set((Player) Bukkit.getOfflinePlayer(args[0]), new Integer(args[2]));

                    break;
                case "add":
                    Ticks.add((Player) Bukkit.getOfflinePlayer(args[0]), new Integer(args[2]));

                    break;
                case "subtract":
                    Ticks.subtract((Player) Bukkit.getOfflinePlayer(args[0]), new Integer(args[2]));

                    break;
            }
        }
        return true;
    }
}
