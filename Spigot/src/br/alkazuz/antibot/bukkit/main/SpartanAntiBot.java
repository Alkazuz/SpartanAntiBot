package br.alkazuz.antibot.bukkit.main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.ProtocolManager;

import br.alkazuz.antibot.bukkit.captcha.Captcha;
import br.alkazuz.antibot.bukkit.command.CommandSAB;
import br.alkazuz.antibot.bukkit.command.SubCommands;
import br.alkazuz.antibot.bukkit.command.subcommands.NotificarSubCMD;
import br.alkazuz.antibot.bukkit.config.Config;
import br.alkazuz.antibot.bukkit.config.Messages;
import br.alkazuz.antibot.bukkit.config.MessagesManager;
import br.alkazuz.antibot.bukkit.database.SQLManager;
import br.alkazuz.antibot.bukkit.hooks.AuthmeListener;
import br.alkazuz.antibot.bukkit.hooks.NoneListener;
import br.alkazuz.antibot.bukkit.hooks.nLoginListener;
import br.alkazuz.antibot.bukkit.listener.AsyncPreLoginListener;
import br.alkazuz.antibot.bukkit.listener.ChatListener;
import br.alkazuz.antibot.bukkit.listener.MotdPingListener;
import br.alkazuz.antibot.bukkit.protection.protections.CountryProtection;
import br.alkazuz.antibot.bukkit.scrapper.ProxyScrapper;
import br.alkazuz.antibot.bukkit.utils.craftersland.consolefix.EngineInterface;
import br.alkazuz.antibot.bukkit.utils.craftersland.consolefix.NewEngine;
import br.alkazuz.antibot.bukkit.utils.craftersland.consolefix.OldEngine;
import br.alkazuz.antibot.utils.TitleAPI;
import br.alkazuz.antibot.utils.Utils;
import br.alkazuz.antibot.utils.maxmind.geoip.GeoIpService;
import br.alkazuz.antibot.utils.maxmind.geoip.LookupService;

public class SpartanAntiBot extends JavaPlugin{
	
	public static Logger log;
    public boolean is19Server;
    public boolean oldEngine;
	private static Plugin plugin;
	private static EngineInterface eng;
	public ProtocolManager protocolMgr;
	private static GeoIpService geoIP;
	
	private static SpartanAntiBot instance;
	public static SpartanAntiBot theInstance() {
		return instance;
	}
	
	public static Plugin thePlugin() {
		return plugin;
	}
	
	public void sendConcoleMessage(String msg) {
		Bukkit.getServer().getConsoleSender().sendMessage("§5§l[Anti-Bot]§f "+msg);
	}
	
	public static GeoIpService getGeoIpService() {
		return geoIP;
	}
	
	private void createSQLConnections() {
        SQLManager.SQLManagerConnection();
    }
	
	@Override
	public void onEnable() {
		sendConcoleMessage("Carregando o anti bot...");
		plugin = this;
		instance = this;
		getMcVersion();
		
		new Captcha(this);
		
		sendConcoleMessage("Carregando arquivos e configuracoes...");
		new Config();
		new Messages();
		
		new Configuration();
		
		createSQLConnections();
		sendConcoleMessage("Carregando protecoes...");
		geoIP = new GeoIpService(new File(plugin.getDataFolder(), "GeoIP.dat"), CountryProtection.cl);
		geoIP.createDownloadTask();
		geoIP.isDataAvailable();
		TitleAPI.load();
		try {
			CountryProtection.cl = new LookupService(new File(plugin.getDataFolder(), "GeoIP.dat"), 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log = this.getLogger();
        getMcVersion();
        if (this.oldEngine) {
            eng = new OldEngine();
        }
        else {
            eng = new NewEngine();
        }
        getEngine().hideConsoleMessages();
		
        if(Config.getConfig().getBoolean("ProxyScrapper.Ativado")) {
        	ProxyScrapper.loadFromLinks();
        }
        
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			public void run() {
				if(Utils.nproxy != 0) {
					for(String pd : NotificarSubCMD.notify) {
						Player p = Bukkit.getPlayer(pd);
		        		if(p != null) {
		        			br.alkazuz.antibot.utils.Utils.EnviarTitle(p, MessagesManager.getString("Title.Title"), MessagesManager.getString("Title.BotsBloqueados").replace("%n%", String.valueOf(Utils.nproxy)), 10, 10, 10);
		        		}
		        	}
				}
				Utils.nproxy = 0;
			}
	    }, 0L, 20 * 10);
        
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			public void run() {
				
				Utils.botpsec = 0;
				Utils.hpps = 0;
			}
	    }, 0L, 20);
        
        sendConcoleMessage("Registrando comandos e eventos...");
        new SubCommands();
        getCommand("sab").setExecutor(new CommandSAB());
        getServer().getPluginManager().registerEvents(new AsyncPreLoginListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new MotdPingListener(), this);
        //ProtocolLibrary.getProtocolManager().addPacketListener(new ProtocolBlock(this, new PacketType[] { PacketType.Handshake.Client.SET_PROTOCOL } ));
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
            @Override
            public void run(){
            	sendConcoleMessage("Servidor esta totalmente carregado, aceitando conexoes...");
                Utils.loaded = true;
                startHooks();
            }
        });
	}

	public EngineInterface getEngine() {
        return eng;
    }
	
	public void startHooks(){
		if(getServer().getPluginManager().getPlugin("AuthMe")!=null){
			getServer().getPluginManager().registerEvents(new AuthmeListener(), this);
			sendConcoleMessage("Authme encontrado.");
			return;
		}
		getServer().getPluginManager().registerEvents(new NoneListener(), this);
	}
	
	private void getMcVersion() {
        final String[] serverVersion = Bukkit.getBukkitVersion().split("-");
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
