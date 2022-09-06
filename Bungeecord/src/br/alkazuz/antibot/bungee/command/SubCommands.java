package br.alkazuz.antibot.bungee.command;

import java.util.HashSet;

import br.alkazuz.antibot.bungee.command.subcommands.NotificarSubCMD;
import br.alkazuz.antibot.bungee.command.subcommands.ReloadSubCMD;

public class SubCommands {

	private static HashSet<SubCommand> subs = new HashSet<SubCommand>();
	
	public SubCommands() {
		subs.add(new NotificarSubCMD());
		subs.add(new ReloadSubCMD());
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
