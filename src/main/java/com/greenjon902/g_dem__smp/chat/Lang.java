package com.greenjon902.g_dem__smp.chat;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Lang {
    @SuppressWarnings("FieldCanBeLocal")
    private File langFile;
    @SuppressWarnings("FieldCanBeLocal")
    private YamlConfiguration langYamlConfiguration;
    @SuppressWarnings("FieldCanBeLocal")
    private LangSection lang;

    public void setup() {

        langFile = new File(G_Dem__SMP.getInstance().getDataFolder(), "lang.yml");
        langYamlConfiguration = new YamlConfiguration();
        try {
            if (!langFile.exists()) {
                //noinspection ResultOfMethodCallIgnored
                langFile.getParentFile().mkdirs();
                G_Dem__SMP.getInstance().saveResource("lang.yml", false);
            }
            langYamlConfiguration.load(langFile);
            lang = LangSection.build(langYamlConfiguration);

        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}

class LangSection {
    private String standardOut;
    private HashMap<String, LangSection> children = new HashMap<>();

    public static LangSection build(ConfigurationSection configurationSection) {
        LangSection langSection = new LangSection();
        langSection.standardOut = configurationSection.getCurrentPath();

        for (String component : configurationSection.getKeys(false)) {
            System.out.println(component);
            if (component.equals("otherForms")) {
                for (String component2 : configurationSection.getConfigurationSection(component).getKeys(false)) {
                    if (configurationSection.isConfigurationSection(component)) {
                        //noinspection ConstantConditions
                        langSection.children.put(component2, LangSection.build(configurationSection.getConfigurationSection(component).getConfigurationSection(component2)));

                    } else {
                        System.out.println("WAHT THE FICJK");
                    }
                }

            } else if (configurationSection.isConfigurationSection(component)) {
                //noinspection ConstantConditions
                langSection.children.put(component, LangSection.build(configurationSection.getConfigurationSection(component)));

            } else if (component.equals("out")) {
                langSection.standardOut = configurationSection.getString("out");
            } else {
                System.out.println("WAHT THE FICJK");
            }

        }
        return langSection;
    }

    public String get(String path) {
        if (path.contains(".")) {
            String[] splitPath = path.split("\\.", 2);
            return children.get(splitPath[0]).get(splitPath[1]);
        } else {
            return children.get(path).getOut();
        }
    }

    public String getOut() {
        return standardOut;
    }
}
