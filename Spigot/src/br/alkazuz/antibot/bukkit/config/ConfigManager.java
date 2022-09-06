package br.alkazuz.antibot.bukkit.config;

import java.util.HashMap;


public class ConfigManager {

	private static HashMap<String, Object> msg = new HashMap<String, Object>();
	
	public ConfigManager() {
		msg.clear();
		for(String keys : Config.getConfig().getKeys(true)) {
			msg.put(keys, Config.getConfig().get(keys));
		}
	}
	
	public static Object get(String key) {
		if(msg.containsKey(key)) {
			return msg.get(key);
		}
		return null;
	}
	
}
