package br.alkazuz.antibot.bukkit.command;

import org.bukkit.command.CommandSender;

public class SubCommand {

	private String subCommand;
	private String description;
	private String usage;
	
	public SubCommand(String sb, String usage, String desc) {
		subCommand = sb;
		this.usage = usage;
		description = desc;
	}
	
	public String getUsage() {
		return usage;
	}
	
	public String getSubCommand() {
		return subCommand;
	}

	public void setSubCommand(String subCommand) {
		this.subCommand = subCommand;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void run(String[] args, CommandSender s) {}
	
}
