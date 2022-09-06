package br.alkazuz.antibot.utils;

import java.util.HashSet;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class Utils {
	
	public static int nproxy = 0;
	public static int botpsec = 0;
	public static int hpps = 0;
	public static boolean loaded = false;
	
	public static HashSet<String> proxies = new HashSet<String>();
	public static HashSet<String> whitelist = new HashSet<String>();
	
	public static void EnviarTitle(final Player player, final String title, final String subtitle, final int fadeIn, final int stay, final int fadeOut) {
        TitleAPI.sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
    }
    
    public static void EnviarActionbar(final Player p, final String msg) {
        final IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + msg + "\"}");
        final PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte)2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(ppoc);
    }
	
	
}
