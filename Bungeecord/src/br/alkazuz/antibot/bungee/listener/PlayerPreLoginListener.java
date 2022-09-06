package br.alkazuz.antibot.bungee.listener;

import br.alkazuz.antibot.bungee.command.subcommands.NotificarSubCMD;
import br.alkazuz.antibot.bungee.config.Messages;
//import br.alkazuz.antibot.bungee.handler.event.PlayerHandshakeEvent;
import br.alkazuz.antibot.bungee.main.SpartanAntiBot;
import br.alkazuz.antibot.bungee.protection.Protection;
import br.alkazuz.antibot.bungee.protection.ProtectionManager;
import br.alkazuz.antibot.bungee.protection.protections.ProxyProtection;
import br.alkazuz.antibot.bungee.protection.protections.ServerPingProtection;
import br.alkazuz.antibot.utils.Utils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerHandshakeEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerPreLoginListener implements Listener {
	
	@EventHandler
    public void onProxyPing(PlayerHandshakeEvent event) {
		String IP = event.getConnection().getAddress().getAddress().getHostAddress();
		Utils.hpps++;
		if(ProxyProtection.proxies.contains(IP)) {
			event.getConnection().disconnect(new TextComponent("bot"));
				for(ProxiedPlayer p : NotificarSubCMD.notify) {
					if(p!= null && p.isConnected()) {
						BaseComponent[] title = TextComponent.fromLegacyText(Messages.getConfig().getString("ActionBar.BotBlock")
	        					.replace("%pps%", String.valueOf(Utils.botpsec))
	        					.replace("&", "§")
	        					.replace("%protection%", "Proxy")
	        					.replace("%bot%", IP)
	        					.replace("%hps%", String.valueOf(Utils.hpps)));
						
						p.sendMessage(ChatMessageType.ACTION_BAR, title);
					}
				}
		
		}
	}
	
	@EventHandler
    public void onProxyPreLogin(PreLoginEvent event) {
		String IP = event.getConnection().getAddress().getAddress().getHostAddress();
		if(ServerPingProtection.ips.contains(IP)) {
			ServerPingProtection.ips.remove(IP);
		}
		Utils.botpsec++;
		for(Protection protections : ProtectionManager.All()) {
			protections.run(IP);
			if(protections.isFail()) {
				event.getConnection().disconnect(new TextComponent( protections.toString()));
				SpartanAntiBot.theInstance().sendConcoleMessage("§cConexão bloqueada, usuário: "+event.getConnection().getName()+", IP: "+IP+" Metodo: "+protections.getName());
				Utils.nproxy ++;
				for(ProxiedPlayer p : NotificarSubCMD.notify) {
					if(p!= null && p.isConnected()) {
						BaseComponent[] title = TextComponent.fromLegacyText(Messages.getConfig().getString("ActionBar.BotBlock")
	        					.replace("%pps%", String.valueOf(Utils.botpsec))
	        					.replace("&", "§")
	        					.replace("%protection%", protections.getName())
	        					.replace("%bot%", IP)
	        					.replace("%hps%", String.valueOf(Utils.hpps)));
						
						p.sendMessage(ChatMessageType.ACTION_BAR, title);
					}
				}
				return;
			}
		}
		SpartanAntiBot.theInstance().sendConcoleMessage("Iniciando conexão, usuário: "+event.getConnection().getName()+" IP: "+IP);
	}
	
	@EventHandler
    public void onProxyPing(ProxyPingEvent event) {
		String IP = event.getConnection().getAddress().getAddress().getHostAddress();
		if(!ServerPingProtection.ips.contains(IP)) {
			ServerPingProtection.ips.add(IP);
		}
	}

}
