package com.greenjon902.g_dem__smp.chat.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.chat.Chat;
import com.greenjon902.g_dem__smp.chat.ChatAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class CommandListBannedWords implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 0) {
            ChatAPI.sendMessage("chat.commands.listBannedWords.errors.takesNoArguments", "Chat", sender);
        } else {
            ChatAPI.sendMessage("chat.commands.listBannedWords.output",
                    new HashMap<String, String>() {{
                        put("BannedWords", String.join(", ", ((Chat) G_Dem__SMP.getComponent("Chat")).getBannedWords()));
                    }}, "Chat", sender);
        }
        return true;
    }
}
