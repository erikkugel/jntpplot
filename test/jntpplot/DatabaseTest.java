/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jntpplot;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Ernest Kugel
 */
public class DatabaseTest {
    
    public DatabaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of openDb method, of class Database.
     */    
    @Test
    public void testOpenDb() {
        System.out.println("openDb");
        try {
            Path statsDbPath = FileSystems.getDefault().getPath("test", "jntpplot", "stats_db");
            Files.deleteIfExists(statsDbPath);
        } catch (IOException x) {
            // File permission problems are caught here.
            System.err.println(x);
        }
        Database instance = new Database();
        instance.setDbName("test/jntpplot/stats_db");
        Connection result = instance.openDb();
        assertNotNull(result);
    }

    /**
     * Test of crateTable method, of class Database.
     */
    //@Ignore ("Not ready")
    @Test
    public void testCrateTable() {
        System.out.println("crateTable");
        String dbName = "test/jntpplot/stats_db";
        String tableName = "sysstats";
        String tableColumns = "(date INT," +
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
                "kiss_of_death INT)";
        Database instance = new Database();
        instance.setDbName(dbName);
        Connection conn = instance.openDb();
        instance.setTableName(tableName);
        instance.setTableColumns(tableColumns);
        instance.setDbConnection(conn);
        Boolean result = instance.crateTable();
        assertTrue(result);
    }

    /**
     * Test of insertStat method, of class Database.
     */
    @Test
    public void testInsertStat() throws SQLException {
        System.out.println("insertStat");
        String dbName = "test/jntpplot/stats_db";
        String tableName = "sysstats";
        ArrayList<String> message =
                new ArrayList<>(Arrays.asList("57368", "85263.715", "3600",
                "49046", "17", "35918", "13128", "0", "0", "11", "0", "0", "0"));
        Database instance = new Database();
        instance.setDbName(dbName);
        Connection conn = instance.openDb();
        instance.setTableName(tableName);
        //instance.setTableColumns(tableColumns);
        instance.setDbConnection(conn);
        instance.setStatMessage(message);
        Boolean result = instance.insertStat();
        assertTrue(result);
    }
    
}