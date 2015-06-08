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
public class QueenTest {
    
    Piece aPiece = Q;
    
    @Test
    public void queen3x3boardPosition4() throws ThreatensOccupiedSquare{
        
        Set<Integer> threatenedSquares = new HashSet<>();
        aPiece.threatenedSquares(4, 3, 3, (Integer p) -> { threatenedSquares.add(p);});
        assertThat(threatenedSquares).hasSize(8).containsOnly(0, 1, 2, 3, 5, 6, 7, 8);
    }

    @Test
    public void queen5x5boardPosition3() throws ThreatensOccupiedSquare{
        
        Set<Integer> threatenedSquares = new HashSet<>();
        aPiece.threatenedSquares(3, 5, 5, (Integer p) -> { threatenedSquares.add(p);});
        assertThat(threatenedSquares).hasSize(12).containsOnly(0, 1, 2, 4, 7, 8, 9, 11, 13, 15, 18, 23);

    }
}
