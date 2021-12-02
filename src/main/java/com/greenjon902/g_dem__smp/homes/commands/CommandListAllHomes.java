package com.greenjon902.g_dem__smp.homes.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.homes.Homes;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class CommandListAllHomes implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        HashMap<UUID, Set<String>> uniqueIdVersion = ((Homes) G_Dem__SMP.getComponent("Homes")).storage.getAllPlayerHomes();
        HashMap<String, Set<String>> nameVersion = new HashMap<>();

        for (UUID uniqueId : uniqueIdVersion.keySet()) {
            nameVersion.put(Bukkit.getOfflinePlayer(uniqueId).getName(), uniqueIdVersion.get(uniqueId));
        }

        sender.sendMessage(nameVersion.toString());

        return true;
    }
}
