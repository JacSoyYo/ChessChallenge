package es.jacsoyyo.chesschallenge;

import static es.jacsoyyo.chesschallenge.Piece.QUEEN;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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

        ChessChallenge chessChallenge = new ChessChallenge(rows, columns, pieces);

        chessChallenge.doChallenge();
    }

    private Board board;
    private List<Piece> pieces;

    public ChessChallenge(int rows, int columns, List<Piece> pieces) {
        this.board = new Board(rows, columns);
        this.pieces = pieces;
    }

    public int doChallenge() {

        Set<Map<Integer, Piece>> solutions = new HashSet<>();

        List<Integer> safeSquares = board.getSquares();
        Map<Integer, Piece> candidate = new HashMap<>(pieces.size());
        
        long before = System.currentTimeMillis();
        // Try to place pieces
        placePieces(pieces, board, safeSquares, candidate, solutions);
        long after = System.currentTimeMillis();
        
        printSolutions(solutions, after, before);
        return solutions.size();
    }

    private void placePieces(List<Piece> pieces, Board board, List<Integer> safeSquares, Map<Integer, Piece> candidate, Set<Map<Integer, Piece>> solutions) {
        Piece piece = pieces.get(0);
        List<Piece> remainingPieces = new ArrayList<>(pieces);
        remainingPieces.remove(0);
        // for every safe square remaining
        safeSquares.parallelStream().forEach((candidateSquare) -> {
            List<Integer> candidateSafeSquares = new ArrayList<>(safeSquares);
            candidateSafeSquares.remove(candidateSquare);
            try {
                board.placePiece(piece, candidateSquare, candidateSafeSquares, candidate);
                Map<Integer, Piece> newCandidate = new HashMap<>(candidate);
                newCandidate.put(candidateSquare, piece);
                if (!remainingPieces.isEmpty()) {
                    // try to place remaining pieces
                    placePieces(remainingPieces, board, candidateSafeSquares, newCandidate, solutions);
                } else {
                    // we got to a solution
                    Map<Integer, Piece> solution = new HashMap<>(newCandidate);
                    synchronized (solutions){
                        solutions.add(solution);
                    }
                }
            } catch (ThreatensOccupiedSquare e) {
                // candidate failed
            }
        });
    }

    private void printSolutions(Set<Map<Integer, Piece>> solutions, long after, long before) {
        System.out.println(solutions.size() + " solutions found in " + (after - before) + "ms");
        System.out.println();
        for (Map<Integer, Piece> solution : solutions) {
            for (Integer position : solution.keySet()) {
                System.out.print(position + " - " + solution.get(position) + " : ");
            }
            System.out.println();
        }
    }

}
