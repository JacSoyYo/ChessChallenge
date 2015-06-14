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
public class Bishop extends Piece {

    @Override
    public void visitPossibleMoves(int row, int column, int rows, int columns, SquareVisitor squareVisitor) {
        for (int i = row + 1, j = column + 1; i < rows && j < columns; i++, j++) {
            squareVisitor.visitSquare(j + i * columns);
        }
        for (int i = row + 1, j = column - 1; i < rows && j >= 0; i++, j--) {
            squareVisitor.visitSquare(j + i * columns);
        }
        for (int i = row - 1, j = column + 1; i >= 0 && j < columns; i--, j++) {
            squareVisitor.visitSquare(j + i * columns);
        }
        for (int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--, j--) {
            squareVisitor.visitSquare(j + i * columns);
        }
    }

}
