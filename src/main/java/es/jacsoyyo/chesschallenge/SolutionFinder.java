package es.jacsoyyo.chesschallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Finds solutions
 *
 * @author jacobo
 */
public class SolutionFinder {

    private final Board board;
    private final List<Piece> pieces;

    /**
     * Creates a new finder
     *
     * @param rows board number of rows
     * @param columns board number of columns
     * @param pieces pieces to arrange on the board
     */
    public SolutionFinder(int rows, int columns, List<Piece> pieces) {
        this.board = new Board(rows, columns);
        this.pieces = new ArrayList(pieces);
        Collections.sort(this.pieces);
    }

    /**
     * Returns found solutions
     *
     * @param solutionHandler called for every solution found
     */
    public void findSolutions(SolutionHandler solutionHandler) {

        List<Integer> safeSquares = board.getSquares();
        Map<Integer, Piece> candidate = new HashMap<>(pieces.size());

        // Try to place pieces
        placePieces(pieces, safeSquares, candidate, new HashMap<>(), solutionHandler);
    }

    /**
     * Recursive method the tries to place the remaning pieces.
     *
     * @param pieces remaining pieces to place on the board (at lease one)
     * @param safeSquares list of safe squares (not threatened by previous
     * placed pieces)
     * @param candidate pieces already set on the board
     * @param triedPiecePosition higher already position tried for each piece (to avoid duplicates)
     * @param solutionHandler called for every solution
     */
    private void placePieces(List<Piece> pieces, List<Integer> safeSquares, Map<Integer, Piece> candidate, Map<Piece, Integer> triedPiecePosition, SolutionHandler solutionHandler) {
        Piece piece = pieces.get(0);
        List<Piece> remainingPieces = new ArrayList<>(pieces);
        remainingPieces.remove(0);
        // for every safe square remaining
        for (Integer candidateSquare : safeSquares) {
            Integer lastTriedPosition = (triedPiecePosition.get(piece) != null ? triedPiecePosition.get(piece) : -1);
            if (candidateSquare > lastTriedPosition) {
                Map<Piece, Integer> newTriedPiecePosition = new HashMap<>();
                newTriedPiecePosition.put(piece, candidateSquare);
                List<Integer> candidateSafeSquares = new ArrayList<>(safeSquares);
                candidateSafeSquares.remove(candidateSquare);
                try {
                    board.placePiece(piece, candidateSquare, candidateSafeSquares, candidate);
                    Map<Integer, Piece> newCandidate = new HashMap<>(candidate);
                    newCandidate.put(candidateSquare, piece);
                    if (!remainingPieces.isEmpty()) {
                        // try to place remaining pieces
                        placePieces(remainingPieces, candidateSafeSquares, newCandidate, newTriedPiecePosition, solutionHandler);
                    } else {
                        // we got to a solution
                        solutionHandler.handleSolution(newCandidate);
                    }
                } catch (ThreatensOccupiedSquare e) {
                    // candidate failed
                }
            }
        }
    }

    /**
     * Handler for solutions
     */
    public interface SolutionHandler {

        /**
         * Method called for every solution
         * 
         * @param solution as a map with pieces position
         */
        void handleSolution(Map<Integer, Piece> solution);
    }
}
