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
import org.junit.Ignore;

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

        ArrayList<ArrayList<String>> expResult = new ArrayList<ArrayList<String>>();
        ArrayList<String> expMessage = new ArrayList<>(Arrays.asList("37914"));
        expResult.add(expMessage);
        
        ArrayList<ArrayList<String>> payload = new ArrayList<ArrayList<String>>();
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
    @Ignore
    @Test
    public void testAppendEpochTimeFromNTPStats() {
        System.out.println("appendEpochTimeFromNTPStats");
        Mutator instance = new Mutator();
        ArrayList<ArrayList<String>> expResult = null;
        ArrayList<ArrayList<String>> result = instance.appendEpochTimeFromNTPStats();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
