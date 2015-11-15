/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ntpplot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author ernest
 */
public class StatsTest {

    public StatsTest() {
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

    String fileName = "/tmp/sys.test";
    Stats instance = new Stats();

    /**
     * Test of setFileName method, of class Stats.
     */
    @Test
    public void testSetFileName() {
        System.out.println("setFileName");
        instance.setFileName(fileName);
    }

    /**
     * Test of getFileName method, of class Stats.
     */
    @Test
    public void testGetFileName() {
        System.out.println("getFileName");
        String expResult = fileName;
        instance.setFileName(expResult);
        String result = instance.getFileName();
        System.out.println("Result: " + result);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInjestFile method, of class Stats.
     */
    @Test
    public void testGetInjestFile() throws Exception {
        System.out.println("getInjestFile");
        instance.setFileName(fileName);
        ArrayList<ArrayList<String>> result = instance.getInjestFile();
        System.out.println("Result: " + result);
        ArrayList<String> firstResult = result.get(0);
        String firstStat = firstResult.get(0);
        assertEquals ("57341", firstStat);
    }

    /**
     * Test of getInsertFile method, of class Stats.
     */
    @Test
    public void testSetInsertFile() throws Exception {
        System.out.println("getInsertFile");
        instance.setFileName(fileName);
        Boolean expResult = true;
        Boolean result = instance.setInsertFile();
        assertEquals(expResult, result);
    }
    
}

