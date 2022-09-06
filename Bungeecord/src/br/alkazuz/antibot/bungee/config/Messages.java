package br.alkazuz.antibot.bungee.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import br.alkazuz.antibot.bungee.main.SpartanAntiBot;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Messages
{
    private static Configuration config;
    private static File configFile;
    
    public Messages(Plugin pl) {
    	try {
        Messages.configFile = new File(SpartanAntiBot.thePlugin().getDataFolder(), "mensagens.yml");
        if (!Messages.configFile.exists()) {
        	InputStream in = pl.getResourceAsStream("mensagens.yml");
        	Files.copy(in, configFile.toPath());
        }
        
        Messages.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        new MessagesManager();
    	}catch (Exception e) {}
    }
    
    public static Configuration getConfig() {
        return Messages.config;
    }
    
    public static String getString(String ath) {
    	return String.valueOf(ConfigManager.get(ath)).replace("&", "§");
    }
    
    public static void saveConfig() {
        if (Messages.config == null || Messages.configFile == null) {
            return;
        }
        try {
        	ConfigurationProvider.getProvider(YamlConfiguration.class).save(config,Messages.configFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
