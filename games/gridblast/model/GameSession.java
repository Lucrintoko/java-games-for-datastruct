package games.gridblast.model;

import java.util.List;

public class GameSession {

    private final GameBoard board;
    private List<BlockPiece> availablePieces;
    private int score;

    public GameSession() {
        this.board = new GameBoard();
        this.availablePieces = UnifiedPieceFactory.generatePieceSet();
        this.score = 0;
    }

    public GameBoard getBoard() {
        return board;
    }

    public List<BlockPiece> getAvailablePieces() {
        return availablePieces;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        // If ANY piece can be placed anywhere, game is not over
        for (BlockPiece piece : availablePieces) {
            if (canPlaceAnywhere(piece)) {
                return false;
            }
        }
        return true;
    }

    private boolean canPlaceAnywhere(BlockPiece piece) {
        for (int r = 0; r < GameBoard.SIZE; r++) {
            for (int c = 0; c < GameBoard.SIZE; c++) {
                if (board.canPlace(piece, r, c)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean usePiece(BlockPiece piece, int row, int col) {
        if (!board.canPlace(piece, row, col)) {
            return false;
        }

        int gained = board.place(piece, row, col);
        score += gained;

        availablePieces.remove(piece);

        // If all pieces are used, generate a new set
        if (availablePieces.isEmpty()) {
            availablePieces = UnifiedPieceFactory.generatePieceSet();
        }

        return true;
    }
}
