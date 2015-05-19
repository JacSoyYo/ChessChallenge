package es.jacsoyyo.chesschallenge;

import static es.jacsoyyo.chesschallenge.Piece.QUEEN;
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
        // Init
        // TODO read parameters
        int rows = 8;
        int columns = 8;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(QUEEN, QUEEN, QUEEN, QUEEN, QUEEN, QUEEN, QUEEN, QUEEN));

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
