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

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ernest
 */
public abstract class Output {
    
    String dbName;
    String tableName;
    List<String> columnNames;
    //List<List<String>> stats;
    
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
    
    public void setColumnNames(List<String> names) {
        columnNames = names;
    }
    
    abstract void plotStats () throws SQLException;
    
}
