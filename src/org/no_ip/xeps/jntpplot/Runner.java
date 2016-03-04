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
        
        Input sysStatsIngestor = new SysInput();
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
        Input peerStats = new PeerIngestor();
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
        
        Input peerStatsIngestor = new PeerInput();
        peerStatsIngestor.setDbName("/tmp/stats.db");
        peerStatsIngestor.setFileName("/tmp/peers");
        peerStatsIngestor.ingestFileIntoDatabase();
        
        Output peerStatsOutput = new PeerOutput();
        peerStatsOutput.setDbName(peerStatsIngestor.getDbName());
        peerStatsOutput.plotStats();
        return true;
    }
}