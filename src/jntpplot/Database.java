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
        
        try {
            Statement checkStmt = dbConnection.createStatement();
            ResultSet checkResult = checkStmt.executeQuery( "SELECT date,time FROM " + tableName + " WHERE date = " + statMessage.get(0) + " AND time = " + statMessage.get(1) );

            if ( !checkResult.next() ) {
                String rowValues = "(";
                for (String stat : statMessage) {
                    rowValues += "\"" + stat + "\"" + ", ";
                }
        
                rowValues = rowValues.substring(0, rowValues.length() - 2) + ")";
                System.out.println ("rowValues = " + rowValues);
                
                Statement insertStmt = dbConnection.createStatement();

                String sql = "INSERT INTO " + tableName + " VALUES " + rowValues;
                System.out.println("sql: " + sql);
                insertStmt.executeUpdate(sql);

                insertStmt.close();
                dbConnection.close();
                System.out.println("Record created successfully");
            } else {
                System.out.println("Record alredy present");
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        return true;
    }
    
}