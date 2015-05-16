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

    private void updateSquares(Integer position, List<Integer> safeSquares, Set<Integer> occupiedSquares) throws ThreatensOccupiedSquare{
        if (occupiedSquares.contains(position)){
            throw new ThreatensOccupiedSquare();
        }
        safeSquares.remove(position);
    }

    void placePiece(Piece piece, Integer candidateSquare, List<Integer> candidateSafeSquares) throws ThreatensOccupiedSquare {
        calculatePieceMoves(piece, candidateSquare, candidateSafeSquares);
        occupiedSquares.add(candidateSquare);
        candidate.put(candidateSquare, piece);
    }
    
    void removePiece(Integer candidateSquare) {
        occupiedSquares.remove(candidateSquare);
        candidate.remove(candidateSquare);
       
    }

    private void calculatePieceMoves(Piece piece, Integer position, List<Integer> safeSquares) throws ThreatensOccupiedSquare {
        int row = position / rows;
        int column = position - (row * columns);
        switch (piece) {
            case KING:
                for (int i = row - 1; i < row + 2 && i < rows; i++) {
                    if (i >= 0) {
                        for (int j = column - 1; j < column + 2 && j < columns; j++) {
                            if (j >= 0) {
                                updateSquares(j + i * columns, safeSquares, occupiedSquares);
                            }
                        }
                    }
                }
                break;
            case QUEEN:
                calculatePieceMoves(ROOK, position, safeSquares);
                calculatePieceMoves(BISHOP, position, safeSquares);
                break;
            case BISHOP:
                for (int i = row, j = column; i < rows && j < columns; i++, j++) {
                    updateSquares(j + i * columns, safeSquares, occupiedSquares);
                }
                for (int i = row, j = column; i < rows && j >= 0; i++, j--) {
                    updateSquares(j + i * columns, safeSquares, occupiedSquares);
                }
                for (int i = row, j = column; i >= 0 && j < columns; i--, j++) {
                    updateSquares(j + i * columns, safeSquares, occupiedSquares);
                }
                for (int i = row, j = column; i >= 0 && j >= 0; i--, j--) {
                    updateSquares(j + i * columns, safeSquares, occupiedSquares);
                }
                break;
            case ROOK:
                for (int i = 0; i < columns; i++) {
                    updateSquares(i + row * columns, safeSquares, occupiedSquares);
                }
                for (int i = 0; i < rows; i++) {
                    updateSquares(column + i * columns, safeSquares, occupiedSquares);
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
                                    updateSquares(nColumn + nRow * columns, safeSquares, occupiedSquares);
                                }
                            }
                        }
                    }
                }
                break;
            default:
        }
    }    

}
