package com.greenjon902.g_dem__smp.components.chat.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.components.chat.Chat;
import com.greenjon902.g_dem__smp.components.chat.ChatAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandListBannedWords implements TabExecutor {
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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }
}
