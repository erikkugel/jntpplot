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
package jntpplot;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ernest
 */
public class PeerOutput extends Output {
    
    @Override
    public void plotStats () throws SQLException {        
        setTableName("peerstats");
        
        List columns = new ArrayList<>();
        columns.add("peer_address");
        columns.add("offset");
        columns.add("delay");
        columns.add("dispersion");
        columns.add("jitter");
        
        setColumnNames(columns);
        
        Database statsDb = new Database();
        statsDb.setDbName(dbName);
        Connection dbConnection = statsDb.openDb();
        statsDb.setDbConnection(dbConnection);
        statsDb.setTableName(tableName);
        statsDb.setColumnNames(columnNames);
        List<List<String>> stats = statsDb.selectStats();
        //System.out.println("SSSSTATS: " + stats);

        Plotter statsPlot = new Plotter();     
        statsPlot.setStats(stats);
        statsPlot.setStatsLabels(columnNames);
        statsPlot.setYLabel("Seconds");
        statsPlot.setPlotLabel(tableName);
        statsPlot.setOutput("/tmp/peerstats_per_" + columnNames.get(0) + ".jpeg");
        statsPlot.setChartType("bar");
        statsPlot.chart();
    }
}
