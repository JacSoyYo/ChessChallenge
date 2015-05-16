/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jacsoyyo.chesschallenge;

import static es.jacsoyyo.chesschallenge.Piece.BISHOP;
import static es.jacsoyyo.chesschallenge.Piece.ROOK;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author jacobo
 */
public class Board {

    private int rows;
    private int columns;

    private final List<Integer> squares;
    private final Set<Integer> occupiedSquares;
    private final Map<Integer, Piece> candidate;

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
        
        // No occupied squares
        occupiedSquares = new HashSet<>(rows * columns);

        candidate = new HashMap<>(rows * columns);
    }

    public List<Integer> getSquares() {
        return squares;
    }

    public Map<Integer, Piece> getCandidate() {
        return candidate;
    }

    private void updateSquares(Integer position, List<Integer> safeSquares) throws ThreatensOccupiedSquare{
        if (occupiedSquares.contains(position)){
            throw new ThreatensOccupiedSquare();
        }
        safeSquares.remove(position);
    }

    void placePiece(Piece piece, Integer candidateSquare, List<Integer> candidateSafeSquares) throws ThreatensOccupiedSquare {
        piece.threatenedSquares(candidateSquare, rows, columns, p -> updateSquares(p, candidateSafeSquares));
        occupiedSquares.add(candidateSquare);
        candidate.put(candidateSquare, piece);
    }
    
    void removePiece(Integer candidateSquare) {
        occupiedSquares.remove(candidateSquare);
        candidate.remove(candidateSquare);
       
    }

}
