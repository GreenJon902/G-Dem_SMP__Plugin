package com.greenjon902.g_dem__smp.ticks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerSessionEvents implements Listener {
    @EventHandler
    public void onPlayerJon(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Ticks.takeRecord(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Ticks.takeRecord(player);
    }
}
