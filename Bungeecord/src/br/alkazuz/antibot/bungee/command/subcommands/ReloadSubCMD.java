package br.alkazuz.antibot.bungee.command.subcommands;

import br.alkazuz.antibot.bungee.command.SubCommand;
import br.alkazuz.antibot.bungee.config.Config;
import br.alkazuz.antibot.bungee.config.Messages;
import br.alkazuz.antibot.bungee.main.SpartanAntiBot;
import br.alkazuz.antibot.bungee.protection.ProtectionManager;
import net.md_5.bungee.api.CommandSender;

public class ReloadSubCMD extends SubCommand{
	
	public ReloadSubCMD() {
		super("reload", "", "recarrega arquivos e configurações");
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run(String[] args, CommandSender s) {
		new Config(SpartanAntiBot.thePlugin());
		new Messages(SpartanAntiBot.thePlugin());
		new ProtectionManager();
		s.sendMessage("§aArquivos e configurações recarregadas");
	}
	
}
