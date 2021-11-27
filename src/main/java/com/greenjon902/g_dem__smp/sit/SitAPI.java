package com.greenjon902.g_dem__smp.sit;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SitAPI {
    private final Map<Player, LivingEntity> chairs = new HashMap<>();

    public boolean isSitting(Player player) {
        return chairs.containsKey(player);
    }

    public void sit(Player player) {
        Logger logger = Bukkit.getLogger();
        logger.info("Sitting " + player.toString());

        World world = player.getWorld();
        Block block = player.getLocation().getBlock();

        Bat chair = (Bat) world.spawnEntity(new Location(world, 0.0, 0.0, 0.0), EntityType.BAT);

        Location location;
        if (block.getBlockData() instanceof Stairs) {
            location = block.getLocation().add(0.5, 0.5-chair.getHeight(),0.5);

        } else if (block.getLocation().subtract(0, 1, 0).getBlock().getBlockData() instanceof Stairs) {
            location = block.getLocation().add(0.5,-0.5-chair.getHeight(),0.5);

        } else {
            location = block.getLocation().add(0.5, -chair.getHeight(),0.5);
        }


        chair.setAwake(true);
        chair.setAI(false);
        chair.setInvulnerable(true);
        chair.setCollidable(false);
        chair.setSilent(true);
        chair.setGravity(false);
        chair.teleport(location);
        chair.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,
                999999, 1, false, false ,false));

        chair.addPassenger(player);

        this.chairs.put(player, chair);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Mans found a chair init"));
    }

    public void stand(Player player) {
        Logger logger = Bukkit.getLogger();
        logger.info("Standing " + player.toString());

        LivingEntity chair = chairs.get(player);

        this.chairs.remove(player);

        chair.removePassenger(player);
        chair.remove();
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Chairs r for weaklings"));
    }

    public void toggle(Player player) {
        if (isSitting(player)) {
            stand(player);
        } else {
            sit(player);
        }
    }

    public void standAll() {
        Logger logger = Bukkit.getLogger();
        logger.info("Standing all");

        Player[] players = chairs.keySet().toArray(new Player[0]);
        int player_index;
        Player player;
        for (player_index = 0; player_index < players.length; player_index++) {
            player = players[player_index];
            stand(player);
        }

        logger.info("Finished standing all");
    }
}
