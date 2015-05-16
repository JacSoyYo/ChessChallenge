/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jacsoyyo.chesschallenge;

/**
 *
 * @author jacobo
 */
public enum Piece {
    KING, QUEEN, BISHOP, ROOK, KNIGHT;
    
    void threatenedSquares(Integer square, int rows, int columns, ThreatenedSquare threatenedSquare) throws ThreatensOccupiedSquare {
        int row = square / rows;
        int column = square - (row * columns);
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
                ROOK.threatenedSquares(square, rows, columns, threatenedSquare);
                BISHOP.threatenedSquares(square, rows, columns, threatenedSquare);
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
