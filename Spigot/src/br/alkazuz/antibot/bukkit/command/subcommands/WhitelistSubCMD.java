package br.alkazuz.antibot.bukkit.command.subcommands;

import java.util.HashSet;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.alkazuz.antibot.bukkit.command.SubCommand;
import br.alkazuz.antibot.utils.Utils;
import br.alkazuz.antibot.utils.database.SQLFunctions;

public class WhitelistSubCMD extends SubCommand{

	public static HashSet<Player> notify = new HashSet<Player>();
	
	public WhitelistSubCMD() {
		super("whitelist", "[ADD/DEL] [Player/IP]", "Jogadores na WhiteList.");
	}
	
	private static final Pattern PATTERN = Pattern.compile(
	        "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

	private static boolean validate(final String ip) {
	    return PATTERN.matcher(ip).matches();
	}
	
	@Override
	public void run(String[] args, CommandSender s) {
		if (args.length != 3) {
			s.sendMessage("§cComando correto: §f/sab whitelist [ADD/DEL] [Player/IP]§c.");
			return;
		}
		if (args[1].equalsIgnoreCase("add") ) {
			if(args[2].contains(".")) {
				if(validate(args[2])) {
					if(Utils.whitelist.contains(args[2])){
						s.sendMessage("§cEste IP já está na whitelist interna.");
						return;
					}
					Utils.whitelist.add(args[2]);
					SQLFunctions.insertToWhitelist(args[2]);
					s.sendMessage("§aVocê adicionou com sucesso o IP §f" + args[2] + " §ana WhiteList.");
					return;
				}else{
					s.sendMessage("§cFormato de IP é inválido!");
					return;
				}
			}else {
				Player target = Bukkit.getPlayer(args[2]);
				if(target == null) {
					s.sendMessage("§cJogador inexistente ou offline.");
					return;
				}
				String ip = target.getAddress().getAddress().getHostAddress();
				System.out.println(ip);
				if(Utils.whitelist.contains(ip)){
					s.sendMessage("§cEste IP já está na whitelist interna!");
					return;
				}
				Utils.whitelist.add(ip);
				SQLFunctions.insertToWhitelist(ip);
				s.sendMessage("§aVocê adicionou com sucesso o IP §f" + ip + " §ana WhiteList.");
				return;
			}
		}
		if (args[1].equalsIgnoreCase("del") ) {
			if(args[2].contains(".")) {
				if(validate(args[2])) {
					if(!Utils.whitelist.contains(args[2])){
						s.sendMessage("§cEste IP não está na whitelist interna");
						return;
					}
					Utils.whitelist.remove(args[2]);
					SQLFunctions.delFromWhitelist(args[2]);
					s.sendMessage("§aVocê removeu com sucesso o IP §f" + args[2] + " §ada WhiteList");
					return;
				}
				s.sendMessage("§cFormato de IP é inválido");
				return;
			}else {
				Player target = Bukkit.getPlayer(args[2]);
				if(target == null) {
					s.sendMessage("§cJogador inexistente ou offline!");
					return;
				}
				String ip = target.getAddress().getAddress().getHostAddress();
				System.out.println(ip);
				if(!Utils.whitelist.contains(ip)){
					s.sendMessage("§cEste IP não está na whitelist interna!");
					return;
				}
				Utils.whitelist.remove(ip);
				SQLFunctions.delFromWhitelist(ip);
				s.sendMessage("§aVocê removeu com sucesso o IP §f" + ip + " §ada WhiteList.");
				return;
			}
		}
	}
	
}
