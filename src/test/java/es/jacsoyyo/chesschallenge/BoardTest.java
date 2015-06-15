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
    public void testBoardInitialization() {
        Board board = new Board(3, 3);
        
        List<Integer> result = board.getSquares();
        
        assertThat(result).containsOnly(0, 1, 2, 3, 4, 5, 6, 7, 8);
        assertThat(board.getSafeSquares()).containsOnly(0, 1, 2, 3, 4, 5, 6, 7, 8);
        assertThat(board.getPlacedPieces()).isEmpty();
    }

    @Test
    public void testPlaceKingPosition4on3x3board() throws Exception {
        Piece king = new King();
        Integer position = 4;
        Board board = new Board(3, 3);
        List<Integer> safeSquares = new ArrayList<>();
        safeSquares.addAll(board.getSquares());
        Map<Integer, Piece> placedPieces = new HashMap<>();
        
        board.placePiece(king, position, safeSquares, placedPieces);
        
        assertThat(safeSquares).containsOnly(4);
        assertThat(board.getSafeSquares()).containsOnly(4);
        assertThat(board.getPlacedPieces()).containsOnly(MapEntry.entry(position, king));
    }

    @Test
    public void newTestPlaceKingPosition4on3x3board() throws Exception {
        Piece king = new King();
        Integer position = 4;
        Board board = new Board(3, 3);
   
        board.placePiece(king, position);
        
        assertThat(board.getSafeSquares()).isEmpty();;
        assertThat(board.getPlacedPieces()).containsOnly(MapEntry.entry(position, king));
    }
    
    @Test
    public void testPlaceKingPosition4on3x3boardThreatensPiece() throws Exception {
        Piece king = new King();
        Integer position = 4;
        List<Integer> safeSquares = new ArrayList<>();
        Board board = new Board(3, 3);
        safeSquares.addAll(board.getSquares());
        Map<Integer, Piece> placedPieces = new HashMap<>();
        placedPieces.put(0, new Rook());
        safeSquares.remove(0);
        
        assertThatThrownBy(() ->{ board.placePiece(king, position, safeSquares, placedPieces);}).isInstanceOf(ThreatensOccupiedSquare.class);        
    }
    
    @Test
    public void newTestPlaceKingPosition4on3x3boardThreatensPiece() throws Exception {
        Piece king = new King();
        Integer position = 4;
        Board board = new Board(3, 3);

        board.placePiece(new Rook(), 0);
        
        assertThatThrownBy(() ->{ board.placePiece(king, position);}).isInstanceOf(ThreatensOccupiedSquare.class);        
    }
    
    @Test
    public void testPushAndPopBoardState(){
        Piece king = new King();
        Board board = new Board(3, 3);
        List<Integer> safeSquares = new ArrayList<>();
        safeSquares.addAll(board.getSquares());
        Map<Integer, Piece> placedPieces = new HashMap<>();
        
        board.placePiece(king, 0, safeSquares, placedPieces);
        placedPieces.put(0, king);
        
        assertThat(safeSquares).containsOnly(0, 2, 5, 6, 7, 8);
        assertThat(placedPieces).containsOnly(MapEntry.entry(0, king));
        assertThat(board.getSafeSquares()).containsOnly(0, 2, 5, 6, 7, 8);
        assertThat(board.getPlacedPieces()).containsOnly(MapEntry.entry(0, king));
        
        board.pushState();
        
        board.placePiece(king, 8, safeSquares, placedPieces);
        placedPieces.put(8, king);
        
        assertThat(safeSquares).containsOnly(0, 2, 6, 8);
        assertThat(board.getSafeSquares()).containsOnly(0, 2, 6, 8);
        assertThat(board.getPlacedPieces()).containsOnly(MapEntry.entry(0, king), MapEntry.entry(8, king));
        
        board.popState();
        
        assertThat(safeSquares).containsOnly(0, 2, 6, 8);
        assertThat(placedPieces).containsOnly(MapEntry.entry(0, king), MapEntry.entry(8, king));        
        assertThat(board.getSafeSquares()).containsOnly(0, 2, 5, 6, 7, 8);
        assertThat(board.getPlacedPieces()).containsOnly(MapEntry.entry(0, king));

    }
    
    @Test
    public void newTestPushAndPopBoardState(){
        Piece king = new King();
        Board board = new Board(3, 3);
        
        board.placePiece(king, 0);
        
        assertThat(board.getSafeSquares()).containsOnly(2, 5, 6, 7, 8);
        assertThat(board.getPlacedPieces()).containsOnly(MapEntry.entry(0, king));
        
        board.pushState();
        
        board.placePiece(king, 8);
        
        assertThat(board.getSafeSquares()).containsOnly(2, 6);
        assertThat(board.getPlacedPieces()).containsOnly(MapEntry.entry(0, king), MapEntry.entry(8, king));
        
        board.popState();
             
        assertThat(board.getSafeSquares()).containsOnly(2, 5, 6, 7, 8);
        assertThat(board.getPlacedPieces()).containsOnly(MapEntry.entry(0, king));

    }
    @Test
    public void testPopBoardWithoutPushThrowsNoSuchElementException(){
        Board board = new Board(3, 3);
        
        assertThatThrownBy(() ->{ board.popState();}).isInstanceOf(java.util.NoSuchElementException.class);
    }
}
