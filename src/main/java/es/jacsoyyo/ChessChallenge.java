package es.jacsoyyo;

import java.util.*;

/**
 * Created by jacobo on 13/05/15.
 */
public class ChessChallenge {

    public static void main(String[] args) {
        // Init
        // TODO read parameters
        int rows = 8;
        int columns = 8;
        List<String> pieces = new ArrayList<>(Arrays.asList("Q", "Q", "Q", "Q", "Q", "Q", "Q", "Q"));

        ChessChallenge chessChallenge = new ChessChallenge(rows, columns, pieces);

        chessChallenge.doChallenge();
    }

    private int rows;
    private int columns;
    private List<String> pieces;

    public ChessChallenge(int rows, int columns, List<String> pieces) {
        this.rows = rows;
        this.columns = columns;
        this.pieces = pieces;
    }

    public void doChallenge() {

        // All squares are safe
        Set<Integer> safeSquares = new HashSet<>(rows * columns);
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                safeSquares.add(column + row * columns);
            }
        }

        // No occupied squares
        Set<Integer> occupiedSquares = new HashSet<>(pieces.size());

        Map<Integer, String> candidate = new HashMap<>(pieces.size());
        Set<Map<Integer, String>> solutions = new HashSet<>();

        // Try to place every piece on every square
        placePieces(pieces, safeSquares, occupiedSquares, candidate, solutions);

        System.out.println("Solutions: " + solutions.size());
        for (Map<Integer, String> solution : solutions) {
            for (Integer position : solution.keySet()) {
                System.out.print(position + " - " + solution.get(position) + " : ");
            }
            System.out.println();
        }
    }

    private void placePieces(List<String> pieces, Set<Integer> safeSquares, Set<Integer> occupiedSquares, Map<Integer, String> candidate, Set<Map<Integer, String>> solutions) {
        String piece = pieces.get(0);
        List<String> remainingPieces = new ArrayList<>(pieces);
        remainingPieces.remove(0);
        // for every safe square remaining
        for (Integer candidateSquare : safeSquares) {
            Set<Integer> threatenedSquares = threatenedSquares(piece, candidateSquare); //TODO calculate threatened squares
            if (Collections.disjoint(threatenedSquares, occupiedSquares)) {
                // update safe squares (threatens any other piece? -> skip)
                Set<Integer> candidateOccupiedSquares = new HashSet<>(occupiedSquares);
                candidateOccupiedSquares.add(candidateSquare);
                Map<Integer, String> newCandidate = new HashMap<>(candidate);
                newCandidate.put(candidateSquare, piece);
                Set<Integer> candidateSafeSquares = new HashSet<>(safeSquares);
                candidateSafeSquares.removeAll(threatenedSquares);
                candidateSafeSquares.remove(candidateSquare);
                // try to place remaining pieces
                if (!remainingPieces.isEmpty()) {
                    placePieces(remainingPieces, candidateSafeSquares, candidateOccupiedSquares, newCandidate, solutions);
                } else {
                    solutions.add(newCandidate);
                }
            }
        }
    }

    private Set<Integer> threatenedSquares(String piece, Integer position) {
        int row = position / rows;
        int column = position - (row * columns);
        Set<Integer> threatenedSquares = new HashSet<>();
        switch (piece) {
            case "K":
                for (int i = row - 1; i < row + 2 && i < rows; i++) {
                    if (i >= 0) {
                        for (int j = column - 1; j < column + 2 && j < columns; j++) {
                            if (j >= 0) {
                                threatenedSquares.add(j + i * columns);
                            }
                        }
                    }
                }
                break;
            case "Q":
                threatenedSquares.addAll(threatenedSquares("R", position));
                threatenedSquares.addAll(threatenedSquares("B", position));
                break;
            case "B":
                for (int i = row, j = column; i < rows && j < columns; i++, j++) {
                    threatenedSquares.add(j + i * columns);
                }
                for (int i = row, j = column; i < rows && j >= 0; i++, j--) {
                    threatenedSquares.add(j + i * columns);
                }
                for (int i = row, j = column; i >= 0 && j < columns; i--, j++) {
                    threatenedSquares.add(j + i * columns);
                }
                for (int i = row, j = column; i >= 0 && j >= 0; i--, j--) {
                    threatenedSquares.add(j + i * columns);
                }
                break;
            case "R":
                for (int i = 0; i < columns; i++) {
                    threatenedSquares.add(i + row * columns);
                }
                for (int i = 0; i < rows; i++) {
                    threatenedSquares.add(column + i * columns);
                }
                break;
            case "N":
                for (int i = 1; i <= 2; i++) {
                    for (int x = -1; x <= 1; x += 2) {
                        int nRow = row + i * x;
                        if (nRow >= 0 && nRow < rows) {
                            int j = i == 1 ? 2 : 1;
                            for (int y = -1; y <= 1; y += 2) {
                                int nColumn = column + j * y;
                                if (nColumn >= 0 && nColumn < columns) {
                                    threatenedSquares.add(nColumn + nRow * columns);
                                }
                            }
                        }
                    }
                }
                break;
            default:
        }
        return threatenedSquares;
    }
}
