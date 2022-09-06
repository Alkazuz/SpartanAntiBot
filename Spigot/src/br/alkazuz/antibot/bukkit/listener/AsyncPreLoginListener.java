package br.alkazuz.antibot.bukkit.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import br.alkazuz.antibot.bukkit.command.subcommands.NotificarSubCMD;
import br.alkazuz.antibot.bukkit.config.Config;
import br.alkazuz.antibot.bukkit.config.MessagesManager;
import br.alkazuz.antibot.bukkit.main.Configuration;
import br.alkazuz.antibot.bukkit.main.SpartanAntiBot;
import br.alkazuz.antibot.bukkit.protection.protections.BlockIPProtection;
import br.alkazuz.antibot.bukkit.protection.protections.CountryProtection;
import br.alkazuz.antibot.bukkit.protection.protections.ProxyProtection;
import br.alkazuz.antibot.bukkit.protection.protections.RejoinProtection;
import br.alkazuz.antibot.utils.Utils;

public class AsyncPreLoginListener implements Listener{
	
	@EventHandler
	public void onJoinEvent(PlayerJoinEvent event) {
		String IP = event.getPlayer().getAddress().getAddress().getHostAddress();
		
		Location location = event.getPlayer().getLocation();
		if(!Utils.whitelist.contains(IP) && Config.getConfig().getBoolean("Whitelist.AutoAdicionar.Ativado")) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(SpartanAntiBot.thePlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                	if(event.getPlayer()!= null &&event.getPlayer().isOnline()){
                		if(location.getX() != event.getPlayer().getLocation().getX() && location.getZ() != event.getPlayer().getLocation().getZ()){
                		Utils.whitelist.add(IP);
                		for(String pd : NotificarSubCMD.notify) {
    						Player p = Bukkit.getPlayer(pd);
    		        		if(p != null&& !Utils.whitelist.contains(IP)) {
    		        			p.sendMessage(MessagesManager.getString("Whitelist.Adicionado").replace("{0}", IP));
    		        		}
    		        	}
                		}
                	}
                }
            }, 20L * Config.getConfig().getInt("Whitelist.AutoAdicionar.Tempo"));
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPreLogin(AsyncPlayerPreLoginEvent event) {
		String IP = event.getAddress().getHostAddress();
		Utils.botpsec++;
		String nick = event.getName().toLowerCase();
		for(String protections : Configuration.protections) {
			if(Configuration.WHITELIST_ATIVADO && !Config.getConfig().getBoolean(protections+".IgnorarWhitelist")
					&& Utils.whitelist.contains(IP))continue;
			if(protections.equalsIgnoreCase("BloqueioDeIP")){
				BlockIPProtection b = new BlockIPProtection(nick, IP);
				b.run();
				if(b.isFailed()){
					event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, b.paraString());
					return;
				}
			}
			if(protections.equalsIgnoreCase("BloqueioDePaises")){
				CountryProtection b = new CountryProtection(nick, IP);
				b.run();
				if(b.isFailed()){
					event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, b.paraString());
					return;
				}
			}
			if(protections.equalsIgnoreCase("BloqueioDeProxy")){
				ProxyProtection b = new ProxyProtection(nick, IP);
				b.run();
				if(b.isFailed()){
					event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, b.paraString());
					return;
				}
			}
			if(protections.equalsIgnoreCase("Rejoin")){
				RejoinProtection b = new RejoinProtection(nick, IP);
				b.run();
				if(b.isFailed()){
					event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, b.paraString());
					return;
				}
			}
		}
		SpartanAntiBot.theInstance().sendConcoleMessage("§aJogador §f" + event.getName() + "§a iniciando conexao com o ip §f" + IP + "§a.");
	}

}
