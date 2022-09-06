package br.alkazuz.antibot.bungee.command.subcommands;

import java.util.HashSet;

import br.alkazuz.antibot.bungee.command.SubCommand;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class NotificarSubCMD extends SubCommand{

	public static HashSet<ProxiedPlayer> notify = new HashSet<ProxiedPlayer>();
	
	public NotificarSubCMD() {
		super("notificar", "[ON/OFF]", "ativa e desativa notifica��es do Plugin");
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run(String[] args, CommandSender s) {
		if (args.length != 2) {
			s.sendMessage("�cComando correto: �4/sab notificar [ON/OFF]");
			return;
		}
		if (args[1].equalsIgnoreCase("on") && s instanceof ProxiedPlayer) {
			final ProxiedPlayer p = (ProxiedPlayer)s;
			if(notify.contains(p)) {
				s.sendMessage("�cSuas notifica��es j� est�o ativadas");
				return;
			}else {
				notify.add(p);
				s.sendMessage("�aNotifica��es foram ativadas para voc�");
			}
		}
		if (args[1].equalsIgnoreCase("off") && s instanceof ProxiedPlayer) {
			final ProxiedPlayer p = (ProxiedPlayer)s;
			if(!notify.contains(p)) {
				s.sendMessage("�cSuas notifica��es j� est�o desativadas");
				return;
			}else {
				notify.remove(p);
				s.sendMessage("�cNotifica��es foram desativadas para voc�");
			}
		}
	}
	
}
