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
public class PeerIngestor extends Ingestor {
    
    final static byte PEER_STATUS_HEX_FIELD = 3;
    
    @Override
    public ArrayList<ArrayList<String>> mutateStats (ArrayList<ArrayList<String>> stats) {
        System.out.println("PeerIngestor mutateStats");
        Mutator peerMutator = new Mutator();
        peerMutator.setStats(stats);
        peerMutator.setStatIndex(PEER_STATUS_HEX_FIELD);
        peerMutator.setMutateAction("hex_to_dec");
        return peerMutator.mutateHexToDec();
    }
    
}