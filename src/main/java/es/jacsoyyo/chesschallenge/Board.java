/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jacsoyyo.chesschallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jacobo
 */
public class Board {

    private int rows;
    private int columns;

    private final List<Integer> squares;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        
        // All squares are safe
        squares = new ArrayList<>(rows * columns);
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                squares.add(column + row * columns);
            }
        }
        
    }

    public List<Integer> getSquares() {
        return squares;
    }

    private void updateSquares(Integer position, List<Integer> safeSquares, Map<Integer, Piece> candidate) throws ThreatensOccupiedSquare{
        if (candidate.keySet().contains(position)){
            throw new ThreatensOccupiedSquare();
        }
        safeSquares.remove(position);
    }

    void placePiece(Piece piece, Integer candidateSquare, List<Integer> candidateSafeSquares, Map<Integer, Piece> candidate) throws ThreatensOccupiedSquare {
        piece.threatenedSquares(candidateSquare, rows, columns, p -> updateSquares(p, candidateSafeSquares, candidate));
    }
    
}
