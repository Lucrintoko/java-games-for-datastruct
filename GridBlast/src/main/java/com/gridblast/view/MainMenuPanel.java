package com.gridblast.view;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {

    private final JButton startButton;
    private final JButton exitButton;

    public MainMenuPanel() {
        setBackground(UIConstants.BACKGROUND_COLOR);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("GRID BLAST");
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 48));
        title.setBorder(BorderFactory.createEmptyBorder(60, 0, 40, 0));

        startButton = createButton("START");
        exitButton = createButton("EXIT");

        add(title);
        add(startButton);
        add(Box.createVerticalStrut(20));
        add(exitButton);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setAlignmentX(CENTER_ALIGNMENT);
        btn.setPreferredSize(new Dimension(200, 60));
        btn.setMaximumSize(new Dimension(200, 60));
        btn.setBackground(new Color(60, 60, 60));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 24));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120), 2));
        return btn;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}
