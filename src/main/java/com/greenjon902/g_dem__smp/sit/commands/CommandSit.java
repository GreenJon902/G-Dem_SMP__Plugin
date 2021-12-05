package com.greenjon902.g_dem__smp.sit.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.chat.ChatAPI;
import com.greenjon902.g_dem__smp.sit.Sit;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.logging.Logger;

public class CommandSit implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Logger logger = Bukkit.getLogger();

        if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender console = (ConsoleCommandSender) sender;

            ChatAPI.sendMessage("sit.commands.sit.errors.consoleCantSit", "Sit", console);
            return false;


        } else if (sender instanceof Player) {
            Player player = (Player) sender;

            if (Objects.equals(((Sit) G_Dem__SMP.getComponent("Sit")).toggle(player), "sitting")) {
                ChatAPI.sendMessage("sit.commands.sit.nowSitting", "Sit", player);
            } else {
                ChatAPI.sendMessage("sit.commands.sit.nowStanding", "Sit", player);
            }
        }

        return true;
    }
}
