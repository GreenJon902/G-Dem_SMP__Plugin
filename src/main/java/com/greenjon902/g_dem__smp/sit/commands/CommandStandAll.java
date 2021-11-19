package com.greenjon902.g_dem__smp.sit.commands;

import com.greenjon902.g_dem__smp.sit.SitAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.logging.Logger;

public class CommandStandAll implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        SitAPI.getAPI().standAll();

        return true;
    }
}
