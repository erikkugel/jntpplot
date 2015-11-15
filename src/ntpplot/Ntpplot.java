/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ntpplot;

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
public class Ntpplot {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        // TODO code application logic here

        // http://stackoverflow.com/questions/4008223/print-in-new-line-java
        System.out.println("Hello ntpplot!");
    }
}

class Stats {
    
    private String statsFileName;
    
    public void setFileName (String fileName) {
        statsFileName = fileName;
    }
    
    public String getFileName () {
        return statsFileName;
    }
    
    public ArrayList<ArrayList<String>> getInjestFile() throws IOException, FileNotFoundException, ClassNotFoundException {
        return injestFile(statsFileName);
    }
    
    public Boolean setInsertFile() throws IOException, FileNotFoundException, ClassNotFoundException {
        return insertFile(statsFileName);
    }
    
    private ArrayList<ArrayList<String>> injestFile(String statsFileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        System.out.println("Injesting file: " + statsFileName);
        
        // http://stackoverflow.com/questions/5868369/how-to-read-a-large-text-file-line-by-line-using-java
        BufferedReader in;
        in = new BufferedReader(new FileReader(statsFileName));
        String line;
        ArrayList<String> message = new ArrayList<String>();
        ArrayList<ArrayList<String>> messages = new ArrayList<ArrayList<String>>();
        while ((line = in.readLine()) != null) {
            message = injestLine(line);
            messages.add(message);
        }
        return messages;
    } 

    private Boolean insertFile(String statsFileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        System.out.println("Injesting file: " + statsFileName);

        // http://stackoverflow.com/questions/5868369/how-to-read-a-large-text-file-line-by-line-using-java
        BufferedReader in;
        in = new BufferedReader(new FileReader(statsFileName));
        String line;
        ArrayList<String> message = new ArrayList<String>();
        ArrayList<ArrayList<String>> messages = new ArrayList<ArrayList<String>>();
        while ((line = in.readLine()) != null) {
            message = injestLine(line);
            insertStats(message);
        }
        return true;
    }

    private ArrayList<String> injestLine(String line) throws ClassNotFoundException {
        ArrayList<String> message = new ArrayList<String>();
        for(String stat : line.split(" ")) {
            message.add(stat);
        }
        return message;
    }
    
    private Boolean insertStats(ArrayList<String> message) throws ClassNotFoundException {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:product.db";
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                System.out.println("Connected to the database");
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
                conn.close();
                return true;
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return null;
    }
}
