package br.alkazuz.antibot.bukkit.listener;

import java.util.HashMap;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class MotdPingListener implements Listener{

	public static HashMap<String, Long> delay = new HashMap<String, Long>();
	
	@EventHandler
	public void onmotd(ServerListPingEvent e){
		delay.put(e.getAddress().getHostAddress(), System.currentTimeMillis());
	}
	
}
