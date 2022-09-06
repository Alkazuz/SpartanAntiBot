package br.alkazuz.antibot.bukkit.protection.protections;

import java.util.HashSet;

import br.alkazuz.antibot.bukkit.config.Config;
import br.alkazuz.antibot.bukkit.config.ConfigManager;
import br.alkazuz.antibot.utils.maxmind.geoip.LookupService;

public class CountryProtection{

	public static boolean enabled = false;
	public static HashSet<String> list = new HashSet<String>();
	public static LookupService cl;
	
	public String Nick,IP;
	public boolean Fail = false;
	String name = "BloqueioDePaises";
	
	public CountryProtection(String name, String IP) {
		this.Nick = name;
		this.IP = IP;
	}
	
	
	public void run() {
		if(String.valueOf(ConfigManager.get("BloqueioDePaises.Tipo")).equalsIgnoreCase("WHITELIST")) {
			if(!list.contains(cl.getCountry(IP).getCode())) {
				setFail(true);
				return;
			}
		}
		if(String.valueOf(ConfigManager.get("BloqueioDePaises.Tipo")).equalsIgnoreCase("BLACKLIST")) {
			if(list.contains(cl.getCountry(IP).getCode())) {
				setFail(true);
				return;
			}
		}
	}
	
	public void setFail(boolean b) {
		this.Fail = b;
	}
	
	public boolean isFailed(){
		return Fail;
	}
	
    public String paraString() {
        final StringBuilder sb = new StringBuilder();
        for (final String str : Config.getConfig().getStringList(String.valueOf(this.name) + ".Kick")) {
            sb.append(str.replace("&", "§"));
            sb.append("\n");
        }
        return sb.toString();
    }
	
}