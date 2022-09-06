package br.alkazuz.antibot.utils.database.mysql;

import java.sql.*;

import br.alkazuz.antibot.bukkit.database.*;

public class MySQL extends Database
{
    private final String hostname;
    private final String port;
    private final String database;
    private final String username;
    private final String password;
    
    public MySQL(final String hostname, final String port, final String database, final String username, final String password) {
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }
    
    @Override
    public Connection openConnection() throws SQLException, ClassNotFoundException {
        if (this.isConnected()) {
            return this.connection;
        }
        String connectionURL = "jdbc:mysql://" + this.hostname + ":" + this.port;
        if (this.database != null) {
            connectionURL = connectionURL + "/" + this.database;
        }
        Class.forName("com.mysql.jdbc.Driver");
        return this.connection = DriverManager.getConnection(connectionURL, this.username, this.password);
    }
}
