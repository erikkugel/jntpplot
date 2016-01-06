/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jntpplot;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author ernest
 */
public class SysIngestor extends Ingestor {
    
    final static byte DAY_FIELD = 0;
    final static byte SECOND_FIELD = 1;
    final static byte OUTPUT_FIELD = 13;
    
    private static final Logger logger = LogManager.getLogger(Jntpplot.class);
    
    @Override
    ArrayList<ArrayList<String>> mutateStats () {
        logger.trace("SysIngestor mutateStats");
        Mutator sysMutator = new Mutator();
        sysMutator.setStats(stats);
        sysMutator.setStatIndex(DAY_FIELD, SECOND_FIELD, OUTPUT_FIELD);
        return sysMutator.appendEpochTimeFromNTPStats();
    }
    
}
