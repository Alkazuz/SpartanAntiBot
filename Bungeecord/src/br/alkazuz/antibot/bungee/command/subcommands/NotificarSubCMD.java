package br.alkazuz.antibot.bungee.command.subcommands;

import java.util.HashSet;

import br.alkazuz.antibot.bungee.command.SubCommand;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class NotificarSubCMD extends SubCommand{

	public static HashSet<ProxiedPlayer> notify = new HashSet<ProxiedPlayer>();
	
	public NotificarSubCMD() {
		super("notificar", "[ON/OFF]", "ativa e desativa notificações do Plugin");
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run(String[] args, CommandSender s) {
		if (args.length != 2) {
			s.sendMessage("§cComando correto: §4/sab notificar [ON/OFF]");
			return;
		}
		if (args[1].equalsIgnoreCase("on") && s instanceof ProxiedPlayer) {
			final ProxiedPlayer p = (ProxiedPlayer)s;
			if(notify.contains(p)) {
				s.sendMessage("§cSuas notificações já estão ativadas");
				return;
			}else {
				notify.add(p);
				s.sendMessage("§aNotificações foram ativadas para você");
			}
		}
		if (args[1].equalsIgnoreCase("off") && s instanceof ProxiedPlayer) {
			final ProxiedPlayer p = (ProxiedPlayer)s;
			if(!notify.contains(p)) {
				s.sendMessage("§cSuas notificações já estão desativadas");
				return;
			}else {
				notify.remove(p);
				s.sendMessage("§cNotificações foram desativadas para você");
			}
		}
	}
	
}
