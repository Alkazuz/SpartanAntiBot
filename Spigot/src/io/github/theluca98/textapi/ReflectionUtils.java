package io.github.theluca98.textapi;

import org.bukkit.*;
import org.bukkit.entity.*;
import java.lang.reflect.*;

public class ReflectionUtils
{
    public static Class<?> getNMSClass(final String name) {
        final String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("net.minecraft.server." + version + "." + name);
        }
        catch (ClassNotFoundException | ArrayIndexOutOfBoundsException ex2) {
            return null;
        }
    }
    
    public static Class<?> getOBClass(final String name) {
        final String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("org.bukkit.craftbukkit." + version + "." + name);
        }
        catch (ClassNotFoundException | ArrayIndexOutOfBoundsException ex2) {
            return null;
        }
    }
    
    public static void sendPacket(final Player player, final Object packet) {
        try {
            final Object entityPlayer = player.getClass().getMethod("getHandle", (Class<?>[])new Class[0]).invoke(player, new Object[0]);
            final Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        }
        catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException ex2) {
            ex2.printStackTrace();
        }
    }
}
