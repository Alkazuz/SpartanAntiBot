package br.alkazuz.antibot.bungee.command;

import br.alkazuz.antibot.bungee.main.SpartanAntiBot;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class CommandSAB extends Command
{
	
	public CommandSAB() {
		super("sab", "sab.admin");
	}
	
	@SuppressWarnings("deprecation")
	@Override
	  public void execute(CommandSender Sender, String[] Args) {
		if (Sender.hasPermission("sab.admin")) {
        	if(Args.length == 0) {
        		Sender.sendMessage("          ");
                Sender.sendMessage("          §3SpartanAntiBot v"+SpartanAntiBot.thePlugin().getDescription().getVersion());
                for(SubCommand sb : SubCommands.all()) {
                	Sender.sendMessage("§e/sab "+sb.getSubCommand()+" "+sb.getUsage()+" §f- "+sb.getDescription());
                }
                Sender.sendMessage("          ");
                return;
        	}
        	SubCommand subcommand = SubCommands.get(Args[0]);
        	if(subcommand == null) {
        		Sender.sendMessage("          ");
                Sender.sendMessage("          §3SpartanAntiBot v"+SpartanAntiBot.thePlugin().getDescription().getVersion());
                for(SubCommand sb : SubCommands.all()) {
                	Sender.sendMessage("§e/sab "+sb.getSubCommand()+" "+sb.getUsage()+" §f- "+sb.getDescription());
                }
                Sender.sendMessage("          ");
                return;
        	}
        	
        	subcommand.run(Args, Sender);
        	
        }
	  }

}
