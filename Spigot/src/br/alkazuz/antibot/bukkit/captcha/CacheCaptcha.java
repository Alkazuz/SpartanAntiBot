package br.alkazuz.antibot.bukkit.captcha;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import br.alkazuz.antibot.bukkit.main.SpartanAntiBot;

public class CacheCaptcha
{
    private static FileConfiguration config;
    private static File configFile;
    
    public CacheCaptcha() {
        CacheCaptcha.configFile = new File(SpartanAntiBot.thePlugin().getDataFolder(), "headcache.yml");
        if (!CacheCaptcha.configFile.exists()) {
            SpartanAntiBot.thePlugin().saveResource("headcache.yml", false);
        }
        CacheCaptcha.config = (FileConfiguration)YamlConfiguration.loadConfiguration(CacheCaptcha.configFile);
        new CacheCaptchaManager();
    }
    
    public static FileConfiguration getConfig() {
        return CacheCaptcha.config;
    }
    
    public static String getString(String ath) {
    	return String.valueOf(CacheCaptchaManager.get(ath)).replace("&", "§");
    }
    
    public static void saveConfig() {
        if (CacheCaptcha.config == null || CacheCaptcha.configFile == null) {
            return;
        }
        try {
            CacheCaptcha.config.save(CacheCaptcha.configFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
