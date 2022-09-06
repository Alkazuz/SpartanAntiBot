package br.alkazuz.antibot.bukkit.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import br.alkazuz.antibot.bukkit.config.MessagesManager;
import br.alkazuz.antibot.bukkit.main.SpartanAntiBot;

public class CommandSAB implements CommandExecutor {

	@Override
	public boolean onCommand(final CommandSender Sender, final Command Cmd, final String Label, final String[] Args) {
		if (Cmd.getName().equalsIgnoreCase("sab")) {
			if (Sender.hasPermission("sab.admin")) {
				if (Args.length == 0) {
					Sender.sendMessage("          ");
					Sender.sendMessage("          §5§lVoid Anti-Bot §8§l» §7 v"
							+ SpartanAntiBot.thePlugin().getDescription().getVersion());
					for (SubCommand sb : SubCommands.all()) {
						Sender.sendMessage(" §8§l» §f" + sb.getSubCommand() + " " + sb.getUsage() + " §f- §7"
								+ sb.getDescription());
					}
					Sender.sendMessage("          ");
					return true;
				}
				SubCommand subcommand = SubCommands.get(Args[0]);
				if (subcommand == null) {
					Sender.sendMessage("          ");
					Sender.sendMessage("          §5§lVoid Anti-Bot §8§l» §7 v"
							+ SpartanAntiBot.thePlugin().getDescription().getVersion());
					for (SubCommand sb : SubCommands.all()) {
						Sender.sendMessage(" §8§l» §f" + sb.getSubCommand() + " " + sb.getUsage() + " §f- §7"
								+ sb.getDescription());
					}
					Sender.sendMessage("          ");
					return true;
				}

				subcommand.run(Args, Sender);

			} else {
				Sender.sendMessage(MessagesManager.getString("Comandos.SemPermissao"));
			}
		}
		return true;
	}

}
