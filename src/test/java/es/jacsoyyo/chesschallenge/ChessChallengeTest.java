package es.jacsoyyo.chesschallenge;

import static es.jacsoyyo.chesschallenge.Piece.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
        List<Piece> pieces = new ArrayList<>(Arrays.asList(R, R, R, R));
        SolutionFinder chessChallenge = new SolutionFinder(rows, columns, pieces);
        Set<Map<Integer, Piece>> solutions = chessChallenge.findSolutions();
        assertEquals(0, solutions.size());
    }
    
    @Test
    public void test7Queens7x7() {
        int rows = 7;
        int columns = 7;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(Q, Q, Q, Q, Q, Q, Q));
        SolutionFinder chessChallenge = new SolutionFinder(rows, columns, pieces);
        Set<Map<Integer, Piece>> solutions = chessChallenge.findSolutions();
        assertEquals(40, solutions.size());
    }
    
    @Test
    public void test2Kings1Rook3x3() {
        int rows = 3;
        int columns = 3;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(K, K, R));
        SolutionFinder chessChallenge = new SolutionFinder(rows, columns, pieces);
        Set<Map<Integer, Piece>> solutions = chessChallenge.findSolutions();
        assertEquals(4, solutions.size());
    }

    @Test
    public void test2Rooks4Knights4x4() {
        int rows = 4;
        int columns = 4;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(R, R, N, N, N, N));
        SolutionFinder chessChallenge = new SolutionFinder(rows, columns, pieces);
        Set<Map<Integer, Piece>> solutions = chessChallenge.findSolutions();
        assertEquals(8, solutions.size());
    }
}
