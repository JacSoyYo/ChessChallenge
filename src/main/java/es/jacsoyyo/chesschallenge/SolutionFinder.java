package es.jacsoyyo.chesschallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Finds solutions
 *
 * @author jacobo
 */
public class SolutionFinder {

    private Board board;
    private List<Piece> pieces;

    /**
     * Creates a new finder
     *
     * @param rows board number of rows
     * @param columns board number of columns
     * @param pieces pieces to arrange on the board
     */
    public SolutionFinder(int rows, int columns, List<Piece> pieces) {
        this.board = new Board(rows, columns);
        this.pieces = pieces;
    }

    /**
     * Returns found solutions
     *
     * @return Set of solutions. Each one is a map where the key is the position
     * (number) of the square
     */
    public Set<Map<Integer, Piece>> findSolutions() {

        Set<Map<Integer, Piece>> solutions = new HashSet<>();

        List<Integer> safeSquares = board.getSquares();
        Map<Integer, Piece> candidate = new HashMap<>(pieces.size());

        long before = System.currentTimeMillis();
        // Try to place pieces
        placePieces(pieces, board, safeSquares, candidate, solutions);
        long after = System.currentTimeMillis();

        System.out.println(solutions.size() + " solutions found in " + (after - before) + "ms");

        return solutions;
    }

    /**
     * Recursive method the tries to place the remaning pieces. Stores the
     * candidate as a solution if there are no remaining pieces.
     *
     * @param pieces remaining pieces to place on the board (at lease one)
     * @param board the board
     * @param safeSquares list of safe squares (not threatened by previous
     * placed pieces)
     * @param candidate pieces already set on the board
     * @param solutions accumulated solutions
     */
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
                    synchronized (solutions) {
                        solutions.add(solution);
                    }
                }
            } catch (ThreatensOccupiedSquare e) {
                // candidate failed
            }
        });
    }

}
