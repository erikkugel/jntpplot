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

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author ernest
 */
public class Output {
    
    private String dbName;
    private String tableName;
    private String columnName;
    
    public void setDbName(String name) {
        dbName = name;
    } 
    
    public String getDbName() {
        return dbName;
    }
    
    public void setTableName(String name) {
        tableName = name;
    }
    
    public String getTableName() {
        return tableName;
    }
    
    public void setColumnName(String name) {
        columnName = name;
    }
    
    public void plotStats () throws SQLException {
        
        Database statsDb = new Database();
        statsDb.setDbName(dbName);
        Connection dbConnection = statsDb.openDb();
        statsDb.setDbConnection(dbConnection);
        statsDb.setTableName(tableName);
        statsDb.setColumnName(columnName);
        ArrayList<String> stats = statsDb.selectStat();      

        Plotter statsPlot = new Plotter();       
        statsPlot.setStats(stats);
        statsPlot.setStatLablel(columnName);
        statsPlot.setPerLabel("Test Interval");
        statsPlot.setOutput("/tmp/" + tableName + "_" + columnName + ".jpeg");
        statsPlot.lineChart();
    }
    
}
