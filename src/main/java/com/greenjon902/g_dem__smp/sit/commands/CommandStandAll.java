package com.greenjon902.g_dem__smp.sit.commands;

import com.greenjon902.g_dem__smp.sit.Sit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandStandAll implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Sit.API.standAll();

        return true;
    }
}
