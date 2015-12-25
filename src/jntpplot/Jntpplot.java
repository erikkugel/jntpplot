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
        Ingestor ingestThis = new Ingestor();
        System.out.println(ingestThis + ": " + ingestThis.ingestFileIntoDatabase() + " errors.");
    }
}