/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jntpplot;

import java.sql.*;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    
    private static final Logger logger = LogManager.getLogger(Jntpplot.class);
    
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
    } catch ( ClassNotFoundException | SQLException e ) {
        logger.error( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
    }
       logger.trace("Opened database successfully");
        return dbConnection;
    }
    
    public boolean crateTable() {
        Statement stmt;    
        try {
            stmt = dbConnection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " " + tableColumns;
            stmt.executeUpdate(sql);
            stmt.close();
            dbConnection.close();
        } catch ( Exception e ) {
            logger.error( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        logger.trace("Table created successfully");
        return true;
    }
    
    public Boolean insertStat() throws SQLException {

        Statement checkStmt = dbConnection.createStatement();
        ResultSet checkResult = checkStmt.executeQuery( "SELECT date,time FROM " + tableName + " WHERE date = " + statMessage.get(0) + " AND time = " + statMessage.get(1) );
        
        if ( !checkResult.next() ) {
            String rowValues = "(";
            for (String stat : statMessage) {
                rowValues += "\"" + stat + "\"" + ", ";
            }

            rowValues = rowValues.substring(0, rowValues.length() - 2) + ")";
            logger.debug("rowValues = " + rowValues);

            Statement insertStmt = dbConnection.createStatement();

            String sql = "INSERT INTO " + tableName + " VALUES " + rowValues;
            logger.debug("sql: " + sql);
            insertStmt.executeUpdate(sql);

            insertStmt.close();
            dbConnection.close();
            logger.trace("Record created successfully");
            return true;
        } else {
            logger.trace("Record alredy present");
            return false;
        }
    }
    
}