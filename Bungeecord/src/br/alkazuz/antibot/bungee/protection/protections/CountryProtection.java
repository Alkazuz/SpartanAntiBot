package br.alkazuz.antibot.bungee.protection.protections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import br.alkazuz.antibot.bungee.config.Config;
import br.alkazuz.antibot.bungee.main.SpartanAntiBot;
import br.alkazuz.antibot.bungee.protection.Protection;

public class CountryProtection extends Protection{

	private static HashMap<String, Integer> connections = new HashMap<String, Integer>();
	private static HashSet<String> ipsblocked = new HashSet<String>();
	
	public CountryProtection() {
		super("BloqueioDeIP");
	}
	
	@Override
	public void run(String IP) {
		if(!connections.containsKey(IP)) {
			connections.put(IP, 0);
		}
		if(ipsblocked.contains(IP)) {
			setFail(true);
			return;
		}
		Integer index = connections.get(IP);
        connections.put(IP, ++index);
            SpartanAntiBot.thePlugin().getProxy().getScheduler().schedule(SpartanAntiBot.thePlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                	Integer index = connections.get(IP);
                	connections.put(IP,--index);
                }
            },5, TimeUnit.SECONDS);
        if (connections.get(IP) >= 4) {
            ipsblocked.add(IP);
            SpartanAntiBot.thePlugin().getProxy().getScheduler().schedule(SpartanAntiBot.thePlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    ipsblocked.remove(IP);
                }
            }, Config.getConfig().getInt("BloqueioDeIP.Tempo"), TimeUnit.SECONDS);
        }
        SpartanAntiBot.thePlugin().getProxy().getScheduler().schedule(SpartanAntiBot.thePlugin(), (Runnable)new Runnable() {
            @Override
            public void run() {
                Integer index = connections.get(IP);
                connections.put(IP, --index);
            }
        }, 20, TimeUnit.SECONDS);
		setFail(false);
	}

}
