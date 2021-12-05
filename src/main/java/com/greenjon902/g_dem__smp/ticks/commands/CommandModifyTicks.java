package com.greenjon902.g_dem__smp.ticks.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.chat.ChatAPI;
import com.greenjon902.g_dem__smp.ticks.Ticks;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class CommandModifyTicks implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 3) {
            ChatAPI.sendMessage("ticks.commands.modifyTicks.errors.requiresThreeArguments", "Ticks", sender);

        } else {
            int before = ((Ticks) G_Dem__SMP.getComponent("Ticks")).get(Bukkit.getOfflinePlayer(args[0]));

            switch (args[1]) {
                case "set":
                    ((Ticks) G_Dem__SMP.getComponent("Ticks")).set(Bukkit.getOfflinePlayer(args[0]), new Integer(args[2]));

                    ChatAPI.sendMessage("ticks.commands.modifyTicks.set", new HashMap<String, String>() {{
                        put("userName", args[1]);
                        put("amount", args[2]);
                        put("before", String.valueOf(before));
                        put("after", String.valueOf(((Ticks) G_Dem__SMP.getComponent("Ticks")).get(Bukkit.getOfflinePlayer(args[0]))));
                    }}, "Ticks", sender);

                    break;
                case "add":
                    ((Ticks) G_Dem__SMP.getComponent("Ticks")).add(Bukkit.getOfflinePlayer(args[0]), new Integer(args[2]));

                    ChatAPI.sendMessage("ticks.commands.modifyTicks.add", new HashMap<String, String>() {{
                        put("userName", args[1]);
                        put("amount", args[2]);
                        put("before", String.valueOf(before));
                        put("after", String.valueOf(((Ticks) G_Dem__SMP.getComponent("Ticks")).get(Bukkit.getOfflinePlayer(args[0]))));
                    }}, "Ticks", sender);

                    break;
                case "subtract":
                    ((Ticks) G_Dem__SMP.getComponent("Ticks")).subtract(Bukkit.getOfflinePlayer(args[0]), new Integer(args[2]));

                    ChatAPI.sendMessage("ticks.commands.modifyTicks.subtract", new HashMap<String, String>() {{
                        put("userName", args[1]);
                        put("amount", args[2]);
                        put("before", String.valueOf(before));
                        put("after", String.valueOf(((Ticks) G_Dem__SMP.getComponent("Ticks")).get(Bukkit.getOfflinePlayer(args[0]))));
                    }}, "Ticks", sender);

                    break;
            }
        }
        return true;
    }
}
