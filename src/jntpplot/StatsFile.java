/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jntpplot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author ernest
 */
public class StatsFile {
    
    private String statsFileName;
    
    private static final Logger logger = LogManager.getLogger(Jntpplot.class);
    
    public void setFileName (String fileName) {
        statsFileName = fileName;
    }
    
    public String getFileName () {
        return statsFileName;
    }
    
    public ArrayList<ArrayList<String>> injestFile() throws FileNotFoundException, IOException, ClassNotFoundException {
       logger.trace("Injesting file: " + statsFileName);
               
        // http://stackoverflow.com/questions/5868369/how-to-read-a-large-text-file-line-by-line-using-java
        BufferedReader in;
        in = new BufferedReader(new FileReader(statsFileName));
        String line;
        ArrayList<String> message;
        ArrayList<ArrayList<String>> messages = new ArrayList<>();
        while ((line = in.readLine()) != null) {
            message = injestLine(line);
            messages.add(message);
        }
        return messages;
    } 

    private ArrayList<String> injestLine(String line) throws ClassNotFoundException {
        ArrayList<String> message = new ArrayList<>();
        message.addAll(Arrays.asList(line.split(" ")));
        return message;
    }

}