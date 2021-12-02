package com.greenjon902.g_dem__smp.homes.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.homes.Homes;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandDelHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ((args.length == 0 || args.length == 1) && sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender console = (ConsoleCommandSender) sender;
            console.sendMessage("Console cannot have homes so what are you trying to delete");
        } else {
            Player player = (Player) sender;
            UUID user = player.getUniqueId();
            String home = "home";

            if (args.length == 1) {
                home = args[0];
            } else if (args.length == 2) {
                user = Bukkit.getOfflinePlayer(args[0]).getUniqueId();
                home = args[1];
            }
            //noinspection ConstantConditions
            if (sender.hasPermission("G_Dem__SMP.homes.delhome.other") || Bukkit.getOfflinePlayer(user).getName().equals(sender.getName())) {
                ((Homes) G_Dem__SMP.getComponent("Homes")).storage.deleteHome(user, home);
            }
        }

        return true;
    }
}
