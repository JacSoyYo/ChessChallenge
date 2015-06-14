/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jacsoyyo.chesschallenge.pieces;

import es.jacsoyyo.chesschallenge.pieces.Piece;
import es.jacsoyyo.chesschallenge.pieces.Queen;
import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

/**
 *
 * @author jacobo
 */
public class QueenTest {
    
    Piece aPiece = new Queen();
    
    @Test
    public void queen3x3boardPosition4() {
        
        Set<Integer> threatenedSquares = new HashSet<>();
        aPiece.visitPossibleMoves(1, 1, 3, 3, (p) -> { threatenedSquares.add(p);});
        assertThat(threatenedSquares).hasSize(8).containsOnly(0, 1, 2, 3, 5, 6, 7, 8);
    }

    @Test
    public void queen5x5boardPosition3() {
        
        Set<Integer> threatenedSquares = new HashSet<>();
        aPiece.visitPossibleMoves(0, 3, 5, 5, (p) -> { threatenedSquares.add(p);});
        assertThat(threatenedSquares).hasSize(12).containsOnly(0, 1, 2, 4, 7, 8, 9, 11, 13, 15, 18, 23);

    }
}
