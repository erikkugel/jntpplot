/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jntpplot;

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author ernest
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
        Database instance = new Database();
        instance.setDbName("stats_test");
        //Connection expResult = null;
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
        Connection c = null;
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
                "rate_exceeded INT)";
        Database instance = new Database();
        instance.setDbName("stats_test");
        Connection conn = instance.openDb();
        boolean result = instance.crateTable(conn, tableName, tableColumns);
        assertTrue(result);
    }
    
}
