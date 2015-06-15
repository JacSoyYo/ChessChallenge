package es.jacsoyyo.chesschallenge;

import es.jacsoyyo.chesschallenge.pieces.Piece;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The board
 *
 * @author jacobo
 */
public class Board {

    private final int rows;
    private final int columns;

    private State state;
    
    private final Deque<State> savedStates;
    
    /**
     * Creates a new board with the indicated dimensions
     *
     * @param rows
     * @param columns
     */
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        this.savedStates = new ArrayDeque<>();
        
        this.state = new State();
        
        // All squares are safe
        this.state.safeSquares = new ArrayList<>(rows * columns);
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                this.state.safeSquares.add(column + row * columns);
            }
        }

        this.state.placedPieces = new HashMap<>();
    }

    /**
     * Returns squares
     *
     * @return list of squares positions (starting at 0)
     */
    public List<Integer> getSquares() {
        return Collections.unmodifiableList(this.state.safeSquares);
    }

    public List<Integer> getSafeSquares() {
        return Collections.unmodifiableList(this.state.safeSquares);
    }

    public Map<Integer, Piece> getPlacedPieces() {
        return this.state.placedPieces;
    }

    private void updateSquares(int position) {
        if (this.state.placedPieces.keySet().contains(position)) {
            throw new ThreatensOccupiedSquare();
        }
        this.state.safeSquares.remove(new Integer(position));
    }

    public void placePiece(Piece piece, int position) {
        int row = position / rows;
        int column = position - (row * columns);        
        piece.visitPossibleMoves(row, column, rows, columns, p -> updateSquares(p));
        this.state.placedPieces.put(position, piece);
        this.state.safeSquares.remove(new Integer(position));
    }

    public void pushState(){
        savedStates.push(this.state);
        this.state = this.state.cloneState();
    }
    
    public void popState(){
        this.state = savedStates.pop();
    }
    
    private class State {
        private Map<Integer, Piece> placedPieces;
        private List<Integer> safeSquares;
        
        public State cloneState(){
            State newState = new State();
            newState.placedPieces = new HashMap<>(placedPieces);
            newState.safeSquares = new ArrayList<>(safeSquares);
            return newState;
        }
    }
}
