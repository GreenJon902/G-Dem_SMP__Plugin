package com.greenjon902.g_dem__smp.chat;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.PluginComponent;
import com.greenjon902.g_dem__smp.chat.commands.CommandBanWord;
import com.greenjon902.g_dem__smp.chat.commands.CommandUnbanWord;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Chat implements PluginComponent {
    private final ChatHandler chatHandler = new ChatHandler();

    @Override
    public void setup() {
        G_Dem__SMP.getInstance().getServer().getPluginManager().registerEvents(chatHandler, G_Dem__SMP.getInstance());
        //noinspection ConstantConditions
        G_Dem__SMP.getInstance().getCommand("banWord").setExecutor(new CommandBanWord());
        //noinspection ConstantConditions
        G_Dem__SMP.getInstance().getCommand("unbanWord").setExecutor(new CommandUnbanWord());
    }

    @Override
    public void enable() {

    }

    @Override
    public void end() {

    }

    public void sendMessage(String messageId, String componentId, CommandSender receiver) {
        // TODO: Load actual message and stuff
        receiver.sendMessage("[" + componentId + "]  " + messageId);
    }

    public void announceMessage(String messageId, String componentId) {
        // TODO: Load actual message and stuff
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            player.sendMessage("[" + componentId + "]  " + messageId);
        }
    }

    public void banWord(String word) {
        chatHandler.banWord(word);
    }

    public void unbanWord(String word) {
        chatHandler.unbanWord(word);
    }
}
