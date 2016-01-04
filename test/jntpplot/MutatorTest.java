/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jntpplot;

import java.util.ArrayList;
import java.util.Arrays;
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
public class MutatorTest {
    
    public MutatorTest() {
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
     * Test of mutateHexToDec method, of class Mutator.
     */
    @Test
    public void testMutateHexToDec() {
        
        final byte PEER_STATUS_HEX_FIELD = 0;
        
        System.out.println("mutateHexToDec");
        Mutator hexToDec = new Mutator();

        ArrayList<ArrayList<String>> expResult = new ArrayList<>();
        ArrayList<String> expMessage = new ArrayList<>(Arrays.asList("37914"));
        expResult.add(expMessage);
        
        ArrayList<ArrayList<String>> payload = new ArrayList<>();
        ArrayList<String> payloadMessage = new ArrayList<>(Arrays.asList("941a"));
        payload.add(payloadMessage);

        hexToDec.setStatIndex(PEER_STATUS_HEX_FIELD);
        hexToDec.setStats(payload);
        
        ArrayList<ArrayList<String>> result = hexToDec.mutateHexToDec();
        assertEquals(expResult, result);
    }

    /**
     * Test of appendEpochTimeFromNTPStats method, of class Mutator.
     */
    //@Ignore
    @Test
    public void testAppendEpochTimeFromNTPStats() {
        System.out.println("appendEpochTimeFromNTPStats");
        Mutator instance = new Mutator();
        
        final byte DAY_FIELD = 0;
        final byte SECOND_FIELD = 1;
        final byte OUTPUT_FIELD = 2;
        
        ArrayList<ArrayList<String>> expResult = new ArrayList<>();
        ArrayList<String> expMessage = new ArrayList<>(Arrays.asList("12345", "6789.123", "1066614789123"));
        expResult.add(expMessage);
        
        ArrayList<ArrayList<String>> payload = new ArrayList<>();
        ArrayList<String> payloadMessage = new ArrayList<>(Arrays.asList("12345", "6789.123"));
        payload.add(payloadMessage);

        instance.setStatIndex(DAY_FIELD, SECOND_FIELD, OUTPUT_FIELD);
        instance.setStats(payload);
        
        ArrayList<ArrayList<String>> result = instance.appendEpochTimeFromNTPStats();

        assertEquals(expResult, result);
    }
    
}
