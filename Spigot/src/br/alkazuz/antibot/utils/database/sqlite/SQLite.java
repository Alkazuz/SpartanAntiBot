package br.alkazuz.antibot.utils.database.sqlite;

import java.io.*;
import java.sql.*;

import br.alkazuz.antibot.bukkit.database.*;

public class SQLite extends Database
{
    private final File file;
    
    public SQLite(final File file) {
        this.file = file;
    }
    
    @Override
    public Connection openConnection() throws SQLException, ClassNotFoundException {
        if (this.isConnected()) {
            return this.connection;
        }
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            }
            catch (IOException e) {
                System.out.println("Unable to create database!");
            }
        }
        Class.forName("org.sqlite.JDBC");
        return this.connection = DriverManager.getConnection("jdbc:sqlite:" + this.file);
    }
}
