package com.gridblast.view;

import com.gridblast.controller.InputState;
import com.gridblast.model.BlockPiece;
import com.gridblast.model.GameSession;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.List;

public class PieceSelectionPanel extends JPanel {

    private final GameSession session;
    private final InputState inputState;

    public PieceSelectionPanel(GameSession session, InputState inputState) {
        this.session = session;
        this.inputState = inputState;
        setBackground(UIConstants.BACKGROUND_COLOR);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        List<BlockPiece> pieces = session.getAvailablePieces();
        if (pieces == null || pieces.isEmpty()) return;

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int pieceCount = pieces.size();

        int horizontalPadding = 20;
        int usableWidth = panelWidth - 2 * horizontalPadding;
        if (usableWidth <= 0) return;

        // Determine the largest piece dimension (in cells)
        int maxPieceWidth = 1;
        int maxPieceHeight = 1;
        for (BlockPiece piece : pieces) {
            maxPieceWidth = Math.max(maxPieceWidth, piece.getWidth());
            maxPieceHeight = Math.max(maxPieceHeight, piece.getHeight());
        }

        int maxCellsAcross = Math.max(maxPieceWidth, maxPieceHeight);

        // Vertical spacing between pieces
        int spacing = panelHeight / (pieceCount + 1);

        // Max height allowed per piece block area
        int maxBlockAreaHeight = spacing - 20;

        // Compute cell size based on width and height constraints
        int cellSizeByWidth = usableWidth / maxCellsAcross;
        int cellSizeByHeight = maxBlockAreaHeight / maxCellsAcross;

        int cellSize = Math.max(8, Math.min(cellSizeByWidth, cellSizeByHeight));
        int gap = Math.max(2, cellSize / 6);

        // Draw each piece
        for (int i = 0; i < pieceCount; i++) {
            BlockPiece piece = pieces.get(i);
            int yCenter = spacing * (i + 1);

            int piecePixelWidth =
                    piece.getWidth() * cellSize + (piece.getWidth() - 1) * gap;
            int piecePixelHeight =
                    piece.getHeight() * cellSize + (piece.getHeight() - 1) * gap;

            int startX = horizontalPadding + (usableWidth - piecePixelWidth) / 2;
            int startY = yCenter - piecePixelHeight / 2;

            // Selection highlight
            if (inputState.getSelectedPiece() == piece) {
                g.setColor(new Color(80, 80, 80));
                g.fillRoundRect(startX - 6, startY - 6,
                        piecePixelWidth + 12, piecePixelHeight + 12, 10, 10);
            }

            // Draw piece blocks
            boolean[][] shape = piece.getShape();
            g.setColor(piece.getColor());

            for (int r = 0; r < piece.getHeight(); r++) {
                for (int c = 0; c < piece.getWidth(); c++) {
                    if (!shape[r][c]) continue;

                    int x = startX + c * (cellSize + gap);
                    int y = startY + r * (cellSize + gap);
                    g.fillRect(x, y, cellSize, cellSize);
                }
            }
        }
    }
}
