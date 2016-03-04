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
import java.util.List;

/**
 *
 * @author ernest
 */
public class SysOutput extends Output {
    
    @Override
    public void plotStats () throws SQLException {        
        setTableName("sysstats");
        
        List columns = new ArrayList<>();
        columns.add("julian_milliseconds");
        columns.add("packets_recieved");
        columns.add("packets_processed");
        columns.add("current_version");
        columns.add("previous_version");
        columns.add("bad_version");
        columns.add("access_denied");
        columns.add("bad_length_or_format");
        columns.add("bad_authentication");
        columns.add("rate_exceeded");
        columns.add("kiss_of_death");
        
        setColumnNames(columns);
        
        Database statsDb = new Database();
        statsDb.setDbName(dbName);
        Connection dbConnection = statsDb.openDb();
        statsDb.setDbConnection(dbConnection);
        statsDb.setTableName(tableName);
        statsDb.setColumnNames(columnNames);
        List<List<String>> stats = statsDb.selectStats();

        Plotter statsPlot = new Plotter();     
        statsPlot.setStats(stats);
        statsPlot.setStatsLabels(columnNames);
        statsPlot.setYLabel("Count");
        statsPlot.setPlotLabel(tableName);
        statsPlot.setOutput("/tmp/sysstats_per_" + columnNames.get(0) + ".jpeg");
        statsPlot.chart();
    }
}
