package com.greenjon902.g_dem__smp.sit.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.chat.ChatAPI;
import com.greenjon902.g_dem__smp.sit.Sit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class CommandStandAll implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int amount = ((Sit) G_Dem__SMP.getComponent("Sit")).standAll();
        ChatAPI.sendMessage("sit.commands.standAll.allStood", new HashMap<String, String>() {{
            put("amountStood", String.valueOf(amount));
        }}, "Sit", sender);

        return true;
    }
}
