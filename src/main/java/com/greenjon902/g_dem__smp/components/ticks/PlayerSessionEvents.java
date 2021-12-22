package com.greenjon902.g_dem__smp.components.ticks;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerSessionEvents implements Listener {
    @EventHandler
    public void onPlayerJon(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ((Ticks) G_Dem__SMP.getComponent("Ticks")).takeRecord(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        ((Ticks) G_Dem__SMP.getComponent("Ticks")).takeRecord(player);
    }
}
