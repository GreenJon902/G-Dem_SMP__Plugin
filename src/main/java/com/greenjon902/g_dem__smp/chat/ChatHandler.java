package com.greenjon902.g_dem__smp.chat;

import org.apache.commons.lang.StringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

public class ChatHandler implements Listener {
    ArrayList<String> bannedWords = new ArrayList<>();

    public void banWord(String word) {
        bannedWords.add(word);
    }


    public void unbanWord(String word) {
        bannedWords.remove(word);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        if (!event.getPlayer().hasPermission("G_Dem__SMP.chat.bypassbannedwords")) {
            for (String bannedWord : bannedWords) {
                if (!event.getPlayer().hasPermission("G_Dem__SMP.chat.bypassbannedwords." + bannedWord)) {
                    message = message.replace(bannedWord, StringUtils.repeat("*", bannedWord.length()));
                }
            }
        }

        event.setMessage(message);
    }

    public ArrayList<String> getBannedWords() {
        //noinspection unchecked
        return (ArrayList<String>) bannedWords.clone();
    }
}
