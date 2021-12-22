package com.greenjon902.g_dem__smp.homes.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.chat.ChatAPI;
import com.greenjon902.g_dem__smp.homes.Homes;
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

public class CommandDelHome implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ((args.length == 0 || args.length == 1) && sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender console = (ConsoleCommandSender) sender;
            ChatAPI.sendMessage("homes.commands.delHome.errors.consoleCantHaveHomes", "Homes", console);
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
            if (sender.hasPermission("g_dem__smp.homes.delhome.other") || Bukkit.getOfflinePlayer(user).getName().equals(sender.getName())) {
                ((Homes) G_Dem__SMP.getComponent("Homes")).storage.deleteHome(user, home);

                String finalHome = home;
                UUID finalUser = user;
                //noinspection ConstantConditions
                if (Bukkit.getOfflinePlayer(user).getName().equals(sender.getName())) {
                    ChatAPI.sendMessage("homes.commands.delHome.deletedHome", new HashMap<String, String>() {{
                        put("homeName", finalHome);
                    }}, "Homes", player);
                } else {
                    ChatAPI.sendMessage("homes.commands.delHome.deletedHome.other", new HashMap<String, String>() {{
                        //noinspection ConstantConditions
                        put("userName", Bukkit.getServer().getPlayer(finalUser).getName());
                        put("homeName", finalHome);
                    }}, "Homes", player);
                }
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            ArrayList<String> solutions = new ArrayList<>();

            if (sender.hasPermission("G_Dem__SMP.homes.delhome.other")) {
                solutions.addAll(TabCompleterHelper.filterWithFunction(
                        ((Homes) G_Dem__SMP.getComponent("Homes")).storage.getAllPlayerHomes().keySet().toArray(),
                        (uuid) -> Bukkit.getOfflinePlayer((UUID) uuid).getName(),
                        args[0]
                ));
            }

            if (sender instanceof Player) {
                Player player = (Player) sender;

                solutions.addAll(TabCompleterHelper.filter(((Homes) G_Dem__SMP.getComponent("Homes")).storage.getPlayerHomes(player.getUniqueId()), args[0]));
            }
            return solutions;

        } else if (args.length == 2) {
            if (sender.hasPermission("G_Dem__SMP.homes.delhome.other")) {
                return TabCompleterHelper.filter(((Homes) G_Dem__SMP.getComponent("Homes")).storage.getPlayerHomes(Bukkit.getOfflinePlayer(args[0]).getUniqueId()), args[1]);
            }
        }
        return TabCompleterHelper.noSolutions;
    }
}
