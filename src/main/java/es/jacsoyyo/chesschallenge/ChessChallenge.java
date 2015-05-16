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

    private Board board;
    private List<Piece> pieces;

    public ChessChallenge(int rows, int columns, List<Piece> pieces) {
        this.board = new Board(rows, columns);
        this.pieces = pieces;
    }

    public int doChallenge() {

        Set<Map<Integer, Piece>> solutions = new HashSet<>();

        List<Integer> safeSquares = board.getSquares();
                
        // Try to place every piece on every square
        placePieces(pieces, board, safeSquares, solutions);

        System.out.println("Solutions: " + solutions.size());
        for (Map<Integer, Piece> solution : solutions) {
            for (Integer position : solution.keySet()) {
                System.out.print(position + " - " + solution.get(position) + " : ");
            }
            System.out.println();
        }
        return solutions.size();
    }

    private void placePieces(List<Piece> pieces, Board board, List<Integer> safeSquares, Set<Map<Integer, Piece>> solutions) {
        Piece piece = pieces.get(0);
        pieces.remove(0);
        // for every safe square remaining
        for (Integer candidateSquare : safeSquares) {
            List<Integer> candidateSafeSquares = new ArrayList<>(safeSquares);
            candidateSafeSquares.remove(candidateSquare);
            try {
                board.placePiece(piece, candidateSquare, candidateSafeSquares);
                if (!pieces.isEmpty()) {
                    // try to place remaining pieces
                    placePieces(pieces, board, candidateSafeSquares, solutions);
                } else {
                    // we got to a solution
                    Map<Integer, Piece> solution = new HashMap<>(board.getCandidate());
                    solutions.add(solution);
                }
                board.removePiece(candidateSquare);
            } catch (ThreatensOccupiedSquare e) {
                // skip
            }
        }
        pieces.add(piece);
    }

}
