package br.alkazuz.antibot.bungee.protection.protections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Scanner;

import br.alkazuz.antibot.bungee.main.SpartanAntiBot;
import br.alkazuz.antibot.bungee.protection.Protection;

public class ProxyProtection extends Protection{

	public static HashSet<String> proxies = new HashSet<String>();
	
	public ProxyProtection() {
		super("BloqueioDeProxy");
		try {
			SpartanAntiBot.theInstance().sendConcoleMessage("Fazendo download da blacklist de proxies...");
			URL website = new URL("https://www.advanced-bot.tk/api/proxy.php?ip=getall");
	          URLConnection connection = website.openConnection();
	          connection = website.openConnection();
	          connection.addRequestProperty("User-Agent", 
	            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
	          BufferedReader in = new BufferedReader(
	            new InputStreamReader(
	            connection.getInputStream()));
	          String inputLine;
	          while ((inputLine = in.readLine()) != null)
	          {
	        	  for(String proxy : inputLine.split("<br>")) {
	        		  if (!proxies.contains(proxy)) {
	    	              proxies.add(proxy);
	        		  }
	        	  }
	          }
	          in.close();
	          SpartanAntiBot.theInstance().sendConcoleMessage("Foram carregados "+proxies.size()+" proxies");
	       } catch (Exception e) {e.printStackTrace();}
	}
	
	@Override
	public void run(String IP) {
		if(proxies.contains(IP)) {
			setFail(true);
			return;
		}
		if(isProxy(IP)) {
			proxies.add(IP);
			setFail(true);
			return;
		}
		setFail(false);
	}

	private boolean isProxy(String IP) {
		try {
	         String res = "";
	         Scanner scanner = new Scanner(new URL("https://www.advanced-bot.tk/api/proxy.php?ip=" + IP).openStream());
	         while (scanner.hasNextLine()) {
	           res = res + scanner.nextLine();
	         }
	         if(res.contains("Y")) {
	        	 scanner.close();
	        	 return true;
	         }
	         scanner.close();
	       } catch (Exception e) {e.printStackTrace();}
		return false;
	}
	
}
