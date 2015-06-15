package es.jacsoyyo.chesschallenge;

import es.jacsoyyo.chesschallenge.pieces.Knight;
import es.jacsoyyo.chesschallenge.pieces.King;
import es.jacsoyyo.chesschallenge.pieces.Piece;
import es.jacsoyyo.chesschallenge.pieces.Bishop;
import es.jacsoyyo.chesschallenge.pieces.Queen;
import es.jacsoyyo.chesschallenge.pieces.Rook;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
        List<Piece> pieces = new ArrayList<>(Arrays.asList(new Rook(), new Rook(), new Rook(), new Rook()));
        SolutionFinder solutionFinder = new SolutionFinder(rows, columns, pieces);
        final AtomicInteger solutionCounter = new AtomicInteger(0);
        
        solutionFinder.findSolutions(c -> { solutionCounter.incrementAndGet(); });
        
        assertThat(solutionCounter.get()).isEqualTo(0);
    }

    @Test
    public void test2Kings1Rook3x3board() {
        int rows = 3;
        int columns = 3;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(new King(), new King(), new Rook()));
        SolutionFinder solutionFinder = new SolutionFinder(rows, columns, pieces);
        final AtomicInteger solutionCounter = new AtomicInteger(0);
        
        solutionFinder.findSolutions(c -> { solutionCounter.incrementAndGet(); });
        
        assertThat(solutionCounter.get()).isEqualTo(4);
    }

    @Test
    public void test2Rooks4Knights4x4board() {
        int rows = 4;
        int columns = 4;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(new Rook(), new Rook(), new Knight(), new Knight(), new Knight(), new Knight()));
        SolutionFinder solutionFinder = new SolutionFinder(rows, columns, pieces);
        final AtomicInteger solutionCounter = new AtomicInteger(0);
        
        solutionFinder.findSolutions(c -> { solutionCounter.incrementAndGet(); });
        
        assertThat(solutionCounter.get()).isEqualTo(8);
    }

    @Test
    public void test8Queens8x8board() {
        int rows = 8;
        int columns = 8;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(new Queen(), new Queen(), new Queen(), new Queen(), new Queen(), new Queen(), new Queen(), new Queen()));
        SolutionFinder solutionFinder = new SolutionFinder(rows, columns, pieces);
        final AtomicInteger solutionCounter = new AtomicInteger(0);
        
        solutionFinder.findSolutions(c -> { solutionCounter.incrementAndGet(); });
        
        assertThat(solutionCounter.get()).isEqualTo(92);
    }

    @Ignore("Too long for a unit test")
    @Test
    public void test2Kings2Queens2Bishops1Knight7x7board() {
        int rows = 7;
        int columns = 7;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(new Queen(), new Queen(), new Bishop(), new Bishop(), new King(), new King(), new Knight()));
        SolutionFinder solutionFinder = new SolutionFinder(rows, columns, pieces);
        final AtomicInteger solutionCounter = new AtomicInteger(0);
        
        solutionFinder.findSolutions(c -> { solutionCounter.incrementAndGet(); });
        
        assertThat(solutionCounter.get()).isEqualTo(3063828);
    }
}
