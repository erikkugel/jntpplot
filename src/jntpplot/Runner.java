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
public class Runner {
    
    private static final Logger logger = LogManager.getLogger(Jntpplot.class);
    
    // Define and ingestor for system statistics:
    public boolean sysStats () throws SQLException, IOException, FileNotFoundException, ClassNotFoundException {
        logger.trace(getClass().getName());
        
        Ingestor sysStats = new SysIngestor();
        sysStats.setFileName("/tmp/sys");
        sysStats.setDbName("/tmp/stats.db");
        sysStats.setTableName("sysstats");
        sysStats.setTableColumns("(julian_milliseconds LONG PRIMARY KEY DESC," +
                "date INT," +
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
                "rate_exceeded INT," +
                "kiss_of_death INT);");
              
        sysStats.ingestFileIntoDatabase();
        
        Output sysPlot = new Output();
        sysPlot.setDbName("/tmp/stats.db");
        sysPlot.setTableName("sysstats");
        sysPlot.setColumnName("packets_recieved");
        sysPlot.plotStats();
        
        return true;
    }

    // Define and ingestor for peer statistics:
    public boolean peerStats () throws SQLException, IOException, FileNotFoundException, ClassNotFoundException {
        logger.trace(getClass().getName());
        
        Ingestor peerStats = new PeerIngestor();
        peerStats.setFileName("/tmp/peers");
        peerStats.setDbName("/tmp/stats.db");
        peerStats.setTableName("peerstats");
        peerStats.setTableColumns("(julian_milliseconds LONG PRIMARY KEY DESC," + 
                "date INT," +
                "time REAL," +
                "peer_address VARCHAR(15)," +
                "status INT," +
                "offset REAL," +
                "delay REAL," +
                "dispersion REAL," +
                "jitter REAL);");
        
        peerStats.ingestFileIntoDatabase();
        return true;
    }
}