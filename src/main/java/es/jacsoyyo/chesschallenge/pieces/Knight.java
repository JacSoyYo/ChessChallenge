/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jacsoyyo.chesschallenge.pieces;

/**
 *
 * @author jacobo
 */
public class Knight extends Piece {

    @Override
    public void visitPossibleMoves(int row, int column, int rows, int columns, SquareVisitor squareVisitor) {
        for (int i = 1; i <= 2; i++) {
            for (int x = -1; x <= 1; x += 2) {
                int nRow = row + i * x;
                if (nRow >= 0 && nRow < rows) {
                    int j = i == 1 ? 2 : 1;
                    for (int y = -1; y <= 1; y += 2) {
                        int nColumn = column + j * y;
                        if (nColumn >= 0 && nColumn < columns) {
                            squareVisitor.visitSquare(nColumn + nRow * columns);
                        }
                    }
                }
            }
        }
    }

}
