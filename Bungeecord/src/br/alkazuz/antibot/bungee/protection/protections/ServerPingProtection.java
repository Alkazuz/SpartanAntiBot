package br.alkazuz.antibot.bungee.protection.protections;

import java.util.HashSet;

import br.alkazuz.antibot.bungee.protection.Protection;

public class ServerPingProtection extends Protection{

	public static HashSet<String> ips = new HashSet<String>();
	
	public ServerPingProtection() {
		super("ServerPing");
	}
	
	@Override
	public void run(String IP) {
		if(!ips.contains(IP)) {
			setFail(true);
			return;
		}
		ips.remove(IP);
		setFail(false);
	}
	
}
