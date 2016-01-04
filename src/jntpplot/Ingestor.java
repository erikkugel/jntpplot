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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author ernest
 */
public class Ingestor {
    
    protected String fileName;
    protected String dbName;
    protected String tableName;
    protected String tableColumns;
    
    private static final Logger logger = LogManager.getLogger(Jntpplot.class);

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
        logger.trace("Ingestor mutateStats");
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
                    logger.error("No such table: " + tableName);
                    System.exit(1);
                    // Trow everything else
                } else {
                    throw insertStatException;
                }
            }
        }
        logger.debug("Duplicates: " + duplicates);
        return errors;    
    }
    
    public Integer ingestFileIntoDatabase() throws IOException, ClassNotFoundException, SQLException {
        logger.trace("Ingestor ingestFileIntoDatabase");
        
        int intoDatabaseErrors = intoDatabase(mutateStats(ingestFile(fileName)));
        if ( intoDatabaseErrors > 0 ) {
            logger.error(intoDatabaseErrors + " errors during insertion");
        }
        return intoDatabaseErrors;
    }
    
}