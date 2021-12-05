package com.greenjon902.g_dem__smp.ticks.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.chat.ChatAPI;
import com.greenjon902.g_dem__smp.ticks.Ticks;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class CommandModifyTicks implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 3) {
            ChatAPI.sendMessage("ticks.commands.modifyTicks.errors.requiresThreeArguments", "Ticks", sender);

        } else {
            int before = ((Ticks) G_Dem__SMP.getComponent("Ticks")).get(Bukkit.getOfflinePlayer(args[0]));
            System.out.println(before);
            switch (args[1]) {
                case "set":
                    System.out.println(Arrays.toString(args));
                    ((Ticks) G_Dem__SMP.getComponent("Ticks")).get(Bukkit.getOfflinePlayer(args[0]));
                    ((Ticks) G_Dem__SMP.getComponent("Ticks")).set(Bukkit.getOfflinePlayer(args[0]), new Integer(args[2]));

                    break;
                case "add":
                    ((Ticks) G_Dem__SMP.getComponent("Ticks")).add(Bukkit.getOfflinePlayer(args[0]), new Integer(args[2]));

                    break;
                case "subtract":
                    ((Ticks) G_Dem__SMP.getComponent("Ticks")).subtract(Bukkit.getOfflinePlayer(args[0]), new Integer(args[2]));

                    break;
            }
        }
        return true;
    }
}
