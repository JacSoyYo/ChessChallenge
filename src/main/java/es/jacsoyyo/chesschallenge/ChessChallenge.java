package es.jacsoyyo.chesschallenge;

import static es.jacsoyyo.chesschallenge.Piece.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jacobo on 13/05/15.
 */
public class ChessChallenge {

    public static void main(String[] args) {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");
        System.out.println("paralelismo" + System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism"));
        int rows;
        int columns;
        List<Piece> pieces = new ArrayList<>();
        if (args.length == 0) {
            System.out.println("No parameters? OK, will do 7x7 K,K,Q,Q,B,B,N then.");
            rows = 7;
            columns = 7;
            pieces = new ArrayList<>(Arrays.asList(Q, Q, B, B, K, K, N));
        } else if (args.length != 3) {
            System.out.println("Invalid number of arguments!");
            System.out.println("Usage: ChessChallenge M N P,P,P...");
            System.out.println();
            System.out.println("M\tnumber of rows");
            System.out.println("N\tnumber of columns");
            System.out.println("P,P,P...\tcomma separated list of pieces: (K)ing, (Q)ueen, (B)ishop, (R)ook or k(N)ight.");
            System.out.println();
            System.out.println("Example: 7 7 K,K,Q,Q,B,B,N");
            return;
        } else {
            rows = Integer.parseInt(args[0]);
            columns = Integer.parseInt(args[1]);
            List<String> piecesS = Arrays.asList(args[2].split(","));
            for (String piece : piecesS) {
                pieces.add(Piece.valueOf(piece));
            }
        }

        SolutionFinder chessChallenge = new SolutionFinder(rows, columns, pieces);

        Set<Map<Integer, Piece>> solutions = chessChallenge.findSolutions();

        printSolutions(solutions);
    }

    private static void printSolutions(Set<Map<Integer, Piece>> solutions) {

        System.out.println();
        for (Map<Integer, Piece> solution : solutions) {
            for (Integer position : solution.keySet()) {
                System.out.print(position + " - " + solution.get(position) + " : ");
            }
            System.out.println();
        }
    }

}
