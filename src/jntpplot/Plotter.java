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
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartUtilities; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ernest
 */
public class Plotter {
    
    private List<List<String>> stats;
    private List<String> statsLabels;
    private String yLabel;
    private String plotLabel;
    private String output;
    private String chartType;
    
    private static final Logger logger = LogManager.getLogger(Jntpplot.class);
    
    public void setStats (List<List<String>> strings) {
        stats = strings;
    }
    
    public void setStatsLabels (List<String> names) {
        statsLabels = names;
    }
    
    public void setYLabel (String name) {
       yLabel = name;
    }
    
    public void setPlotLabel (String name) {
        plotLabel = name;
    }
    
    public void setOutput (String name) {
        output = name;
    }
    
    public void setChartType (String type) {
        chartType = type;
    }
    
    // http://www.tutorialspoint.com/jfreechart/jfreechart_line_chart.htm
    public void chart () {
        DefaultCategoryDataset statsDataset = new DefaultCategoryDataset();
        System.out.println(stats);
        for (List<String> stat : stats) {
            for ( int i = 1; i < statsLabels.size(); i++ ) {
                BigDecimal bd = new BigDecimal(stat.get(i));
                
                statsDataset.addValue(
                        //(Number) Long.valueOf(stat.get(i)),
                        bd,
                        (Comparable) statsLabels.get(i),
                        (Comparable) stat.get(0)
                );
            }
        }
        
        // Chart bars if instructed to
        if ( chartType == "bar" ) {
            JFreeChart barChartObject = ChartFactory.createBarChart(
                    plotLabel,
                    statsLabels.get(0),
                    yLabel,
                    statsDataset,PlotOrientation.VERTICAL,
                    true,true,false
            );

            int width = 1280;
            int height = 1024;
            File barChart = new File(output); 
            try {
                ChartUtilities.saveChartAsJPEG(barChart ,barChartObject, width ,height);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(Plotter.class.getName()).log(Level.SEVERE, null, ex);
                logger.error("Failed to output linechart JPEG @ " + output + ": " + ex);
            }
        // Default to line charts
        } else {
                JFreeChart lineChartObject = ChartFactory.createLineChart(
                    plotLabel,
                    statsLabels.get(0),
                    yLabel,
                    statsDataset,PlotOrientation.VERTICAL,
                    true,true,false
            );

            int width = 1280;
            int height = 1024;
            File lineChart = new File(output); 
            try {
                ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(Plotter.class.getName()).log(Level.SEVERE, null, ex);
                logger.error("Failed to output linechart JPEG @ " + output + ": " + ex);
            }
        }
    }
            
}
