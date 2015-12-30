/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jntpplot;

import java.util.ArrayList;

/**
 *
 * @author ernest
 */
public class Mutator {
    
    private ArrayList<ArrayList<String>> stats;
    private int statIndex;
    
    public void setStats (ArrayList<ArrayList<String>> sourceStats) {
        stats = sourceStats;
    }
    
    public ArrayList<ArrayList<String>> getStats () {
        return stats;
    }
    
    public void setStatIndex (int index) {
        statIndex = index;
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

        String stat;
        
        for (int messageIndex = 0 ; messageIndex < stats.size() ; messageIndex ++) {
            ArrayList<String> message = stats.get(messageIndex);
            stat = message.get(statIndex);
            // appendEpochTime logic goes here
            message.set(statIndex, stat);
            stats.set(messageIndex, message);
        }
        return stats;
    }
}