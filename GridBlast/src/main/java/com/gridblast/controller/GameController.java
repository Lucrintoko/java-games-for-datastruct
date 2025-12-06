package com.gridblast.controller;

import com.gridblast.model.BlockPiece;
import com.gridblast.model.GameBoard;
import com.gridblast.model.GameSession;
import com.gridblast.view.GamePanel;
import com.gridblast.view.PieceSelectionPanel;
import com.gridblast.view.ScorePanel;

import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameController extends MouseAdapter {

    private final GameSession session;
    private final InputState inputState;
    private final GamePanel gamePanel;
    private final PieceSelectionPanel piecePanel;
    private final ScorePanel scorePanel;

    public GameController(GameSession session,
                          InputState inputState,
                          GamePanel gamePanel,
                          PieceSelectionPanel piecePanel,
                          ScorePanel scorePanel) {
        this.session = session;
        this.inputState = inputState;
        this.gamePanel = gamePanel;
        this.piecePanel = piecePanel;
        this.scorePanel = scorePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!SwingUtilities.isLeftMouseButton(e)) return;
        if (!inputState.hasSelection()) return;

        BlockPiece piece = inputState.getSelectedPiece();

        int mouseX = e.getX();
        int mouseY = e.getY();

        int[] rc = gamePanel.mouseToCell(mouseX, mouseY);
        int row = rc[0];
        int col = rc[1];

        boolean placed = false;

        if (row >= 0 && col >= 0 && row < GameBoard.SIZE && col < GameBoard.SIZE) {
            placed = session.usePiece(piece, row, col);
        }

        if (placed) {
            inputState.clearSelection();
            gamePanel.setHoverCell(-1, -1);
        }

        gamePanel.repaint();
        piecePanel.repaint();
        scorePanel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!inputState.hasSelection()) {
            gamePanel.setHoverCell(-1, -1);
            return;
        }

        int[] rc = gamePanel.mouseToCell(e.getX(), e.getY());
        gamePanel.setHoverCell(rc[0], rc[1]);
    }
}
