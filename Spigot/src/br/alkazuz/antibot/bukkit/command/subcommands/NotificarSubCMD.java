package br.alkazuz.antibot.bukkit.command.subcommands;

import java.util.HashSet;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.alkazuz.antibot.bukkit.command.SubCommand;
import br.alkazuz.antibot.bukkit.config.MessagesManager;

public class NotificarSubCMD extends SubCommand{

	public static HashSet<String> notify = new HashSet<String>();
	
	public NotificarSubCMD() {
		super("notificar", "[ON/OFF]", "Receber notificaçao de BOT.");
	}
	
	@Override
	public void run(String[] args, CommandSender s) {
		if (args.length != 2) {
			s.sendMessage("§cComando correto: §f/sab notificar [ON/OFF]§c.");
			return;
		}
		if (args[1].equalsIgnoreCase("on") && s instanceof Player) {
			final Player p = (Player)s;
			if(notify.contains(p.getName())) {
				s.sendMessage(MessagesManager.getString("Comandos.Notificacao.JaAtivada"));
				return;
			}else {
				notify.add(p.getName());
				s.sendMessage(MessagesManager.getString("Comandos.Notificacao.Ativada"));
			}
		}
		if (args[1].equalsIgnoreCase("off") && s instanceof Player) {
			final Player p = (Player)s;
			if(!notify.contains(p.getName())) {
				s.sendMessage(MessagesManager.getString("Comandos.Notificacao.Desativada"));
				return;
			}else {
				notify.remove(p.getName());
				s.sendMessage(MessagesManager.getString("Comandos.Notificacao.JaDesativada"));
			}
		}
	}
	
}
