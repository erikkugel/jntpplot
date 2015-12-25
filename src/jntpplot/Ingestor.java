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
    
    private String fileName = "test/jntpplot/sys";
    private String dbName = "test/jntpplot/stats_db";
    private String tableName = "sysstats";
    private String tableColumns = "(date INT," +
            "time REAL," +
            "time_since_restart INT," +
            "packets_recieved INT," +
            "packats_processed INT," +
            "current_version INT," +
            "previous_version INT," +
            "bad_version INT," +
            "access_denied INT," +
            "bad_length_or_format INT," +
            "bad_authentication INT," +
            "rate_exceeded INT)";

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
        tableColumns = name;
    }
    
    public String getTableName () {
        return tableColumns;
    }
    
    public void setTableColumns (String columns) {
        tableColumns = columns;
    }
    
    public String getTableColumns () {
        return tableColumns;
    }
    
    public Integer ingestFileIntoDatabase () throws SQLException, IOException, FileNotFoundException, ClassNotFoundException {

        File statsFile = new File();
        
        statsFile.setFileName(fileName);
        ArrayList<ArrayList<String>> stats = statsFile.getInjestFile();      

        int errors = 0;
        for (ArrayList<String> message: stats) {
            Database statsDb = new Database();
            statsDb.setDbName(dbName);
            statsDb.setTableName(tableName);
            statsDb.setTableColumns(tableColumns);
            Connection conn = statsDb.openDb();
            System.out.println(message);
            statsDb.setStatMessage(message);
            statsDb.setDbConnection(conn);
            
            if ( ! statsDb.insertStat() ) {
                errors++;
            }
        }
        return errors;
    }
}