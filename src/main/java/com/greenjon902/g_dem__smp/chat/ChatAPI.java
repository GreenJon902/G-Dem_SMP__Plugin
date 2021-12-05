package com.greenjon902.g_dem__smp.chat;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class ChatAPI {
    public static void sendMessage(String messageId, String componentId, CommandSender receiver) {
        ((Chat) G_Dem__SMP.getComponent("Chat")).sendMessage(messageId, componentId, receiver);
    }

    public static void announceMessage(String messageId, String componentId) {
        ((Chat) G_Dem__SMP.getComponent("Chat")).announceMessage(messageId, componentId);
    }

    public static void sendMessage(String messageId, HashMap<String, String> formatItems, String componentId, CommandSender receiver) {
        ((Chat) G_Dem__SMP.getComponent("Chat")).sendMessage(messageId, formatItems, componentId, receiver);
    }
}
