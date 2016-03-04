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
public class SysInput extends Input {
    
    final static byte DAY_FIELD = 0;
    final static byte SECOND_FIELD = 1;
    
    private static final Logger logger = LogManager.getLogger(Jntpplot.class);
    
    @Override
    boolean setupDatabase() throws SQLException {
        setTableName("sysstats");
        
        Database statsDb = new Database();
        statsDb.setDbName(dbName);
        statsDb.setTableName(getTableName());
        statsDb.setTableColumns("(julian_milliseconds LONG PRIMARY KEY DESC," +
                "date INT," +
                "time REAL," +
                "time_since_restart INT," +
                "packets_recieved INT," +
                "packets_processed INT," +
                "current_version INT," +
                "previous_version INT," +
                "bad_version INT," +
                "access_denied INT," +
                "bad_length_or_format INT," +
                "bad_authentication INT," +
                "rate_exceeded INT," +
                "kiss_of_death INT)");
        
        // Make sure the table is in place and create it otherwise
        Connection conn = statsDb.openDb();
        statsDb.setDbConnection(conn);
        return statsDb.crateTable();
    }
    
    @Override
    ArrayList<ArrayList<String>> mutateStats () {
        logger.trace("SysIngestor mutateStats");
        Mutator sysMutator = new Mutator();
        sysMutator.setStats(stats);
        sysMutator.setStatIndex(DAY_FIELD, SECOND_FIELD);
        return sysMutator.appendEpochTimeFromNTPStats();
    }
    
}
