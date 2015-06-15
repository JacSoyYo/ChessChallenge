/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jacsoyyo.chesschallenge.pieces;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

/**
 *
 * @author jacobo
 */
public class KnightTest {
    
    Piece aPiece = new Knight();
    
    @Test
    public void night5x5boardPosition12() {
        
        Set<Integer> threatenedSquares = new HashSet<>();
        aPiece.visitPossibleMoves(2, 2, 5, 5, (p) -> { threatenedSquares.add(p);});
        assertThat(threatenedSquares).hasSize(8).containsOnly(1, 3, 5, 9, 15, 19, 21, 23);
    }

    @Test
    public void night5x5boardPosition3() {
        
        Set<Integer> threatenedSquares = new HashSet<>();
        aPiece.visitPossibleMoves(0, 3, 5, 5, (p) -> { threatenedSquares.add(p);});
        assertThat(threatenedSquares).hasSize(3).containsOnly(6, 12, 14);
    }
}
