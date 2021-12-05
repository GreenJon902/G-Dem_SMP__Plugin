package com.greenjon902.g_dem__smp.homes.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.chat.ChatAPI;
import com.greenjon902.g_dem__smp.homes.Homes;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CommandListHomes implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        UUID uniqueId;
        if (args.length == 0) {
            if (sender instanceof ConsoleCommandSender) {
                ConsoleCommandSender console = (ConsoleCommandSender) sender;
                ChatAPI.sendMessage("G_Dem__SMP.homes.listHome.errors.consoleCantHaveHomes", "Homes", console);
                return true;
            } else {
                Player player = (Player) sender;
                uniqueId = player.getUniqueId();
            }
        } else {
            uniqueId = Bukkit.getOfflinePlayer(args[0]).getUniqueId();
        }
        //noinspection ConstantConditions
        if (sender.hasPermission("G_Dem__SMP.homes.listHome.other") || Bukkit.getOfflinePlayer(uniqueId).getName().equals(sender.getName())) {
            sender.sendMessage(((Homes) G_Dem__SMP.getComponent("Homes")).storage.getPlayerHomes(uniqueId).toString());

            //noinspection ConstantConditions
            if (Bukkit.getOfflinePlayer(uniqueId).getName().equals(sender.getName())) {
                ChatAPI.sendMessage("homes.commands.listHomes.list", new HashMap<String, String>() {{
                    put("list", ((Homes) G_Dem__SMP.getComponent("Homes")).storage.getPlayerHomes(uniqueId).toString());
                }}, "Homes", sender);

            } else {
                ChatAPI.sendMessage("homes.commands.listHomes.list.other", new HashMap<String, String>() {{
                    put("user", Bukkit.getServer().getOfflinePlayer(uniqueId).getName());
                    put("list", ((Homes) G_Dem__SMP.getComponent("Homes")).storage.getPlayerHomes(uniqueId).toString());
                }}, "Homes", sender);
            }
        }

        return true;
    }
}
