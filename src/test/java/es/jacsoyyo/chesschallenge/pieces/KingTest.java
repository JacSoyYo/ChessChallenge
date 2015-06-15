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
public class KingTest {
    
    Piece aPiece = new King();
    
    @Test
    public void king3x3boardPosition4() {
        
        Set<Integer> threatenedSquares = new HashSet<>();
        aPiece.visitPossibleMoves(1, 1, 3, 3, (p) -> { threatenedSquares.add(p);});
        assertThat(threatenedSquares).hasSize(8).containsOnly(0, 1, 2, 3, 5, 6, 7, 8);
    }

    @Test
    public void king5x5boardPosition3() {
        
        Set<Integer> threatenedSquares = new HashSet<>();
        aPiece.visitPossibleMoves(0, 3, 5, 5, (p) -> { threatenedSquares.add(p);});
        assertThat(threatenedSquares).hasSize(5).containsOnly(2, 4, 7, 8, 9);
    }
}
