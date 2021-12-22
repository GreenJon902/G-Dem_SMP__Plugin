package com.greenjon902.g_dem__smp.chat.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.chat.Chat;
import com.greenjon902.g_dem__smp.chat.ChatAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class CommandBanWord implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ChatAPI.sendMessage("Banning word \"" + String.join(" ", args) + "\"!", "Chat", sender);
        ((Chat) G_Dem__SMP.getComponent("Chat")).banWord(String.join(" ", args));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }
}
