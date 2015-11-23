/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jntpplot;

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

    /**
     * Test of getInjestFile method, of class Stats.
     */
    @Test
    public void testGetInjestFile() throws Exception {
        System.out.println("getInjestFile");
        String fileName = "test/jntpplot/sys";
        Stats instance = new Stats();
        instance.setFileName(fileName);
        ArrayList<ArrayList<String>> result = instance.getInjestFile();
        System.out.println("Result: " + result);
        ArrayList<String> firstResult = result.get(0);
        String firstStat = firstResult.get(0);
        assertEquals ("57341", firstStat);
    }
    
}