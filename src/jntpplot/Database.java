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
    private String columnName;
    private String primaryKey;
    private Byte primaryKeyPosition;
    
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
    
    public void setColumnName (String name) {
        columnName = name;
    }
    
    public String getColumnName () {
        return columnName;
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
            logger.debug("SQL: " + sql);
            stmt.executeUpdate(sql);
            stmt.close();
            //dbConnection.close();
        } catch ( Exception e ) {
            logger.error( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        logger.trace("Table created successfully");
        return true;
    }
    
    public Boolean insertStat() throws SQLException {
        String rowValues = "(";
        for (String stat : statMessage) {
            rowValues += "\"" + stat + "\"" + ", ";
        }

        rowValues = rowValues.substring(0, rowValues.length() - 2) + ")";
        logger.debug("rowValues = " + rowValues);

        try {
            Statement insertStmt = dbConnection.createStatement();

            String sql = "INSERT INTO " + tableName + " VALUES " + rowValues;
            logger.debug("sql: " + sql);
            insertStmt.executeUpdate(sql);
            insertStmt.close();
            //dbConnection.close();
            logger.trace("Record created successfully");
            return true;
        } catch (SQLException insertStatException) {
            // Alert on missing table
            if ( insertStatException.getMessage().contains("(no such table: " + tableName + ")") ) {
                logger.error("No such table: " + tableName);
            // Unique primary key duplicate
            } else if ( insertStatException.getMessage().contains("UNIQUE constraint failed: " + tableName) ) {
                logger.trace("Unique key already present, discarding as duplicate.");
            } else {
                throw insertStatException;
            }
        }
        return false;
    }
   
    
    public ArrayList<String> selectStat() throws SQLException {
        ArrayList<String> stat = new ArrayList<>();
        Statement stmt;    
        try {
            String nextData;
            String sql = "SELECT " + columnName + " FROM " + tableName;
            stmt = dbConnection.createStatement();
            ResultSet queryResult = stmt.executeQuery(sql);
            while (queryResult.next()) {
                nextData = queryResult.getString(columnName);
                logger.trace("Next result: " + nextData);
                stat.add(nextData);
            }
            logger.trace("Data queried successfully, result set: " + stat);
        } catch ( Exception e ) {
            logger.error( e.getClass().getName() + ": " + e.getMessage() );       
        }
        return stat;
    }

    /*  It's debatable whether the deDupStats method is needed, since a relational database
        will filter out duplicates on primary key
    */
    /*
    public ArrayList<ArrayList<String>> deDupStats (ArrayList<ArrayList<String>> stats) {
        try {
            DatabaseMetaData metaData = dbConnection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, tableName, primaryKey);
            primaryKeyPosition = Byte.valueOf(columns.getString(17));
            logger.trace("Found primary key titled <" + primaryKey + "> at position " + primaryKeyPosition);
            String sql = "SELECT " + primaryKey + " FROM " + tableName;
            Statement stmt = dbConnection.createStatement();
            ResultSet presentStats = stmt.executeQuery(sql);
            while (presentStats.next()) {
                String presentStat = presentStats.getString(0);
                stats.remove(presentStat);
            }
            
        } catch (SQLException e) {
            logger.error( e.getClass().getName() + ": " + e.getMessage());
        }
        return stats;
    }
    */
    
}