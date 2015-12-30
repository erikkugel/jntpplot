/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jntpplot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ernest
 */
public class Ingestor {
    
    protected String fileName;
    protected String dbName;
    protected String tableName;
    protected String tableColumns;

    public void setFileName (String name) {
        fileName = name;
    }
    
    public String getFileName () {
        return fileName;
    }
    
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
    
    private ArrayList<ArrayList<String>> ingestFile (String fileName) throws IOException, FileNotFoundException, ClassNotFoundException {
        StatsFile statsFile = new StatsFile();
        
        statsFile.setFileName(fileName);
        return statsFile.injestFile();
    }
    
    public ArrayList<ArrayList<String>> mutateStats (ArrayList<ArrayList<String>> stats) {
        // Override me to mutate some stats!
        System.out.println("Ingestor mutateStats");
        return stats;
    }
    
    private int intoDatabase (ArrayList<ArrayList<String>> stats) throws SQLException {
        int errors = 0;
        int duplicates = 0;
        Database statsDb = new Database();
        statsDb.setDbName(dbName);
        statsDb.setTableName(tableName);
        statsDb.setTableColumns(tableColumns);
        
        // Make sure the table is in place and create it otherwise
        Connection initConn = statsDb.openDb();
        statsDb.setDbConnection(initConn);
        statsDb.crateTable();

        // Ingest
        for (ArrayList <String> stat : stats) {
            try {
                Connection conn = statsDb.openDb();
                statsDb.setDbConnection(conn);
                statsDb.setStatMessage(stat);
                
                if ( ! statsDb.insertStat() ) {
                    duplicates ++;
                }
            } catch (SQLException insertStatException) {
                errors ++;
                // Alert on missing table
                if ( insertStatException.getMessage().contains("(no such table: " + tableName + ")") ) {
                    System.err.println("No such table: " + tableName);
                    System.exit(1);
                    // Trow everything else
                } else {
                    throw insertStatException;
                }
            }
        }
        System.out.println("Duplicates: " + duplicates);
        return errors;    
    }
    
    public Integer ingestFileIntoDatabase() throws IOException, ClassNotFoundException, SQLException {
        System.out.println("Ingestor ingestFileIntoDatabase");
        
        int intoDatabaseErrors = intoDatabase(mutateStats(ingestFile(fileName)));
        if ( intoDatabaseErrors > 0 ) {
            System.err.println(intoDatabaseErrors + " errors during insertion");
        }
        return intoDatabaseErrors;
    }
    
}