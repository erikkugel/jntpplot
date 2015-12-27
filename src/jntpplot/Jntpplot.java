/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jntpplot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author ernest
 */
public class Jntpplot {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, IOException, FileNotFoundException, ClassNotFoundException {
        // TODO code application logic here

        // http://stackoverflow.com/questions/4008223/print-in-new-line-java
        System.out.println("Hello ntpplot!");
        Runner runner1 = new Runner();
        runner1.sysStats();
        runner1.peerStats();
    }
}