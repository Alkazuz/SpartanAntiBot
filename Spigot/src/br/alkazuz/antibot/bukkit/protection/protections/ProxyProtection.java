package br.alkazuz.antibot.bukkit.protection.protections;

import java.net.URL;
import java.util.Scanner;

import br.alkazuz.antibot.bukkit.config.Config;
import br.alkazuz.antibot.utils.Utils;

public class ProxyProtection{
	
	@SuppressWarnings("unused")
	private String Nick,IP;
	private boolean Fail = false;
	String name = "BloqueioDeProxy";
	
	public ProxyProtection(String name, String IP) {
		this.Nick = name;
		this.IP = IP;
	}
	
	public void run() {
		if(Utils.proxies.contains(IP)){
			setFail(true);
			return;
		}
		if(isProxy(IP)){
			setFail(true);
			return;
		}
	}

	public boolean isProxy(String IP){
		try{
				String status = getString("https://advanced-bot.tk/bot/api/proxy.php?ip="+IP);
				return status.contains("Y");
		} catch (Exception e) {e.printStackTrace();}
		return false;
	}

	public String getString(String URL){
		String res = "";
		try {
			Scanner scanner = new Scanner(new URL(URL).openStream());
		
        while (scanner.hasNextLine()) {
          res = res + scanner.nextLine();
        }
        scanner.close();
        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "N";
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
