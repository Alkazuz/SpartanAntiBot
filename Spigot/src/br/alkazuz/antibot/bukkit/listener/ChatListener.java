package br.alkazuz.antibot.bukkit.listener;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import br.alkazuz.antibot.bukkit.command.subcommands.NotificarSubCMD;
import br.alkazuz.antibot.bukkit.main.GlobalBlock;

public class ChatListener implements Listener{

	private static HashMap<Player, Long> delayCommand = new HashMap<Player, Long>();
	private static HashMap<Player, Integer> regCommand = new HashMap<Player, Integer>();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		delayCommand.put(e.getPlayer(), System.currentTimeMillis());
	}
	
	private static String[] commands = new String[]{"/register","/registrar","/reg"};
	@EventHandler
	public void onchat(PlayerCommandPreprocessEvent event){
		String cmd = event.getMessage();
		if(cmd.contains(" ")){
			cmd = cmd.split(" ")[0];
		}
		Player p = event.getPlayer();
		for(String cmds : commands){
			if(cmds.equalsIgnoreCase(cmd)){
				String IP = event.getPlayer().getAddress().getAddress().getHostAddress();
				if(!regCommand.containsKey(p)){
					regCommand.put(p, 0);
				}
				int count = regCommand.get(p) + 1;
				regCommand.put(p, count);
				if(count >= 3){
					if(delayCommand.containsKey(event.getPlayer())){
						Long now = System.currentTimeMillis();
						Long d = delayCommand.get(event.getPlayer());
						if(now - d <= 10000){
							event.getPlayer().kickPlayer("§cBot");
							GlobalBlock.addIP(IP);
							for(String pd : NotificarSubCMD.notify) {
								Player pdd = Bukkit.getPlayer(pd);
				        		if(pdd != null) {
				        			pdd.sendMessage("§f"+IP+" §7foi bloqueado permanentemente do handler, floodou o registro");
				        		}
				        	}
							return;
						}
					}
				}
				if(delayCommand.containsKey(event.getPlayer())){
					Long now = System.currentTimeMillis();
					Long d = delayCommand.get(event.getPlayer());
					if(now - d <= 2000){
						event.getPlayer().kickPlayer("§cBot");
						return;
					}
				}
			}
		}
	}
}
