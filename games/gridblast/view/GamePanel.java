package games.gridblast.view;

import games.gridblast.controller.InputState;
import games.gridblast.model.BlockPiece;
import games.gridblast.model.GameBoard;
import games.gridblast.model.GameSession;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class GamePanel extends JPanel {

    private final GameSession session;
    private final InputState inputState;

    private int hoverRow = -1;
    private int hoverCol = -1;

    public GamePanel(GameSession session, InputState inputState) {
        this.session = session;
        this.inputState = inputState;
        setBackground(UIConstants.BACKGROUND_COLOR);
    }

    public void setHoverCell(int row, int col) {
        this.hoverRow = row;
        this.hoverCol = col;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        GameBoard board = session.getBoard();
        boolean[][] cells = board.getCells();
        Color[][] colors = board.getColorGrid();

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int gridSize = Math.min(panelWidth, panelHeight) - 2 * UIConstants.BOARD_PADDING;
        if (gridSize <= 0) return;

        int cellSize = gridSize / GameBoard.SIZE;
        int gap = Math.max(2, cellSize / 10);

        int totalGridSize = GameBoard.SIZE * cellSize + (GameBoard.SIZE - 1) * gap;
        int startX = (panelWidth - totalGridSize) / 2;
        int startY = (panelHeight - totalGridSize) / 2;

        // Draw grid
        g.setColor(UIConstants.GRID_COLOR);
        for (int r = 0; r < GameBoard.SIZE; r++) {
            for (int c = 0; c < GameBoard.SIZE; c++) {
                int x = startX + c * (cellSize + gap);
                int y = startY + r * (cellSize + gap);
                g.fillRect(x, y, cellSize, cellSize);
            }
        }

        // Draw placed blocks
        for (int r = 0; r < GameBoard.SIZE; r++) {
            for (int c = 0; c < GameBoard.SIZE; c++) {
                if (!cells[r][c]) continue;
                g.setColor(colors[r][c]);
                int x = startX + c * (cellSize + gap);
                int y = startY + r * (cellSize + gap);
                g.fillRect(x, y, cellSize, cellSize);
            }
        }

        // Draw preview
        if (inputState.hasSelection() && hoverRow >= 0 && hoverCol >= 0) {
            BlockPiece piece = inputState.getSelectedPiece();
            boolean[][] shape = piece.getShape();

            boolean valid = true;
            for (int pr = 0; pr < piece.getHeight(); pr++) {
                for (int pc = 0; pc < piece.getWidth(); pc++) {
                    if (!shape[pr][pc]) continue;
                    int br = hoverRow + pr;
                    int bc = hoverCol + pc;
                    if (br < 0 || bc < 0 || br >= GameBoard.SIZE || bc >= GameBoard.SIZE || cells[br][bc]) {
                        valid = false;
                    }
                }
            }

            g.setColor(valid ? UIConstants.PREVIEW_COLOR : UIConstants.PREVIEW_INVALID_COLOR);

            for (int pr = 0; pr < piece.getHeight(); pr++) {
                for (int pc = 0; pc < piece.getWidth(); pc++) {
                    if (!shape[pr][pc]) continue;
                    int br = hoverRow + pr;
                    int bc = hoverCol + pc;
                    if (br < 0 || bc < 0 || br >= GameBoard.SIZE || bc >= GameBoard.SIZE) continue;
                    int x = startX + bc * (cellSize + gap);
                    int y = startY + br * (cellSize + gap);
                    g.fillRect(x, y, cellSize, cellSize);
                }
            }
        }
    }

    public int[] mouseToCell(int mouseX, int mouseY) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int gridSize = Math.min(panelWidth, panelHeight) - 2 * UIConstants.BOARD_PADDING;
        if (gridSize <= 0) return new int[]{-1, -1};

        int cellSize = gridSize / GameBoard.SIZE;
        int gap = Math.max(2, cellSize / 10);

        int totalGridSize = GameBoard.SIZE * cellSize + (GameBoard.SIZE - 1) * gap;
        int startX = (panelWidth - totalGridSize) / 2;
        int startY = (panelHeight - totalGridSize) / 2;

        int col = (mouseX - startX) / (cellSize + gap);
        int row = (mouseY - startY) / (cellSize + gap);

        if (row < 0 || col < 0 || row >= GameBoard.SIZE || col >= GameBoard.SIZE) {
            return new int[]{-1, -1};
        }
        return new int[]{row, col};
    }
}
