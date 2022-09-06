package br.alkazuz.antibot.bungee.protection;

import java.util.HashSet;

import br.alkazuz.antibot.bungee.config.Config;
import br.alkazuz.antibot.bungee.protection.protections.BlockIPProtection;
import br.alkazuz.antibot.bungee.protection.protections.CountryProtection;
import br.alkazuz.antibot.bungee.protection.protections.ProxyProtection;
import br.alkazuz.antibot.bungee.protection.protections.ServerPingProtection;

public class ProtectionManager {
	
	private static HashSet<Protection> protections = new HashSet<Protection>();
	private static HashSet<Protection> protections2 = new HashSet<Protection>();
	
	public ProtectionManager() {
		protections.clear();
		protections2.clear();
		protections2.add(new CountryProtection());
		protections2.add(new ProxyProtection());
		protections2.add(new ServerPingProtection());
		protections2.add(new BlockIPProtection());
		
		
		for(String pr : Config.getConfig().getStringList("Protecao")) {
			Protection protect = getBy(pr, protections2);
			if(protect != null) {
				protections.add(protect);
			}
		}
	}
	
	private static Protection getBy(String name, HashSet<Protection> list) {
		for(Protection p : list) {
			if(p.getName().equalsIgnoreCase(name)) {
				return p;
			}
		}
		return null;
	}
	
	public static Protection getBy(String name) {
		for(Protection p : All()) {
			if(p.getName().equalsIgnoreCase(name)) {
				return p;
			}
		}
		return null;
	}
	
	public static HashSet<Protection> All(){
		return protections;
	}
	
}
