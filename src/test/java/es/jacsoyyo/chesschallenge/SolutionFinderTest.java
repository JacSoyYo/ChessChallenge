package es.jacsoyyo.chesschallenge;

import static es.jacsoyyo.chesschallenge.Piece.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author jacobo
 */
public class SolutionFinderTest {

    public SolutionFinderTest() {
    }

    @Test
    public void test4Rooks3x3board() {
        int rows = 3;
        int columns = 3;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(R, R, R, R));
        SolutionFinder solutionFinder = new SolutionFinder(rows, columns, pieces);
        
        int solutions = solutionFinder.findSolutions(c -> {});
        
        assertThat(solutions).isEqualTo(0);
    }

    @Test
    public void test2Kings1Rook3x3board() {
        int rows = 3;
        int columns = 3;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(K, K, R));
        SolutionFinder solutionFinder = new SolutionFinder(rows, columns, pieces);
        
        int solutions = solutionFinder.findSolutions(c -> {});
        
        assertThat(solutions).isEqualTo(4);
    }

    @Test
    public void test2Rooks4Knights4x4board() {
        int rows = 4;
        int columns = 4;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(R, R, N, N, N, N));
        SolutionFinder solutionFinder = new SolutionFinder(rows, columns, pieces);
        
        int solutions = solutionFinder.findSolutions(c -> {});
        
        assertThat(solutions).isEqualTo(8);
    }

    @Test
    public void test8Queens8x8board() {
        int rows = 8;
        int columns = 8;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(Q, Q, Q, Q, Q, Q, Q, Q));
        SolutionFinder solutionFinder = new SolutionFinder(rows, columns, pieces);
        
        int solutions = solutionFinder.findSolutions(c -> {});
        
        assertThat(solutions).isEqualTo(92);
    }

    @Ignore("Too long for a unit test")
    @Test
    public void test2Kings2Queens2Bishops1Knight7x7board() {
        int rows = 7;
        int columns = 7;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(Q, Q, B, B, K, K, N));
        SolutionFinder solutionFinder = new SolutionFinder(rows, columns, pieces);
        
        int solutions = solutionFinder.findSolutions(c -> {});
        
        assertThat(solutions).isEqualTo(3063828);
    }
}
