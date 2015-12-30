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
public class Runner {
    
    // Define and ingestor for system statistics:
    public boolean sysStats () throws SQLException, IOException, FileNotFoundException, ClassNotFoundException {
        Ingestor sysStats = new Ingestor();
        sysStats.setFileName("/tmp/sys");
        sysStats.setDbName("/tmp/stats_db");
        sysStats.setTableName("sysstats");
        sysStats.setTableColumns("(date INT," +
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
              
        return sysStats.ingestFileIntoDatabase() == 0;
    }

    // Define and ingestor for peer statistics:
    public boolean peerStats () throws SQLException, IOException, FileNotFoundException, ClassNotFoundException {
        Ingestor peerStats = new PeerIngestor();
        peerStats.setFileName("/tmp/peers");
        peerStats.setDbName("/tmp/stats_db");
        peerStats.setTableName("peerstats");
        peerStats.setTableColumns("(date INT," +
            "time REAL," +
            "peer_address VARCHAR(15)," +
            "status INT," +
            "offset REAL," +
            "delay REAL," +
            "dispersion REAL," +
            "jitter REAL);");

        return peerStats.ingestFileIntoDatabase() == 0;
    }
}