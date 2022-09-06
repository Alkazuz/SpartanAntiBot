package br.alkazuz.antibot.bukkit.captcha;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;

import br.alkazuz.antibot.bukkit.main.SpartanAntiBot;

public class CaptchaListener implements Listener{
	
	@SuppressWarnings("deprecation")
	public void checkPlayer(Player p){
        PacketContainer pkt = new PacketContainer(PacketType.Play.Server.POSITION);
        
        pkt.getIntegers().write(0, p.getEntityId());
        
        pkt.getBooleans().write(0, !p.isOnGround());
        try {
        	SpartanAntiBot.theInstance().protocolMgr.sendServerPacket(p, pkt);
        } catch (InvocationTargetException ex) {
            throw new RuntimeException("§5§lCaptcha §8§l» §cNăo foi possível enviar o pacote.", ex);
        }
	}

	@EventHandler
	public void onClose(InventoryCloseEvent e){
		if(e.getInventory().getName().contains("Clique no(a):") && Captcha.time.containsKey(e.getPlayer().getName())){
			System.out.println(System.currentTimeMillis() - Captcha.time.get(e.getPlayer().getName()));
			if(System.currentTimeMillis() - Captcha.time.get(e.getPlayer().getName()) <= 200){
				new BukkitRunnable() {
					
					@Override
					public void run() {
						((Player) e.getPlayer()).kickPlayer("§cBot");
						
					}
				};
			}
		}
	}
	
    @EventHandler
    public void sendmessage(final PlayerCommandPreprocessEvent e) {
        final Player p = e.getPlayer();
        if (Captcha.request.containsKey(p)) {
            if (e.getMessage().toLowerCase().startsWith("/login") || e.getMessage().toLowerCase().startsWith("/register")) {
                return;
            }
            p.getWorld().playSound(p.getLocation(), Sound.WOOD_CLICK, 10.0f, 10.0f);
            e.setCancelled(true);
            p.openInventory(Captcha.inve.get(p));
        }
    }
    
    @EventHandler
    public void sendmessage(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (Captcha.request.containsKey(p)) {
        	p.getWorld().playSound(p.getLocation(), Sound.WOOD_CLICK, 10.0f, 10.0f);
            e.setCancelled(true);
            p.openInventory((Inventory)Captcha.inve.get(p));
        }
    }
    
    @EventHandler
    public void sendmessage(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        if (Captcha.request.containsKey(p)) {
        	p.getWorld().playSound(p.getLocation(), Sound.WOOD_CLICK, 10.0f, 10.0f);
            e.setCancelled(true);
            p.openInventory((Inventory)Captcha.inve.get(p));
        }
    }
    
    @EventHandler
    public void onClick(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        if (Captcha.request.containsKey(p) && e.getInventory() != null && e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR && e.getInventory().getTitle().toLowerCase().contains("clique no(a): ")) {
            e.setCancelled(true);
            ItemStack item = e.getCurrentItem();
            String itemName = ChatColor.stripColor(e.getInventory().getTitle()).split(": ")[1].trim();
            if(Captcha.getItemName(item).equalsIgnoreCase(itemName)){
                    p.sendMessage("§5§lCaptcha §8§l» §aVocê desbloqueou sua conta acertando o sistema de segurança, BOM JOGO.");
                    p.getWorld().playSound(p.getLocation(), Sound.LEVEL_UP, 10.0f, 10.0f);
                    Captcha.clearPlayer(p);
                    p.closeInventory();
                    return;
            }
            p.kickPlayer("§5§l§5§lAnti-Bot\n\n\n§cClique no item correto para desbloquear sua \nconta e verificar-mos que você não é um BOT!");
        }
    }
	
}
