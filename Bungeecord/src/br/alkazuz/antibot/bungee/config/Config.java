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

public class Config
{
    private static Configuration config;
    private static File configFile;
    
    public Config(Plugin pl) {
    	try {
    		if(!SpartanAntiBot.thePlugin().getDataFolder().exists()) {
    			SpartanAntiBot.thePlugin().getDataFolder().mkdirs();
    		}
        Config.configFile = new File(SpartanAntiBot.thePlugin().getDataFolder(), "config.yml");
        if (!Config.configFile.exists()) {
        	InputStream in = pl.getResourceAsStream("config.yml");
        	Files.copy(in, configFile.toPath());
        }
        
        Config.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        new ConfigManager();
    	}catch (Exception e) {}
    }
    
    public static Configuration getConfig() {
        return Config.config;
    }
    
    public static String getString(String ath) {
    	return String.valueOf(ConfigManager.get(ath)).replace("&", "§");
    }
    
    public static void saveConfig() {
        if (Config.config == null || Config.configFile == null) {
            return;
        }
        try {
        	ConfigurationProvider.getProvider(YamlConfiguration.class).save(config,Config.configFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
