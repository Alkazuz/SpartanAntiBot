package br.alkazuz.antibot.bungee.main;

import java.io.File;
import java.util.logging.Logger;

import br.alkazuz.antibot.bungee.command.CommandSAB;
import br.alkazuz.antibot.bungee.command.SubCommands;
import br.alkazuz.antibot.bungee.command.subcommands.NotificarSubCMD;
import br.alkazuz.antibot.bungee.config.Config;
import br.alkazuz.antibot.bungee.config.Messages;
import br.alkazuz.antibot.bungee.handler.Pipeline;
import br.alkazuz.antibot.bungee.listener.PlayerPreLoginListener;
import br.alkazuz.antibot.bungee.protection.ProtectionManager;
import br.alkazuz.antibot.bungee.protection.protections.BlockIPProtection;
import br.alkazuz.antibot.utils.Utils;
import br.alkazuz.antibot.utils.maxmind.geoip.GeoIpService;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

public class SpartanAntiBot extends Plugin{
	
	public static Logger log;
    public boolean is19Server;
    public boolean oldEngine;
	private static Plugin plugin;
	private static GeoIpService geoIP;
	
	private static SpartanAntiBot instance;
	
	public static SpartanAntiBot theInstance() {
		return instance;
	}
	
	public static Plugin thePlugin() {
		return plugin;
	}
	
	@SuppressWarnings("deprecation")
	public void sendConcoleMessage(String msg) {
		getProxy().getConsole().sendMessage("§a[SpartanAntiBot] "+msg);
	}
	
	public static GeoIpService getGeoIpService() {
		return geoIP;
	}
	
	@Override
	public void onEnable() {
		sendConcoleMessage("Carregando o anti bot...");
		plugin = this;
		instance = this;
		log = this.getLogger();
		sendConcoleMessage("Carregando arquivos e configurações...");
		new Config(this);
		new Messages(this);
		
		sendConcoleMessage("Carregando proteções...");
		new ProtectionManager();
		geoIP = new GeoIpService(new File(plugin.getDataFolder(), "GeoIP.dat"), BlockIPProtection.cl);
		geoIP.createDownloadTask();
		geoIP.isDataAvailable();
		new Pipeline();
		log = this.getLogger();
       
			new Thread() {
				public void run() {
					try {
					while(true) {
						if(Utils.nproxy != 0) {
							for(ProxiedPlayer p : NotificarSubCMD.notify) {
								try {
									
				        			Title t = getProxy().createTitle();
				        			t.reset();
				        			t.subTitle((BaseComponent)new TextComponent(String.valueOf(Messages.getConfig().getString("Title.BotsBloqueados"))
				        					.replace("%n%", String.valueOf(Utils.nproxy))
				        					.replace("&", "§")));
				        			t.title((BaseComponent)new TextComponent(String.valueOf(Messages.getConfig().getString("Title.Title"))
				        					.replace("&", "§")));
				        			
				        			t.stay(3);
				        			t.fadeOut(80);
				        			t.fadeIn(40);
				        			t.send(p);
								}catch (Exception e) {
									continue;
								}
				        		}
						}
						Utils.nproxy = 0;
						Thread.sleep(1000 * 10);
					}
					}catch (Exception e) {}
				}
			}.start();
        
			
			new Thread() {
				public void run() {
					try {
					while(true) {
						Utils.botpsec = 0;
						Utils.hpps = 0;
						Thread.sleep(1000);
					}
					}catch (Exception e) {}
				}
			}.start();
        
        sendConcoleMessage("Registrando comandos e eventos...");
        new SubCommands();
        getMcVersion();
        getProxy().getPluginManager().registerCommand(this, new CommandSAB());
        getProxy().getPluginManager().registerListener(this, new PlayerPreLoginListener());
	}
	
	private void getMcVersion() {
		System.out.println(getProxy().getVersion());
        final String[] serverVersion = getProxy().getVersion().split("-");
        final String version = serverVersion[0];
        if (version.matches("1.7.10") || version.matches("1.7.9") || version.matches("1.7.5") || version.matches("1.7.2") || version.matches("1.8.8") || version.matches("1.8.3") || version.matches("1.8.4") || version.matches("1.8")) {
            is19Server = false;
            oldEngine = true;
        }
        else if (version.matches("1.9") || version.matches("1.9.1") || version.matches("1.9.2") || version.matches("1.9.3") || version.matches("1.9.4") || version.matches("1.10") || version.matches("1.10.1") || version.matches("1.10.2") || version.matches("1.11") || version.matches("1.11.1") || version.matches("1.11.2")) {
            oldEngine = true;
        }
        else {
            oldEngine = false;
        }
    }
	
}
