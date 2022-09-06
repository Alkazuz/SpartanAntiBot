package br.alkazuz.antibot.bungee.protection.protections;

import java.io.File;
import java.util.HashSet;

import br.alkazuz.antibot.bungee.config.Config;
import br.alkazuz.antibot.bungee.config.ConfigManager;
import br.alkazuz.antibot.bungee.main.SpartanAntiBot;
import br.alkazuz.antibot.bungee.protection.Protection;
import br.alkazuz.antibot.utils.Utils;
import br.alkazuz.antibot.utils.maxmind.geoip.LookupService;

public class BlockIPProtection extends Protection{

	private static HashSet<String> list = new HashSet<String>();
	public static LookupService cl;
	
	
	public BlockIPProtection() {
		super("BloqueioDePaises");
		
		try {
		String dbfile = SpartanAntiBot.thePlugin().getDataFolder().getAbsolutePath()+File.separator+"GeoIP.dat";
		
		cl = new LookupService(dbfile,LookupService.GEOIP_MEMORY_CACHE);
		
		list.clear();
		
		for(String str : Config.getConfig().getStringList("BloqueioDePaises.Codes")) {
			list.add(str);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(String IP) {
		if(String.valueOf(ConfigManager.get("BloqueioDePaises.Tipo")).equalsIgnoreCase("WHITELIST")) {
			if(!list.contains(cl.getCountry(IP).getCode())) {
				if(Boolean.valueOf((boolean) ConfigManager.get("BloqueioDePaises.LimiteDeLogin.Ativado"))){
					if(Integer.valueOf((String) ConfigManager.get("BloqueioDePaises.LimiteDeLogin.Logins"))>= Utils.botpsec) {
						setFail(true);
						return;
					}else {
						setFail(false);
						return;
					}
				}
				setFail(true);
				return;
			}
		}
		if(String.valueOf(ConfigManager.get("BloqueioDePaises.Tipo")).equalsIgnoreCase("BLACKLIST")) {
			if(list.contains(cl.getCountry(IP).getCode())) {
				if(Boolean.valueOf((boolean) ConfigManager.get("BloqueioDePaises.LimiteDeLogin.Ativado"))){
					if(Integer.valueOf((String) ConfigManager.get("BloqueioDePaises.LimiteDeLogin.Logins"))>= Utils.botpsec) {
						setFail(true);
						return;
					}else {
						setFail(false);
						return;
					}
				}
				setFail(true);
				return;
			}
		}
		setFail(false);
	}

}
