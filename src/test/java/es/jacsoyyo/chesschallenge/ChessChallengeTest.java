package es.jacsoyyo.chesschallenge;

import static es.jacsoyyo.chesschallenge.Piece.B;
import static es.jacsoyyo.chesschallenge.Piece.K;
import static es.jacsoyyo.chesschallenge.Piece.N;
import static es.jacsoyyo.chesschallenge.Piece.Q;
import static es.jacsoyyo.chesschallenge.Piece.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
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
        List<Piece> pieces = new ArrayList<>(Arrays.asList(R, R, R, R));
        SolutionFinder chessChallenge = new SolutionFinder(rows, columns, pieces);
        int solutions = chessChallenge.findSolutions(c -> {});
        assertEquals(0, solutions);
    }

    @Test
    public void test2Kings1Rook3x3() {
        int rows = 3;
        int columns = 3;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(K, K, R));
        SolutionFinder chessChallenge = new SolutionFinder(rows, columns, pieces);
        int solutions = chessChallenge.findSolutions(c -> {});
        assertEquals(4, solutions);
    }

    @Test
    public void test2Rooks4Knights4x4() {
        int rows = 4;
        int columns = 4;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(R, R, N, N, N, N));
        SolutionFinder chessChallenge = new SolutionFinder(rows, columns, pieces);
        int solutions = chessChallenge.findSolutions(c -> {});
        assertEquals(8, solutions);
    }

    @Test
    public void test8Queens8x8() {
        int rows = 8;
        int columns = 8;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(Q, Q, Q, Q, Q, Q, Q, Q));
        SolutionFinder chessChallenge = new SolutionFinder(rows, columns, pieces);
        int solutions = chessChallenge.findSolutions(c -> {});
        assertEquals(92, solutions);
    }

    @Ignore("Too long for a unit test")
    @Test
    public void test2Kings2Queens2Bishops1Knight7x7() {
        int rows = 7;
        int columns = 7;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(Q, Q, B, B, K, K, N));
        SolutionFinder chessChallenge = new SolutionFinder(rows, columns, pieces);
        int solutions = chessChallenge.findSolutions(c -> {});
        assertEquals(3063828, solutions);
    }
}
