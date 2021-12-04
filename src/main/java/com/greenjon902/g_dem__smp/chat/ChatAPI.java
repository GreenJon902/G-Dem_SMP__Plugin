package com.greenjon902.g_dem__smp.chat;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import org.bukkit.entity.Player;

public class ChatAPI {
    public static void sendMessage(String messageId, String componentId, Player player) {
        ((Chat) G_Dem__SMP.getComponent("Chat")).sendMessage(messageId, componentId, player);
    }

    public static void announceMessage(String messageId, String componentId) {
        ((Chat) G_Dem__SMP.getComponent("Chat")).announceMessage(messageId, componentId);
    }
}
