package es.jacsoyyo.chesschallenge.pieces;

/**
 * Chess pieces (except pawn)
 *
 * @author jacobo
 */
public abstract class Piece {

    public abstract void visitPossibleMoves(int row, int column, int rows, int columns, SquareVisitor squareVisitor);
    
    /**
     * Callback called for every square visited by the piece
     */
    public interface SquareVisitor {

        /**
         * Called for every possible pice movement
         * 
         * @param position
         * @throws ThreatensOccupiedSquare throw if the square is occupied
         */
        void visitSquare(int position);
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass();
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

    
}
