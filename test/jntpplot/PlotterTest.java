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
public class PlotterTest {
    
    public PlotterTest() {
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
     * Test of setStats method, of class Plotter.
     */
    @Test
    public void testSetStats() {
    }

    /**
     * Test of setStatLablel method, of class Plotter.
     */
    @Test
    public void testSetStatLablel() {
    }

    /**
     * Test of setPerLabel method, of class Plotter.
     */
    @Test
    public void testSetPerLabel() {
    }

    /**
     * Test of setPlotName method, of class Plotter.
     */
    @Test
    public void testSetPlotName() {
    }

    /**
     * Test of setOutput method, of class Plotter.
     */
    @Test
    public void testSetOutput() {
    }

    /**
     * Test of lineChart method, of class Plotter.
     */
    @Test
    public void testLineChart() {
        System.out.println("lineChart");
        ArrayList<String> stats = new ArrayList<>();
        stats.add("1");
        stats.add("3");
        stats.add("9");
        stats.add("27");
        Plotter instance = new Plotter();       
        instance.setStats(stats);
        instance.setStatLablel("Test Stats");
        instance.setPerLabel("Test Interval");
        instance.setOutput("test/jntpplot/lineChart.jpeg");
        instance.lineChart();
        File jpeg = new File("test/jntpplot/lineChart.jpeg");
        assertTrue( jpeg.isFile() );
    }
    
}
