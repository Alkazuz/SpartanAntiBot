package br.alkazuz.antibot.bukkit.database;

import java.sql.*;
import org.bukkit.configuration.file.*;

import br.alkazuz.antibot.bukkit.config.Config;
import br.alkazuz.antibot.bukkit.main.SpartanAntiBot;
import br.alkazuz.antibot.utils.database.SQLFunctions;
import br.alkazuz.antibot.utils.database.mysql.*;
import br.alkazuz.antibot.utils.database.sqlite.*;

import java.io.*;

public class SQLManager
{
    private static MySQL MySQL;
    private static SQLite SQLite;
    public static Connection connection;
    
    public static void SQLManagerConnection() {
        if (getConfig().getBoolean("MySQL.enable")) {
            MySQLConnection();
        }
        else {
            SQLiteConnection();
        }
        
    }
    
    public static void createTables(final String code) {
        try {
            final Statement statement = SQLManager.connection.createStatement();
            statement.executeUpdate(code);
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void MySQLConnection() {
        try {
        	SpartanAntiBot.theInstance().sendConcoleMessage("Tentando conectar ao MySQL...");
            SQLManager.connection = SQLManager.MySQL.openConnection();
            SpartanAntiBot.theInstance().sendConcoleMessage("Conectado ao MySQL");
            SQLFunctions.loadDataBase();
        }
        catch (ClassNotFoundException | SQLException ex2) {
        	SpartanAntiBot.theInstance().sendConcoleMessage("§cFalhar ao conectar no MySQL");
        	SpartanAntiBot.theInstance().sendConcoleMessage("§cO servidor usuará SQLLite devido à falha na conexão");
        	SQLiteConnection();
        }
    }
    
    private static void SQLiteConnection() {
        try {
        	SpartanAntiBot.theInstance().sendConcoleMessage("Configurando SQLLite...");
            SQLManager.connection = SQLManager.SQLite.openConnection();
            SpartanAntiBot.theInstance().sendConcoleMessage("Conectado ao SQLLite");
            SQLFunctions.loadDataBase();
        }
        catch (ClassNotFoundException | SQLException ex2) {
            ex2.printStackTrace();
        }
    }
    
    private static FileConfiguration getConfig() {
        return Config.getConfig();
    }
    
    static {
        SQLManager.MySQL = new MySQL(getConfig().getString("MySQL.host"), getConfig().getString("MySQL.port"), getConfig().getString("MySQL.database"), getConfig().getString("MySQL.user"), getConfig().getString("MySQL.password"));
        SQLManager.SQLite = new SQLite(new File(SpartanAntiBot.theInstance().getDataFolder(), "database.db"));
        SQLManager.connection = null;
    }
}
