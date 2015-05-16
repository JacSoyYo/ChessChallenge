package es.jacsoyyo.chesschallenge;

import static es.jacsoyyo.chesschallenge.Piece.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jacobo on 13/05/15.
 */
public class ChessChallenge {

    public static void main(String[] args) {
        // Init
        // TODO read parameters
        int rows = 8;
        int columns = 8;
        List<Piece> pieces = new ArrayList<>(Arrays.asList(QUEEN, QUEEN, QUEEN, QUEEN, QUEEN, QUEEN, QUEEN, QUEEN));

        ChessChallenge chessChallenge = new ChessChallenge(rows, columns, pieces);

        chessChallenge.doChallenge();
    }

    private int rows;
    private int columns;
    private List<Piece> pieces;

    public ChessChallenge(int rows, int columns, List<Piece> pieces) {
        this.rows = rows;
        this.columns = columns;
        this.pieces = pieces;
    }

    public int doChallenge() {

        // All squares are safe
        List<Integer> safeSquares = new ArrayList<>(rows * columns);
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                safeSquares.add(column + row * columns);
            }
        }

        // No occupied squares
        Set<Integer> occupiedSquares = new HashSet<>(pieces.size());

        Map<Integer, Piece> candidate = new HashMap<>(pieces.size());
        Set<Map<Integer, Piece>> solutions = new HashSet<>();

        // Try to place every piece on every square
        placePieces(pieces, safeSquares, occupiedSquares, candidate, solutions);

        System.out.println("Solutions: " + solutions.size());
        for (Map<Integer, Piece> solution : solutions) {
            for (Integer position : solution.keySet()) {
                System.out.print(position + " - " + solution.get(position) + " : ");
            }
            System.out.println();
        }
        return solutions.size();
    }

    private void placePieces(List<Piece> pieces, List<Integer> safeSquares, Set<Integer> occupiedSquares, Map<Integer, Piece> candidate, Set<Map<Integer, Piece>> solutions) {
        Piece piece = pieces.get(0);
        pieces.remove(0);
        // for every safe square remaining
        for (Integer candidateSquare : safeSquares) {
            List<Integer> candidateSafeSquares = new ArrayList<>(safeSquares);
            candidateSafeSquares.remove(candidateSquare);
            try {
                calculatePieceMoves(piece, candidateSquare, candidateSafeSquares, occupiedSquares);
                occupiedSquares.add(candidateSquare);
                candidate.put(candidateSquare, piece);
                // try to place remaining pieces
                if (!pieces.isEmpty()) {
                    placePieces(pieces, candidateSafeSquares, occupiedSquares, candidate, solutions);
                } else {
                    Map<Integer, Piece> solution = new HashMap<>(candidate);
                    solutions.add(solution);
                }
                occupiedSquares.remove(candidateSquare);
                candidate.remove(candidateSquare);
            } catch (ThreatensOccupiedSquare e) {
                // skip
            }
        }
        pieces.add(piece);
    }

    private void calculatePieceMoves(Piece piece, Integer position, List<Integer> safeSquares, Set<Integer> occupiedSquares) throws ThreatensOccupiedSquare {
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
                calculatePieceMoves(ROOK, position, safeSquares, occupiedSquares);
                calculatePieceMoves(BISHOP, position, safeSquares, occupiedSquares);
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
    
    private void updateSquares(Integer position, List<Integer> safeSquares, Set<Integer> occupiedSquares) throws ThreatensOccupiedSquare{
        if (occupiedSquares.contains(position)){
            throw new ThreatensOccupiedSquare();
        }
        safeSquares.remove(position);
    }

}
