package br.alkazuz.antibot.bungee.protection;

import br.alkazuz.antibot.bungee.config.Config;

public class Protection {
	
	private String name;
	private boolean fail = false;
	
	public Protection(String name) {
		this.name = name;
	}
	
	public boolean isFail() {
		return fail;
	}
	
	public void setFail(boolean fail) {
		this.fail = fail;
	}
	public String getName() {
		return name;
	}

	public void run(String IP) {}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(String str : Config.getConfig().getStringList(name+".Kick")) {
			sb.append(str.replace("&", "§"));
			sb.append("\n");
		}
		return sb.toString();
	}
	
}
