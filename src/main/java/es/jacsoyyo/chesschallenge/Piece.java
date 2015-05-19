package es.jacsoyyo.chesschallenge;

/**
 * Chess pieces (except pawn)-
 *
 * @author jacobo
 */
public enum Piece {

    KING, QUEEN, BISHOP, ROOK, KNIGHT;

    /**
     * Calculates all possible movements, calling the provided method for each
     * threatened squade
     *
     * @param position
     * @param rows number of rows
     * @param columns number of columns
     * @param threatenedSquare method to call for each threatened square
     * @throws ThreatensOccupiedSquare if it threatens a occupied square
     */
    public void threatenedSquares(Integer position, int rows, int columns, ThreatenedSquare threatenedSquare) throws ThreatensOccupiedSquare {
        int row = position / rows;
        int column = position - (row * columns);
        switch (this) {
            case KING:
                for (int i = row - 1; i < row + 2 && i < rows; i++) {
                    if (i >= 0) {
                        for (int j = column - 1; j < column + 2 && j < columns; j++) {
                            if (j >= 0) {
                                threatenedSquare.markUnsafe(j + i * columns);
                            }
                        }
                    }
                }
                break;
            case QUEEN:
                ROOK.threatenedSquares(position, rows, columns, threatenedSquare);
                BISHOP.threatenedSquares(position, rows, columns, threatenedSquare);
                break;
            case BISHOP:
                for (int i = row, j = column; i < rows && j < columns; i++, j++) {
                    threatenedSquare.markUnsafe(j + i * columns);
                }
                for (int i = row, j = column; i < rows && j >= 0; i++, j--) {
                    threatenedSquare.markUnsafe(j + i * columns);
                }
                for (int i = row, j = column; i >= 0 && j < columns; i--, j++) {
                    threatenedSquare.markUnsafe(j + i * columns);
                }
                for (int i = row, j = column; i >= 0 && j >= 0; i--, j--) {
                    threatenedSquare.markUnsafe(j + i * columns);
                }
                break;
            case ROOK:
                for (int i = 0; i < columns; i++) {
                    threatenedSquare.markUnsafe(i + row * columns);
                }
                for (int i = 0; i < rows; i++) {
                    threatenedSquare.markUnsafe(column + i * columns);
                }
                break;
            case KNIGHT:
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

    interface ThreatenedSquare {

        void markUnsafe(Integer position) throws ThreatensOccupiedSquare;
    }

}
