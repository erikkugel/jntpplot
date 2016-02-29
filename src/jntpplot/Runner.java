/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jntpplot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        
        Ingestor sysStatsIngestor = new SysIngestor();
        sysStatsIngestor.setDbName("/tmp/stats.db");
        sysStatsIngestor.setFileName("/tmp/sys");
        sysStatsIngestor.ingestFileIntoDatabase();
        
        Output sysStatsOutput = new SysOutput();
        sysStatsOutput.setDbName(sysStatsIngestor.getDbName());
        sysStatsOutput.plotStats();
        return true;
    }

    // Define and ingestor for peer statistics:
    public boolean peerStats () throws SQLException, IOException, FileNotFoundException, ClassNotFoundException {
        logger.trace(getClass().getName());
        /*
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
                */
        return true;
                
    }
}