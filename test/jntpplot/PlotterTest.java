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
import java.util.List;
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
     * Test of lineChart method, of class Plotter.
     */
    @Test
    public void testLineChart() {
        System.out.println("lineChart");  
        
        List stats = new ArrayList<>();
        for (int i = 1; i <= 4; i ++) {
            List stat = new ArrayList<>();
            stat.add(String.valueOf(i));
            stat.add(String.valueOf(i+i));
            stat.add(String.valueOf(i*i));
            stat.add(String.valueOf(i*i*i));
            stats.add(stat);
        }
        
        List statsLabels = new ArrayList<>();
        statsLabels.add("I");
        statsLabels.add("I+I");
        statsLabels.add("I^2");
        statsLabels.add("I^3");
        
        Plotter instance = new Plotter();       
        instance.setStats(stats);
        instance.setStatsLabels(statsLabels);
        instance.setPlotLabel("Test Interval");
        instance.setYLabel("Count");
        instance.setOutput("test/jntpplot/lineChart.jpeg");
        instance.lineChart();
        
        File jpeg = new File("test/jntpplot/lineChart.jpeg");
        assertTrue( jpeg.isFile() );
    }
    
}
