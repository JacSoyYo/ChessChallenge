package es.jacsoyyo.chesschallenge;

import es.jacsoyyo.chesschallenge.pieces.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The board
 *
 * @author jacobo
 */
public class Board {

    private int rows;
    private int columns;

    private final List<Integer> squares;

    /**
     * Creates a new board with the indicated dimensions
     *
     * @param rows
     * @param columns
     */
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        // All squares are safe
        squares = new ArrayList<>(rows * columns);
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                squares.add(column + row * columns);
            }
        }

    }

    /**
     * Returns squares
     *
     * @return list of squares positions (starting at 0)
     */
    public List<Integer> getSquares() {
        return squares;
    }

    /**
     * Updates a square, removing it from safe squares. Throws exception if
     * square is occupied.
     *
     * @param position square to update
     * @param safeSquares list of safe (unthreatened) squares remaining
     * @param placedPieces already placed pieces
     * @throws ThreatensOccupiedSquare if the square is occupied
     */
    private void updateSquares(int position, List<Integer> safeSquares, Map<Integer, Piece> placedPieces) {
        if (placedPieces.keySet().contains(position)) {
            throw new ThreatensOccupiedSquare();
        }
        safeSquares.remove(new Integer(position));
    }

    /**
     * Places the piece on the square, removing threatened squares from remining
     * safe squares. Throws an exception if the piece threatens an already
     * placed piece.
     *
     * @param piece piece to place
     * @param position position
     * @param safeSquares board safe squares
     * @param placedPieces already placed pieces
     * @throws ThreatensOccupiedSquare if the piece would threaten another piece
     */
    public void placePiece(Piece piece, int position, List<Integer> safeSquares, Map<Integer, Piece> placedPieces) {
        int row = position / rows;
        int column = position - (row * columns);        
        piece.visitPossibleMoves(row, column, rows, columns, p -> updateSquares(p, safeSquares, placedPieces));
    }

}
