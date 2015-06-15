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
public class RookTest {
    
    Piece aPiece = new Rook();
    
    @Test
    public void rook3x3boardPosition4() {
        
        Set<Integer> threatenedSquares = new HashSet<>();
        aPiece.visitPossibleMoves(1, 1, 3, 3, (p) -> { threatenedSquares.add(p);});
        assertThat(threatenedSquares).hasSize(4).containsOnly(1, 3, 5, 7);
    }

    @Test
    public void rook5x5boardPosition3() {
        
        Set<Integer> threatenedSquares = new HashSet<>();
        aPiece.visitPossibleMoves(0, 3, 5, 5, (p) -> { threatenedSquares.add(p);});
        assertThat(threatenedSquares).hasSize(8).containsOnly(0, 1, 2, 4, 8, 13, 18, 23);
    }
}
