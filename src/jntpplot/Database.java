/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jntpplot;

import java.sql.*;

/**
 *
 * @author ernest
 */
public class Database {
    
    private String dbName;
    private String tableName;
    private String tableColumns;
    private Connection dbConnection;
    
    public void setDbName (String name) {
        dbName = name;
    }
    
    public String getDbName () {
        return dbName;
    }
    
    public void setTableName (String name) {
        tableName = name;
    }
    
    public String getTableName () {
        return tableName;
    }
    
    public void setTableColumns (String columns) {
        tableColumns = columns;
    }
    
    public String getTableColumns () {
        return tableColumns;
    }
    
    public void setDbConnection (Connection conn) {
        dbConnection = conn;
    }
    
    public Connection getDbConnection () {
        return dbConnection;
    }
    
    // http://www.tutorialspoint.com/sqlite/sqlite_java.htm
    public Connection openDb() {
    Connection c = null;
    try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:" + dbName);
    } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
    }
        System.out.println("Opened database successfully");
        return c;
    }
    
    public boolean crateTable() {
        Statement stmt = null;
    
        try {
            stmt = dbConnection.createStatement();
            String sql = "CREATE TABLE " + tableName + " " + tableColumns;
            stmt.executeUpdate(sql);
            stmt.close();
            dbConnection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            return false;
        }
        System.out.println("Table created successfully");
        return true;
    }
    
}