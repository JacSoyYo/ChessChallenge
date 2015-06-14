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
public class Rook extends Piece {

    @Override
    public void visitPossibleMoves(int row, int column, int rows, int columns, SquareVisitor squareVisitor) {
        for (int j = 0; j < column; j++) {
            squareVisitor.visitSquare(j + row * columns);
        }
        for (int j = column + 1; j < columns; j++) {
            squareVisitor.visitSquare(j + row * columns);
        }
        for (int i = 0; i < row; i++) {
            squareVisitor.visitSquare(column + i * columns);
        }
        for (int i = row + 1; i < rows; i++) {
            squareVisitor.visitSquare(column + i * columns);
        }
    }

}
