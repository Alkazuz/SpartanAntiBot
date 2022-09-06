package br.alkazuz.antibot.bungee.config;

import java.util.HashMap;


public class MessagesManager {

	private static HashMap<String, Object> msg = new HashMap<String, Object>();
	
	public MessagesManager() {
		msg.clear();
		for(String keys : Messages.getConfig().getKeys()) {
			msg.put(keys, Messages.getConfig().get(keys));
		}
	}
	
	public static Object get(String key) {
		if(msg.containsKey(key)) {
			return msg.get(key);
		}
		return null;
	}
	
}
