/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jntpplot;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
            //System.out.println("pre: " + stat);
            int newStat = Integer.parseInt(stat, 16);
            stat = String.valueOf(newStat);
            //System.out.println("post: " + stat);
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
            
            //System.out.println(TimeUnit.MILLISECONDS.convert(days, TimeUnit.DAYS) + (long)(seconds * 1000));
            message.add(statOutputIndex, String.valueOf(TimeUnit.MILLISECONDS.convert(days, TimeUnit.DAYS) + (long)(seconds * 1000)));
            stats.set(messageIndex, message);
        }
        return stats;
    }
}