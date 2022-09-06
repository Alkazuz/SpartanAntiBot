package br.alkazuz.antibot.bukkit.command;

import java.util.HashSet;

import br.alkazuz.antibot.bukkit.command.subcommands.*;

public class SubCommands {

	private static HashSet<SubCommand> subs = new HashSet<SubCommand>();
	
	public SubCommands() {
		subs.add(new NotificarSubCMD());
		subs.add(new ReloadSubCMD());
		subs.add(new WhitelistSubCMD());
		subs.add(new BlacklistSubCMD());
		subs.add(new InfoSubCMD());
	}
	
	public static HashSet<SubCommand> all(){
		return subs;
	}
	
	public static SubCommand get(String sub) {
		for(SubCommand sc : all()) {
			if(sc.getSubCommand().equalsIgnoreCase(sub)) {
				return sc;
			}
		}
		return null;
	}
	
}
