package br.alkazuz.antibot.bukkit.database;

import java.sql.*;

public abstract class Database
{
    protected Connection connection;
    
    protected Database() {
        this.connection = null;
    }
    
    public abstract Connection openConnection() throws SQLException, ClassNotFoundException;
    
    public boolean isConnected() throws SQLException {
        return this.connection != null && !this.connection.isClosed();
    }
    
    public Connection getConnection() {
        return this.connection;
    }
    
    public boolean closeConnection() throws SQLException {
        if (this.connection == null) {
            return false;
        }
        this.connection.close();
        return true;
    }
    
    public ResultSet Query(final String query) throws SQLException, ClassNotFoundException {
        if (!this.isConnected()) {
            this.openConnection();
        }
        final Statement statement = this.connection.createStatement();
        final ResultSet result = statement.executeQuery(query);
        return result;
    }
    
    public int Update(final String query) throws SQLException, ClassNotFoundException {
        if (!this.isConnected()) {
            this.openConnection();
        }
        final Statement statement = this.connection.createStatement();
        final int result = statement.executeUpdate(query);
        return result;
    }
}
