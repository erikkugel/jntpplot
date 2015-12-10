/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jntpplot;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ernest
 */
public class Database {
    
    private String dbName;
    private String tableName;
    private String tableColumns;
    private Connection dbConnection;
    private ArrayList<String> statMessage;
    
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
    
    public void setStatMessage (ArrayList<String> message) {
        statMessage = message;
    }
    
    public void setDbConnection (Connection conn) {
        dbConnection = conn;
    }
    
    public Connection getDbConnection () {
        return dbConnection;
    }
    
    // http://www.tutorialspoint.com/sqlite/sqlite_java.htm
    public Connection openDb() {
    try {
        Class.forName("org.sqlite.JDBC");
        dbConnection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
    } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
    }
        System.out.println("Opened database successfully");
        return dbConnection;
    }
    
    public boolean crateTable() {
        Statement stmt = null;    
        try {
            stmt = dbConnection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " " + tableColumns;
            stmt.executeUpdate(sql);
            stmt.close();
            dbConnection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            //System.exit(0);
            return false;
        }
        System.out.println("Table created successfully");
        return true;
    }
    
    public boolean insertStat() throws SQLException {
        Statement stmt = null;
        String rowValues = "(";
        int statIndex = 0;
        for (String stat : statMessage) {
            if (stat.indexOf('.') >= 0) {
                rowValues += "\"" + Float.parseFloat(stat) + "\"" + ", ";
            } else {
                rowValues += "\"" + Integer.parseInt(stat) + "\"" + ", ";
            }
        }
        rowValues = rowValues.substring(0, rowValues.length() - 2) + ")";
        tableColumns = tableColumns.replace(" INT", "");
        tableColumns = tableColumns.replace(" REAL", "");
        //rowValues = rowValues.replace(",",")");
        System.out.println ("rowValues = " + rowValues);
        try {
            stmt = dbConnection.createStatement();
               
            String sql = "INSERT INTO " + tableName + " " + tableColumns + " VALUES " + rowValues + ";";
            System.out.println("sql: " + sql);
            stmt.executeUpdate(sql);

            stmt.close();
            dbConnection.commit();
            dbConnection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            //System.exit(0);
            return false;
        }
        System.out.println("Records created successfully");
        return true;
    }
    
}