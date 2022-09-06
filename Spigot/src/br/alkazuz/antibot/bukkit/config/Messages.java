package br.alkazuz.antibot.bukkit.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import br.alkazuz.antibot.bukkit.main.SpartanAntiBot;

public class Messages
{
    private static FileConfiguration config;
    private static File configFile;
    
    public Messages() {
        Messages.configFile = new File(SpartanAntiBot.thePlugin().getDataFolder(), "mensagens.yml");
        if (!Messages.configFile.exists()) {
            SpartanAntiBot.thePlugin().saveResource("mensagens.yml", false);
        }
        Messages.config = (FileConfiguration)YamlConfiguration.loadConfiguration(Messages.configFile);
        new MessagesManager();
    }
    
    public static FileConfiguration getConfig() {
        return Messages.config;
    }
    
    public static String getString(String ath) {
    	return String.valueOf(MessagesManager.get(ath)).replace("&", "§");
    }
    
    public static void saveConfig() {
        if (Messages.config == null || Messages.configFile == null) {
            return;
        }
        try {
            Messages.config.save(Messages.configFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
