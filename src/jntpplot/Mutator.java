/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jntpplot;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author ernest
 */
public class Mutator {
    
    private ArrayList<ArrayList<String>> stats;
    private byte statIndex;
    private byte statIndex0;
    private byte statIndex1;
    private byte statOutputIndex;
    
    private static final Logger logger = LogManager.getLogger(Jntpplot.class);
    
    public void setStats (ArrayList<ArrayList<String>> sourceStats) {
        stats = sourceStats;
    }
    
    public ArrayList<ArrayList<String>> getStats () {
        return stats;
    }
    
    public void setStatIndex (byte index) {
        statIndex = index;
    }
    
    public void setStatIndex (byte index0, byte index1, byte outputIndex) {
        statIndex0 = index0;
        statIndex1 = index1;
        statOutputIndex = outputIndex;
    }
    
    public int getStatIndex () {
        return statIndex;
    }
    
    public ArrayList<ArrayList<String>> mutateHexToDec() {

        String stat;
        
        for (int messageIndex = 0 ; messageIndex < stats.size() ; messageIndex ++) {
            ArrayList<String> message = stats.get(messageIndex);
            stat = message.get(statIndex);
            logger.debug("pre: " + stat);
            int newStat = Integer.parseInt(stat, 16);
            stat = String.valueOf(newStat);
            logger.debug("post: " + stat);
            message.set(statIndex, stat);
            stats.set(messageIndex, message);
        }
        return stats;
    }
    
    public ArrayList<ArrayList<String>> appendEpochTimeFromNTPStats() {

        long days;
        float seconds;
        
        for (int messageIndex = 0 ; messageIndex < stats.size() ; messageIndex ++) {
            ArrayList<String> message = stats.get(messageIndex);
            days = Long.parseLong(message.get(statIndex0));
            seconds = Float.parseFloat(message.get(statIndex1));
            
            logger.debug("pre: " + days + " days, " + seconds + " seconds,");
            logger.debug("post: " + TimeUnit.MILLISECONDS.convert(days, TimeUnit.DAYS) + (long)(seconds * 1000) + " milliseconds.");
            message.add(statOutputIndex, String.valueOf(TimeUnit.MILLISECONDS.convert(days, TimeUnit.DAYS) + (long)(seconds * 1000)));
            stats.set(messageIndex, message);
        }
        return stats;
    }
}