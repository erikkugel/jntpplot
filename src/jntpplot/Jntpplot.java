/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jntpplot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ernest
 */
public class Jntpplot {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
        // TODO code application logic here

        // http://stackoverflow.com/questions/4008223/print-in-new-line-java
        System.out.println("Hello ntpplot!");
        
        String fileName = "test/jntpplot/sys";
        Stats statsFile = new Stats();
        statsFile.setFileName(fileName);
        ArrayList<ArrayList<String>> stats = statsFile.getInjestFile();
        
        
        for (ArrayList<String> message: stats) {
            Database statsDb = new Database();
            statsDb.setDbName("stats_test");
            statsDb.setTableName("sysstats");
            statsDb.setTableColumns("(date INT," +
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
                "rate_exceeded INT)");
            Connection conn = statsDb.openDb();
            System.out.println(message);
            statsDb.setStatMessage(message);
            statsDb.setDbConnection(conn);
            Boolean result = statsDb.insertStat();
            System.out.println(result);
        }
    }
}