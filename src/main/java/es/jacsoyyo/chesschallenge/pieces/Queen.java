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
public class Queen extends Piece {

    private static final Rook aRook = new Rook();
    private static final Bishop aBishop = new Bishop();
    
    @Override
    public void visitPossibleMoves(int row, int column, int rows, int columns, SquareVisitor squareVisitor) {
                aRook.visitPossibleMoves(row, column, rows, columns, squareVisitor);
                aBishop.visitPossibleMoves(row, column, rows, columns, squareVisitor);
    }
    
}
