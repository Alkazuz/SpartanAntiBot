package br.alkazuz.antibot.bukkit.protection;

public class BotBlock {

	private final String IP;
	private final Long time;
	
	public BotBlock(String IP, Long time) {
		this.IP = IP;
		this.time = time;
	}

	public String getIP() {
		return IP;
	}

	public Long getTime() {
		return time;
	}
	
	public boolean isRecent(){
		return System.currentTimeMillis() - time <= 10000;
	}
	
}
