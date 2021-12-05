package com.greenjon902.g_dem__smp.sit;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.PluginComponent;
import com.greenjon902.g_dem__smp.sit.commands.CommandSit;
import com.greenjon902.g_dem__smp.sit.commands.CommandStandAll;
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
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Sit implements PluginComponent {
    @Override
    public void setup() {
        //noinspection ConstantConditions
        G_Dem__SMP.getInstance().getCommand("sit").setExecutor(new CommandSit());
        //noinspection ConstantConditions
        G_Dem__SMP.getInstance().getCommand("standAll").setExecutor(new CommandStandAll());
    }

    @Override
    public void enable() {

    }

    @Override
    public void end() {
        standAll();
    }



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

        chairs.put(player, chair);
        new BukkitRunnable(){
            @Override
            public void run() {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Mans found a chair init"));
            }
        }.runTaskLater(G_Dem__SMP.getInstance(), 2);
    }

    public void stand(Player player) {
        Logger logger = Bukkit.getLogger();
        logger.info("Standing " + player.toString());

        LivingEntity chair = chairs.get(player);

        chairs.remove(player);

        chair.removePassenger(player);
        chair.remove();
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Chairs r for weaklings"));
    }

    public String toggle(Player player) {
        if (isSitting(player)) {
            stand(player);
            return "standing";
        } else {
            sit(player);
            return "sitting";
        }
    }

    public int standAll() {
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
        return player_index;
    }
}
