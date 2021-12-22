package com.greenjon902.g_dem__smp.homes.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.chat.ChatAPI;
import com.greenjon902.g_dem__smp.homes.Homes;
import com.greenjon902.tabCompleterHelper.TabCompleterHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CommandListAllHomes implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        HashMap<UUID, List<String>> uniqueIdVersion = ((Homes) G_Dem__SMP.getComponent("Homes")).storage.getAllPlayerHomes();
        HashMap<String, List<String>> nameVersion = new HashMap<>();

        for (UUID uniqueId : uniqueIdVersion.keySet()) {
            nameVersion.put(Bukkit.getOfflinePlayer(uniqueId).getName(), uniqueIdVersion.get(uniqueId));
        }

        ChatAPI.sendMessage("homes.commands.listAllHomes.list", new HashMap<String, String>() {{
            put("list", nameVersion.toString());
        }}, "Homes", sender);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return TabCompleterHelper.noSolutions;
    }
}
