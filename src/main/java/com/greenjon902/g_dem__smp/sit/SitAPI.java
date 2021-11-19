package com.greenjon902.g_dem__smp.sit;

import org.bukkit.Bukkit;
import org.bukkit.World;
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
    protected static SitAPI INSTANCE = new SitAPI();

    protected SitAPI() {
        INSTANCE = this;
    }

    public static SitAPI getAPI() {
        return INSTANCE;
    }



    private final Map<Player, LivingEntity> chairs = new HashMap<>();

    public boolean isSitting(Player player) {
        return chairs.containsKey(player);
    }

    public void sit(Player player) {
        Logger logger = Bukkit.getLogger();
        logger.info("Sitting " + player.toString());

        World world = player.getWorld();

        Bat chair = (Bat) world.spawnEntity(player.getLocation().add(0.5,-0.5,0.5), EntityType.BAT);
        chair.setAwake(true);
        chair.setAI(false);
        chair.setInvulnerable(true);
        chair.setCollidable(false);
        chair.setSilent(true);
        chair.setGravity(false);
        chair.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,
                999999, 1, false, false ,false));

        chair.addPassenger(player);
        chair.setRotation(player.getLocation().getYaw(), player.getLocation().getPitch());

        this.chairs.put(player, chair);
    }

    public void stand(Player player) {
        Logger logger = Bukkit.getLogger();
        logger.info("Standing " + player.toString());

        LivingEntity chair = chairs.get(player);

        this.chairs.remove(player);

        chair.removePassenger(player);

        chair.remove();
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
