package com.greenjon902.g_dem__smp.components.homes.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.components.chat.ChatAPI;
import com.greenjon902.g_dem__smp.components.homes.Homes;
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
import java.util.UUID;

public class CommandListHomes implements TabExecutor {
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
        if (sender.hasPermission("g_dem__smp.homes.listHome.other") || Bukkit.getOfflinePlayer(uniqueId).getName().equals(sender.getName())) {
            sender.sendMessage(((Homes) G_Dem__SMP.getComponent("Homes")).storage.getPlayerHomes(uniqueId).toString());

            //noinspection ConstantConditions
            if (Bukkit.getOfflinePlayer(uniqueId).getName().equals(sender.getName())) {
                ChatAPI.sendMessage("homes.commands.listHomes", new HashMap<String, String>() {{
                    put("list", ((Homes) G_Dem__SMP.getComponent("Homes")).storage.getPlayerHomes(uniqueId).toString());
                }}, "Homes", sender);

            } else {
                ChatAPI.sendMessage("homes.commands.listHomes.other", new HashMap<String, String>() {{
                    put("userName", Bukkit.getServer().getOfflinePlayer(uniqueId).getName());
                    put("list", ((Homes) G_Dem__SMP.getComponent("Homes")).storage.getPlayerHomes(uniqueId).toString());
                }}, "Homes", sender);
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            ArrayList<String> solutions = new ArrayList<>();

            if (sender.hasPermission("g_dem__smp.homes.listhomes.other")) {
                solutions.addAll(TabCompleterHelper.filterWithFunction(
                        ((Homes) G_Dem__SMP.getComponent("Homes")).storage.getAllPlayerHomes().keySet().toArray(),
                        (uuid) -> Bukkit.getOfflinePlayer((UUID) uuid).getName(),
                        args[0]
                ));
            }

            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!solutions.contains(player.getName())) {
                    solutions.add(player.getName());
                }
            }
            return solutions;
        }
        return TabCompleterHelper.noSolutions;
    }
}
