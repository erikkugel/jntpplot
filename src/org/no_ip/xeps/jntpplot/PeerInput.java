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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author ernest
 */
public class PeerInput extends Input {
    
    final static byte DAY_FIELD = 0;
    final static byte SECOND_FIELD = 1;
    
    final static byte PEER_STATUS_HEX_FIELD = 4;
    
    private static final Logger logger = LogManager.getLogger(Jntpplot.class);
    
        @Override
    boolean setupDatabase() throws SQLException {
        setTableName("peerstats");
        
        Database statsDb = new Database();
        statsDb.setDbName(dbName);
        statsDb.setTableName(getTableName());
        statsDb.setTableColumns("(julian_milliseconds LONG PRIMARY KEY DESC," + 
                "date INT," +
                "time REAL," +
                "peer_address VARCHAR(15)," +
                "status INT," +
                "offset REAL," +
                "delay REAL," +
                "dispersion REAL," +
                "jitter REAL)");
        
        // Make sure the table is in place and create it otherwise
        Connection conn = statsDb.openDb();
        statsDb.setDbConnection(conn);
        return statsDb.crateTable();
    }
    
    @Override
    ArrayList<ArrayList<String>> mutateStats () {
        logger.trace("PeerIngestor mutateStats");
        Mutator peerMutator = new Mutator();
        peerMutator.setStats(stats);
        peerMutator.setStatIndex(DAY_FIELD, SECOND_FIELD);
        stats = peerMutator.appendEpochTimeFromNTPStats();
        peerMutator.setStatIndex(PEER_STATUS_HEX_FIELD);
        stats = peerMutator.mutateHexToDec();
        return stats;
    }
    
}