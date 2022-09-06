package br.alkazuz.antibot.bukkit.hooks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import br.alkazuz.antibot.bukkit.captcha.Captcha;
import br.alkazuz.antibot.bukkit.main.Configuration;
import br.alkazuz.antibot.bukkit.main.SpartanAntiBot;
import nickultracraft.login.events.LoginEvent;

public class nLoginListener implements Listener{
	
	public boolean isSameBlock(final Location loc, final Location loc2) {
        return loc.getBlockX() == loc2.getBlockX() && loc.getBlockY() == loc2.getBlockY() && loc.getBlockZ() == loc2.getBlockZ();
    }
	
	@EventHandler
    public void onJoin(final LoginEvent e) {
		Player p = e.getPlayer();
		
		
		if(!Configuration.CAPTCHA_ATIVADO)return;
        Bukkit.getScheduler().scheduleSyncDelayedTask(SpartanAntiBot.thePlugin(), (Runnable)new Runnable() {
            @Override
            public void run() {
            	//p.setWalkSpeed(0.0f);
                p.getWorld().playSound(p.getLocation(), Sound.WOOD_CLICK, 10.0f, 10.0f);
                Captcha.createCaptcha(e.getPlayer());
                Captcha.time.put(p.getName(), System.currentTimeMillis());
            }
        }, 10L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(SpartanAntiBot.thePlugin(), (Runnable)new Runnable() {
            @Override
            public void run() {
            	if (p != null && p.isOnline() && Captcha.request.containsKey(p)) {
            		p.kickPlayer("§cVocê demorou demais para verificar sua conta, relogue!");
            	}
            }
        }, 20L * Configuration.CAPTCHA_TEMPO);	
    }

}
