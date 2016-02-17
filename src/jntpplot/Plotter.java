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
 *            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *     TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 * 
 *     0. You just DO WHAT THE FUCK YOU WANT TO.
 */
package jntpplot;

import java.io.*;
import java.util.ArrayList;
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartUtilities; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author ernest
 */
public class Plotter {
    
    private ArrayList<String> stats;
    private String statLabel;
    private String perLabel;
    private String plotName;
    private String output;
    
    private static final Logger logger = LogManager.getLogger(Jntpplot.class);
    
    public void setStats (ArrayList<String> strings) {
        stats = strings;
    }
    
    public void setStatLablel (String name) {
        statLabel = name;
    }
    
    public void setPerLabel (String name) {
        perLabel = name;
    }
    
    public void setPlotName (String name) {
        plotName = name;
    }
    
    public void setOutput (String name) {
        output = name;
    }
    
    // http://www.tutorialspoint.com/jfreechart/jfreechart_line_chart.htm
    public void lineChart () {
        int x = 1;
        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
        for (String stat : stats) {
            line_chart_dataset.addValue(
                    Float.parseFloat(stat),
                    "stats",
                    String.valueOf(x) );
            x ++;
        }
        
        JFreeChart lineChartObject = ChartFactory.createLineChart(
                plotName,
                perLabel,
                statLabel,
                line_chart_dataset,PlotOrientation.VERTICAL,
                true,true,false);

        int width = 640; /* Width of the image */
        int height = 480; /* Height of the image */ 
        File lineChart = new File(output); 
        try {
            ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);
        } catch (Exception e) {
            logger.error( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
            
}
