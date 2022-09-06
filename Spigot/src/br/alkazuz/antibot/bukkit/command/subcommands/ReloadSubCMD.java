package br.alkazuz.antibot.bukkit.command.subcommands;

import java.util.HashSet;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.avaje.ebeaninternal.server.core.Message;

import br.alkazuz.antibot.bukkit.command.SubCommand;
import br.alkazuz.antibot.bukkit.config.Config;
import br.alkazuz.antibot.bukkit.config.MessagesManager;
import br.alkazuz.antibot.bukkit.main.Configuration;

public class ReloadSubCMD extends SubCommand{

	public static HashSet<Player> notify = new HashSet<Player>();
	
	public ReloadSubCMD() {
		super("reload", "", "Recarregar Plugin.");
	}
	
	@Override
	public void run(String[] args, CommandSender s) {
		new Config();
		new Message();
		Configuration.reload();
		new MessagesManager();
		s.sendMessage("§aArquivos e configurações recarregadas");
	}
	
}
