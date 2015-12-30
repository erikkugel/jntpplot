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

/**
 *
 * @author ernest
 */
public class StatsFile {
    
    private String statsFileName;
    
    public void setFileName (String fileName) {
        statsFileName = fileName;
    }
    
    public String getFileName () {
        return statsFileName;
    }
    
    public ArrayList<ArrayList<String>> injestFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        System.out.println("Injesting file: " + statsFileName);
               
        // http://stackoverflow.com/questions/5868369/how-to-read-a-large-text-file-line-by-line-using-java
        BufferedReader in;
        in = new BufferedReader(new FileReader(statsFileName));
        String line;
        ArrayList<String> message = new ArrayList<String>();
        ArrayList<ArrayList<String>> messages = new ArrayList<ArrayList<String>>();
        while ((line = in.readLine()) != null) {
            message = injestLine(line);
            messages.add(message);
        }
        return messages;
    } 

    private ArrayList<String> injestLine(String line) throws ClassNotFoundException {
        ArrayList<String> message = new ArrayList<String>();
        for(String stat : line.split(" ")) {
            message.add(stat);
        }
        return message;
    }

}