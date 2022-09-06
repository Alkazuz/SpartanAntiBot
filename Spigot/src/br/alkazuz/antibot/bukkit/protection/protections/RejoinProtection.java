package br.alkazuz.antibot.bukkit.protection.protections;

import java.util.HashMap;

import br.alkazuz.antibot.bukkit.config.Config;

public class RejoinProtection{

	private static HashMap<String, String> checked = new HashMap<String, String>();
	
	private String Nick,IP;
	private boolean Fail = false;
	String name = "Rejoin";
	
	public RejoinProtection(String name, String IP) {
		this.Nick = name;
		this.IP = IP;
	}
	
	public void run() {
		if(!checked.containsKey(IP)){
			checked.put(IP, Nick.toLowerCase());
			setFail(true);
			return;
		}else{
			if(!checked.get(IP).equalsIgnoreCase(Nick.toLowerCase())){
				checked.put(IP, Nick.toLowerCase());
				setFail(true);
				return;
			}
		}
		setFail(false);
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
