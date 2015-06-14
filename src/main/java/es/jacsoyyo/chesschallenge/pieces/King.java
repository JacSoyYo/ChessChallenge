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
public class King extends Piece {

    @Override
    public void visitPossibleMoves(int row, int column, int rows, int columns, SquareVisitor squareVisitor) {
        for (int i = row - 1; i < row + 2 && i < rows; i++) {
            if (i >= 0) {
                for (int j = column - 1; j < column + 2 && j < columns; j++) {
                    if (j >= 0 && (i != row || j != column)) {
                        squareVisitor.visitSquare(j + i * columns);
                    }
                }
            }
        }
    }

}
