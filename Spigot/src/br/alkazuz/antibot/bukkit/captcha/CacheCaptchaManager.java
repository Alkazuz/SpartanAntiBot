package br.alkazuz.antibot.bukkit.captcha;

import java.util.HashMap;


public class CacheCaptchaManager {

	private static HashMap<String, Object> msg = new HashMap<String, Object>();
	
	public CacheCaptchaManager() {
		msg.clear();
		for(String keys : CacheCaptcha.getConfig().getKeys(true)) {
			msg.put(keys, CacheCaptcha.getConfig().get(keys));
		}
	}
	
	public static Object get(String key) {
		if(msg.containsKey(key)) {
			return msg.get(key);
		}
		return null;
	}
	
}
