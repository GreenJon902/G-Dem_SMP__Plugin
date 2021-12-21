package com.greenjon902.g_dem__smp.tpa.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.chat.ChatAPI;
import com.greenjon902.g_dem__smp.tpa.NoTpaRequestException;
import com.greenjon902.g_dem__smp.tpa.Tpa;
import com.greenjon902.tabCompleterHelper.TabCompleterHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CommandTpaDeny implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            ChatAPI.sendMessage("tpa.commands.tpaDeny.errors.consoleCannotDenyTpaRequests", "Tpa", sender);
        } else if (!(args.length < 2)) {
            ChatAPI.sendMessage("tpa.commands.tpaDeny.errors.acceptsMaximumOneArgument", "Tpa", sender);

        } else if (args.length == 1) {
            Player playerSender = (Player) sender;
            Player supposedTpaRequestSender = Bukkit.getPlayerExact(args[0]);

            if (supposedTpaRequestSender == null) {
                ChatAPI.sendMessage("tpa.commands.tpaDeny.errors.playerIsNotOnline", new HashMap<String, String>() {{
                    put("recipient", args[0]);
                    put("sender", playerSender.getName());
                }}, "Tpa", sender);
                return true;
            }

            try {
                ((Tpa) G_Dem__SMP.getComponent("Tpa")).tpaDeny(playerSender, supposedTpaRequestSender);
                ChatAPI.sendMessage("tpa.commands.tpaDeny", new HashMap<String, String>() {{
                    put("sender", supposedTpaRequestSender.getName());
                    put("recipient", playerSender.getName());
                }}, "Tpa", playerSender);
            } catch (NoTpaRequestException e) {
                ChatAPI.sendMessage("tpa.commands.tpaDeny.errors.noTpaRequest.withName", new HashMap<String, String>() {{
                    put("sender", supposedTpaRequestSender.getName());
                    put("recipient", playerSender.getName());
                }}, "Tpa", playerSender);
            }


        } else {
            Player playerSender = (Player) sender;
            Player supposedTpaRequestSender = ((Tpa) G_Dem__SMP.getComponent("Tpa")).getLastPlayerWhoSentATpaRequestWhereRecipientIs(playerSender);

            if (supposedTpaRequestSender == null) {
                ChatAPI.sendMessage("tpa.commands.tpaDeny.errors.noTpaRequest", new HashMap<String, String>() {{
                    put("sender", playerSender.getName());
                }}, "Tpa", playerSender);
                return true;
            }

            try {
                ((Tpa) G_Dem__SMP.getComponent("Tpa")).tpaDeny(playerSender, supposedTpaRequestSender);
                ChatAPI.sendMessage("tpa.commands.tpaDeny", new HashMap<String, String>() {{
                    put("sender", supposedTpaRequestSender.getName());
                    put("recipient", playerSender.getName());
                }}, "Tpa", playerSender);
            } catch (NoTpaRequestException e) { // Should be impossible but just to please java / intelliJ
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            Player playerSender = (Player) sender;

            if (args.length == 1) {
                return TabCompleterHelper.filterWithFunction(
                        new List[]{((Tpa) G_Dem__SMP.getComponent("Tpa")).getPlayersWhoSentATpaRequestWhereRecipientIs(playerSender)},
                        (uuid) -> Bukkit.getOfflinePlayer((UUID) uuid).getName(),
                        args[0]);
            }
        }
        return TabCompleterHelper.noSolutions;
    }
}
