package br.alkazuz.antibot.bukkit.protection.protections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import br.alkazuz.antibot.bukkit.command.subcommands.NotificarSubCMD;
import br.alkazuz.antibot.bukkit.config.Config;
import br.alkazuz.antibot.bukkit.main.GlobalBlock;
import br.alkazuz.antibot.bukkit.main.SpartanAntiBot;

public class BlockIPProtection{

	private static HashMap<String, List<String>> connections = new HashMap<String, List<String>>();
	public static HashMap<String, Long> ipsblocked = new HashMap<String, Long>();
	private String Nick,IP;
	private boolean Fail = false;
	String name = "BloqueioDeIP";
	
	public BlockIPProtection(String name, String IP) {
		this.Nick = name;
		this.IP = IP;
	}
	
	public void run() {
		List<String> list = new ArrayList<String>();
		if(!connections.containsKey(IP)) {
			list.add(Nick);
			connections.put(IP, list);
		}
		if(ipsblocked.containsKey(IP) && System.currentTimeMillis() - ipsblocked.get(IP)
				<= (1000 * Config.getConfig().getInt("BloqueioDeIP.Tempo"))) {
			setFail(true);
			return;
		}
	    list = connections.get(IP);
		if(!list.contains(Nick)){
			list.add(Nick);
		} 
		connections.put(IP, list);
		int perIP = connections.get(IP).size();
		if(perIP >= 3){
			GlobalBlock.addIP(IP);
			setFail(true);
			for(String pd : NotificarSubCMD.notify) {
				Player p = Bukkit.getPlayer(pd);
        		if(p != null) {
        			p.sendMessage("§f"+IP+" §7foi bloqueado permanentemente do handler");
        		}
        	}
			return;
		}
		Bukkit.getScheduler().scheduleSyncDelayedTask(SpartanAntiBot.thePlugin(), new Runnable() {
			public void run() {
				List<String> list = connections.get(IP);
				if(list.contains(Nick)){
					list.remove(Nick);
				}
				connections.put(IP,list);
			}
		}, 20L * 60L);
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
