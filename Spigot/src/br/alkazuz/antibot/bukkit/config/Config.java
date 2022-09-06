package br.alkazuz.antibot.bukkit.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import br.alkazuz.antibot.bukkit.main.SpartanAntiBot;
import br.alkazuz.antibot.bukkit.protection.protections.CountryProtection;
import br.alkazuz.antibot.utils.Utils;

public class Config
{
    private static FileConfiguration config;
    private static File configFile;
    
    public Config() {
        Config.configFile = new File(SpartanAntiBot.thePlugin().getDataFolder(), "config.yml");
        if (!Config.configFile.exists()) {
            SpartanAntiBot.thePlugin().saveResource("config.yml", false);
        }
        Config.config = (FileConfiguration)YamlConfiguration.loadConfiguration(Config.configFile);
        new ConfigManager();
        for(String protections : Config.getConfig().getStringList("Protecao")) {
			if(protections.equalsIgnoreCase("BloqueioDePaises")){
				CountryProtection.enabled = true;
			}
        }
        for(String code : Config.getConfig().getStringList("BloqueioDePaises.Codes")) {
        	CountryProtection.list.add(code.toUpperCase());
        }
        try {
            SpartanAntiBot.theInstance().sendConcoleMessage("Fazendo download da blacklist de proxies...");
            final URL website = new URL("https://www.advanced-bot.tk/bot/api/proxy.php?ip=getall");
            URLConnection connection = website.openConnection();
            connection = website.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] split;
                for (int length = (split = inputLine.split("<br>")).length, i = 0; i < length; ++i) {
                    final String proxy = split[i];
                    if (!Utils.proxies.contains(proxy)) {
                        Utils.proxies.add(proxy);
                    }
                }
            }
            in.close();
            SpartanAntiBot.theInstance().sendConcoleMessage("Foram carregados " + Utils.proxies.size() + " proxies");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static FileConfiguration getConfig() {
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
            Config.config.save(Config.configFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
