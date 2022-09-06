package br.alkazuz.antibot.bukkit.command.subcommands;

import java.util.HashSet;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.alkazuz.antibot.bukkit.command.SubCommand;
import br.alkazuz.antibot.bukkit.config.MessagesManager;
import br.alkazuz.antibot.utils.Utils;
import br.alkazuz.antibot.utils.database.SQLFunctions;

public class BlacklistSubCMD extends SubCommand{

	public static HashSet<Player> notify = new HashSet<Player>();
	
	public BlacklistSubCMD() {
		super("blacklist", "[ADD/DEL] [Player/IP]", "Jogadores na BlackList.");
	}
	
	private static final Pattern PATTERN = Pattern.compile(
	        "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

	private static boolean validate(final String ip) {
	    return PATTERN.matcher(ip).matches();
	}
	
	@Override
	public void run(String[] args, CommandSender s) {
		if (args.length != 3) {
			s.sendMessage("§cComando correto: §f/sab blacklist [ADD/DEL] [Player/IP]§c.");
			return;
		}
		if (args[1].equalsIgnoreCase("add") ) {
			if(args[2].contains(".")) {
				if(validate(args[2])) {
					if(Utils.proxies.contains(args[2])){
						s.sendMessage(MessagesManager.getString("Comandos.Blacklist.JaExiste"));
						return;
					}
					Utils.proxies.add(args[2]);
					SQLFunctions.insertToProxies(args[2]);
					s.sendMessage(MessagesManager.getString("Comandos.Blacklist.Adicionou").replace("{0}", args[2]));
					return;
				}
			}else {
				Player target = Bukkit.getPlayer(args[2]);
				if(target == null) {
					s.sendMessage(MessagesManager.getString("Comandos.Blacklist.PlayerInexistente"));
					return;
				}
				String ip = target.getAddress().getAddress().getHostAddress();
				System.out.println(ip);
				if(Utils.proxies.contains(ip)){
					s.sendMessage(MessagesManager.getString("Comandos.Blacklist.JaExiste"));
					return;
				}
				Utils.proxies.add(ip);
				SQLFunctions.insertToProxies(ip);
				s.sendMessage(MessagesManager.getString("Comandos.Blacklist.Adicionou").replace("{0}", ip));
				return;
			}
		}
		if (args[1].equalsIgnoreCase("del") ) {
			if(args[2].contains(".")) {
				if(validate(args[2])) {
					if(!Utils.proxies.contains(args[2])){
						s.sendMessage(MessagesManager.getString("Comandos.Blacklist.NaoEstaNaBlacklist"));
						return;
					}
					Utils.proxies.remove(args[2]);
					SQLFunctions.delFromProxies(args[2]);
					s.sendMessage(MessagesManager.getString("Comandos.Blacklist.Removeu").replace("{0}", args[2]));
					return;
				}
			}else {
				Player target = Bukkit.getPlayer(args[2]);
				if(target == null) {
					s.sendMessage(MessagesManager.getString("Comandos.Blacklist.PlayerInexistente"));
					return;
				}
				String ip = target.getAddress().getAddress().getHostAddress();
				System.out.println(ip);
				if(!Utils.proxies.contains(ip)){
					s.sendMessage(MessagesManager.getString("Comandos.Blacklist.NaoEstaNaBlacklist"));
					return;
				}
				Utils.proxies.remove(ip);
				SQLFunctions.delFromProxies(ip);
				s.sendMessage(MessagesManager.getString("Comandos.Blacklist.Removeu").replace("{0}", ip));
				return;
			}
		}
	}
	
}
