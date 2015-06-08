/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jacsoyyo.chesschallenge;

import es.jacsoyyo.chesschallenge.Piece;
import es.jacsoyyo.chesschallenge.ThreatensOccupiedSquare;
import static org.assertj.core.api.Assertions.*;
import static es.jacsoyyo.chesschallenge.Piece.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

/**
 *
 * @author jacobo
 */
public class BishopTest {
    
    Piece aPiece = B;
    
    @Test
    public void bishop3x3boardPosition4() throws ThreatensOccupiedSquare{
        
        Set<Integer> threatenedSquares = new HashSet<>();
        aPiece.threatenedSquares(4, 3, 3, (Integer p) -> { threatenedSquares.add(p);});
        assertThat(threatenedSquares.size()).isEqualTo(4);
    }
}
