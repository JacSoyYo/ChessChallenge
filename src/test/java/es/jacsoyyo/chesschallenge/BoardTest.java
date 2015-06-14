/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jacsoyyo.chesschallenge;

import es.jacsoyyo.chesschallenge.pieces.King;
import es.jacsoyyo.chesschallenge.pieces.Piece;
import es.jacsoyyo.chesschallenge.pieces.Rook;
import static org.assertj.core.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
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
        Piece piece = new King();
        Integer position = 4;
        Board board = new Board(3, 3);
        List<Integer> safeSquares = new ArrayList<>();
        safeSquares.addAll(board.getSquares());
        Map<Integer, Piece> placedPieces = new HashMap<>();
        
        board.placePiece(piece, position, safeSquares, placedPieces);
        
        assertThat(safeSquares).containsOnly(4);
    }
    
    @Test
    public void testPlaceKingPosition4on3x3boardThreatensPiece() throws Exception {
        System.out.println("placePiece");
        Piece piece = new King();
        Integer position = 4;
        List<Integer> safeSquares = new ArrayList<>();
        Board board = new Board(3, 3);
        safeSquares.addAll(board.getSquares());
        Map<Integer, Piece> placedPieces = new HashMap<>();
        placedPieces.put(0, new Rook());
        safeSquares.remove(0);
        
        
        assertThatThrownBy(() ->{ board.placePiece(piece, position, safeSquares, placedPieces);}).isInstanceOf(ThreatensOccupiedSquare.class);
        
    }
}
