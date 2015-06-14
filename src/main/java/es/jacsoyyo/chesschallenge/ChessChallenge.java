package es.jacsoyyo.chesschallenge;

import es.jacsoyyo.chesschallenge.pieces.King;
import es.jacsoyyo.chesschallenge.pieces.Piece;
import es.jacsoyyo.chesschallenge.pieces.Knight;
import es.jacsoyyo.chesschallenge.pieces.Bishop;
import es.jacsoyyo.chesschallenge.pieces.Queen;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jacobo on 13/05/15.
 */
public class ChessChallenge {

    private static void doChallenge(SolutionFinder solutionFinder) {
        
        final AtomicInteger solutionCounter = new AtomicInteger(0);
        
        long before = System.currentTimeMillis();
        
        solutionFinder.findSolutions((Map<Integer, Piece> c) -> {
            solutionCounter.incrementAndGet();
            if (solutionCounter.get() % 100000 == 0) {
                System.out.println(solutionCounter.get() + " solutions so far");
            }
        });
        
        long after = System.currentTimeMillis();
        System.out.println(solutionCounter.get() + " solutions found in " + (after - before) + "ms");

    }
 
    public static void main(String[] args) {
        int rows;
        int columns;
        List<Piece> pieces = new ArrayList<>();
        
        if (args.length == 0) {
            System.out.println("No parameters? OK, will do 7x7 King,King,Queen,Queen,Bishop,Bishop,Knight then.");
            rows = 7;
            columns = 7;
            pieces = new ArrayList<>(Arrays.asList(new Queen(), new Queen(), new Bishop(), new Bishop(), new King(), new King(), new Knight()));
        } else if (args.length != 3) {
            printInstructions();
            return;
        } else {
            try {
                rows = Integer.parseInt(args[0]);
                columns = Integer.parseInt(args[1]);
                List<String> piecesS = Arrays.asList(args[2].split(","));
                for (String piece : piecesS) {
                    pieces.add((Piece) Class.forName("es.jacsoyyo.chesschallenge.pieces." + piece).newInstance());
                }
            } catch (Exception e) {
                printInstructions();
                return;
            }
        }
        
        SolutionFinder solutionFinder = new SolutionFinder(rows, columns, pieces);
        ChessChallenge.doChallenge(solutionFinder);
    }

    private static void printInstructions() {
        System.out.println("Invalid number of arguments!");
        System.out.println("Usage: ChessChallenge M N Piece,Piece,Piece...");
        System.out.println();
        System.out.println("M\tnumber of rows");
        System.out.println("N\tnumber of columns");
        System.out.println("Piece,Piece,Piece...\tcomma separated list of pieces: King, Queen, Bishop, Rook or Knight.");
        System.out.println();
        System.out.println("Example: 7 7 King,King,Queen,Queen,Bishop,Bishop,Knight");
    }

}
