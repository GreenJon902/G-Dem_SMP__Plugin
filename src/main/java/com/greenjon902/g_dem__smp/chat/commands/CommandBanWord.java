package com.greenjon902.g_dem__smp.chat.commands;

import com.greenjon902.g_dem__smp.chat.ChatAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandBanWord implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ChatAPI.sendMessage("Banning word(s) \"" + String.join(" ", args) + "\"", "Chat", sender);
        return true;
    }
}
