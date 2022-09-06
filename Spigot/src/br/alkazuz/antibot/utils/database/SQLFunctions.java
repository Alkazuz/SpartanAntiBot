package br.alkazuz.antibot.utils.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.alkazuz.antibot.bukkit.database.SQLManager;
import br.alkazuz.antibot.utils.Utils;

public class SQLFunctions {

	public static void delFromWhitelist(String ip) {
		try {
            	
                final PreparedStatement ps = SQLManager.connection.prepareStatement("Delete * FROM whitelist WHERE `IP`= '"+ip+"';");
                ps.executeUpdate();
                ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public static void delFromProxies(String ip) {
		try {
            	
                final PreparedStatement ps = SQLManager.connection.prepareStatement("Delete * FROM proxies WHERE `IP`= '"+ip+"';");
                ps.executeUpdate();
                ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public static void insertToProxies(String ip) {
		try {
            	
                final PreparedStatement ps = SQLManager.connection.prepareStatement("INSERT INTO proxies (IP) VALUES (?);");
                ps.setString(1, ip);
                ps.executeUpdate();
                ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public static void insertToWhitelist(String ip) {
		try {
            	
                final PreparedStatement ps = SQLManager.connection.prepareStatement("INSERT INTO whitelist (IP) VALUES (?);");
                ps.setString(1, ip);
                ps.executeUpdate();
                ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public static void loadDataBase() {
        SQLManager.createTables("CREATE TABLE IF NOT EXISTS proxies(IP VARCHAR(20) NOT NULL);");
        try {
            final Statement statement = SQLManager.connection.createStatement();
            final ResultSet res = statement.executeQuery("SELECT * FROM proxies");
            while (res.next())
            {
                final String pub = res.getString("IP");
                
                res.close();
                Utils.proxies.add(pub);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        SQLManager.createTables("CREATE TABLE IF NOT EXISTS whitelist(IP VARCHAR(20) NOT NULL);");
        try {
            final Statement statement = SQLManager.connection.createStatement();
            final ResultSet res = statement.executeQuery("SELECT * FROM whitelist");
            while (res.next())
            {
                final String pub = res.getString("IP");
                
                res.close();
                Utils.whitelist.add(pub);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
