package br.alkazuz.antibot.bukkit.protection;

import java.net.URL;
import java.util.Scanner;

import br.alkazuz.antibot.utils.Utils;

public class ProxyRunnable {
	
	private String IP;
	public boolean proxy = false;
	public ProxyRunnable(String IP) {
		this.IP = IP;
	}
	
	public void run(){
		new Thread(){
			public void run(){
				try{
				String res = "";
		         Scanner scanner = new Scanner(new URL("https://www.advanced-bot.tk/bot/api/proxy.php?ip=" + IP).openStream());
		         while (scanner.hasNextLine()) {
		           res = res + scanner.nextLine();
		         }
		         if(res.contains("Y")) {
		        	 Utils.proxies.add(IP);
		         }
		         scanner.close();
		       } catch (Exception e) {e.printStackTrace();}
			}
		}.start();
	}
}
