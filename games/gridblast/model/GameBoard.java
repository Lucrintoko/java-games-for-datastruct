package games.gridblast.model;

import java.awt.Color;

public class GameBoard {

    public static final int SIZE = 10;

    private final boolean[][] cells = new boolean[SIZE][SIZE];
    private final Color[][] colorGrid = new Color[SIZE][SIZE];

    public boolean[][] getCells() {
        return cells;
    }

    public Color[][] getColorGrid() {
        return colorGrid;
    }

    public boolean canPlace(BlockPiece piece, int row, int col) {
        boolean[][] shape = piece.getShape();

        for (int r = 0; r < piece.getHeight(); r++) {
            for (int c = 0; c < piece.getWidth(); c++) {
                if (!shape[r][c]) continue;

                int br = row + r;
                int bc = col + c;

                if (br < 0 || bc < 0 || br >= SIZE || bc >= SIZE) return false;
                if (cells[br][bc]) return false;
            }
        }
        return true;
    }

    public int place(BlockPiece piece, int row, int col) {
        boolean[][] shape = piece.getShape();
        Color color = piece.getColor();
        int filled = 0;

        for (int r = 0; r < piece.getHeight(); r++) {
            for (int c = 0; c < piece.getWidth(); c++) {
                if (!shape[r][c]) continue;

                int br = row + r;
                int bc = col + c;

                cells[br][bc] = true;
                colorGrid[br][bc] = color;
                filled++;
            }
        }

        int cleared = clearLines();
        return filled + cleared * 10;
    }

    private int clearLines() {
        int cleared = 0;

        // Clear full rows
        for (int r = 0; r < SIZE; r++) {
            boolean full = true;
            for (int c = 0; c < SIZE; c++) {
                if (!cells[r][c]) {
                    full = false;
                    break;
                }
            }
            if (full) {
                cleared++;
                for (int c = 0; c < SIZE; c++) {
                    cells[r][c] = false;
                    colorGrid[r][c] = null;
                }
            }
        }

        // Clear full columns
        for (int c = 0; c < SIZE; c++) {
            boolean full = true;
            for (int r = 0; r < SIZE; r++) {
                if (!cells[r][c]) {
                    full = false;
                    break;
                }
            }
            if (full) {
                cleared++;
                for (int r = 0; r < SIZE; r++) {
                    cells[r][c] = false;
                    colorGrid[r][c] = null;
                }
            }
        }

        return cleared;
    }
}
