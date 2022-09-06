package br.alkazuz.antibot.bukkit.protection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

import br.alkazuz.antibot.bukkit.main.SpartanAntiBot;
import br.alkazuz.antibot.bukkit.protection.protections.CountryProtection;
import br.alkazuz.antibot.utils.Utils;

public class ProtocolBlock extends PacketAdapter {

	private static HashMap<String, String> codes = new HashMap<String, String>();
    SpartanAntiBot m;
    public ProtocolBlock(SpartanAntiBot plugin, PacketType[] types) {
        super(plugin, ListenerPriority.HIGHEST, types);
        this.m = plugin;
    }

    private static List<BotBlock> blocks = Collections.synchronizedList(new ArrayList<BotBlock>(100));
    
    public static int getRecents(){
    	try {
    	int i = 0;
    	for(BotBlock b : blocks){
    		if(b.isRecent()){
    			i++;
    		}else{
    			blocks.remove(b);
    		}
    	}
    	return i;
    	}catch (Exception e) {
			return -1;
		}
    }
    
    @Override
    public void onPacketReceiving(final PacketEvent event) {
        String ip = event.getPlayer().getAddress().getHostString();
        if(!Utils.loaded){
        	event.setCancelled(true);
        	return;
        }
        Utils.hpps++;
        if(Utils.whitelist.contains(ip))return;
        
        if(Utils.proxies.contains(ip)) {
        	event.setCancelled(true);
        	Utils.nproxy++;
        	blocks.add(new BotBlock(ip, System.currentTimeMillis()));
        	return;
        }
        
        int r = getRecents();
        if(r >= 5 || r == -1){
        	blocks.add(new BotBlock(ip, System.currentTimeMillis()));
        	event.setCancelled(true);
        	Utils.nproxy++;
        	return;
        }
        if(Utils.hpps >= 10){
        	blocks.add(new BotBlock(ip, System.currentTimeMillis()));
        	event.setCancelled(true);
        	Utils.nproxy++;
        	return;
        }
        if(!CountryProtection.enabled)return;
        String code = "";
        if(codes.containsKey(ip)){
        	code = codes.get(ip);
        }else{
        	code = CountryProtection.cl.getCountry(ip).getCode();
        }
        if(!CountryProtection.list.contains(code)) {
        	codes.put(ip, code);
        	event.setCancelled(true);
        	blocks.add(new BotBlock(ip, System.currentTimeMillis()));
        }
    }

}