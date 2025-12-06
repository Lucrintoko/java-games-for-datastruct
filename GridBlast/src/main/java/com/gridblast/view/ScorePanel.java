package com.gridblast.view;

import com.gridblast.model.GameSession;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class ScorePanel extends JPanel {

    private final GameSession session;

    public ScorePanel(GameSession session) {
        this.session = session;
        setBackground(UIConstants.BACKGROUND_COLOR);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        String scoreText = "Score: " + session.getScore();

        // Glow effect: draw multiple layers
        g.setFont(UIConstants.SCORE_FONT);

        g.setColor(new Color(255, 100, 255)); // glow
        g.drawString(scoreText, 22, 32);
        g.drawString(scoreText, 18, 28);

        g.setColor(Color.WHITE); // foreground
        g.drawString(scoreText, 20, 30);

        if (session.isGameOver()) {
            g.setColor(Color.RED);
            g.drawString("GAME OVER", 20, 60);
        }
    }
}
