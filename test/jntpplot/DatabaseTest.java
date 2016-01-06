/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jntpplot;

import java.io.IOException;
import java.io.File;
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
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Ernest Kugel
 */

/**
 * "It is recommended that test methods be written so that they are independent
 *  of the order that they are executed"
 * 
 *  (http://junit.org/apidocs/index.html?org/junit/FixMethodOrder.html).
 * 
 *  However, there's still value in testing simple things like database
 *  connection and table creation. Until this can be elegantly addressed,
 *  JUnit 4.12 was imported with support for FixMethodOrder.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    public void test1OpenDb() {
        System.out.println("openDb");
        File databaseFile = new File("test/jntpplot/stats_db");
        databaseFile.deleteOnExit();
        Database instance = new Database();
        instance.setDbName("test/jntpplot/stats_db");
        Connection result = instance.openDb();
        assertNotNull(result);
    }

    /**
     * Test of crateTable method, of class Database.
     */
    @Test
    public void test2CrateTable() {
        System.out.println("crateTable");
        String dbName = "test/jntpplot/stats_db";
        String tableName = "sysstats";
        String tableColumns = "(date INT, time REAL)";
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
    public void test3InsertStat() throws SQLException {
        System.out.println("insertStat");
        String dbName = "test/jntpplot/stats_db";
        String tableName = "sysstats";
        ArrayList<String> message =
                new ArrayList<>(Arrays.asList("12345", "6789.123"));
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