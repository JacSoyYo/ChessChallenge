/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jacsoyyo.chesschallenge;

import static org.assertj.core.api.Assertions.*;
import static es.jacsoyyo.chesschallenge.Piece.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

/**
 *
 * @author jacobo
 */
public class NightTest {
    
    Piece aPiece = N;
    
    @Test
    public void night5x5boardPosition12() throws ThreatensOccupiedSquare{
        
        Set<Integer> threatenedSquares = new HashSet<>();
        aPiece.threatenedSquares(12, 5, 5, (Integer p) -> { threatenedSquares.add(p);});
        assertThat(threatenedSquares).hasSize(8).containsOnly(1, 3, 5, 9, 15, 19, 21, 23);
    }

    @Test
    public void night5x5boardPosition3() throws ThreatensOccupiedSquare{
        
        Set<Integer> threatenedSquares = new HashSet<>();
        aPiece.threatenedSquares(3, 5, 5, (Integer p) -> { threatenedSquares.add(p);});
        assertThat(threatenedSquares).hasSize(3).containsOnly(6, 12, 14);
    }
}
