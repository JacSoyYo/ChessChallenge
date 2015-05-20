package es.jacsoyyo.chesschallenge;

import static es.jacsoyyo.chesschallenge.Piece.B;
import static es.jacsoyyo.chesschallenge.Piece.K;
import static es.jacsoyyo.chesschallenge.Piece.N;
import static es.jacsoyyo.chesschallenge.Piece.Q;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jacobo on 13/05/15.
 */
public class ChessChallenge {

    public static void main(String[] args) {
        ChessChallenge chessChallenge = new ChessChallenge();
        chessChallenge.doChallange(args);
    }

    private void doChallange(String[] args) throws NumberFormatException {
        int rows;
        int columns;
        List<Piece> pieces = new ArrayList<>();
        if (args.length == 0) {
            System.out.println("No parameters? OK, will do 7x7 K,K,Q,Q,B,B,N then.");
            rows = 7;
            columns = 7;
            pieces = new ArrayList<>(Arrays.asList(Q, Q, B, B, K, K, N));
        } else if (args.length != 3) {
            printInstructions();
            return;
        } else {
            rows = Integer.parseInt(args[0]);
            columns = Integer.parseInt(args[1]);
            List<String> piecesS = Arrays.asList(args[2].split(","));
            for (String piece : piecesS) {
                pieces.add(Piece.valueOf(piece));
            }
        }

        List<Map<Integer, Piece>> solutions = new ArrayList<>();

        SolutionFinder chessChallenge = new SolutionFinder(rows, columns, pieces);
        chessChallenge.findSolutions(c -> {
            Map<Integer, Piece> solution = new HashMap<>(c);
            solutions.add(solution);
        });

        //printSolutions(solutions);
    }

    private static void printSolutions(Collection<Map<Integer, Piece>> solutions) {

        System.out.println();
        for (Map<Integer, Piece> solution : solutions) {
            for (Integer position : solution.keySet()) {
                System.out.print(position + " - " + solution.get(position) + " : ");
            }
            System.out.println();
        }
    }

    private void printInstructions() {
        System.out.println("Invalid number of arguments!");
        System.out.println("Usage: ChessChallenge M N P,P,P...");
        System.out.println();
        System.out.println("M\tnumber of rows");
        System.out.println("N\tnumber of columns");
        System.out.println("P,P,P...\tcomma separated list of pieces: (K)ing, (Q)ueen, (B)ishop, (R)ook or k(N)ight.");
        System.out.println();
        System.out.println("Example: 7 7 K,K,Q,Q,B,B,N");
    }

}
