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
import org.junit.Ignore;

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

@FixMethodOrder
public class DatabaseTest {
    
    static String databaseFilePath = "test/jntpplot/stats_db";
    static String tableName = "sysstats";
    public DatabaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws IOException {       
        System.out.println("setupClass - openDb");
        File databaseFile = new File(databaseFilePath);
        databaseFile.createNewFile();
    }
    
    @AfterClass
    public static void tearDownClass() {
        File databaseFile = new File(databaseFilePath);
        databaseFile.delete();
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
        Database instance = new Database();
        instance.setDbName(databaseFilePath);
        Connection result = instance.openDb();
        assertNotNull(result);
    }
    
    /**
     * Test of crateTable method, of class Database.
     */
    @Test
    public void test2CrateTable() {
        System.out.println("crateTable");
        String dbName = databaseFilePath;
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
     * @throws java.sql.SQLException
     */
    @Test
    public void test3InsertStat() throws SQLException {
        System.out.println("insertStat");
        String dbName = databaseFilePath;
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

    /**
     * Test of selectStat method, of class Database.
     * @throws java.lang.Exception
     */
    @Ignore
    @Test
    public void test4SelectStat() throws Exception {
        System.out.println("selectStat");
        Database instance = new Database();
        instance.selectStat();
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDbName method, of class Database.
     */
    @Test
    public void testSetDbName() {
    }

    /**
     * Test of getDbName method, of class Database.
     */
    @Test
    public void testGetDbName() {
    }

    /**
     * Test of setTableName method, of class Database.
     */
    @Test
    public void testSetTableName() {
    }

    /**
     * Test of getTableName method, of class Database.
     */
    @Test
    public void testGetTableName() {
    }

    /**
     * Test of setTableColumns method, of class Database.
     */
    @Test
    public void testSetTableColumns() {
    }

    /**
     * Test of getTableColumns method, of class Database.
     */
    @Test
    public void testGetTableColumns() {
    }

    /**
     * Test of setStatMessage method, of class Database.
     */
    @Test
    public void testSetStatMessage() {
    }

    /**
     * Test of setDbConnection method, of class Database.
     */
    @Test
    public void testSetDbConnection() {
    }

    /**
     * Test of getDbConnection method, of class Database.
     */
    @Test
    public void testGetDbConnection() {
    }
    
}