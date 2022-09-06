package br.alkazuz.antibot.bukkit.captcha;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

public class Captcha
{
	
	public static HashMap<String, Long> time = new HashMap<String, Long>();
	
	public Captcha(Plugin plugin){
		cache.put("Grama", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ5YzYzYmM1MDg3MjMzMjhhMTllNTk3ZjQwODYyZDI3YWQ1YzFkNTQ1NjYzYWMyNDQ2NjU4MmY1NjhkOSJ9fX0="));
        
		cache.put("Pedra", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDEyNjk1ODRmNjI5MjI3NzEzMTA3YjRlMGEwMmRkNjVkZGZlNzgwZTdjNzExOGNiMWVjMjI3NWM1MTRjYzk1ZCJ9fX0="));
		
		cache.put("Bloco Diamante", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWUwNTc5OTZjYmE0NzMyOGRmNzJmYmEzZWEyYjlhYTM1YzhhODIyN2YxY2VjODljMTg4NGRjYWRhYTgyNGQ4NSJ9fX0="));
		
		cache.put("Tronco", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODRmNjAwZWFmYzhkODE0NjE1ZDFlZmFkMTYyODcyM2YxMDZhNjhjM2I3MWMyYTcwOWY4NTdmZTI3NDE2YWUxZSJ9fX0="));
		
		cache.put("Madeira", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTljMDRmZGZkMjE3YzE0NmJmOGJlMjQzNjA1OGU1ZDY3MTZkY2IzNjhlYTc1Y2VjMjRmZTI0NGFkYTM3YWNjNiJ9fX0="));
		
		cache.put("Bloco Ferro", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjU2MjljMWM3N2FlYTJiMGNlYmNmMzMzNjU1ZTY4ZGIxMzRmNDg0MWMwOGQ5ZTg3NWMzMDc0YWMzMGUyYTZkZSJ9fX0="));

		cache.put("Bloco Lazuli", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmU2ZmZhZGEzMjA4Njg5Mzc1MTM3ODRlOGY3OTc5YzczOWM3MjgzMzkyZTUyYmNkMTc4Yjg0OGY0NDk1NjA3YSJ9fX0="));

		
		cache.put("Esponja", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTRlYzU3NDQwZDYyZTgyMDgwOTg0N2NmYTE5ZDQyNzMwY2RlOTIyZDIyYTM3OWNkMWRhZjRlOWJhOTkwNjY1OCJ9fX0="));

		
		cache.put("Neve", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWRmZDc3MjRjNjlhMDI0ZGNmYzYwYjE2ZTAwMzM0YWI1NzM4ZjRhOTJiYWZiOGZiYzc2Y2YxNTMyMmVhMDI5MyJ9fX0="));

		
		cache.put("Gelo", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmE4M2QxYWFjMDY2ZGZkN2Q2NjhhZjFmNmIyZTUyMDE1MmFlYmI2MjQ1MjRhMGY5OWQ1MDkzNWJhM2Q0ZTIyYSJ9fX0="));

		
		cache.put("Obsidian", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGU3NjBiYmMxMTNjMjczZmFjNDA4OTZmYTIwODlhNTZjYzc0NmE3OWE3YTgyNzVmNjM4NTdlNjllNmY3NzAzYSJ9fX0="));

		
		cache.put("Areia", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTMzOThhYjNjYjY5NmIzNDQzMGJlOTQ0YjE0YWZiZDIyN2ZkODdlOTkwMjZiY2ZjOGI3Mzg3YTg2MWJkZSJ9fX0="));

		
		cache.put("Lava", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTY3OTYzY2FiNjU3ZDI1NDlkZWE1MzRiZTIzODQ5OWQ2NGQ2MTY1YTU5MmNhMzYxNTEyNjg3YTk2ZWQ5NjAifX19"));
		
		cache.put("Bloco Azul", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjA5MmQ5OWQ5NGQwOWNjOWQ1NTdlMjU3ZWQ0MGY4YTk2YzIxNzllZTQ5NzExZDIzZjgwYzE4YTM2N2FhZWMifX19"));

		
		cache.put("Bloco Verde", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTA1NGQ0MTY0ZWE4YmJhMDI4MzZiZDUxM2M0MjBkMDRkZDkxYjlmZGJiM2RhMTdlNjlmOWJmODlmZmQ2OTUifX19"));

		
		cache.put("Bloco Vermelho", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmEyNGEyYjZiNGI1YTkyZDdhODJhMzczZmU1ZjZiYjY2ODcyZWFkNjZjMTI2ZjgyZTg4NjQxNzNjZDc4M2EifX19"));


		cache.put("Bloco Branco", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U2ZmVkMTkxYTM3ODUxYmE1ZTU1MzU3MjFiYTUyYTFiMjY2ZDJlMTRiNjQwZGM2MmU0ZjJjZDlhNTA5ZCJ9fX0="));

		
		cache.put("Bloco Amarelo", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzdhODIxYTRmOTkzZTU2M2IyOGFhYTFiMDliODhlMmIyN2NlNWQzMGQxNTY1OWNjY2Q3Zjk2MzU1MWI1NzUifX19"));

		
		cache.put("Bloco Slime", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTBlNjVlNmU1MTEzYTUxODdkYWQ0NmRmYWQzZDNiZjg1ZThlZjgwN2Y4MmFhYzIyOGE1OWM0YTk1ZDZmNmEifX19"));

        
		cache.put("Globo", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDM0NjdhNTMxOTc4ZDBiOGZkMjRmNTYyODVjNzI3MzRkODRmNWVjODhlMGI0N2M0OTMyMzM2Mjk3OWIzMjNhZiJ9fX0="));

		cache.put("Dado", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzZkNGEwMWRiNjEyNjYwMWRlZDE0MDZjZjYyMzhjZTJiNzAyNGVhY2U1ZWE2MDRmYmMyMDhhMmFmMjljOTdhZCJ9fX0="));


		cache.put("Poke Bola", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTNlNjg3NjhmNGZhYjgxYzk0ZGY3MzVlMjA1YzNiNDVlYzQ1YTY3YjU1OGYzODg0NDc5YTYyZGQzZjRiZGJmOCJ9fX0="));


		cache.put("Bloco Comando", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTA0ZmUzNzdjOWY1NTM0MDU1NzhjNGVjMDc2NmM2ZTM1NDRjODA0NzU1YmE2YjM0ZTIxNmM4YmNhZmRhM2NiNiJ9fX0="));


		cache.put("Fornalha", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTRmZjFmODMzNzMxZGZkMzA0MWYyM2U4ZmUyYTkwZGE2YzExMmJmYWVkN2E2YzVkMmNlODA2ZGY2NDFmNDM0YiJ9fX0="));


		cache.put("Pistão Grudento", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzNmYmVjZjNhMmZlMTQ4MGUyOWQ2NGIwNDZkYzAyNjMyODU2NjZlYjA1NzE5NDM0NDYzZDBmYjk2NjUxZDYzZiJ9fX0="));

		cache.put("Ovo Dragão", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTBjYmI4YmQzMmE5Zjg3MjAyYjk3MWY2MzZiNzAyZjk4ZDUxOThjOGM0ZWRmZGZmYmZlY2JhODlmN2E1OTE1NSJ9fX0="));


		cache.put("Dinamite", ItemUtils.createHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTIyNTk4NWViNjU1Njc3MTA3NzIyZjhlYjkxZjNjYmIyODdmNGM4NzRiZjlhNjlmMzYyYjRkOGMzYjJjMWM0ZCJ9fX0="));
		
        Bukkit.getPluginManager().registerEvents(new CaptchaListener(), plugin);
        
	}
	
    public static HashMap<Player, Inventory> inve = new HashMap<Player, Inventory>();
    public static HashMap<Player, String> request = new HashMap<Player, String>();
    public static HashMap<String, ItemStack> cache = new HashMap<String, ItemStack>();
    public static Random rand = new Random();

    public static void clearPlayer(final Player p) {
        if (request.containsKey(p)) {
            request.remove(p);
            inve.remove(p);
        }
    }
    

    public static String getFromIndex(int index){
    	List<String> keys = new ArrayList<String>(cache.keySet());
    	return keys.get(index);
    }
    
    public static ItemStack getRandomItem(boolean bol) {
        final int i = rand.nextInt(cache.size());
        ItemStack item = cache.get(getFromIndex(i));
        return item;
    }
    
    public static ItemStack getRandomItem() {
        final int i = rand.nextInt(cache.size());
        return cache.get(getFromIndex(i));
    }
    
    public static int random(final int min, final int max) {
        return rand.nextInt(max - min) + min;
    }
    
    public static String getItemName(final ItemStack i) {
        for (final String d : cache.keySet()) {
        	ItemStack head = cache.get(d);
        	SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        	SkullMeta headMeta2 = (SkullMeta) i.getItemMeta();
        	Field profileField = null;
        	Field profileField2 = null;
            try
            {
                profileField = headMeta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                
                profileField2 = headMeta2.getClass().getDeclaredField("profile");
                profileField2.setAccessible(true);
                if(profileField.get(headMeta) != null && profileField.get(headMeta).equals(profileField2.get(headMeta2))){
                	return d;
                }
            }
            catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public static void createCaptcha(final Player p) {
        ItemStack item = getRandomItem(true);
        while(item == null){
        	item = getRandomItem(true);
        }
        while(getItemName(item) == null){
        	item = getRandomItem(true);
        }
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 27, "Clique no(a): §0" + getItemName(item));
        int[] sts = new int[]{10,11,12,13,14,15,16};
        for (int i : sts) {
            ItemStack itemd = getRandomItem();
            while(itemd == null || getItemName(itemd).equalsIgnoreCase(getItemName(item))){
            	itemd = getRandomItem();
            }
            inv.setItem(i, itemd);
        }
        final int d = sts[rand.nextInt(sts.length)];
        inv.setItem(d, item);
        p.openInventory(inv);
        request.put(p, getItemName(item));
        inve.put(p, inv);
        
    }
}
