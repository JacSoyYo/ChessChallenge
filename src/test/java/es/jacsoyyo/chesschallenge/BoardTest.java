/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jacsoyyo.chesschallenge;

import static org.assertj.core.api.Assertions.*;
import static es.jacsoyyo.chesschallenge.Piece.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import org.assertj.core.data.MapEntry;
import org.junit.Test;

/**
 *
 * @author jacobo
 */
public class BoardTest {
    
    @Test
    public void testGetSquares() {
        Board instance = new Board(3, 3);
        List<Integer> result = instance.getSquares();
        assertThat(result).containsOnly(0, 1, 2, 3, 4, 5, 6, 7, 8);
    }

    @Test
    public void testPlaceKingPosition4on3x3board() throws Exception {
        System.out.println("placePiece");
        Piece piece = K;
        Integer position = 4;
        List<Integer> safeSquares = new ArrayList<>();
        safeSquares.addAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
        Map<Integer, Piece> placedPieces = new HashMap<>();
        Board instance = new Board(3, 3);
        
        instance.placePiece(piece, position, safeSquares, placedPieces);
        
        assertThat(safeSquares).containsOnly(4);
    }
    
    @Test
    public void testPlaceKingPosition4on3x3boardThreatensPiece() throws Exception {
        System.out.println("placePiece");
        Piece piece = K;
        Integer position = 4;
        List<Integer> safeSquares = new ArrayList<>();
        safeSquares.addAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
        Map<Integer, Piece> placedPieces = new HashMap<>();
        placedPieces.put(0, R);
        
        Board instance = new Board(3, 3);
        
        assertThatThrownBy(() ->{ instance.placePiece(piece, position, safeSquares, placedPieces);}).isInstanceOf(ThreatensOccupiedSquare.class);
        
    }
}
