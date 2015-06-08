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
public class KingTest {
    
    Piece aPiece = K;
    
    @Test
    public void king3x3boardPosition4() throws ThreatensOccupiedSquare{
        
        Set<Integer> threatenedSquares = new HashSet<>();
        aPiece.threatenedSquares(4, 3, 3, (Integer p) -> { threatenedSquares.add(p);});
        assertThat(threatenedSquares.size()).isEqualTo(8);
    }

    @Test
    public void king5x5boardPosition3() throws ThreatensOccupiedSquare{
        
        Set<Integer> threatenedSquares = new HashSet<>();
        aPiece.threatenedSquares(3, 5, 5, (Integer p) -> { threatenedSquares.add(p);});
        assertThat(threatenedSquares.size()).isEqualTo(5);
    }
}