/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jacsoyyo.chesschallenge;

import static es.jacsoyyo.chesschallenge.Piece.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author jacobo
 */
public class ChessChallengeTest {
    
    public ChessChallengeTest() {
    }

    public void algo(){
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
    }
    
    @Test
    public void test4Rooks3x3() {
        int rows = 3;
        int columns = 3;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(ROOK, ROOK, ROOK, ROOK));
        ChessChallenge chessChallenge = new ChessChallenge(rows, columns, pieces);
        assertEquals(0, chessChallenge.doChallenge());
    }
    
    @Test
    public void test7Queens7x7() {
        int rows = 7;
        int columns = 7;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(QUEEN, QUEEN, QUEEN, QUEEN, QUEEN, QUEEN, QUEEN));
        ChessChallenge chessChallenge = new ChessChallenge(rows, columns, pieces);
        assertEquals(40, chessChallenge.doChallenge());
    }
    
    @Test
    public void test2Kings1Rook3x3() {
        int rows = 3;
        int columns = 3;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(KING, KING, ROOK));
        ChessChallenge chessChallenge = new ChessChallenge(rows, columns, pieces);
        assertEquals(4, chessChallenge.doChallenge());
    }

    @Test
    public void test2Rooks4Knights4x4() {
        int rows = 4;
        int columns = 4;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(ROOK, ROOK, KNIGHT, KNIGHT, KNIGHT, KNIGHT));
        ChessChallenge chessChallenge = new ChessChallenge(rows, columns, pieces);
        assertEquals(8, chessChallenge.doChallenge());
    }
}
