package es.jacsoyyo.chesschallenge;

import es.jacsoyyo.chesschallenge.pieces.Piece;
import java.util.ArrayList;
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
    private SolutionHandler solutionHandler;
    
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
    }

    /**
     * Find solutions and call the handler for each one of them
     *
     * @param solutionHandler called for every solution found
     */
    public void findSolutions(SolutionHandler solutionHandler) {

        this.solutionHandler = solutionHandler;

        Map<Piece, Integer> triedPiecePosition = new HashMap<>();
        
        placePieces(pieces, triedPiecePosition);
    }

    /**
     * Recursive method the tries to place the remaning pieces.
     *
     * @param pieces remaining pieces to place on the board (at lease one)
     * @param triedPiecePosition higher already position tried for each piece (to avoid duplicates)
     */
    private void placePieces(List<Piece> pieces, Map<Piece, Integer> triedPiecePosition) {
        Piece piece = pieces.get(0);
        List<Piece> remainingPieces = new ArrayList<>(pieces);
        remainingPieces.remove(0);
        // for every safe square remaining
        for (Integer candidateSquare : board.getSafeSquares()) {
            Integer lastTriedPosition = (triedPiecePosition.get(piece) != null ? triedPiecePosition.get(piece) : -1);
            if (candidateSquare > lastTriedPosition) {
                Map<Piece, Integer> newTriedPiecePosition = new HashMap<>();
                newTriedPiecePosition.put(piece, candidateSquare);
                board.pushState();
                try {
                    board.placePiece(piece, candidateSquare);
                    if (!remainingPieces.isEmpty()) {
                        // try to place remaining pieces
                        placePieces(remainingPieces, newTriedPiecePosition);
                    } else {
                        // we got to a solution
                        this.solutionHandler.handleSolution(board.getPlacedPieces());
                    }
                } catch (ThreatensOccupiedSquare e) {
                    // candidate failed
                } finally {
                    board.popState();
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
