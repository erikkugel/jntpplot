/*
 *         DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *                 Version 2, December 2004
 * 
 * Copyright (C) 2004 Sam Hocevar <sam@hocevar.net>
 * 
 * Everyone is permitted to copy and distribute verbatim or modified 
 * copies of this license document, and changing it is allowed as long
 * as the name is changed.
 * 
 *             DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *     TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 * 
 *     0. You just DO WHAT THE FUCK YOU WANT TO.
 */
package org.no_ip.xeps.jntpplot;

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
public abstract class Input {
    
    String fileName;
    String dbName;
    String tableName;
    String tableColumns;
    ArrayList<ArrayList<String>> stats;
    
    private static final Logger logger = LogManager.getLogger(Jntpplot.class);

    public void setFileName(String name) {
        fileName = name;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public void setDbName(String name) {
        dbName = name;
    } 
    
    public String getDbName() {
        return dbName;
    }

    public void setTableName(String name) {
        tableName = name;
    }
    
    public String getTableName() {
        return tableName;
    }
    
    public void setTableColumns(String columns) {
        tableColumns = columns;
    }
    
    public String getTableColumns() {
        return tableColumns;
    }
    
    private void readStats() throws IOException, FileNotFoundException, ClassNotFoundException {
        StatsFile statsFile = new StatsFile();        
        statsFile.setFileName(fileName);
        stats = statsFile.injestFile();
    }
    
    abstract ArrayList<ArrayList<String>> mutateStats ();
    
    abstract boolean setupDatabase() throws SQLException;
    
    private int insertStats() throws SQLException {
        Database statsDb = new Database();
        statsDb.setDbName(dbName);
        statsDb.setTableName(tableName);
        
        // Make sure the table is in place and create it otherwise
        Connection conn = statsDb.openDb();
        statsDb.setDbConnection(conn);

        // Ingest
        int statsCount = 0;
        int insertCount = 0;
        for (ArrayList <String> stat : stats) {
            statsDb.setStatMessage(stat);
            statsCount ++;
            if ( statsDb.insertStat() ) {
                insertCount ++;
            }
        }
        logger.trace("Ingested " + insertCount + " out of " + statsCount + " stats.");
        return insertCount;    
    }
    
    public Integer ingestFileIntoDatabase() throws IOException, ClassNotFoundException, SQLException {
        logger.trace("Ingestor ingestFileIntoDatabase");
        setupDatabase();
        readStats();
        mutateStats();
        int intoDatabase = insertStats();
        return intoDatabase;
    }
    
}