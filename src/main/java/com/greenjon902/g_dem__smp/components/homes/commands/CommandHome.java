package com.greenjon902.g_dem__smp.components.homes.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.components.chat.ChatAPI;
import com.greenjon902.g_dem__smp.components.homes.Homes;
import com.greenjon902.tabCompleterHelper.TabCompleterHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CommandHome implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender console = (ConsoleCommandSender) sender;
            ChatAPI.sendMessage("homes.commands.home.errors.consoleCannotTeleportToHomes", "Homes", console);
        } else {
            Player player = (Player) sender;

            String name = "home";
            UUID uniqueId = player.getUniqueId();

            if (args.length > 2) {
                sender.sendMessage("/home cannot have more than two argument");
                return true;

            } else if (args.length == 2) {
                uniqueId = Bukkit.getOfflinePlayer(args[0]).getUniqueId();
                name = args[1];

            } else if (args.length == 1) {
                name = args[0];
            }

            String finalName = name;
            UUID finalUniqueId = uniqueId;
            //noinspection ConstantConditions
            if (sender.hasPermission("G_Dem__SMP.homes.home.other") || Bukkit.getOfflinePlayer(uniqueId).getName().equals(sender.getName())) {
                Location homeLocation = ((Homes) G_Dem__SMP.getComponent("Homes")).storage.getHomeLocation(name, uniqueId);
                player.teleport(homeLocation);

                //noinspection ConstantConditions
                if (Bukkit.getOfflinePlayer(uniqueId).getName().equals(sender.getName())) {
                    if (name.equals("home")) {
                        ChatAPI.sendMessage("homes.commands.home", new HashMap<String, String>() {{
                            //noinspection ConstantConditions
                            put("world", homeLocation.getWorld().getName());
                            put("x", String.valueOf(homeLocation.getBlock().getX()));
                            put("y", String.valueOf(homeLocation.getBlock().getY()));
                            put("z", String.valueOf(homeLocation.getBlock().getZ()));
                        }}, "Homes", player);
                    } else {
                        ChatAPI.sendMessage("homes.commands.home.withName", new HashMap<String, String>() {{
                            put("homeName", finalName);
                            //noinspection ConstantConditions
                            put("world", homeLocation.getWorld().getName());
                            put("x", String.valueOf(homeLocation.getBlock().getX()));
                            put("y", String.valueOf(homeLocation.getBlock().getY()));
                            put("z", String.valueOf(homeLocation.getBlock().getZ()));
                        }}, "Homes", player);
                    }

                } else {
                    ChatAPI.sendMessage("homes.commands.home.other.withName", new HashMap<String, String>() {{
                        put("userName", Bukkit.getServer().getOfflinePlayer(finalUniqueId).getName());
                        put("homeName", finalName);
                        //noinspection ConstantConditions
                        put("world", homeLocation.getWorld().getName());
                        put("x", String.valueOf(homeLocation.getBlock().getX()));
                        put("y", String.valueOf(homeLocation.getBlock().getY()));
                        put("z", String.valueOf(homeLocation.getBlock().getZ()));
                    }}, "Homes", player);
                }
            } else {
                ChatAPI.sendMessage("homes.commands.home.errors.noTeleportToOtherPermission", new HashMap<String, String>() {{
                    put("userName", Bukkit.getServer().getOfflinePlayer(finalUniqueId).getName());
                    put("homeName", finalName);
                }}, "Homes", player);
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            ArrayList<String> solutions = new ArrayList<>();

            if (sender.hasPermission("g_dem__smp.homes.home.other")) {
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
            if (sender.hasPermission("g_dem__smp.homes.home.other")) {
                return TabCompleterHelper.filter(((Homes) G_Dem__SMP.getComponent("Homes")).storage.getPlayerHomes(Bukkit.getOfflinePlayer(args[0]).getUniqueId()), args[1]);
            }
        }
        return TabCompleterHelper.noSolutions;
    }
}
