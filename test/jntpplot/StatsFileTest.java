/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jntpplot;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ernest
 */
public class StatsFileTest {
    
    public StatsFileTest() {
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
     * Test of getInjestFile method, of class StatsFile.
     */
    @Test
    public void testInjestFile() throws Exception {
        System.out.println("getInjestFile");
        String fileName = "test/jntpplot/sys";
        
        File statsFile = new File(fileName);
        statsFile.deleteOnExit();
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        writer.println("57368");
        writer.close();
        
        StatsFile instance = new StatsFile();
        instance.setFileName(fileName);
        ArrayList<ArrayList<String>> result = instance.injestFile();
        System.out.println("Result: " + result);
        ArrayList<String> firstResult = result.get(0);
        String firstStat = firstResult.get(0);
        assertEquals ("57368", firstStat);
    }
    
}