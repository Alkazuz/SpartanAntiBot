package br.alkazuz.antibot.bukkit.command.subcommands;

import java.util.HashSet;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.alkazuz.antibot.bukkit.command.SubCommand;
import br.alkazuz.antibot.bukkit.main.Configuration;

public class ToggleCMD extends SubCommand{

	public static HashSet<Player> notify = new HashSet<Player>();
	
	public ToggleCMD() {
		super("toggle", "<Antiproxy | Whitelist | Blacklist | BlockIP | Rejoin | Countryblock>", "Desativa/ativa��o a fun��o.");
	}

	@Override
	public void run(String[] args, CommandSender s) {
		if (args.length != 2) {
			s.sendMessage("�cComando correto: �f/sab toggle <Antiproxy | Whitelist | Blacklist | BlockIP | Rejoin | Countryblock>�c.");
			return;
		}
		if (args[1].equalsIgnoreCase("Antiproxy")) {
			if(Configuration.protections.contains("BloqueioDeProxy")) { 
				Configuration.protections.remove("BloqueioDeProxy"); } else {
				Configuration.protections.add("BloqueioDeProxy");
			}
			boolean has = Configuration.protections.contains("BloqueioDeProxy");
			s.sendMessage("�aFun��o Antiproxy foi "+(has? "ligada" : "desligada")+" com sucesso.");
		}
		
		if (args[1].equalsIgnoreCase("Whitelist")) {
			Configuration.WHITELIST_ATIVADO = !Configuration.WHITELIST_ATIVADO;
			s.sendMessage("�aFun��o Whitelist foi "+(Configuration.WHITELIST_ATIVADO? "ligada" : "desligada")+" com sucesso.");
		}
		
	}
	
}
