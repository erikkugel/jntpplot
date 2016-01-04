/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jntpplot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

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
    
    // http://logging.apache.org/log4j/2.x/manual/configuration.html
    private static final Logger logger = LogManager.getLogger(Jntpplot.class);
    
    public static void main(String[] args) throws SQLException, IOException, FileNotFoundException, ClassNotFoundException {
        // TODO code application logic here

        logger.trace("Hello ntpplot!");
        Runner runner1 = new Runner();
        runner1.sysStats();
        runner1.peerStats();
    }
}