/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jacsoyyo.chesschallenge;

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

    @Test
    public void test4Rooks3x3() {
        int rows = 3;
        int columns = 3;
        List<String> pieces = new ArrayList<>(Arrays.asList("R", "R", "R", "R"));
        ChessChallenge chessChallenge = new ChessChallenge(rows, columns, pieces);
        assertEquals(chessChallenge.doChallenge(), 0);
    }
    
    @Test
    public void test7Queens7x7() {
        int rows = 7;
        int columns = 7;
        List<String> pieces = new ArrayList<>(Arrays.asList("Q", "Q", "Q", "Q", "Q", "Q", "Q"));
        ChessChallenge chessChallenge = new ChessChallenge(rows, columns, pieces);
        assertEquals(chessChallenge.doChallenge(), 40);
    }
    
    @Test
    public void test2Kings1Rook3x3() {
        int rows = 3;
        int columns = 3;
        List<String> pieces = new ArrayList<>(Arrays.asList("K", "K", "R"));
        ChessChallenge chessChallenge = new ChessChallenge(rows, columns, pieces);
        assertEquals(chessChallenge.doChallenge(), 4);
    }

    @Test
    public void test2Rooks4Knights4x4() {
        int rows = 4;
        int columns = 4;
        List<String> pieces = new ArrayList<>(Arrays.asList("R", "R", "N", "N", "N", "N"));
        ChessChallenge chessChallenge = new ChessChallenge(rows, columns, pieces);
        assertEquals(chessChallenge.doChallenge(), 8);
    }
}
