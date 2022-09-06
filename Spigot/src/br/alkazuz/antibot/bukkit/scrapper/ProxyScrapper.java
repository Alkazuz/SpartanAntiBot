package br.alkazuz.antibot.bukkit.scrapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.alkazuz.antibot.bukkit.config.Config;
import br.alkazuz.antibot.bukkit.main.SpartanAntiBot;
import br.alkazuz.antibot.utils.Utils;

public class ProxyScrapper {
	
	public static List<String> downloadFrom(String link){
		List<String> proxies = new ArrayList<String>();
		
		try {
		URLConnection connection = new URL(link).openConnection();
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		connection.connect();

		BufferedReader r  = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
		
		String line;
		while ((line = r.readLine()) != null) {
	            String ip = getIP(line);
	            if(ip != null) {
	            	proxies.add(ip);
	            }else {
	            	if(line.contains("\"")) {
	            		for(String s : line.split("\"")) {
	            			ip = getIP(s);
	        	            if(ip != null) {
	        	            	proxies.add(ip);
	        	            }
	            		}
	            	}
	            	if(line.contains(":")) {
	            		for(String s : line.split(":")) {
	            			ip = getIP(s);
	        	            if(ip != null) {
	        	            	proxies.add(ip);
	        	            }
	            		}
	            	}
	            }
	        }
	    } catch (Exception mue) {
	         mue.printStackTrace();
	    }
		 return proxies;
	}
	
	private static String getIP(String ip) {
		Pattern pattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
		Matcher matcher = pattern.matcher(ip);

		if (matcher.find()) {
		    return matcher.group(0);
		} else {
		    return null;
		}
	}
	
	public static void loadFromLinks() {
		for(String url : Config.getConfig().getStringList("ProxyScrapper.Links")) {
			SpartanAntiBot.theInstance().sendConcoleMessage("Baixando §e"+ url);
			
			int i = 0;
			for(String proxies : ProxyScrapper.downloadFrom(url)) {
				if(!Utils.proxies.contains(proxies)){
					Utils.proxies.add(proxies);
					i++;
				}
			}
			SpartanAntiBot.theInstance().sendConcoleMessage(url+" foram carregados §e"+ i+ " §fIPs");
		}
	}
	
}
