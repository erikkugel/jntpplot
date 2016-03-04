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

import org.no_ip.xeps.jntpplot.StatsFile;
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
        String fileName = "/tmp/test_sys";
        
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