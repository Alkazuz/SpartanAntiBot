package br.alkazuz.antibot.bukkit.config;

import java.util.HashMap;

public class MessagesManager {

	private static HashMap<String, Object> msg = new HashMap<String, Object>();
	
	public MessagesManager() {
		msg.clear();
		for(String keys : Messages.getConfig().getKeys(true)) {
			msg.put(keys, Messages.getConfig().get(keys).toString());
		}
	}
	
	public static Object get(String key) {
		return msg.get(key);
	}
	
	public static Boolean getBoolean(String key) {
		return (Boolean) msg.get(key);
	}
	
	public static String getString(String key) {
		return ((String) msg.get(key)).replace("&", "§");
	}
}
