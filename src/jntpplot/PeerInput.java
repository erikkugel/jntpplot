/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jntpplot;

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