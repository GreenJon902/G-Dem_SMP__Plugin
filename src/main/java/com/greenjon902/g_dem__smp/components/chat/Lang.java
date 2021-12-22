package com.greenjon902.g_dem__smp.components.chat;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public String get(String messageId) {
        try {
            return lang.get(messageId);
        } catch (Exception e) {
            e.printStackTrace();
            return "§4§l[ERROR]§r§c [Chat] An unexpected error occurred when trying to get chat message!\nmessageId=" + messageId + "\nwith={with}";
        }
    }

    public String format(String messageId, HashMap<String, String> with) {
        with.put("with", with.toString());

        String message = get(messageId);
        String messageBefore = "";

        while (!messageBefore.equals(message)) {
            messageBefore = message;
            message = _format(message, with);
        }

        return message;
    }

    private String _format(String message, HashMap<String, String> with) {
        Pattern pattern = Pattern.compile("\\{[a-zA-Z0-9$.]+}");
        Matcher matcher = pattern.matcher(message);

        String current, current_no_brackets;
        while (matcher.find()) {
            current = matcher.group(0);
            current_no_brackets = current.replace("{", "").replace("}", "");

            if (current_no_brackets.startsWith("$")) {
                message = message.replace(current, get(current_no_brackets.replace("$", "")));
            } else {
                if (with.containsKey(current_no_brackets)) {
                    message = message.replace(current, with.get(current_no_brackets));
                } else {
                    System.out.println("[Lang._Format]  Found \"" + current + "\" in message but nothing to format it with, ignoring!");
                }
            }
        }



        for (Map.Entry<String, String> entry : with.entrySet()) {
            message = message.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return message;
    }
}

class LangSection {
    private String standardOut;
    @SuppressWarnings("FieldMayBeFinal")
    private HashMap<String, LangSection> children = new HashMap<>();

    public static LangSection build(ConfigurationSection configurationSection) {
        LangSection langSection = new LangSection();
        langSection.standardOut = configurationSection.getCurrentPath();

        for (String component : configurationSection.getKeys(false)) {

            if (component.equals("otherForms")) {
                //noinspection ConstantConditions
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
