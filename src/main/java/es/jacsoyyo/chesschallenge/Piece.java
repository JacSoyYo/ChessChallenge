package es.jacsoyyo.chesschallenge;

/**
 * Chess pieces (except pawn)
 *
 * @author jacobo
 */
public enum Piece {

    K, Q, B, R, N;

    /**
     * Calculates all possible movements, calling the provided method for each
     * one
     *
     * @param position
     * @param rows number of rows
     * @param columns number of columns
     * @param threatenedSquare method to call for each threatened square
     * @throws ThreatensOccupiedSquare if it threatens a occupied square
     */
    public void threatenedSquares(Integer position, int rows, int columns, UpdateSquare threatenedSquare) {
        int row = position / rows;
        int column = position - (row * columns);
        switch (this) {
            case K:
                for (int i = row - 1; i < row + 2 && i < rows; i++) {
                    if (i >= 0) {
                        for (int j = column - 1; j < column + 2 && j < columns; j++) {
                            if (j >= 0 && (i != row || j != column)) {
                                threatenedSquare.markUnsafe(j + i * columns);
                            }
                        }
                    }
                }
                break;
            case Q:
                R.threatenedSquares(position, rows, columns, threatenedSquare);
                B.threatenedSquares(position, rows, columns, threatenedSquare);
                break;
            case B:
                for (int i = row + 1, j = column + 1; i < rows && j < columns; i++, j++) {
                    threatenedSquare.markUnsafe(j + i * columns);
                }
                for (int i = row + 1, j = column - 1; i < rows && j >= 0; i++, j--) {
                    threatenedSquare.markUnsafe(j + i * columns);
                }
                for (int i = row - 1, j = column + 1; i >= 0 && j < columns; i--, j++) {
                    threatenedSquare.markUnsafe(j + i * columns);
                }
                for (int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--, j--) {
                    threatenedSquare.markUnsafe(j + i * columns);
                }
                break;
            case R:
                for (int j = 0; j < column; j++) {
                    threatenedSquare.markUnsafe(j + row * columns);
                }
                for (int j = column + 1; j < columns; j++) {
                    threatenedSquare.markUnsafe(j + row * columns);
                }
                for (int i = 0; i < row; i++) {
                    threatenedSquare.markUnsafe(column + i * columns);
                }
                for (int i = row + 1; i < rows; i++) {
                    threatenedSquare.markUnsafe(column + i * columns);
                }
                break;
            case N:
                for (int i = 1; i <= 2; i++) {
                    for (int x = -1; x <= 1; x += 2) {
                        int nRow = row + i * x;
                        if (nRow >= 0 && nRow < rows) {
                            int j = i == 1 ? 2 : 1;
                            for (int y = -1; y <= 1; y += 2) {
                                int nColumn = column + j * y;
                                if (nColumn >= 0 && nColumn < columns) {
                                    threatenedSquare.markUnsafe(nColumn + nRow * columns);
                                }
                            }
                        }
                    }
                }
                break;
            default:
        }
    }

    /**
     * Callback called for every square visited by the piece
     */
    public interface UpdateSquare {

        /**
         * Called for every possible pice movement
         * 
         * @param position
         * @throws ThreatensOccupiedSquare throw if the square is occupied
         */
        void markUnsafe(Integer position);
    }

}
