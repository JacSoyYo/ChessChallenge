package es.jacsoyyo;

import java.util.*;

/**
 * Created by jacobo on 13/05/15.
 */
public class ChessChallenge {

    public static void main(String[] args) {
        // Init
        // TODO read parameters
        int rows = 3;
        int columns = 3;
        List<String> pieces = new ArrayList<>(Arrays.asList("K", "K", "R"));

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
        List<Integer> safeSquares = new ArrayList<>(rows * columns);
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                safeSquares.add(column + row * columns);
            }
        }

        // No occupied squares
        List<Integer> occupiedSquares = new ArrayList<>(pieces.size());

        Map<Integer, String> candidate = new HashMap<>(pieces.size());
        List<Map<Integer, String>> solutions = new ArrayList<>();

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

    private static void placePieces(List<String> pieces, List<Integer> safeSquares, List<Integer> occupiedSquares, Map<Integer, String> candidate, List<Map<Integer, String>> solutions) {
        String piece = pieces.get(0);
        List<String> remainingPieces = new ArrayList<>(pieces);
        remainingPieces.remove(0);
        // for every safe square remaining
        for (Integer candidateSquare : safeSquares) {
            List<Integer> threatenedSquares = threatenedSquares(piece, candidateSquare); //TODO calculate threatened squares
            if (Collections.disjoint(threatenedSquares, occupiedSquares)) {
                // update safe squares (threatens any other piece? -> skip)
                List<Integer> candidateOccupiedSquares = new ArrayList<>(occupiedSquares);
                candidateOccupiedSquares.add(candidateSquare);
                Map<Integer, String> newCandidate = new HashMap<>(candidate);
                newCandidate.put(candidateSquare, piece);
                List<Integer> candidateSafeSquares = new ArrayList<>(safeSquares);
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

    private static List<Integer> threatenedSquares(String piece, Integer position) {
        List<Integer> threatenedSquares = new ArrayList<>();
        switch (piece){
            case "K":
                break;
            case "Q":
                break;
            case "B":
                break;
            case "R":
                break;
            case "N":
                break;
            default:
        }
        return threatenedSquares;
    }
}
