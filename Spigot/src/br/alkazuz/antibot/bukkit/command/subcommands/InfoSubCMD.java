package br.alkazuz.antibot.bukkit.command.subcommands;

import java.util.HashSet;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.alkazuz.antibot.bukkit.command.SubCommand;
import br.alkazuz.antibot.bukkit.main.Configuration;
import br.alkazuz.antibot.utils.Utils;

public class InfoSubCMD extends SubCommand{

	public static HashSet<Player> notify = new HashSet<Player>();
	
	public InfoSubCMD() {
		super("info", "", "Obter informções completas.");
	}

	@Override
	public void run(String[] args, CommandSender s) {
		s.sendMessage("");
		s.sendMessage("          §fInformações");
		s.sendMessage(" ");
		s.sendMessage(" §5§l– §fProxies na Blacklist: §7"+Utils.proxies.size());
		s.sendMessage(" §5§l– §fIPs na WhiteList interna: §7"+Utils.whitelist.size());
		//s.sendMessage(" §5§l– §fIPs bloqueados por spam: §7"+BlockIPProtection.ipsblocked.size());
		s.sendMessage(" §5§l– §fOrdem de proteções:");
		for(String protections : Configuration.protections){
			s.sendMessage("   §a–§7 "+protections);
		}
		s.sendMessage(" ");
	}
	
}
