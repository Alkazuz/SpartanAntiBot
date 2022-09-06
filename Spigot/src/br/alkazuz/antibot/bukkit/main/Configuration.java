package br.alkazuz.antibot.bukkit.main;

import java.util.ArrayList;
import java.util.List;

import br.alkazuz.antibot.bukkit.config.Config;

public class Configuration {

	// WHITELIST
	public static boolean WHITELIST_ATIVADO = false;
	public static boolean WHITELIST_AUTOADD = false;
	public static int WHITELIST_TEMPO = 60;
	
	// HEURISTICA
	public static boolean HEURISTICA_ATIVADO = false;
	public static int HEURISTICA_TEMPO = 3;
	
	// CAPTCHA
	public static boolean CAPTCHA_ATIVADO = false;
	public static int CAPTCHA_TEMPO = 10;
	
	// OUTROS
	public static List<String> protections = new ArrayList<String>();
	
	public static void reload() {
		WHITELIST_ATIVADO = Config.getConfig().getBoolean("Whitelist.Ativado");
		WHITELIST_AUTOADD = Config.getConfig().getBoolean("Whitelist.AutoAdicionar.Ativado");
		WHITELIST_TEMPO = Config.getConfig().getInt("Whitelist.AutoAdicionar.Tempo");
		
		HEURISTICA_ATIVADO = Config.getConfig().getBoolean("Heuristica.Ativado");
		HEURISTICA_TEMPO = Config.getConfig().getInt("Heuristica.TempoParaReber");
		
		CAPTCHA_ATIVADO = Config.getConfig().getBoolean("Captcha.Ativado");
		CAPTCHA_TEMPO = Config.getConfig().getInt("Captcha.TempoParaKick");

		protections = Config.getConfig().getStringList("Protecao");
	}
	
}
