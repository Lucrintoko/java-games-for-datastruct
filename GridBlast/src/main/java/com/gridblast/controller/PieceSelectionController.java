package com.gridblast.controller;

import com.gridblast.model.BlockPiece;
import com.gridblast.model.GameSession;
import com.gridblast.view.PieceSelectionPanel;
import com.gridblast.view.GamePanel;

import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PieceSelectionController extends MouseAdapter {

    private final GameSession session;
    private final InputState inputState;
    private final PieceSelectionPanel piecePanel;
    private final GamePanel gamePanel;

    public PieceSelectionController(GameSession session,
                                    InputState inputState,
                                    PieceSelectionPanel piecePanel,
                                    GamePanel gamePanel) {
        this.session = session;
        this.inputState = inputState;
        this.piecePanel = piecePanel;
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!SwingUtilities.isLeftMouseButton(e)) return;

        List<BlockPiece> pieces = session.getAvailablePieces();
        if (pieces == null || pieces.isEmpty()) return;

        int panelWidth = piecePanel.getWidth();
        int panelHeight = piecePanel.getHeight();
        int pieceCount = pieces.size();

        int horizontalPadding = 20;
        int usableWidth = panelWidth - 2 * horizontalPadding;
        if (usableWidth <= 0) return;

        int maxPieceWidth = 1;
        int maxPieceHeight = 1;
        for (BlockPiece piece : pieces) {
            maxPieceWidth = Math.max(maxPieceWidth, piece.getWidth());
            maxPieceHeight = Math.max(maxPieceHeight, piece.getHeight());
        }

        int maxCellsAcross = Math.max(maxPieceWidth, maxPieceHeight);
        int spacing = panelHeight / (pieceCount + 1);
        int maxBlockAreaHeight = spacing - 20;

        int cellSizeByWidth = usableWidth / maxCellsAcross;
        int cellSizeByHeight = maxBlockAreaHeight / maxCellsAcross;
        int cellSize = Math.max(8, Math.min(cellSizeByWidth, cellSizeByHeight));
        int gap = Math.max(2, cellSize / 6);

        int xClick = e.getX();
        int yClick = e.getY();

        for (int i = 0; i < pieceCount; i++) {
            BlockPiece piece = pieces.get(i);
            int yCenter = spacing * (i + 1);

            int piecePixelWidth =
                    piece.getWidth() * cellSize + (piece.getWidth() - 1) * gap;
            int piecePixelHeight =
                    piece.getHeight() * cellSize + (piece.getHeight() - 1) * gap;

            int startX = horizontalPadding + (usableWidth - piecePixelWidth) / 2;
            int startY = yCenter - piecePixelHeight / 2;

            int endX = startX + piecePixelWidth;
            int endY = startY + piecePixelHeight;

            if (xClick >= startX && xClick <= endX
                    && yClick >= startY && yClick <= endY) {

                if (inputState.getSelectedPiece() == piece) {
                    inputState.clearSelection();
                } else {
                    inputState.setSelectedPiece(piece);
                }

                piecePanel.repaint();
                gamePanel.repaint();
                return;
            }
        }
    }
}
