package com.greenjon902.g_dem__smp.ticks;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.PluginComponent;
import com.greenjon902.g_dem__smp.ticks.commands.CommandModifyTicks;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;


public class Ticks implements PluginComponent {
    private static File ticksFolder;
    private static final HashMap<UUID, BufferedWriter> writers = new HashMap<>();
    private static File tickOffsetsFile;
    private static YamlConfiguration tickOffsets;

    @Override
    public void setup(G_Dem__SMP mainClass) {
        ticksFolder = new File(G_Dem__SMP.getInstance().getDataFolder(), "/ticks/records");
        if (!ticksFolder.exists()) {
            //noinspection ResultOfMethodCallIgnored
            ticksFolder.mkdirs();
        }

        tickOffsetsFile = new File(ticksFolder, "tickOffsets.yml");
        tickOffsets = new YamlConfiguration();
        try {
            if (!tickOffsetsFile.exists()) {
                //noinspection ResultOfMethodCallIgnored
                tickOffsetsFile.getParentFile().mkdirs();
                //noinspection ResultOfMethodCallIgnored
                tickOffsetsFile.createNewFile();
            }
            tickOffsets.load(tickOffsetsFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        //noinspection ConstantConditions
        mainClass.getCommand("modifyTicks").setExecutor(new CommandModifyTicks());
    }

    @Override
    public void enable(G_Dem__SMP mainClass) {
        mainClass.getServer().getPluginManager().registerEvents(new PlayerSessionEvents(), mainClass);
    }

    @Override
    public void end(G_Dem__SMP mainClass) {
        Logger logger = G_Dem__SMP.getInstance().getLogger();

        for (UUID key : writers.keySet()) {
            try {
                logger.info("Closing BufferedWriter for " + key);
                writers.get(key).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            tickOffsets.save(tickOffsetsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void takeRecord(Player player) {
        try {
            if (!writers.containsKey(player.getUniqueId())) {
                G_Dem__SMP.getInstance().getLogger().info("Creating new BufferedWriter for user " + player.getName() + " who's uuid is " + player.getUniqueId());
                writers.put(player.getUniqueId(), new BufferedWriter(new FileWriter(new File(ticksFolder, player.getUniqueId() + ".txt"), true)));
            }
            BufferedWriter output = writers.get(player.getUniqueId());

            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd - hh:mm");
            String strDate = dateFormat.format(date);
            output.write(strDate);

            output.write(" = ");
            output.write(String.valueOf(player.getTicksLived() +
                    tickOffsets.getInt(String.valueOf(player.getUniqueId()), 0)));
            output.write("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void set(Player player, int amount) {
        tickOffsets.set(String.valueOf(player.getUniqueId()), amount - player.getTicksLived());
    }

    public static void add(Player player, int amount) {
        tickOffsets.set(String.valueOf(player.getUniqueId()), tickOffsets.getInt(String.valueOf(player.getUniqueId())) + amount);
    }

    public static void subtract(Player player, int amount) {
        add(player, -amount);
    }
}
